package com.xj.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.xj.library.R;
import com.xj.library.utils.ConvertUtils;

/**
 * @author xujun  on 2016/12/27.
 *  gdutxiaoxu@163.com
 */

public class ImageButtonWithText extends LinearLayout {

    private static final String TAG = "ImageButtonWithText";
    public static final int DEF_VALUE = -1;
    private String mText;
    public ImageView imageView;
    public TextView textView;
    private int mPicture_id;

    Context mContext;
    private int mTextColor;
    private float mTextSize;
    private float mTextMarginTop;
    private int mDefHeightValue;
    private float mIvHeight;
    private float mIvWidth;

    public ImageButtonWithText(Context context) {
        this(context, null);
    }

    public ImageButtonWithText(Context context, AttributeSet attrs) {
        this(context, attrs, -1);


    }

    public ImageButtonWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageButtonWithText);
        mPicture_id = a.getResourceId(R.styleable.ImageButtonWithText_picture, DEF_VALUE);
        mText = a.getString(R.styleable.ImageButtonWithText_text);
        mTextColor = a.getColor(R.styleable.ImageButtonWithText_android_textColor, DEF_VALUE);
        int defTextSize = ConvertUtils.dip2px(mContext, 14);

        mTextSize = a.getDimension(R.styleable.ImageButtonWithText_android_textSize, defTextSize);
        Log.i(TAG, "initData: defTextSize=" + defTextSize + " mTextSize= " + mTextSize);
        mTextMarginTop = a.getDimension(R.styleable.ImageButtonWithText_textMarginTop,
                ConvertUtils.dip2px(mContext, 5));
        mDefHeightValue = ConvertUtils.dip2px(mContext, 20);
//        需要注意的是 getDimension 获得的单位是 px
        mIvHeight = a.getDimension(R.styleable.ImageButtonWithText_pictureHeight, mDefHeightValue);
        mIvWidth = a.getDimension(R.styleable.ImageButtonWithText_pictureWidth, mDefHeightValue);

        a.recycle();

        View.inflate(mContext, R.layout.view_image_text, this);
        //  设置居中显示
        setGravity(Gravity.CENTER);
        imageView = (ImageView) findViewById(R.id.iv);
        textView = (TextView) findViewById(R.id.tv);
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        layoutParams = fixLayoutParams(layoutParams);
        layoutParams.topMargin = (int) mTextMarginTop;
        textView.setLayoutParams(layoutParams);

        if (TextUtils.isEmpty(mText)) {
            mText = "";
        }
        textView.setText(mText);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);


        textView.setTextColor(mTextColor);

        if (mPicture_id != DEF_VALUE) {
            imageView.setImageResource(mPicture_id);
        }

        if (mIvHeight != mDefHeightValue) {
            LayoutParams params = fixLayoutParams((LayoutParams) imageView.getLayoutParams());
            params.height = (int) mIvHeight;
            params.width = (int) mIvWidth;
            imageView.setLayoutParams(params);
        }


    }

    @NonNull
    private LayoutParams fixLayoutParams(LayoutParams layoutParams) {
        if (layoutParams == null) {
            int dimension = (int) getResources().getDimension(LayoutParams.WRAP_CONTENT);
            layoutParams = new LayoutParams(dimension, dimension);

        }
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setText(CharSequence buttonText) {
        textView.setText(buttonText);
    }

    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}