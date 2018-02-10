package vnapps.com.tamilnewsapp.dependencies.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vnapps.com.tamilnewsapp.configs.CommonMethods;
import vnapps.com.tamilnewsapp.configs.Constants;
import vnapps.com.tamilnewsapp.configs.SessionManager;
import vnapps.com.tamilnewsapp.main.JsonResponse;
import vnapps.com.tamilnewsapp.utils.OptimizedNews;


@Module(includes = ApplicationModule.class)
public class AppContainerModule {
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    CommonMethods providesCommonMethods() {
        return new CommonMethods();
    }

    @Provides
    @Singleton
    SessionManager providesSessionManager() {
        return new SessionManager();
    }

    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    ArrayList<String> providesStringArrayList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    JsonResponse providesJsonResponse() {
        return new JsonResponse();
    }

    @Provides
    @Singleton
    OptimizedNews providesOptimizedNews(){return new OptimizedNews();}


}
