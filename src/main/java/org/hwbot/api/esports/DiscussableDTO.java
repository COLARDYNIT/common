package org.hwbot.api.esports;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.CommentDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class DiscussableDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String forumLabel;
    protected String forumTitle;
    protected int discussionForumId;
    protected List<CommentDTO> comments;

    public String getForumLabel() {
        return forumLabel;
    }

    public void setForumLabel(String forumLabel) {
        this.forumLabel = forumLabel;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public int getDiscussionForumId() {
        return discussionForumId;
    }

    public void setDiscussionForumId(int discussionForumId) {
        this.discussionForumId = discussionForumId;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

}
