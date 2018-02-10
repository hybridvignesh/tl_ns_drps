package vnapps.com.tamilnewsapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import vnapps.com.tamilnewsapp.R;
import vnapps.com.tamilnewsapp.models.news.Item;
import vnapps.com.tamilnewsapp.models.news.NewsFeeds;
import vnapps.com.tamilnewsapp.pages.activities.NewsDetailsActivity;

/**
 * Created by vignesh on 24/1/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements View.OnClickListener {


    private Context context;
    private List<Item> newsFeeds;
    private LayoutInflater inflater;

    public NewsAdapter(Context context, List<Item> newsFeeds) {
        this.context = context;
        this.newsFeeds = newsFeeds;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

        Item newsItem = newsFeeds.get(position);

        if (!TextUtils.isEmpty(newsItem.getTitle())) {
            holder.newsTitle.setText(newsItem.getTitle());
        }
        if (!TextUtils.isEmpty(newsItem.getDescription())) {
            holder.newsDetail.setText(newsItem.getDescription());
        }
        if (!TextUtils.isEmpty(newsItem.getImageUrl())) {
            RequestOptions myOptions = new RequestOptions()
                    .centerCrop()
                    .error(R.color.text_light)
                    .placeholder(R.color.text_light);

            Glide.with(context)
                    .load(newsItem.getImageUrl())
                    .apply(myOptions)
                    .into(holder.newsImage);
        }

        holder.cardView.setTag(holder);
        holder.cardView.setOnClickListener(this);


    }

    @Override
    public int getItemCount() {
        return newsFeeds.size();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cv_root_view) {
            ViewHolder vHolder = (ViewHolder) view.getTag();
            Item newsItem = newsFeeds.get(vHolder.getAdapterPosition());
            Intent newsIntent = new Intent(context, NewsDetailsActivity.class);

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, vHolder.newsImage, context.getString(R.string.activity_image_trans));

            newsIntent.putExtra("title", newsItem.getTitle());
            newsIntent.putExtra("image_url", newsItem.getImageUrl());
            newsIntent.putExtra("news_details", newsItem.getNewsDescription());
            context.startActivity(newsIntent, options.toBundle());

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        private TextView newsTitle;
        private TextView newsDetail;

        private ImageView newsImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (itemView).findViewById(R.id.cv_root_view);
            newsTitle = (itemView).findViewById(R.id.tv_news_title);
            newsDetail = (itemView).findViewById(R.id.tv_news_details);
            newsImage = (itemView).findViewById(R.id.img_news_head);
        }
    }
}
