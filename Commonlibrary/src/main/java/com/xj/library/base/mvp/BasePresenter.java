package com.xj.library.base.mvp;

import java.lang.ref.SoftReference;

/**
 * @author mobile.xujun on 2017/3/14 17:59
 * @version 0.1
 */

@SuppressWarnings("rawtypes")
public  class BasePresenter<V extends IBaseView> implements IBasePresenter {

    protected final SoftReference<V> mReference;


    protected BasePresenter(V view) {
        mReference = new SoftReference<>(view);
    }

    @Override
    public void onDestroy() {
        mReference.clear();
    }

    @Override
    public void onCreate() {

    }

    public V getView(){
        return mReference.get();
    }
}
