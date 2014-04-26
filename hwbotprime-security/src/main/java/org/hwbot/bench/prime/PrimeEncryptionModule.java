package org.hwbot.bench.prime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

import org.hwbot.bench.model.Request;
import org.hwbot.bench.security.EncryptionModule;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

public class PrimeEncryptionModule extends Provider implements EncryptionModule {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final long serialVersionUID = 1466458569429262359L;

    public char[] getIv() {
        return new char[] { '0', '0', 'B', 'C', 'A', 'E', '3', '6', '2', '7', '4', '2', 'F', '3', '1', '6', '8', '2', '9', '4', '3', '7', '7', 'E', '8', '7',
                '1', '1', '0', '1', '3', 'F' };
    }

    public char[] getKey() {
        return new char[] { 'D', 'C', '4', 'D', 'E', '0', 'C', '6', '7', 'E', 'F', '1', '0', 'A', '1', '4', '3', '0', 'B', '0', '5', 'B', '2', '8', '9', '9',
                '6', 'E', 'A', 'B', '6', '4' };
    }

    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * Encrypt an array of bytes. Before encrypting, you have to set the cipher to use, key and iv (if applicable)
     * 
     * @param data
     * @return the encrypted data
     */
    public byte[] encrypt(byte[] data, Object arg) {
        Context ctx = (Context) arg;
        // // System.out.println("encrypting " + data.length + " bytes");
        try {
            selfIntegrityChecking(ctx);
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String callee = "";
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (stackTraceElement.getClassName().contains("org.hwbot")) {
                    callee += stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber() + "\n";
                }
            }
            callee = toSHA1(callee.getBytes());
            if (!callee.equals("eeee40bec7f20049e4426cd95f2432ee9197dbc0") && !callee.equals("03c7f4803bc502f38e90b09ff300f3858f959e1f")
                    && !callee.equals("705ea13e47dc37149883b0b8e4edf95325c824b9") && !callee.equals("7166afccd7a625c379203f63d03db8554abf8602") && !callee.equals("1661258401082527122ad34a5e81b58342c453e4")) {
                // System.out.println("encrypt: " + callee);
                // 04-17 00:01:14.559: I/System.out(32596): encrypt: 1661258401082527122ad34a5e81b58342c453e4 04-17 00:02:53.971: I/System.out(354): encrypt: 7166afccd7a625c379203f63d03db8554abf8602
                throw new SecurityException("You may not access this class directly. Bad hacker! shoo!");
            }

            String cipher = "AES/CBC/PKCS5Padding";
            byte[] key = decodeHex(getKey());
            byte[] iv = decodeHex(getIv());

            String[] config = cipher.split("/");
            Key encryptKey = new SecretKeySpec(key, config[0]);
            Cipher c = Cipher.getInstance(cipher);

            if ("CBC".equals(config[1])) {
                c.init(Cipher.ENCRYPT_MODE, encryptKey, new IvParameterSpec(iv));
            } else if ("ECB".equals(config[1])) {
                c.init(Cipher.ENCRYPT_MODE, encryptKey);
            }
            // // System.out.println("encryption ok, encrypting...");
            return c.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt: " + e, e);
        }
    }

    public void addChecksum(Request request) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callee = "";
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().contains("org.hwbot")) {
                callee += stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber() + "\n";
            }
        }
        callee = toSHA1(callee.getBytes());

        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getApplication().getName());
        buffer.append("-");
        buffer.append(request.getScore().getPoints());
        buffer.append("-");
        buffer.append(callee);
        try {
            // System.out.println("checksum: " + callee);
            request.setApplicationChecksum(toSHA1(buffer.toString().getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toSHA1(byte[] string) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        return encodeHexString(md.digest(string));
    }

    // certificate check

    // Flag for avoiding unnecessary self-integrity checking.
    private static boolean verifiedSelfIntegrity = false;

    // Provider's signing cert which is used to sign the jar.
    private static X509Certificate providerCert = null;

    // Raw bytes of provider's own code signing cert.
    // NOTE: YOU NEED TO CHANGE THIS TO YOUR OWN PROVIDER CERTIFICATE
    private static byte[] bytesOfProviderCert = { (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x42, (byte) 0x45, (byte) 0x47,
            (byte) 0x49, (byte) 0x4E, (byte) 0x20, (byte) 0x43, (byte) 0x45, (byte) 0x52, (byte) 0x54, (byte) 0x49, (byte) 0x46, (byte) 0x49, (byte) 0x43,
            (byte) 0x41, (byte) 0x54, (byte) 0x45, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0xA, (byte) 0x4D, (byte) 0x49,
            (byte) 0x49, (byte) 0x46, (byte) 0x4F, (byte) 0x7A, (byte) 0x43, (byte) 0x43, (byte) 0x42, (byte) 0x43, (byte) 0x4F, (byte) 0x67, (byte) 0x41,
            (byte) 0x77, (byte) 0x49, (byte) 0x42, (byte) 0x41, (byte) 0x67, (byte) 0x49, (byte) 0x48, (byte) 0x42, (byte) 0x35, (byte) 0x61, (byte) 0x34,
            (byte) 0x41, (byte) 0x47, (byte) 0x6D, (byte) 0x5A, (byte) 0x6C, (byte) 0x7A, (byte) 0x41, (byte) 0x4E, (byte) 0x42, (byte) 0x67, (byte) 0x6B,
            (byte) 0x71, (byte) 0x68, (byte) 0x6B, (byte) 0x69, (byte) 0x47, (byte) 0x39, (byte) 0x77, (byte) 0x30, (byte) 0x42, (byte) 0x41, (byte) 0x51,
            (byte) 0x55, (byte) 0x46, (byte) 0x41, (byte) 0x44, (byte) 0x43, (byte) 0x42, (byte) 0x79, (byte) 0x6A, (byte) 0x45, (byte) 0x4C, (byte) 0x4D,
            (byte) 0x41, (byte) 0x6B, (byte) 0x47, (byte) 0x41, (byte) 0x31, (byte) 0x55, (byte) 0x45, (byte) 0xA, (byte) 0x42, (byte) 0x68, (byte) 0x4D,
            (byte) 0x43, (byte) 0x56, (byte) 0x56, (byte) 0x4D, (byte) 0x78, (byte) 0x45, (byte) 0x44, (byte) 0x41, (byte) 0x4F, (byte) 0x42, (byte) 0x67,
            (byte) 0x4E, (byte) 0x56, (byte) 0x42, (byte) 0x41, (byte) 0x67, (byte) 0x54, (byte) 0x42, (byte) 0x30, (byte) 0x46, (byte) 0x79, (byte) 0x61,
            (byte) 0x58, (byte) 0x70, (byte) 0x76, (byte) 0x62, (byte) 0x6D, (byte) 0x45, (byte) 0x78, (byte) 0x45, (byte) 0x7A, (byte) 0x41, (byte) 0x52,
            (byte) 0x42, (byte) 0x67, (byte) 0x4E, (byte) 0x56, (byte) 0x42, (byte) 0x41, (byte) 0x63, (byte) 0x54, (byte) 0x43, (byte) 0x6C, (byte) 0x4E,
            (byte) 0x6A, (byte) 0x62, (byte) 0x33, (byte) 0x52, (byte) 0x30, (byte) 0x63, (byte) 0x32, (byte) 0x52, (byte) 0x68, (byte) 0x62, (byte) 0x47,
            (byte) 0x55, (byte) 0x78, (byte) 0x47, (byte) 0x6A, (byte) 0x41, (byte) 0x59, (byte) 0xA, (byte) 0x42, (byte) 0x67, (byte) 0x4E, (byte) 0x56,
            (byte) 0x42, (byte) 0x41, (byte) 0x6F, (byte) 0x54, (byte) 0x45, (byte) 0x55, (byte) 0x64, (byte) 0x76, (byte) 0x52, (byte) 0x47, (byte) 0x46,
            (byte) 0x6B, (byte) 0x5A, (byte) 0x48, (byte) 0x6B, (byte) 0x75, (byte) 0x59, (byte) 0x32, (byte) 0x39, (byte) 0x74, (byte) 0x4C, (byte) 0x43,
            (byte) 0x42, (byte) 0x4A, (byte) 0x62, (byte) 0x6D, (byte) 0x4D, (byte) 0x75, (byte) 0x4D, (byte) 0x54, (byte) 0x4D, (byte) 0x77, (byte) 0x4D,
            (byte) 0x51, (byte) 0x59, (byte) 0x44, (byte) 0x56, (byte) 0x51, (byte) 0x51, (byte) 0x4C, (byte) 0x45, (byte) 0x79, (byte) 0x70, (byte) 0x6F,
            (byte) 0x64, (byte) 0x48, (byte) 0x52, (byte) 0x77, (byte) 0x4F, (byte) 0x69, (byte) 0x38, (byte) 0x76, (byte) 0x59, (byte) 0x32, (byte) 0x56,
            (byte) 0x79, (byte) 0x64, (byte) 0x47, (byte) 0x6C, (byte) 0x6D, (byte) 0xA, (byte) 0x61, (byte) 0x57, (byte) 0x4E, (byte) 0x68, (byte) 0x64,
            (byte) 0x47, (byte) 0x56, (byte) 0x7A, (byte) 0x4C, (byte) 0x6D, (byte) 0x64, (byte) 0x76, (byte) 0x5A, (byte) 0x47, (byte) 0x46, (byte) 0x6B,
            (byte) 0x5A, (byte) 0x48, (byte) 0x6B, (byte) 0x75, (byte) 0x59, (byte) 0x32, (byte) 0x39, (byte) 0x74, (byte) 0x4C, (byte) 0x33, (byte) 0x4A,
            (byte) 0x6C, (byte) 0x63, (byte) 0x47, (byte) 0x39, (byte) 0x7A, (byte) 0x61, (byte) 0x58, (byte) 0x52, (byte) 0x76, (byte) 0x63, (byte) 0x6E,
            (byte) 0x6B, (byte) 0x78, (byte) 0x4D, (byte) 0x44, (byte) 0x41, (byte) 0x75, (byte) 0x42, (byte) 0x67, (byte) 0x4E, (byte) 0x56, (byte) 0x42,
            (byte) 0x41, (byte) 0x4D, (byte) 0x54, (byte) 0x4A, (byte) 0x30, (byte) 0x64, (byte) 0x76, (byte) 0x49, (byte) 0x45, (byte) 0x52, (byte) 0x68,
            (byte) 0x5A, (byte) 0x47, (byte) 0x52, (byte) 0x35, (byte) 0xA, (byte) 0x49, (byte) 0x46, (byte) 0x4E, (byte) 0x6C, (byte) 0x59, (byte) 0x33,
            (byte) 0x56, (byte) 0x79, (byte) 0x5A, (byte) 0x53, (byte) 0x42, (byte) 0x44, (byte) 0x5A, (byte) 0x58, (byte) 0x4A, (byte) 0x30, (byte) 0x61,
            (byte) 0x57, (byte) 0x5A, (byte) 0x70, (byte) 0x59, (byte) 0x32, (byte) 0x46, (byte) 0x30, (byte) 0x61, (byte) 0x57, (byte) 0x39, (byte) 0x75,
            (byte) 0x49, (byte) 0x45, (byte) 0x46, (byte) 0x31, (byte) 0x64, (byte) 0x47, (byte) 0x68, (byte) 0x76, (byte) 0x63, (byte) 0x6D, (byte) 0x6C,
            (byte) 0x30, (byte) 0x65, (byte) 0x54, (byte) 0x45, (byte) 0x52, (byte) 0x4D, (byte) 0x41, (byte) 0x38, (byte) 0x47, (byte) 0x41, (byte) 0x31,
            (byte) 0x55, (byte) 0x45, (byte) 0x42, (byte) 0x52, (byte) 0x4D, (byte) 0x49, (byte) 0x4D, (byte) 0x44, (byte) 0x63, (byte) 0x35, (byte) 0x4E,
            (byte) 0x6A, (byte) 0x6B, (byte) 0x79, (byte) 0xA, (byte) 0x4F, (byte) 0x44, (byte) 0x63, (byte) 0x77, (byte) 0x48, (byte) 0x68, (byte) 0x63,
            (byte) 0x4E, (byte) 0x4D, (byte) 0x54, (byte) 0x4D, (byte) 0x77, (byte) 0x4E, (byte) 0x6A, (byte) 0x45, (byte) 0x35, (byte) 0x4D, (byte) 0x6A,
            (byte) 0x49, (byte) 0x78, (byte) 0x4E, (byte) 0x54, (byte) 0x49, (byte) 0x77, (byte) 0x57, (byte) 0x68, (byte) 0x63, (byte) 0x4E, (byte) 0x4D,
            (byte) 0x54, (byte) 0x51, (byte) 0x77, (byte) 0x4E, (byte) 0x6A, (byte) 0x45, (byte) 0x35, (byte) 0x4D, (byte) 0x6A, (byte) 0x49, (byte) 0x78,
            (byte) 0x4E, (byte) 0x54, (byte) 0x49, (byte) 0x77, (byte) 0x57, (byte) 0x6A, (byte) 0x41, (byte) 0x33, (byte) 0x4D, (byte) 0x53, (byte) 0x45,
            (byte) 0x77, (byte) 0x48, (byte) 0x77, (byte) 0x59, (byte) 0x44, (byte) 0x56, (byte) 0x51, (byte) 0x51, (byte) 0x4C, (byte) 0x45, (byte) 0x78,
            (byte) 0x68, (byte) 0x45, (byte) 0xA, (byte) 0x62, (byte) 0x32, (byte) 0x31, (byte) 0x68, (byte) 0x61, (byte) 0x57, (byte) 0x34, (byte) 0x67,
            (byte) 0x51, (byte) 0x32, (byte) 0x39, (byte) 0x75, (byte) 0x64, (byte) 0x48, (byte) 0x4A, (byte) 0x76, (byte) 0x62, (byte) 0x43, (byte) 0x42,
            (byte) 0x57, (byte) 0x59, (byte) 0x57, (byte) 0x78, (byte) 0x70, (byte) 0x5A, (byte) 0x47, (byte) 0x46, (byte) 0x30, (byte) 0x5A, (byte) 0x57,
            (byte) 0x51, (byte) 0x78, (byte) 0x45, (byte) 0x6A, (byte) 0x41, (byte) 0x51, (byte) 0x42, (byte) 0x67, (byte) 0x4E, (byte) 0x56, (byte) 0x42,
            (byte) 0x41, (byte) 0x4D, (byte) 0x54, (byte) 0x43, (byte) 0x57, (byte) 0x68, (byte) 0x33, (byte) 0x59, (byte) 0x6D, (byte) 0x39, (byte) 0x30,
            (byte) 0x4C, (byte) 0x6D, (byte) 0x39, (byte) 0x79, (byte) 0x5A, (byte) 0x7A, (byte) 0x43, (byte) 0x43, (byte) 0x41, (byte) 0x53, (byte) 0x49,
            (byte) 0x77, (byte) 0xA, (byte) 0x44, (byte) 0x51, (byte) 0x59, (byte) 0x4A, (byte) 0x4B, (byte) 0x6F, (byte) 0x5A, (byte) 0x49, (byte) 0x68,
            (byte) 0x76, (byte) 0x63, (byte) 0x4E, (byte) 0x41, (byte) 0x51, (byte) 0x45, (byte) 0x42, (byte) 0x42, (byte) 0x51, (byte) 0x41, (byte) 0x44,
            (byte) 0x67, (byte) 0x67, (byte) 0x45, (byte) 0x50, (byte) 0x41, (byte) 0x44, (byte) 0x43, (byte) 0x43, (byte) 0x41, (byte) 0x51, (byte) 0x6F,
            (byte) 0x43, (byte) 0x67, (byte) 0x67, (byte) 0x45, (byte) 0x42, (byte) 0x41, (byte) 0x4A, (byte) 0x34, (byte) 0x75, (byte) 0x4E, (byte) 0x6B,
            (byte) 0x6E, (byte) 0x34, (byte) 0x68, (byte) 0x6B, (byte) 0x75, (byte) 0x4B, (byte) 0x4E, (byte) 0x44, (byte) 0x65, (byte) 0x48, (byte) 0x55,
            (byte) 0x39, (byte) 0x79, (byte) 0x67, (byte) 0x2F, (byte) 0x44, (byte) 0x6D, (byte) 0x4E, (byte) 0x57, (byte) 0x44, (byte) 0x2F, (byte) 0x68,
            (byte) 0xA, (byte) 0x50, (byte) 0x62, (byte) 0x32, (byte) 0x34, (byte) 0x68, (byte) 0x6C, (byte) 0x37, (byte) 0x4D, (byte) 0x79, (byte) 0x6B,
            (byte) 0x77, (byte) 0x2F, (byte) 0x38, (byte) 0x37, (byte) 0x30, (byte) 0x47, (byte) 0x59, (byte) 0x7A, (byte) 0x6A, (byte) 0x53, (byte) 0x5A,
            (byte) 0x76, (byte) 0x59, (byte) 0x5A, (byte) 0x69, (byte) 0x75, (byte) 0x5A, (byte) 0x77, (byte) 0x43, (byte) 0x73, (byte) 0x6F, (byte) 0x66,
            (byte) 0x67, (byte) 0x32, (byte) 0x36, (byte) 0x44, (byte) 0x6F, (byte) 0x72, (byte) 0x52, (byte) 0x58, (byte) 0x53, (byte) 0x70, (byte) 0x44,
            (byte) 0x73, (byte) 0x62, (byte) 0x63, (byte) 0x5A, (byte) 0x6B, (byte) 0x50, (byte) 0x32, (byte) 0x76, (byte) 0x6F, (byte) 0x53, (byte) 0x51,
            (byte) 0x44, (byte) 0x61, (byte) 0x68, (byte) 0x4E, (byte) 0x79, (byte) 0x4B, (byte) 0x31, (byte) 0x73, (byte) 0x63, (byte) 0x6C, (byte) 0xA,
            (byte) 0x47, (byte) 0x6B, (byte) 0x65, (byte) 0x67, (byte) 0x64, (byte) 0x4C, (byte) 0x71, (byte) 0x32, (byte) 0x41, (byte) 0x6F, (byte) 0x57,
            (byte) 0x30, (byte) 0x44, (byte) 0x63, (byte) 0x51, (byte) 0x37, (byte) 0x43, (byte) 0x31, (byte) 0x32, (byte) 0x71, (byte) 0x74, (byte) 0x6B,
            (byte) 0x44, (byte) 0x2B, (byte) 0x41, (byte) 0x2B, (byte) 0x44, (byte) 0x55, (byte) 0x39, (byte) 0x55, (byte) 0x45, (byte) 0x50, (byte) 0x76,
            (byte) 0x74, (byte) 0x6B, (byte) 0x77, (byte) 0x77, (byte) 0x4C, (byte) 0x79, (byte) 0x33, (byte) 0x76, (byte) 0x76, (byte) 0x63, (byte) 0x6F,
            (byte) 0x62, (byte) 0x2B, (byte) 0x66, (byte) 0x51, (byte) 0x5A, (byte) 0x7A, (byte) 0x61, (byte) 0x74, (byte) 0x39, (byte) 0x4D, (byte) 0x4A,
            (byte) 0x79, (byte) 0x2F, (byte) 0x70, (byte) 0x78, (byte) 0x49, (byte) 0x6D, (byte) 0x38, (byte) 0x41, (byte) 0x41, (byte) 0xA, (byte) 0x61,
            (byte) 0x52, (byte) 0x30, (byte) 0x57, (byte) 0x42, (byte) 0x64, (byte) 0x79, (byte) 0x36, (byte) 0x56, (byte) 0x6B, (byte) 0x2B, (byte) 0x62,
            (byte) 0x30, (byte) 0x73, (byte) 0x6C, (byte) 0x38, (byte) 0x64, (byte) 0x6A, (byte) 0x38, (byte) 0x68, (byte) 0x34, (byte) 0x47, (byte) 0x64,
            (byte) 0x6F, (byte) 0x4C, (byte) 0x48, (byte) 0x6F, (byte) 0x49, (byte) 0x56, (byte) 0x6F, (byte) 0x52, (byte) 0x50, (byte) 0x2F, (byte) 0x6F,
            (byte) 0x72, (byte) 0x2B, (byte) 0x48, (byte) 0x76, (byte) 0x4A, (byte) 0x61, (byte) 0x63, (byte) 0x71, (byte) 0x53, (byte) 0x5A, (byte) 0x36,
            (byte) 0x73, (byte) 0x41, (byte) 0x6A, (byte) 0x41, (byte) 0x65, (byte) 0x30, (byte) 0x39, (byte) 0x6D, (byte) 0x7A, (byte) 0x66, (byte) 0x52,
            (byte) 0x4A, (byte) 0x36, (byte) 0x51, (byte) 0x7A, (byte) 0x65, (byte) 0x65, (byte) 0x61, (byte) 0x51, (byte) 0xA, (byte) 0x43, (byte) 0x6E,
            (byte) 0x4A, (byte) 0x4F, (byte) 0x4D, (byte) 0x6B, (byte) 0x43, (byte) 0x62, (byte) 0x6C, (byte) 0x68, (byte) 0x74, (byte) 0x37, (byte) 0x54,
            (byte) 0x35, (byte) 0x41, (byte) 0x61, (byte) 0x6C, (byte) 0x71, (byte) 0x43, (byte) 0x59, (byte) 0x73, (byte) 0x45, (byte) 0x71, (byte) 0x2F,
            (byte) 0x48, (byte) 0x38, (byte) 0x52, (byte) 0x37, (byte) 0x32, (byte) 0x31, (byte) 0x2F, (byte) 0x41, (byte) 0x52, (byte) 0x31, (byte) 0x42,
            (byte) 0x2B, (byte) 0x66, (byte) 0x35, (byte) 0x42, (byte) 0x62, (byte) 0x64, (byte) 0x4C, (byte) 0x54, (byte) 0x2F, (byte) 0x77, (byte) 0x35,
            (byte) 0x30, (byte) 0x6E, (byte) 0x4A, (byte) 0x4F, (byte) 0x68, (byte) 0x2F, (byte) 0x45, (byte) 0x63, (byte) 0x44, (byte) 0x78, (byte) 0x7A,
            (byte) 0x56, (byte) 0x63, (byte) 0x71, (byte) 0x77, (byte) 0x69, (byte) 0x65, (byte) 0x42, (byte) 0xA, (byte) 0x55, (byte) 0x4B, (byte) 0x72,
            (byte) 0x68, (byte) 0x36, (byte) 0x66, (byte) 0x33, (byte) 0x2F, (byte) 0x71, (byte) 0x6C, (byte) 0x56, (byte) 0x4E, (byte) 0x46, (byte) 0x35,
            (byte) 0x45, (byte) 0x68, (byte) 0x35, (byte) 0x47, (byte) 0x54, (byte) 0x39, (byte) 0x75, (byte) 0x77, (byte) 0x44, (byte) 0x62, (byte) 0x6D,
            (byte) 0x4A, (byte) 0x6E, (byte) 0x55, (byte) 0x4C, (byte) 0x44, (byte) 0x70, (byte) 0x50, (byte) 0x30, (byte) 0x4C, (byte) 0x51, (byte) 0x2F,
            (byte) 0x74, (byte) 0x4E, (byte) 0x50, (byte) 0x65, (byte) 0x64, (byte) 0x79, (byte) 0x5A, (byte) 0x65, (byte) 0x67, (byte) 0x64, (byte) 0x36,
            (byte) 0x33, (byte) 0x58, (byte) 0x51, (byte) 0x48, (byte) 0x6E, (byte) 0x36, (byte) 0x33, (byte) 0x39, (byte) 0x6C, (byte) 0x45, (byte) 0x6E,
            (byte) 0x73, (byte) 0x43, (byte) 0x41, (byte) 0x77, (byte) 0x45, (byte) 0x41, (byte) 0xA, (byte) 0x41, (byte) 0x61, (byte) 0x4F, (byte) 0x43,
            (byte) 0x41, (byte) 0x62, (byte) 0x59, (byte) 0x77, (byte) 0x67, (byte) 0x67, (byte) 0x47, (byte) 0x79, (byte) 0x4D, (byte) 0x41, (byte) 0x38,
            (byte) 0x47, (byte) 0x41, (byte) 0x31, (byte) 0x55, (byte) 0x64, (byte) 0x45, (byte) 0x77, (byte) 0x45, (byte) 0x42, (byte) 0x2F, (byte) 0x77,
            (byte) 0x51, (byte) 0x46, (byte) 0x4D, (byte) 0x41, (byte) 0x4D, (byte) 0x42, (byte) 0x41, (byte) 0x51, (byte) 0x41, (byte) 0x77, (byte) 0x48,
            (byte) 0x51, (byte) 0x59, (byte) 0x44, (byte) 0x56, (byte) 0x52, (byte) 0x30, (byte) 0x6C, (byte) 0x42, (byte) 0x42, (byte) 0x59, (byte) 0x77,
            (byte) 0x46, (byte) 0x41, (byte) 0x59, (byte) 0x49, (byte) 0x4B, (byte) 0x77, (byte) 0x59, (byte) 0x42, (byte) 0x42, (byte) 0x51, (byte) 0x55,
            (byte) 0x48, (byte) 0x41, (byte) 0x77, (byte) 0x45, (byte) 0x47, (byte) 0xA, (byte) 0x43, (byte) 0x43, (byte) 0x73, (byte) 0x47, (byte) 0x41,
            (byte) 0x51, (byte) 0x55, (byte) 0x46, (byte) 0x42, (byte) 0x77, (byte) 0x4D, (byte) 0x43, (byte) 0x4D, (byte) 0x41, (byte) 0x34, (byte) 0x47,
            (byte) 0x41, (byte) 0x31, (byte) 0x55, (byte) 0x64, (byte) 0x44, (byte) 0x77, (byte) 0x45, (byte) 0x42, (byte) 0x2F, (byte) 0x77, (byte) 0x51,
            (byte) 0x45, (byte) 0x41, (byte) 0x77, (byte) 0x49, (byte) 0x46, (byte) 0x6F, (byte) 0x44, (byte) 0x41, (byte) 0x7A, (byte) 0x42, (byte) 0x67,
            (byte) 0x4E, (byte) 0x56, (byte) 0x48, (byte) 0x52, (byte) 0x38, (byte) 0x45, (byte) 0x4C, (byte) 0x44, (byte) 0x41, (byte) 0x71, (byte) 0x4D,
            (byte) 0x43, (byte) 0x69, (byte) 0x67, (byte) 0x4A, (byte) 0x71, (byte) 0x41, (byte) 0x6B, (byte) 0x68, (byte) 0x69, (byte) 0x4A, (byte) 0x6F,
            (byte) 0x64, (byte) 0x48, (byte) 0x52, (byte) 0x77, (byte) 0xA, (byte) 0x4F, (byte) 0x69, (byte) 0x38, (byte) 0x76, (byte) 0x59, (byte) 0x33,
            (byte) 0x4A, (byte) 0x73, (byte) 0x4C, (byte) 0x6D, (byte) 0x64, (byte) 0x76, (byte) 0x5A, (byte) 0x47, (byte) 0x46, (byte) 0x6B, (byte) 0x5A,
            (byte) 0x48, (byte) 0x6B, (byte) 0x75, (byte) 0x59, (byte) 0x32, (byte) 0x39, (byte) 0x74, (byte) 0x4C, (byte) 0x32, (byte) 0x64, (byte) 0x6B,
            (byte) 0x63, (byte) 0x7A, (byte) 0x45, (byte) 0x74, (byte) 0x4F, (byte) 0x54, (byte) 0x4D, (byte) 0x75, (byte) 0x59, (byte) 0x33, (byte) 0x4A,
            (byte) 0x73, (byte) 0x4D, (byte) 0x46, (byte) 0x4D, (byte) 0x47, (byte) 0x41, (byte) 0x31, (byte) 0x55, (byte) 0x64, (byte) 0x49, (byte) 0x41,
            (byte) 0x52, (byte) 0x4D, (byte) 0x4D, (byte) 0x45, (byte) 0x6F, (byte) 0x77, (byte) 0x53, (byte) 0x41, (byte) 0x59, (byte) 0x4C, (byte) 0x59,
            (byte) 0x49, (byte) 0x5A, (byte) 0x49, (byte) 0xA, (byte) 0x41, (byte) 0x59, (byte) 0x62, (byte) 0x39, (byte) 0x62, (byte) 0x51, (byte) 0x45,
            (byte) 0x48, (byte) 0x46, (byte) 0x77, (byte) 0x45, (byte) 0x77, (byte) 0x4F, (byte) 0x54, (byte) 0x41, (byte) 0x33, (byte) 0x42, (byte) 0x67,
            (byte) 0x67, (byte) 0x72, (byte) 0x42, (byte) 0x67, (byte) 0x45, (byte) 0x46, (byte) 0x42, (byte) 0x51, (byte) 0x63, (byte) 0x43, (byte) 0x41,
            (byte) 0x52, (byte) 0x59, (byte) 0x72, (byte) 0x61, (byte) 0x48, (byte) 0x52, (byte) 0x30, (byte) 0x63, (byte) 0x44, (byte) 0x6F, (byte) 0x76,
            (byte) 0x4C, (byte) 0x32, (byte) 0x4E, (byte) 0x6C, (byte) 0x63, (byte) 0x6E, (byte) 0x52, (byte) 0x70, (byte) 0x5A, (byte) 0x6D, (byte) 0x6C,
            (byte) 0x6A, (byte) 0x59, (byte) 0x58, (byte) 0x52, (byte) 0x6C, (byte) 0x63, (byte) 0x79, (byte) 0x35, (byte) 0x6E, (byte) 0x62, (byte) 0x32,
            (byte) 0x52, (byte) 0x68, (byte) 0xA, (byte) 0x5A, (byte) 0x47, (byte) 0x52, (byte) 0x35, (byte) 0x4C, (byte) 0x6D, (byte) 0x4E, (byte) 0x76,
            (byte) 0x62, (byte) 0x53, (byte) 0x39, (byte) 0x79, (byte) 0x5A, (byte) 0x58, (byte) 0x42, (byte) 0x76, (byte) 0x63, (byte) 0x32, (byte) 0x6C,
            (byte) 0x30, (byte) 0x62, (byte) 0x33, (byte) 0x4A, (byte) 0x35, (byte) 0x4C, (byte) 0x7A, (byte) 0x43, (byte) 0x42, (byte) 0x67, (byte) 0x41,
            (byte) 0x59, (byte) 0x49, (byte) 0x4B, (byte) 0x77, (byte) 0x59, (byte) 0x42, (byte) 0x42, (byte) 0x51, (byte) 0x55, (byte) 0x48, (byte) 0x41,
            (byte) 0x51, (byte) 0x45, (byte) 0x45, (byte) 0x64, (byte) 0x44, (byte) 0x42, (byte) 0x79, (byte) 0x4D, (byte) 0x43, (byte) 0x51, (byte) 0x47,
            (byte) 0x43, (byte) 0x43, (byte) 0x73, (byte) 0x47, (byte) 0x41, (byte) 0x51, (byte) 0x55, (byte) 0x46, (byte) 0x42, (byte) 0x7A, (byte) 0x41,
            (byte) 0x42, (byte) 0xA, (byte) 0x68, (byte) 0x68, (byte) 0x68, (byte) 0x6F, (byte) 0x64, (byte) 0x48, (byte) 0x52, (byte) 0x77, (byte) 0x4F,
            (byte) 0x69, (byte) 0x38, (byte) 0x76, (byte) 0x62, (byte) 0x32, (byte) 0x4E, (byte) 0x7A, (byte) 0x63, (byte) 0x43, (byte) 0x35, (byte) 0x6E,
            (byte) 0x62, (byte) 0x32, (byte) 0x52, (byte) 0x68, (byte) 0x5A, (byte) 0x47, (byte) 0x52, (byte) 0x35, (byte) 0x4C, (byte) 0x6D, (byte) 0x4E,
            (byte) 0x76, (byte) 0x62, (byte) 0x53, (byte) 0x38, (byte) 0x77, (byte) 0x53, (byte) 0x67, (byte) 0x59, (byte) 0x49, (byte) 0x4B, (byte) 0x77,
            (byte) 0x59, (byte) 0x42, (byte) 0x42, (byte) 0x51, (byte) 0x55, (byte) 0x48, (byte) 0x4D, (byte) 0x41, (byte) 0x4B, (byte) 0x47, (byte) 0x50,
            (byte) 0x6D, (byte) 0x68, (byte) 0x30, (byte) 0x64, (byte) 0x48, (byte) 0x41, (byte) 0x36, (byte) 0x4C, (byte) 0x79, (byte) 0x39, (byte) 0x6A,
            (byte) 0xA, (byte) 0x5A, (byte) 0x58, (byte) 0x4A, (byte) 0x30, (byte) 0x61, (byte) 0x57, (byte) 0x5A, (byte) 0x70, (byte) 0x59, (byte) 0x32,
            (byte) 0x46, (byte) 0x30, (byte) 0x5A, (byte) 0x58, (byte) 0x4D, (byte) 0x75, (byte) 0x5A, (byte) 0x32, (byte) 0x39, (byte) 0x6B, (byte) 0x59,
            (byte) 0x57, (byte) 0x52, (byte) 0x6B, (byte) 0x65, (byte) 0x53, (byte) 0x35, (byte) 0x6A, (byte) 0x62, (byte) 0x32, (byte) 0x30, (byte) 0x76,
            (byte) 0x63, (byte) 0x6D, (byte) 0x56, (byte) 0x77, (byte) 0x62, (byte) 0x33, (byte) 0x4E, (byte) 0x70, (byte) 0x64, (byte) 0x47, (byte) 0x39,
            (byte) 0x79, (byte) 0x65, (byte) 0x53, (byte) 0x39, (byte) 0x6E, (byte) 0x5A, (byte) 0x46, (byte) 0x39, (byte) 0x70, (byte) 0x62, (byte) 0x6E,
            (byte) 0x52, (byte) 0x6C, (byte) 0x63, (byte) 0x6D, (byte) 0x31, (byte) 0x6C, (byte) 0x5A, (byte) 0x47, (byte) 0x6C, (byte) 0x68, (byte) 0xA,
            (byte) 0x64, (byte) 0x47, (byte) 0x55, (byte) 0x75, (byte) 0x59, (byte) 0x33, (byte) 0x4A, (byte) 0x30, (byte) 0x4D, (byte) 0x42, (byte) 0x38,
            (byte) 0x47, (byte) 0x41, (byte) 0x31, (byte) 0x55, (byte) 0x64, (byte) 0x49, (byte) 0x77, (byte) 0x51, (byte) 0x59, (byte) 0x4D, (byte) 0x42,
            (byte) 0x61, (byte) 0x41, (byte) 0x46, (byte) 0x50, (byte) 0x32, (byte) 0x73, (byte) 0x59, (byte) 0x54, (byte) 0x4B, (byte) 0x54, (byte) 0x62,
            (byte) 0x45, (byte) 0x58, (byte) 0x57, (byte) 0x34, (byte) 0x75, (byte) 0x36, (byte) 0x46, (byte) 0x58, (byte) 0x35, (byte) 0x71, (byte) 0x36,
            (byte) 0x35, (byte) 0x33, (byte) 0x61, (byte) 0x5A, (byte) 0x61, (byte) 0x4D, (byte) 0x7A, (byte) 0x6E, (byte) 0x4D, (byte) 0x43, (byte) 0x4D,
            (byte) 0x47, (byte) 0x41, (byte) 0x31, (byte) 0x55, (byte) 0x64, (byte) 0x45, (byte) 0x51, (byte) 0x51, (byte) 0x63, (byte) 0xA, (byte) 0x4D,
            (byte) 0x42, (byte) 0x71, (byte) 0x43, (byte) 0x43, (byte) 0x57, (byte) 0x68, (byte) 0x33, (byte) 0x59, (byte) 0x6D, (byte) 0x39, (byte) 0x30,
            (byte) 0x4C, (byte) 0x6D, (byte) 0x39, (byte) 0x79, (byte) 0x5A, (byte) 0x34, (byte) 0x49, (byte) 0x4E, (byte) 0x64, (byte) 0x33, (byte) 0x64,
            (byte) 0x33, (byte) 0x4C, (byte) 0x6D, (byte) 0x68, (byte) 0x33, (byte) 0x59, (byte) 0x6D, (byte) 0x39, (byte) 0x30, (byte) 0x4C, (byte) 0x6D,
            (byte) 0x39, (byte) 0x79, (byte) 0x5A, (byte) 0x7A, (byte) 0x41, (byte) 0x64, (byte) 0x42, (byte) 0x67, (byte) 0x4E, (byte) 0x56, (byte) 0x48,
            (byte) 0x51, (byte) 0x34, (byte) 0x45, (byte) 0x46, (byte) 0x67, (byte) 0x51, (byte) 0x55, (byte) 0x68, (byte) 0x4B, (byte) 0x32, (byte) 0x58,
            (byte) 0x4F, (byte) 0x75, (byte) 0x68, (byte) 0x78, (byte) 0x45, (byte) 0x57, (byte) 0x2B, (byte) 0x2B, (byte) 0xA, (byte) 0x45, (byte) 0x71,
            (byte) 0x51, (byte) 0x4B, (byte) 0x63, (byte) 0x41, (byte) 0x6F, (byte) 0x78, (byte) 0x6C, (byte) 0x62, (byte) 0x32, (byte) 0x48, (byte) 0x6E,
            (byte) 0x56, (byte) 0x73, (byte) 0x77, (byte) 0x44, (byte) 0x51, (byte) 0x59, (byte) 0x4A, (byte) 0x4B, (byte) 0x6F, (byte) 0x5A, (byte) 0x49,
            (byte) 0x68, (byte) 0x76, (byte) 0x63, (byte) 0x4E, (byte) 0x41, (byte) 0x51, (byte) 0x45, (byte) 0x46, (byte) 0x42, (byte) 0x51, (byte) 0x41,
            (byte) 0x44, (byte) 0x67, (byte) 0x67, (byte) 0x45, (byte) 0x42, (byte) 0x41, (byte) 0x44, (byte) 0x58, (byte) 0x43, (byte) 0x6B, (byte) 0x37,
            (byte) 0x63, (byte) 0x75, (byte) 0x6F, (byte) 0x36, (byte) 0x54, (byte) 0x4C, (byte) 0x54, (byte) 0x68, (byte) 0x59, (byte) 0x79, (byte) 0x35,
            (byte) 0x34, (byte) 0x53, (byte) 0x35, (byte) 0x48, (byte) 0x76, (byte) 0x39, (byte) 0x44, (byte) 0xA, (byte) 0x34, (byte) 0x74, (byte) 0x78,
            (byte) 0x53, (byte) 0x54, (byte) 0x36, (byte) 0x79, (byte) 0x64, (byte) 0x36, (byte) 0x68, (byte) 0x6F, (byte) 0x6C, (byte) 0x69, (byte) 0x64,
            (byte) 0x55, (byte) 0x4B, (byte) 0x6A, (byte) 0x56, (byte) 0x36, (byte) 0x57, (byte) 0x7A, (byte) 0x50, (byte) 0x61, (byte) 0x53, (byte) 0x78,
            (byte) 0x6B, (byte) 0x6A, (byte) 0x48, (byte) 0x4B, (byte) 0x67, (byte) 0x4C, (byte) 0x42, (byte) 0x6E, (byte) 0x5A, (byte) 0x36, (byte) 0x50,
            (byte) 0x68, (byte) 0x64, (byte) 0x42, (byte) 0x79, (byte) 0x55, (byte) 0x41, (byte) 0x61, (byte) 0x59, (byte) 0x67, (byte) 0x2F, (byte) 0x7A,
            (byte) 0x5A, (byte) 0x4F, (byte) 0x43, (byte) 0x71, (byte) 0x65, (byte) 0x47, (byte) 0x51, (byte) 0x6A, (byte) 0x67, (byte) 0x45, (byte) 0x72,
            (byte) 0x53, (byte) 0x59, (byte) 0x37, (byte) 0x30, (byte) 0x52, (byte) 0x68, (byte) 0xA, (byte) 0x72, (byte) 0x6D, (byte) 0x35, (byte) 0x42,
            (byte) 0x50, (byte) 0x77, (byte) 0x2F, (byte) 0x34, (byte) 0x57, (byte) 0x4B, (byte) 0x71, (byte) 0x6E, (byte) 0x69, (byte) 0x63, (byte) 0x37,
            (byte) 0x52, (byte) 0x4A, (byte) 0x70, (byte) 0x75, (byte) 0x2B, (byte) 0x7A, (byte) 0x44, (byte) 0x44, (byte) 0x5A, (byte) 0x78, (byte) 0x46,
            (byte) 0x55, (byte) 0x6D, (byte) 0x45, (byte) 0x69, (byte) 0x54, (byte) 0x4B, (byte) 0x34, (byte) 0x55, (byte) 0x6A, (byte) 0x5A, (byte) 0x66,
            (byte) 0x4B, (byte) 0x2F, (byte) 0x73, (byte) 0x73, (byte) 0x30, (byte) 0x54, (byte) 0x6E, (byte) 0x2B, (byte) 0x61, (byte) 0x79, (byte) 0x32,
            (byte) 0x36, (byte) 0x44, (byte) 0x4A, (byte) 0x47, (byte) 0x61, (byte) 0x32, (byte) 0x7A, (byte) 0x69, (byte) 0x65, (byte) 0x5A, (byte) 0x5A,
            (byte) 0x72, (byte) 0x41, (byte) 0x38, (byte) 0x66, (byte) 0x51, (byte) 0xA, (byte) 0x65, (byte) 0x6A, (byte) 0x4B, (byte) 0x74, (byte) 0x34,
            (byte) 0x5A, (byte) 0x6B, (byte) 0x77, (byte) 0x5A, (byte) 0x4C, (byte) 0x72, (byte) 0x55, (byte) 0x78, (byte) 0x57, (byte) 0x6D, (byte) 0x76,
            (byte) 0x69, (byte) 0x4F, (byte) 0x49, (byte) 0x79, (byte) 0x4A, (byte) 0x30, (byte) 0x4A, (byte) 0x5A, (byte) 0x70, (byte) 0x36, (byte) 0x64,
            (byte) 0x34, (byte) 0x65, (byte) 0x2B, (byte) 0x31, (byte) 0x54, (byte) 0x4E, (byte) 0x52, (byte) 0x33, (byte) 0x4E, (byte) 0x50, (byte) 0x33,
            (byte) 0x44, (byte) 0x75, (byte) 0x68, (byte) 0x32, (byte) 0x4C, (byte) 0x63, (byte) 0x69, (byte) 0x77, (byte) 0x69, (byte) 0x47, (byte) 0x2F,
            (byte) 0x2B, (byte) 0x73, (byte) 0x32, (byte) 0x5A, (byte) 0x2F, (byte) 0x69, (byte) 0x4E, (byte) 0x31, (byte) 0x59, (byte) 0x47, (byte) 0x42,
            (byte) 0x33, (byte) 0x74, (byte) 0x43, (byte) 0x47, (byte) 0xA, (byte) 0x2B, (byte) 0x42, (byte) 0x68, (byte) 0x73, (byte) 0x6C, (byte) 0x66,
            (byte) 0x37, (byte) 0x38, (byte) 0x42, (byte) 0x66, (byte) 0x66, (byte) 0x2F, (byte) 0x4B, (byte) 0x67, (byte) 0x79, (byte) 0x58, (byte) 0x4C,
            (byte) 0x43, (byte) 0x74, (byte) 0x4C, (byte) 0x32, (byte) 0x58, (byte) 0x68, (byte) 0x4B, (byte) 0x69, (byte) 0x66, (byte) 0x77, (byte) 0x7A,
            (byte) 0x79, (byte) 0x2B, (byte) 0x67, (byte) 0x72, (byte) 0x69, (byte) 0x6C, (byte) 0x52, (byte) 0x32, (byte) 0x37, (byte) 0x45, (byte) 0x7A,
            (byte) 0x35, (byte) 0x51, (byte) 0x5A, (byte) 0x78, (byte) 0x7A, (byte) 0x7A, (byte) 0x68, (byte) 0x47, (byte) 0x32, (byte) 0x73, (byte) 0x67,
            (byte) 0x76, (byte) 0x57, (byte) 0x72, (byte) 0x75, (byte) 0x4B, (byte) 0x64, (byte) 0x75, (byte) 0x6B, (byte) 0x6C, (byte) 0x71, (byte) 0x4B,
            (byte) 0x4F, (byte) 0x72, (byte) 0x55, (byte) 0xA, (byte) 0x39, (byte) 0x31, (byte) 0x79, (byte) 0x2F, (byte) 0x49, (byte) 0x6D, (byte) 0x6B,
            (byte) 0x36, (byte) 0x4A, (byte) 0x63, (byte) 0x57, (byte) 0x41, (byte) 0x31, (byte) 0x4F, (byte) 0x70, (byte) 0x64, (byte) 0x50, (byte) 0x72,
            (byte) 0x6D, (byte) 0x51, (byte) 0x79, (byte) 0x74, (byte) 0x69, (byte) 0x65, (byte) 0x44, (byte) 0x49, (byte) 0x73, (byte) 0x64, (byte) 0x5A,
            (byte) 0x6E, (byte) 0x77, (byte) 0x70, (byte) 0x79, (byte) 0x58, (byte) 0x6D, (byte) 0x36, (byte) 0x56, (byte) 0x44, (byte) 0x57, (byte) 0x65,
            (byte) 0x4D, (byte) 0x4D, (byte) 0x44, (byte) 0x50, (byte) 0x2B, (byte) 0x52, (byte) 0x42, (byte) 0x4A, (byte) 0x58, (byte) 0x48, (byte) 0x57,
            (byte) 0x76, (byte) 0x7A, (byte) 0x5A, (byte) 0x31, (byte) 0x71, (byte) 0x4C, (byte) 0x73, (byte) 0x7A, (byte) 0x46, (byte) 0x77, (byte) 0x55,
            (byte) 0x4D, (byte) 0x3D, (byte) 0xA, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x45, (byte) 0x4E, (byte) 0x44,
            (byte) 0x20, (byte) 0x43, (byte) 0x45, (byte) 0x52, (byte) 0x54, (byte) 0x49, (byte) 0x46, (byte) 0x49, (byte) 0x43, (byte) 0x41, (byte) 0x54,
            (byte) 0x45, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0x2D, (byte) 0xA };

    public PrimeEncryptionModule() {
        super("PrimeEncryptionModule", 1.0, "hwbot provider");
    }

    /**
     * Perform self-integrity checking. Call this method in all the constructors of your SPI implementation classes. NOTE: The following implementation assumes
     * that all your provider implementation is packaged inside ONE jar.
     */
    private static final X500Principal PROD_DN = new X500Principal("CN=hwbot.org, OU=HWBOT, O=COLARDYN IT GCV, L=Booischot, ST=Antwerpen, C=BE");

    private static final synchronized boolean selfIntegrityChecking(Context ctx) {
        boolean production = false;
        try {
            PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature signatures[] = pinfo.signatures;

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            for (int i = 0; i < signatures.length; i++) {
                ByteArrayInputStream stream = new ByteArrayInputStream(signatures[i].toByteArray());
                X509Certificate cert = (X509Certificate) cf.generateCertificate(stream);
                X500Principal subjectX500Principal = cert.getSubjectX500Principal();
                production = subjectX500Principal.equals(PROD_DN);
                if (production) {
                    break;
                } else {
                    // System.out.println(subjectX500Principal);
                }
            }
        } catch (NameNotFoundException e) {
            // debuggable variable will remain false
        } catch (CertificateException e) {
            // debuggable variable will remain false
        }

        if (!production) {
            throw new SecurityException("Can not submit with development version!");
        }
        return production;
    }

    /*
     * Set up 'providerCert' with the certificate bytes.
     */
    private static X509Certificate setupProviderCert() throws IOException, CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream inStream = new ByteArrayInputStream(bytesOfProviderCert);
        X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
        inStream.close();
        return cert;
    }

    static class JarVerifier {

        private URL jarURL = null;
        private JarFile jarFile = null;

        JarVerifier(URL jarURL) {
            this.jarURL = jarURL;
        }

        /**
         * Retrive the jar file from the specified url.
         */
        private JarFile retrieveJarFileFromURL(URL url) throws PrivilegedActionException, MalformedURLException {
            JarFile jf = null;

            // Prep the url with the appropriate protocol.
            jarURL = url.getProtocol().equalsIgnoreCase("jar") ? url : new URL("jar:" + url.toString() + "!/");
            // Retrieve the jar file using JarURLConnection
            jf = AccessController.doPrivileged(new PrivilegedExceptionAction<JarFile>() {
                public JarFile run() throws Exception {
                    JarURLConnection conn = (JarURLConnection) jarURL.openConnection();
                    // Always get a fresh copy, so we don't have to
                    // worry about the stale file handle when the
                    // cached jar is closed by some other application.
                    conn.setUseCaches(false);
                    return conn.getJarFile();
                }
            });
            return jf;
        }

        /**
         * First, retrieve the jar file from the URL passed in constructor. Then, compare it to the expected X509Certificate. If everything went well and the
         * certificates are the same, no exception is thrown.
         */
        public void verify(X509Certificate targetCert) throws IOException {
            // Sanity checking
            if (targetCert == null) {
                throw new SecurityException("Provider certificate is invalid");
            }

            try {
                if (jarFile == null) {
                    jarFile = retrieveJarFileFromURL(jarURL);
                }
            } catch (Exception ex) {
                SecurityException se = new SecurityException();
                se.initCause(ex);
                throw se;
            }

            Vector<JarEntry> entriesVec = new Vector<JarEntry>();

            // Ensure the jar file is signed.
            Manifest man = jarFile.getManifest();
            if (man == null) {
                throw new SecurityException("The provider is not signed");
            }

            // Ensure all the entries' signatures verify correctly
            byte[] buffer = new byte[8192];
            Enumeration entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry je = (JarEntry) entries.nextElement();

                // Skip directories.
                if (je.isDirectory())
                    continue;
                entriesVec.addElement(je);
                InputStream is = jarFile.getInputStream(je);

                // Read in each jar entry. A security exception will
                // be thrown if a signature/digest check fails.
                int n;
                while ((n = is.read(buffer, 0, buffer.length)) != -1) {
                    // Don't care
                }
                is.close();
            }

            // Get the list of signer certificates
            Enumeration e = entriesVec.elements();

            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();

                // Every file must be signed except files in META-INF.
                Certificate[] certs = je.getCertificates();
                if ((certs == null) || (certs.length == 0)) {
                    if (!je.getName().startsWith("META-INF"))
                        throw new SecurityException("The provider " + "has unsigned " + "class files.");
                } else {
                    // Check whether the file is signed by the expected
                    // signer. The jar may be signed by multiple signers.
                    // See if one of the signers is 'targetCert'.
                    int startIndex = 0;
                    X509Certificate[] certChain;
                    boolean signedAsExpected = false;

                    while ((certChain = getAChain(certs, startIndex)) != null) {
                        // || certChain[0].getSerialNumber().longValue() == 2136041862044055l
                        if (certChain[0].equals(targetCert)) {
                            // Stop since one trusted signer is found.
                            // // System.out.println("Signature verified with: " + certChain[0].getSubjectDN());
                            signedAsExpected = true;
                            break;
                        }
                        // Proceed to the next chain.
                        startIndex += certChain.length;
                    }

                    if (!signedAsExpected) {
                        throw new SecurityException("The provider " + "is not signed by a " + "trusted signer");
                    }
                }
            }
        }

        /**
         * Extracts ONE certificate chain from the specified certificate array which may contain multiple certificate chains, starting from index 'startIndex'.
         */
        private static X509Certificate[] getAChain(Certificate[] certs, int startIndex) {
            if (startIndex > certs.length - 1)
                return null;

            int i;
            // Keep going until the next certificate is not the
            // issuer of this certificate.
            for (i = startIndex; i < certs.length - 1; i++) {
                X509Certificate x509Certificate = (X509Certificate) certs[i + 1];
                if (!x509Certificate.getSubjectDN().equals(((X509Certificate) certs[i]).getIssuerDN())) {
                    break;
                }
            }
            // Construct and return the found certificate chain.
            int certChainSize = (i - startIndex) + 1;
            X509Certificate[] ret = new X509Certificate[certChainSize];
            for (int j = 0; j < certChainSize; j++) {
                ret[j] = (X509Certificate) certs[startIndex + j];
            }
            return ret;
        }

        // Close the jar file once this object is no longer needed.
        protected void finalize() throws Throwable {
            jarFile.close();
        }
    }

    public static char[] encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }

    public static String encodeHexString(final byte[] data) {
        return new String(encodeHex(data));
    }

    public byte[] decode(final byte[] array) {
        return decodeHex(new String(array, DEFAULT_CHARSET).toCharArray());
    }

    public static byte[] decodeHex(final char[] data) {

        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        final byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    protected static int toDigit(final char ch, final int index) {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new IllegalArgumentException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

}
