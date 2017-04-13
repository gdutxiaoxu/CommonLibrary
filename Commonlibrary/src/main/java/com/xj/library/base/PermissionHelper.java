package com.xj.library.base;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.List;

import static android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale;

/**
 * @author meitu.xujun  on 2017/4/7 09:53
 * @version 0.1
 */

public class PermissionHelper {

    public static boolean isM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean somePermissionPermanentlyDenied(@NonNull Activity activity,
                                                          @NonNull List<String> deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (permissionPermanentlyDenied(activity, deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean permissionPermanentlyDenied(@NonNull Activity activity,
                                                      @NonNull String deniedPermission) {
        return !shouldShowRequestPermissionRationale(activity, deniedPermission);
    }
}
