package vnapps.com.tamilnewsapp.pages.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import vnapps.com.tamilnewsapp.R;
import vnapps.com.tamilnewsapp.adapters.MemesAdapter;
import vnapps.com.tamilnewsapp.configs.AppController;
import vnapps.com.tamilnewsapp.configs.CommonMethods;
import vnapps.com.tamilnewsapp.configs.Constants;
import vnapps.com.tamilnewsapp.dependencies.interfaces.ApiService;
import vnapps.com.tamilnewsapp.dependencies.interfaces.ServiceListener;
import vnapps.com.tamilnewsapp.intefaces.BaseActivityInterFace;
import vnapps.com.tamilnewsapp.main.JsonResponse;
import vnapps.com.tamilnewsapp.utils.RequestCallback;

/**
 * Created by vignesh on 11/2/18.
 */

public class MemesFragment extends Fragment implements ServiceListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    CommonMethods commonMethods;
    @Inject
    ApiService apiService;


    private Context context;
    private RecyclerView rv_memeslist;
    private ProgressBar loader;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout networkFailure;

    private MemesAdapter memesAdapter;

    private List<String> imgUrlList = new ArrayList<>();

    private BaseActivityInterFace baseActivityInterFace;

    public MemesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivityInterFace = (BaseActivityInterFace) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppController.getAppComponent().inject(this);
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_memeslist, container, false);
        initView(view);
        getMemesList();
        return view;
    }

    private void initView(View view) {
        rv_memeslist = view.findViewById(R.id.rv_meme_list);
        loader = view.findViewById(R.id.pgs_loader);
        networkFailure = view.findViewById(R.id.rlt_network);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(this);

    }

    private void getMemesList() {
        commonMethods.showLoader(loader);
        apiService.getMemesCount(Constants.MEMES_COUNT_URL).enqueue(new RequestCallback(this));
    }

    @Override
    public void onSuccess(JsonResponse jsonResp, String data) {
        swipeRefreshLayout.setRefreshing(false);
        commonMethods.hideLoader(loader);
        rv_memeslist.setVisibility(View.VISIBLE);
        networkFailure.setVisibility(View.GONE);
        try {
            int lastIndex = getLastUploadIndex(jsonResp.getStrResponse());
            memesAdapter = new MemesAdapter(context, getImgUrlList(lastIndex));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            rv_memeslist.setLayoutManager(gridLayoutManager);
            rv_memeslist.setAdapter(memesAdapter);
        } catch (Exception e) {

        }

    }

    @Override
    public void onFailure(JsonResponse jsonResp, String data) {
        swipeRefreshLayout.setRefreshing(false);
        commonMethods.hideLoader(loader);
        swipeRefreshLayout.setRefreshing(false);
        if (data.equals(getResources().getString(R.string.network_failure))) {
            rv_memeslist.setVisibility(View.GONE);
            networkFailure.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        getMemesList();
    }

    private List<String> getImgUrlList(int lastIndex) {
        imgUrlList.clear();
        for (int i = lastIndex - 199; i <= lastIndex; i++) {
            imgUrlList.add(String.format(getString(R.string.meme_url_format), String.valueOf(i)));
        }
        Collections.reverse(imgUrlList);
        return imgUrlList;
    }

    private int getLastUploadIndex(String response) {
        Document doc = Jsoup.parse(response);
        Elements images = doc.select("img");
        String imgUrl = images.get(0).attr("src");
        String index = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.lastIndexOf("."));
        return Integer.parseInt(index);
    }

}
