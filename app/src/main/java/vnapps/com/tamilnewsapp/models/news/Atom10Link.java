
package vnapps.com.tamilnewsapp.models.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atom10Link {

    @SerializedName("xmlns:atom10")
    @Expose
    private String xmlnsAtom10;
    @SerializedName("rel")
    @Expose
    private String rel;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("type")
    @Expose
    private String type;

    public String getXmlnsAtom10() {
        return xmlnsAtom10;
    }

    public void setXmlnsAtom10(String xmlnsAtom10) {
        this.xmlnsAtom10 = xmlnsAtom10;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
