package com.example.client.clienttest.server;

import android.graphics.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/11/24/0024.
 */

public class MovieLoader extends ObjectLoader {
    private MovieService mMovieService;
    public MovieLoader(){
        mMovieService = RetrofitServiceManager.getInstance().create(MovieService.class);
    }
    /**
     * 获取电影列表
     * @param start
     * @param count
     * @return
     */
    public Observable<List<Movie>> getMovie(int start, int count){
        return observe(mMovieService.getTop250(start,count))
                .map(new Func1<Subject, List<Movie>>() {
                    @Override
                    public List<Movie> call(Subject movieSubject) {
                        return movieSubject.subjects;
                    }
                });
    }

    public Observable<String> getWeatherList(String cityId,String key){
        return observe(mMovieService.getWeather(cityId,key))
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //可以处理对应的逻辑后在返回
                        return s;
                    }
                });
    }

    public interface MovieService{
        //获取豆瓣Top250 榜单
        @GET("top250")
        Observable<Subject> getTop250(@Query("start") int start, @Query("count")int count);

        @FormUrlEncoded
        @POST("/x3/weather")
        Call<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);
    }
}
