package vnapps.com.tamilnewsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import vnapps.com.tamilnewsapp.R;

/**
 * @author Created by vignesh on 13/2/18.
 */

public class MemesDetailsAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> imageUrlList;
    private LayoutInflater inflater;
    private ImageView currentImageView;

    public MemesDetailsAdapter(Context context, ArrayList<String> memesList) {
        this.context = context;
        this.imageUrlList = memesList;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return imageUrlList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.meme_detailed_item, container, false);
        ImageView memeImage = itemView.findViewById(R.id.img_meme_detail_view);
        currentImageView = memeImage;
        if (!TextUtils.isEmpty(imageUrlList.get(position))) {
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .error(R.color.text_light)
                    .placeholder(R.color.text_light);

            Glide.with(context)
                    .load(imageUrlList.get(position))
                    .apply(myOptions)
                    .into(memeImage);
        }

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    public View getCurrentView() {
        return currentImageView;
    }
}
