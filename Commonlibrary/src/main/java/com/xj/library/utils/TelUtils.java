package com.xj.library.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * the utils about TelephonyManager
 *
 * @author meitu.xujun  on 2017/3/24 15:32
 * @version 0.1
 */

public class TelUtils {

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService
                (Context.TELEPHONY_SERVICE);
        String IMEI = tm.getDeviceId();
        if (TextUtils.isEmpty(IMEI)) {
            IMEI = Settings.Secure.getString(context.getApplicationContext().getContentResolver()
                    , Settings.Secure.ANDROID_ID);

        }
        return IMEI;
    }
}
