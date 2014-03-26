package org.hwbot.api.bench.dto;

import java.io.Serializable;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.bench.dto.DeviceRecordDTO.RecordType;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DeviceInfoWithRecordsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private DeviceInfoDTO device;
    private Map<RecordType, DeviceRecordDTO> hwbotPrimeRecords;
    private Map<RecordType, DeviceRecordDTO> hwbotPrimeRecordsPersonal;

    public DeviceInfoDTO getDevice() {
        return device;
    }

    public void setDevice(DeviceInfoDTO device) {
        this.device = device;
    }

    public Map<RecordType, DeviceRecordDTO> getHwbotPrimeRecords() {
        return hwbotPrimeRecords;
    }

    public void setHwbotPrimeRecords(Map<RecordType, DeviceRecordDTO> hwbotPrimeRecords) {
        this.hwbotPrimeRecords = hwbotPrimeRecords;
    }

    public Map<RecordType, DeviceRecordDTO> getHwbotPrimeRecordsPersonal() {
        return hwbotPrimeRecordsPersonal;
    }

    public void setHwbotPrimeRecordsPersonal(Map<RecordType, DeviceRecordDTO> hwbotPrimeRecordsPersonal) {
        this.hwbotPrimeRecordsPersonal = hwbotPrimeRecordsPersonal;
    }

    @Override
    public String toString() {
        return "DeviceInfoWithRecordsDTO [" + (device != null ? "device=" + device + ", " : "")
                + (hwbotPrimeRecords != null ? "hwbotPrimeRecords=" + hwbotPrimeRecords : "") + "]";
    }

}
