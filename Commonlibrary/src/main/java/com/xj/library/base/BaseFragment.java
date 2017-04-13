package com.xj.library.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.xj.library.base.mvp.BasePresenter;

/**
 * Created by xujun、on 2016/4/26.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;
    protected Context mContext;
    public static final String TAG = "xujun";
    protected boolean mIsVisiableToUser = false;
    protected boolean mIsViewInitiated = false;
    protected boolean mIsDataInitiated = false;
    protected View mView;

    public final String className = getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Log.i(TAG, "onAttach: =" + className);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: =" + className);
        mPresenter = setPresenter();
        initAru();
    }

    protected void initAru() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: =" + className);
        if (mView == null) {
            mView = inflater.inflate(getContentViewLayoutID(), container, false);
            initView();
        } else {
            ViewParent parent = mView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mView);
            }
        }
        initEvent();
        mIsViewInitiated = true;
        return mView;
    }

    protected void initEvent() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: =" + className);
        if (mPresenter != null) {
            mPresenter.onCreate();
        }

        prepareFetchData();
        initListener();
        initData();
        // LUtils.d(this.getClass().getSimpleName()+">>>>>>>>>>>onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: =" + className);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: =" + className);

        if (mPresenter != null) mPresenter.onDestroy();

    }

    protected void initData() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareFetchData();

    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /**
     * 注意：
     * 这个方法是对ViewPager里面嵌套多个Fragment而言的
     * 如果想等到界面可见的时候在加载网络数据，可以在这个方法里面执行。
     * 同时如果是Fragment+ViewPager里面嵌套多个Fragment的话，ViewPager里面的第0个
     * Fragment必须 手动去调用fetchData（）方法
     * <p>
     * 这就是 人们常说的懒加载方法
     * <p>
     * 对于单纯用add，show，hide方法显示 的Fragemnt是不会调用的,除非我们手动调用该方法。
     */
    public void fetchData() {

    }

    /***
     * 传true可以强制刷新
     *
     * @param forceUpdate
     * @return
     */
    public boolean prepareFetchData(boolean forceUpdate) {
        if (mIsVisiableToUser && mIsViewInitiated && (!mIsDataInitiated || forceUpdate)) {
            fetchData();
            mIsDataInitiated = true;
            return true;
        }
        return false;
    }

    protected boolean hasEventBus() {
        return false;
    }

    protected void initListener() {
    }

    protected abstract int getContentViewLayoutID();

    //    这个方法只有在View第一次创建的时候才会回调
    protected abstract void initView();

    protected abstract P setPresenter();

    public void readyGo(Class<?> clazz) {
        this.readyGo(clazz, null, "");
    }

    public void readyGo(Class<?> clazz, Parcelable parcelable) {

    }

    public void readyGo(Class<?> clazz, String name, Parcelable parcelable) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != parcelable) {
            intent = intent.putExtra(name, parcelable);
        }
        startActivity(intent);
    }

    public void readyGo(Class<?> clazz, String name, String value) {

        Intent intent = new Intent(getActivity(), clazz);
        if (null != value) {
            intent = intent.putExtra(name, value);
        }
        startActivity(intent);
    }

    protected <T> T checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }


}
