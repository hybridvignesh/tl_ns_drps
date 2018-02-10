
package vnapps.com.tamilnewsapp.models.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("modDate")
    @Expose
    private String modDate;
    @SerializedName("feedburner:origLink")
    @Expose
    private String feedburnerOrigLink;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("guid")
    @Expose
    private Guid guid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content:encoded")
    @Expose
    private String contentEncoded;
    @SerializedName("pubDate")
    @Expose
    private String pubDate;

    private String imageUrl;

    private String newsDescription;


    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public String getFeedburnerOrigLink() {
        return feedburnerOrigLink;
    }

    public void setFeedburnerOrigLink(String feedburnerOrigLink) {
        this.feedburnerOrigLink = feedburnerOrigLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentEncoded() {
        return contentEncoded;
    }

    public void setContentEncoded(String contentEncoded) {
        this.contentEncoded = contentEncoded;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }
}
