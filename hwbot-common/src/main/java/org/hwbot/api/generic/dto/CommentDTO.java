package org.hwbot.api.generic.dto;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

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
    private int score;
    private String avatar;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    @Override
    public String toString() {
        return "CommentDTO [" + (author != null ? "author=" + author + ", " : "") + (content != null ? "content=" + content + ", " : "")
                + (postId != null ? "postId=" + postId + ", " : "") + "time=" + time + ", " + (timeAgo != null ? "timeAgo=" + timeAgo : "") + "]";
    }

}
