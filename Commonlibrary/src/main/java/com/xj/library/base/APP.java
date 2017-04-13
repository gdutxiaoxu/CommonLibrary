package com.xj.library.base;

import android.app.Application;

/**
 * @author mobile.xujun  on 2017/3/7 16:19
 * @version 0.1
 */

public class APP extends Application {

    private static APP mAPP;

  /*  private RefWatcher refWatcher;*/


    @Override
    public void onCreate() {
        super.onCreate();
        mAPP=this;

    /*    if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        mAPP=this;
        WriteLogUtil.init(this);*/
    }

    public static APP getInstance(){
        return mAPP;
    }
/*

    public static RefWatcher getRefWatcher(Context context) {
        APP application = (APP) context.getApplicationContext();
        return application.refWatcher;
    }

*/

}
