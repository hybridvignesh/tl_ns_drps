package vnapps.com.tamilnewsapp.pages.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileOutputStream;

import vnapps.com.tamilnewsapp.BuildConfig;
import vnapps.com.tamilnewsapp.R;
import vnapps.com.tamilnewsapp.configs.AppController;

public class NewsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView newsImage;
    private ImageView backIcon;

    private TextView newsTitle;
    private TextView newsDetails;

    private FloatingActionButton sharingFab;

    private String imageUrl;
    private String title;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppController.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initAddView();
        initViews();
        getIntentValues();
    }

    private void initAddView() {
        AdView mAdView = findViewById(R.id.add_detailsview);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initViews() {
        newsImage = findViewById(R.id.img_news_image);
        newsTitle = findViewById(R.id.tv_news_title);
        backIcon = findViewById(R.id.iv_back);
        newsDetails = findViewById(R.id.tv_news_details);
        sharingFab = findViewById(R.id.fab_share_button);

        backIcon.setOnClickListener(this);
        sharingFab.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getIntentValues() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imageUrl = bundle.getString("image_url");
             title = bundle.getString("title");
             description = bundle.getString("news_details");

            if (!TextUtils.isEmpty(title)) {
                newsTitle.setText(title);
            }
            if (!TextUtils.isEmpty(description)) {
                newsDetails.setText(description);
            }
            if (!TextUtils.isEmpty(imageUrl)) {
                RequestOptions myOptions = new RequestOptions()
                        .centerCrop()
                        .error(R.color.text_light)
                        .placeholder(R.color.text_light);

                Glide.with(this)
                        .load(imageUrl)
                        .apply(myOptions)
                        .into(newsImage);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else {
            showShareWindow();
        }
    }

    private void showShareWindow() {
        Bitmap bitmap = getBitmapFromView(newsImage);
        try {
            File file = new File(this.getExternalCacheDir(), "newsimage.png");

            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);

            Uri photoURI = FileProvider.getUriForFile(NewsDetailsActivity.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file);


            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, photoURI);
            intent.putExtra(Intent.EXTRA_TEXT,description);
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}
