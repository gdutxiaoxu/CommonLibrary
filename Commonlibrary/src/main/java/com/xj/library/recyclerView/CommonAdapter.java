package com.xj.library.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;

import com.xj.library.recyclerView.base.ItemViewDelegate;
import com.xj.library.recyclerView.base.ViewHolder;

import java.util.List;

/**
 * Created by xujun on 16/4/9.
 * if you use XRecyclerView，you should override isXRecyclerView() ,and return true.
 * otherwise when you click item,the position is error,and you will crash sometime
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    protected int mLayoutId;

    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);


}
