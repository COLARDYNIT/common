package org.hwbot.api.generic.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Used for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DiscussionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String author;
    private final String title;
    private final String tag;
    private final long threadId;
    private final long dateCreated;
    private final long dateLastComment;
    private int size;
    private final List<CommentDTO> comments = new ArrayList<CommentDTO>();

    public DiscussionDTO(String author, String title, String tag, long threadId, long dateCreated, long dateLastComment) {
        this.author = author;
        this.title = title;
        this.tag = tag;
        this.threadId = threadId;
        this.dateCreated = dateCreated;
        this.dateLastComment = dateLastComment;
    }

    public String getAuthor() {
        return author;
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

    public long getDateCreated() {
        return dateCreated;
    }

    public long getDateLastComment() {
        return dateLastComment;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DiscussionDTO{");
        sb.append("author='").append(author).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", tag='").append(tag).append('\'');
        sb.append(", threadId=").append(threadId);
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateLastComment=").append(dateLastComment);
        sb.append(", size=").append(size);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }
}
