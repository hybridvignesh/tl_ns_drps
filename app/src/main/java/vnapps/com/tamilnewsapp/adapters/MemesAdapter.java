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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import vnapps.com.tamilnewsapp.R;;
import vnapps.com.tamilnewsapp.pages.activities.MemeDetailActivity;


/**
 * @author Created by vignesh on 12/2/18.
 */

public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<String> imgUrlList = new ArrayList<>();
    private LayoutInflater inflater;


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cv_root_view) {
            MemesAdapter.ViewHolder vHolder = (MemesAdapter.ViewHolder) view.getTag();

            Intent memesIntent = new Intent(context, MemeDetailActivity.class);

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, vHolder.memeImage, context.getString(R.string.activity_image_trans));

            memesIntent.putStringArrayListExtra("memesLists", (ArrayList<String>) imgUrlList);
            memesIntent.putExtra("position", vHolder.getAdapterPosition());

            context.startActivity(memesIntent, options.toBundle());
        }
    }

    public MemesAdapter(Context context, List<String> imgUrls) {
        this.context = context;
        this.imgUrlList = imgUrls;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MemesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.meme_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemesAdapter.ViewHolder holder, int position) {
        if (!TextUtils.isEmpty(imgUrlList.get(position))) {
            RequestOptions myOptions = new RequestOptions()
                    .centerCrop()
                    .error(R.color.text_light)
                    .placeholder(R.color.text_light);

            Glide.with(context)
                    .load(imgUrlList.get(position))
                    .apply(myOptions)
                    .into(holder.memeImage);
        }

        holder.rootView.setTag(holder);
        holder.rootView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return imgUrlList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView memeImage;
        private CardView rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            memeImage = itemView.findViewById(R.id.img_meme_item);
            rootView = itemView.findViewById(R.id.cv_root_view);
        }
    }
}
