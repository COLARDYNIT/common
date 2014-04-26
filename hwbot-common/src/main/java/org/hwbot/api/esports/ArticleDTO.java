package org.hwbot.api.esports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.CommentDTO;
import org.hwbot.util.StringUtil;

/**
 * Article can be used in an overview, list or in detail. Used for android app and esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ArticleDTO {

    private String title;
    private Integer id;
    private String url;
    private long time;

    // optional
    private String author;
    private String content;
    private String excerpt;
    private String tinyPicture;
    private String smallPicture;
    private String largePicture;
    private int amountOfComments;
    private List<String> tags;
    private List<CommentDTO> comments;

    /**
     * Constructor for overview
     */
    public ArticleDTO(String title, Integer id, String url, long time, String excerpt, String smallPicture, int amountOfComments, List<String> tags) {
        super();
        this.title = title;
        this.id = id;
        this.url = url;
        this.time = time;
        this.excerpt = excerpt;
        this.smallPicture = smallPicture;
        this.amountOfComments = amountOfComments;
        this.tags = tags;
    }

    /**
     * Constructor for detail view
     */
    public ArticleDTO(String title, Integer id, String url, long time, String author, String content, String largePicture, int amountOfComments,
            List<String> tags, List<CommentDTO> comments) {
        super();
        this.title = title;
        this.id = id;
        this.url = url;
        this.time = time;
        this.author = author;
        this.content = content;
        this.largePicture = largePicture;
        this.amountOfComments = amountOfComments;
        this.tags = tags;
        this.comments = comments;
    }

    /**
     * Constructor for list
     */
    public ArticleDTO(String title, Integer id, String url, long time, String tinyPicture, int amountOfComments) {
        super();
        this.title = title;
        this.id = id;
        this.url = url;
        this.time = time;
        this.tinyPicture = tinyPicture;
        this.amountOfComments = amountOfComments;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getTinyPicture() {
        return tinyPicture;
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public String getLargePicture() {
        return largePicture;
    }

    public int getAmountOfComments() {
        return amountOfComments;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public String getDateTime() {
        return new SimpleDateFormat("'<time datetime=\"'yyyy-MM-dd'\">'d'<sup>" + StringUtil.getDayOfMonthSuffix(time) + "</sup> 'MMM', 'yyyy'</time>'")
                .format(new Date(time));
    }

    public String getSafeName() {
        return StringUtil.makeUrlSafe(this.getTitle());
    }
}