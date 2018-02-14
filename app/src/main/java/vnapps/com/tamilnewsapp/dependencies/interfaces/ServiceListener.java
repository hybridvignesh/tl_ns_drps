package vnapps.com.tamilnewsapp.dependencies.interfaces;


import vnapps.com.tamilnewsapp.main.JsonResponse;

public interface ServiceListener {

    void onSuccess(JsonResponse jsonResp, String data);

    void onFailure(JsonResponse jsonResp, String data);
}
