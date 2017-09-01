package xj.commonutils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author meitu.xujun on 2017/8/30 20:41
 * @version 0.1
 */
public class DensityUtil {

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float px2sp(Context context, float pxValue){
        return (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int sp2px(Context context, int spValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }
}
