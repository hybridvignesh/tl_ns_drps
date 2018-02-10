package vnapps.com.tamilnewsapp.utils;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import vnapps.com.tamilnewsapp.models.news.Item;

/**
 * Created by vignesh on 24/1/18.
 */

public class OptimizedNews {


    public List<Item> getOptimizedNews(List<Item> itemListitem) {
        for (int i = 0; i < itemListitem.size(); i++) {
            Document doc = Jsoup.parse(itemListitem.get(i).getContentEncoded());
            Elements images = doc.select("img");
            Elements paras = doc.select("p");

            StringBuilder description = new StringBuilder(" ");


            String imgUrl = images.get(0).attr("src");
            itemListitem.get(i).setImageUrl(imgUrl);

            for(int k=0;k<paras.size()-1;k++)
            {
                description.append(String.format("%s\n\n", paras.get(k).text()));
            }

            itemListitem.get(i).setNewsDescription(description.toString());

        }
        return itemListitem;
    }

}
