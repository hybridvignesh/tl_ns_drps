package vnapps.com.tamilnewsapp.pages.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.List;

import javax.inject.Inject;

import vnapps.com.tamilnewsapp.R;
import vnapps.com.tamilnewsapp.configs.AppController;
import vnapps.com.tamilnewsapp.dependencies.interfaces.ApiService;
import vnapps.com.tamilnewsapp.intefaces.BaseActivityInterFace;
import vnapps.com.tamilnewsapp.pages.fragments.AgriFragment;
import vnapps.com.tamilnewsapp.pages.fragments.BusinessFragment;
import vnapps.com.tamilnewsapp.pages.fragments.CinemaFragment;
import vnapps.com.tamilnewsapp.pages.fragments.HomeFragment;
import vnapps.com.tamilnewsapp.pages.fragments.IndiaFragment;
import vnapps.com.tamilnewsapp.pages.fragments.MemesFragment;
import vnapps.com.tamilnewsapp.pages.fragments.PoliticalFragment;
import vnapps.com.tamilnewsapp.pages.fragments.SportsFragment;
import vnapps.com.tamilnewsapp.pages.fragments.TechnicalFragment;
import vnapps.com.tamilnewsapp.pages.fragments.WorldFragment;

public class MainActivity extends AppCompatActivity implements BaseActivityInterFace {

    @Inject
    ApiService apiService;
    @Inject
    Gson gson;

    ViewPager viewPager;
    SmartTabLayout viewPagerTab;
    FragmentPagerItemAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppController.getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        initAddView();
        initViews();
    }

    private void initAddView() {
        MobileAds.initialize(this, "ca-app-pub-5022054076609524~8849532786");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initViews() {

        //add tabs as fragments
        fragmentAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.home, HomeFragment.class)
                .add(R.string.memes, MemesFragment.class)
                .add(R.string.political, PoliticalFragment.class)
                .add(R.string.india, IndiaFragment.class)
                .add(R.string.world, WorldFragment.class)
                .add(R.string.cinema, CinemaFragment.class)
                .add(R.string.sports, SportsFragment.class)
                .add(R.string.tech, TechnicalFragment.class)
                .add(R.string.bussiness, BusinessFragment.class)
                .add(R.string.agri, AgriFragment.class)
                .create());

        //setup viewpager
        viewPager = findViewById(R.id.vp_frame);
        viewPager.setAdapter(fragmentAdapter);

        viewPagerTab = findViewById(R.id.tab_view_pager);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public void changeToMemeDetailedFragment(int position, List<String> memeList) {

    }
}
