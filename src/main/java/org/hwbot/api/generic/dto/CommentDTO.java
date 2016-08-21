package org.hwbot.api.generic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

/**
 * Used for android app and esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String author;
    private final String content;
    private final Long postId;
    private final long time;
    private final String timeAgo;
    private int upvotes;
    private int downvotes;
    private String avatar;
    private String authorSafeName;

    public CommentDTO(String author, String content, Long postId, long time, String timeAgo) {
        this.author = author;
        this.content = content;
        this.postId = postId;
        this.time = time;
        this.timeAgo = timeAgo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }

    public long getTime() {
        return time;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public String getAuthorSafeName() {
        return authorSafeName;
    }

    public void setAuthorSafeName(String authorSafeName) {
        this.authorSafeName = authorSafeName;
    }

    @Override
    public String toString() {
        return "CommentDTO [" + (author != null ? "author=" + author + ", " : "") + (content != null ? "content=" + content + ", " : "")
                + (postId != null ? "postId=" + postId + ", " : "") + "time=" + time + ", " + (timeAgo != null ? "timeAgo=" + timeAgo : "") + "]";
    }

}
