package vnapps.com.tamilnewsapp.configs;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by vignesh on 23/12/17.
 */

public class CommonMethods {

    public void showLoader(ProgressBar loader) {
        if (loader != null && !loader.isShown()) {
            loader.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoader(ProgressBar loader) {
        if (loader != null && loader.isShown()) {
            loader.setVisibility(View.GONE);
        }
    }
}
