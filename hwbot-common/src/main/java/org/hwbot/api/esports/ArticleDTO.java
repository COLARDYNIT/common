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
    // optional, for index page banner
    private String subtitle;
    private Integer id;
    private String url;
    private long time;

    // optional
    private String author;
    private String content;
    // for news overview or index page banner
    private String excerpt;
    // 72 x 60, for small list
    private String thumbPicture;
    // 760 x 570, for news overview
    private String mediumPicture;
    // 1000 x 450 for news detail
    private String largePicture;
    // 2200 x 800, for index page banner, optional
    private String bannerPicture;

    private int amountOfComments;
    private List<String> tags;
    private List<CommentDTO> comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getThumbPicture() {
        return thumbPicture;
    }

    public void setThumbPicture(String thumbPicture) {
        this.thumbPicture = thumbPicture;
    }

    public String getMediumPicture() {
        return mediumPicture;
    }

    public void setMediumPicture(String mediumPicture) {
        this.mediumPicture = mediumPicture;
    }

    public String getLargePicture() {
        return largePicture;
    }

    public void setLargePicture(String largePicture) {
        this.largePicture = largePicture;
    }

    public String getBannerPicture() {
        return bannerPicture;
    }

    public void setBannerPicture(String bannerPicture) {
        this.bannerPicture = bannerPicture;
    }

    public int getAmountOfComments() {
        return amountOfComments;
    }

    public void setAmountOfComments(int amountOfComments) {
        this.amountOfComments = amountOfComments;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getDateTime() {
        return new SimpleDateFormat("'<time datetime=\"'yyyy-MM-dd'\">'d'<sup>" + StringUtil.getDayOfMonthSuffix(time) + "</sup> 'MMM', 'yyyy'</time>'")
                .format(new Date(time));
    }

    public String getSafeName() {
        return StringUtil.makeUrlSafe(this.getTitle());
    }
}