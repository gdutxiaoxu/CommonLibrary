package com.xj.library.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author：xujun on 2016/6/14 16:03
 * gdutxiaoxu@163.com
 */
public class ViewUtils {

    public static  void removeParent(View view){
        ViewGroup viewGroup= (ViewGroup) view.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(view);
        }

    }



}
