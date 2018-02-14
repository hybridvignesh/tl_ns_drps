package vnapps.com.tamilnewsapp.pages.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vnapps.com.tamilnewsapp.BuildConfig;
import vnapps.com.tamilnewsapp.R;
import vnapps.com.tamilnewsapp.adapters.MemesDetailsAdapter;
import vnapps.com.tamilnewsapp.configs.AppController;
import vnapps.com.tamilnewsapp.configs.Constants;
import vnapps.com.tamilnewsapp.dependencies.interfaces.ApiService;
import vnapps.com.tamilnewsapp.utils.Download;
import vnapps.com.tamilnewsapp.utils.DownloadService;

import static vnapps.com.tamilnewsapp.configs.Constants.MESSAGE_PROGRESS;

public class MemeDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Inject
    ApiService apiService;


    private Context context;

    private int position;

    private ViewPager memePager;

    private ArrayList<String> memesList = new ArrayList<>();

    private MemesDetailsAdapter memesDetailsAdapter;

    private FloatingActionButton btnDownload, btnShare;

    private static final int PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_detail);
        AppController.getAppComponent().inject(this);
        context = this;
        initViews();
        initAddView();
        getIntentValues();
        setupViewPager();
        registerReceiver();

    }

    private void initAddView() {
        AdView mAdView = findViewById(R.id.add_detailsview);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void registerReceiver() {
        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(MESSAGE_PROGRESS)) {

                Download download = intent.getParcelableExtra("download");
                if (download.getProgress() == 100) {

                    Toast.makeText(context, "File saved in:" + download.getPath(), Toast.LENGTH_SHORT).show();

                }
            }
        }
    };

    private void setupViewPager() {
        if (!memesList.isEmpty()) {
            memesDetailsAdapter = new MemesDetailsAdapter(context, memesList);
            memePager.setAdapter(memesDetailsAdapter);
            memePager.setCurrentItem(position);
        }
    }

    private void initViews() {
        memePager = findViewById(R.id.vp_meme_pager);
        btnDownload = findViewById(R.id.fab_download);
        btnShare = findViewById(R.id.fab_share);

        btnDownload.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    private void getIntentValues() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            memesList.clear();
            memesList.addAll(bundle.getStringArrayList("memesLists"));
            position = bundle.getInt("position");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_download) {
            //download particular meme
            if (checkPermission()) {
                startDownload(memesList.get(memePager.getCurrentItem()));
            } else {
                requestPermission();
            }
        } else {
            showShareWindow();

        }
    }


    private void showShareWindow() {
        View targetView = memesDetailsAdapter.getCurrentView();
        if (targetView == null) return;

        Bitmap bitmap = getBitmapFromView(targetView);
        try {
            File file = new File(this.getExternalCacheDir(), "memeImage.png");

            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);

            Uri photoURI = FileProvider.getUriForFile(MemeDetailActivity.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file);


            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, photoURI);
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


    private void startDownload(String url) {

        Intent intent = new Intent(this, DownloadService.class).putExtra("downloadUrl", url);
        startService(intent);

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;
        }
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startDownload(memesList.get(memePager.getCurrentItem()));
                } else {

                    Toast.makeText(context, "Permission Denied, Please allow to proceed !", Toast.LENGTH_SHORT).show();

                }
                break;
        }

    }
}
