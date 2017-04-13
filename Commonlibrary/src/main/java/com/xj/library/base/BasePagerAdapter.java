package com.xj.library.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author meitu.xujun  on 2017/4/8 14:22
 * @version 0.1
 */

public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected final Context mContext;
    protected List<T> mDatas;
    protected View mView;

    public  BasePagerAdapter(Context context, List<T> datas){
        mContext = context;
        mDatas=datas;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mView = initView(container, position);
        if(mView.getParent()!=null){
            ((ViewGroup) mView.getParent()) .removeView(mView);
        }
        container.addView(mView);
        initData(position);
        return mView;
    }

    protected  void initData(int position){

    }

    protected abstract View initView(ViewGroup container, int position) ;

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
