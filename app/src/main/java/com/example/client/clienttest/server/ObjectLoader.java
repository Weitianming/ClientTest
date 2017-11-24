package com.example.client.clienttest.server;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/24/0024.
 */

public class ObjectLoader {
    /**
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected  <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
