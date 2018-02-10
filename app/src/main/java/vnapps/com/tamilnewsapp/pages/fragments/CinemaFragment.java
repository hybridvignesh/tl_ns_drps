package vnapps.com.tamilnewsapp.pages.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vnapps.com.tamilnewsapp.R;
import vnapps.com.tamilnewsapp.adapters.NewsAdapter;
import vnapps.com.tamilnewsapp.configs.AppController;
import vnapps.com.tamilnewsapp.configs.CommonMethods;
import vnapps.com.tamilnewsapp.dependencies.interfaces.ApiService;
import vnapps.com.tamilnewsapp.models.news.NewsFeeds;
import vnapps.com.tamilnewsapp.utils.OptimizedNews;

/**
 * Created by vignesh on 3/2/18.
 */

public class CinemaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @Inject
    ApiService apiService;
    @Inject
    Gson gson;
    @Inject
    OptimizedNews optimizedNews;
    @Inject
    CommonMethods commonMethods;

    private Context context;

    private RecyclerView rvCinema;
    private NewsAdapter newsAdapter;
    private ProgressBar loader;

    private SwipeRefreshLayout swipeRefreshLayout;

    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppController.getAppComponent().inject(this);
        context = getContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        initViews(view);
        getNewsFeeds();
        return view;
    }

    private void initViews(View view) {
        rvCinema = view.findViewById(R.id.rv_cinema);
        loader = view.findViewById(R.id.pgs_loader);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getNewsFeeds() {
        apiService.getCinemaNews().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                commonMethods.hideLoader(loader);
                String xmlString = null;
                try {
                    xmlString = response.body().string();
                    XmlToJson xmlToJson = new XmlToJson.Builder(xmlString).build();
                    NewsFeeds newsFeeds = gson.fromJson(xmlToJson.toString(), NewsFeeds.class);
                    setupAdapter(newsFeeds);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                commonMethods.hideLoader(loader);
                Log.e("Error", t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void setupAdapter(NewsFeeds newsFeeds) {
        newsAdapter = new NewsAdapter(context, optimizedNews.getOptimizedNews(newsFeeds.getRss().getChannel().getItem()));
        rvCinema.setLayoutManager(new LinearLayoutManager(context));
        rvCinema.setAdapter(newsAdapter);
    }

    @Override
    public void onRefresh() {
        getNewsFeeds();
    }
}
