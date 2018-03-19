package com.example.myweiyue.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 冯涛 on 2018/1/24.
 * E-mail:716774214@qq.com
 * desc:
 */

public abstract class BaseObserver<T> implements Observer<T> {

    public abstract void onSuccess(T t);

    public abstract void onFailed(Throwable throwable);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
