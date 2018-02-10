
package vnapps.com.tamilnewsapp.models.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guid {

    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("isPermaLink")
    @Expose
    private String isPermaLink;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsPermaLink() {
        return isPermaLink;
    }

    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

}
