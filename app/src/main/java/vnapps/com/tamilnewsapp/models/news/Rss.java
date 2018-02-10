
package vnapps.com.tamilnewsapp.models.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rss {

    @SerializedName("channel")
    @Expose
    private Channel channel;
    @SerializedName("xmlns:feedburner")
    @Expose
    private String xmlnsFeedburner;
    @SerializedName("xmlns:content")
    @Expose
    private String xmlnsContent;
    @SerializedName("version")
    @Expose
    private String version;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getXmlnsFeedburner() {
        return xmlnsFeedburner;
    }

    public void setXmlnsFeedburner(String xmlnsFeedburner) {
        this.xmlnsFeedburner = xmlnsFeedburner;
    }

    public String getXmlnsContent() {
        return xmlnsContent;
    }

    public void setXmlnsContent(String xmlnsContent) {
        this.xmlnsContent = xmlnsContent;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
