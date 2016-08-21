package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;
import java.util.List;

/**
 * Used for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DiscussionDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private final String firstPostAuthor;
    private final String lastPostAuthor;
    private final String title;
    private final String tag;
    private final long threadId;
    private final Long dateCreated;
    private final Long dateLastComment;
    private final int replyCount;
    private final int deletedCount;
    private final int views;
    private final boolean sticky;
    private List<CommentDTO> comments;

    public DiscussionDTO(String firstPostAuthor, String lastPostAuthor, String title, String tag, long threadId, Long dateCreated, Long dateLastComment, int replyCount, int deletedCount, int views, boolean sticky) {
        this.firstPostAuthor = firstPostAuthor;
        this.lastPostAuthor = lastPostAuthor;
        this.title = title;
        this.tag = tag;
        this.threadId = threadId;
        this.dateCreated = dateCreated;
        this.dateLastComment = dateLastComment;
        this.replyCount = replyCount;
        this.deletedCount = deletedCount;
        this.views = views;
        this.sticky = sticky;
    }

    public String getFirstPostAuthor() {
        return firstPostAuthor;
    }

    public String getLastPostAuthor() {
        return lastPostAuthor;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public long getThreadId() {
        return threadId;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public Long getDateLastComment() {
        return dateLastComment;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public int getDeletedCount() {
        return deletedCount;
    }

    public int getViews() {
        return views;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public boolean isSticky() {
        return sticky;
    }
}
