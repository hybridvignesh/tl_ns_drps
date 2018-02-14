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
import android.widget.RelativeLayout;

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
import vnapps.com.tamilnewsapp.configs.Constants;
import vnapps.com.tamilnewsapp.dependencies.interfaces.ApiService;
import vnapps.com.tamilnewsapp.dependencies.interfaces.ServiceListener;
import vnapps.com.tamilnewsapp.main.JsonResponse;
import vnapps.com.tamilnewsapp.models.news.NewsFeeds;
import vnapps.com.tamilnewsapp.utils.OptimizedNews;
import vnapps.com.tamilnewsapp.utils.RequestCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ServiceListener {

    @Inject
    ApiService apiService;
    @Inject
    Gson gson;
    @Inject
    OptimizedNews optimizedNews;
    @Inject
    CommonMethods commonMethods;

    private Context context;

    private RecyclerView rvHome;
    private NewsAdapter newsAdapter;
    private ProgressBar loader;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout networkFailure;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppController.getAppComponent().inject(this);
        context = getContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        getNewsFeeds();
        return view;
    }

    private void initViews(View view) {
        rvHome = view.findViewById(R.id.rv_home);
        loader = view.findViewById(R.id.pgs_loader);
        networkFailure = view.findViewById(R.id.rlt_network);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getNewsFeeds() {
        commonMethods.showLoader(loader);
        apiService.getTamilnaduNews().enqueue(new RequestCallback(this));
    }

    private void setupAdapter(NewsFeeds newsFeeds) {
        newsAdapter = new NewsAdapter(context, optimizedNews.getOptimizedNews(newsFeeds.getRss().getChannel().getItem()));
        rvHome.setLayoutManager(new LinearLayoutManager(context));
        rvHome.setAdapter(newsAdapter);
    }

    @Override
    public void onRefresh() {
        getNewsFeeds();
    }

    @Override
    public void onSuccess(JsonResponse jsonResp, String data) {
        commonMethods.hideLoader(loader);
        rvHome.setVisibility(View.VISIBLE);
        networkFailure.setVisibility(View.GONE);
        try {
            XmlToJson xmlToJson = new XmlToJson.Builder(jsonResp.getStrResponse()).build();
            NewsFeeds newsFeeds = gson.fromJson(xmlToJson.toString(), NewsFeeds.class);
            setupAdapter(newsFeeds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(JsonResponse jsonResp, String data) {
        commonMethods.hideLoader(loader);
        swipeRefreshLayout.setRefreshing(false);
        if (data.equals(getResources().getString(R.string.network_failure))) {
            rvHome.setVisibility(View.GONE);
            networkFailure.setVisibility(View.VISIBLE);
        }
    }
}
