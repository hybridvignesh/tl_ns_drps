package vnapps.com.tamilnewsapp.configs;

import android.app.Application;

import vnapps.com.tamilnewsapp.dependencies.component.AppComponent;
import vnapps.com.tamilnewsapp.dependencies.component.DaggerAppComponent;
import vnapps.com.tamilnewsapp.dependencies.module.ApplicationModule;
import vnapps.com.tamilnewsapp.dependencies.module.NetworkModule;


/**
 * Created by vignesh on 23/12/17.
 */

public class AppController extends Application {
    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
