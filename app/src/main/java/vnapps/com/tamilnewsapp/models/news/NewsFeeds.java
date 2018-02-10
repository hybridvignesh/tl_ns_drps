
package vnapps.com.tamilnewsapp.models.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsFeeds {

    @SerializedName("rss")
    @Expose
    private Rss rss;

    public Rss getRss() {
        return rss;
    }

    public void setRss(Rss rss) {
        this.rss = rss;
    }

}
