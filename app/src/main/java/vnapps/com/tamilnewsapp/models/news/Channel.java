
package vnapps.com.tamilnewsapp.models.news;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("item")
    @Expose
    private List<Item> item = null;
    @SerializedName("feedburner:info")
    @Expose
    private FeedburnerInfo feedburnerInfo;
    @SerializedName("lastBuildDate")
    @Expose
    private String lastBuildDate;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("feedburner:emailServiceId")
    @Expose
    private String feedburnerEmailServiceId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("atom10:link")
    @Expose
    private List<Atom10Link> atom10Link = null;
    @SerializedName("feedburner:feedburnerHostname")
    @Expose
    private String feedburnerFeedburnerHostname;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public FeedburnerInfo getFeedburnerInfo() {
        return feedburnerInfo;
    }

    public void setFeedburnerInfo(FeedburnerInfo feedburnerInfo) {
        this.feedburnerInfo = feedburnerInfo;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeedburnerEmailServiceId() {
        return feedburnerEmailServiceId;
    }

    public void setFeedburnerEmailServiceId(String feedburnerEmailServiceId) {
        this.feedburnerEmailServiceId = feedburnerEmailServiceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Atom10Link> getAtom10Link() {
        return atom10Link;
    }

    public void setAtom10Link(List<Atom10Link> atom10Link) {
        this.atom10Link = atom10Link;
    }

    public String getFeedburnerFeedburnerHostname() {
        return feedburnerFeedburnerHostname;
    }

    public void setFeedburnerFeedburnerHostname(String feedburnerFeedburnerHostname) {
        this.feedburnerFeedburnerHostname = feedburnerFeedburnerHostname;
    }

}
