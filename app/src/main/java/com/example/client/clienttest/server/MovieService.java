package com.example.client.clienttest.server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/24/0024.
 */

public interface MovieService{
    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

    @FormUrlEncoded
    @POST("/x3/weather")
    Call<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);
}

