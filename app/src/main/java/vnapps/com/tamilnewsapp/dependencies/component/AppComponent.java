package vnapps.com.tamilnewsapp.dependencies.component;


import javax.inject.Singleton;

import dagger.Component;
import vnapps.com.tamilnewsapp.pages.activities.MainActivity;
import vnapps.com.tamilnewsapp.dependencies.module.AppContainerModule;
import vnapps.com.tamilnewsapp.dependencies.module.ApplicationModule;
import vnapps.com.tamilnewsapp.dependencies.module.NetworkModule;
import vnapps.com.tamilnewsapp.pages.activities.MemeDetailActivity;
import vnapps.com.tamilnewsapp.pages.activities.NewsDetailsActivity;
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
import vnapps.com.tamilnewsapp.utils.DownloadService;
import vnapps.com.tamilnewsapp.utils.RequestCallback;


@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class, AppContainerModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(HomeFragment homeFragment);

    void inject(IndiaFragment indiaFragment);

    void inject(WorldFragment worldFragment);

    void inject(SportsFragment sportsFragment);

    void inject(BusinessFragment businessFragment);

    void inject(AgriFragment agriFragment);

    void inject(NewsDetailsActivity newsDetailsActivity);

    void inject(CinemaFragment cinemaFragment);

    void inject(PoliticalFragment politicalFragment);

    void inject(TechnicalFragment technicalFragment);

    void inject(RequestCallback requestCallback);

    void inject(MemesFragment memesFragment);

    void inject(MemeDetailActivity memeDetailActivity);

    void inject(DownloadService downloadService);
}
