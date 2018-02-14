package vnapps.com.tamilnewsapp.dependencies.interfaces;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiService {

    @GET("Puthiyathalaimurai_Tamilnadu_News")
    Call<ResponseBody> getTamilnaduNews();

    @GET("Puthiyathalaimurai_India_News")
    Call<ResponseBody> getIndiaNews();

    @GET("Puthiyathalaimurai_World_News")
    Call<ResponseBody> getWorldNews();

    @GET("Puthiyathalaimurai_Finance_News")
    Call<ResponseBody> getBussinessNews();

    @GET("Puthiyathalaimurai_Sports_News")
    Call<ResponseBody> getSportsNews();

    @GET("Puthiyathalaimurai_Agriculture_News")
    Call<ResponseBody> getAgriNews();

    @GET("Puthiyathalaimurai_Politics_News")
    Call<ResponseBody> getPoliticalNews();

    @GET("Puthiyathalaimurai_Science_Technology_News")
    Call<ResponseBody> getTechnicalNews();

    @GET("Puthiyathalaimurai_Cinema_News")
    Call<ResponseBody> getCinemaNews();

    @GET
    Call<ResponseBody> getMemesCount(@Url String url);

    @GET
    Call<ResponseBody> downloadImage(@Url String url);
}
