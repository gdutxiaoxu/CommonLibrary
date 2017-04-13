package com.xj.library.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 *
 * @author：xujun on 2016/5/30 18:15
 * gdutxiaoxu@163.com
 */
public class ScreenUtils {

    private ScreenUtils(){

    }

    public static int  getScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);

        return displayMetrics.heightPixels;
    }

    public static int  getScreenWidth(Activity activity){
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        return displayMetrics.widthPixels;
    }


    public static DisplayMetrics getDisplayMetrics(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
