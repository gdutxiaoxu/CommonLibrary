package com.xj.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xj.library.R;
import com.xj.library.utils.StringUtils;

/**
 *
 * @author：xujun on 2016/11/20 09:45
 * gdutxiaoxu@163.com
 */
public class SettingClickItem extends RelativeLayout {

    public static final int DEF_VALUE = -1;
    private ImageView iconLeft;
    private TextView tvDes;
    private ImageView iconRight;
    private TextView tvContent;
    private Context mContext;
    private String mContent;
    private String mDes;
    private int mLeftIconId;
    private int mRightIconId;

    public SettingClickItem(Context context) {
        this(context, null);
    }

    public SettingClickItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingClickItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_setting_click, this);
        iconLeft = (ImageView) findViewById(R.id.icon_left);
        tvDes = (TextView) findViewById(R.id.tv_des);
        iconRight = (ImageView) findViewById(R.id.icon_right);
        tvContent = (TextView) findViewById(R.id.tv_content);
        mContent = StringUtils.getStr(mContent,"");
        tvContent.setText(mContent);
        mDes = StringUtils.getStr(mDes, "");
        tvDes.setText(mDes);
        if (mLeftIconId != DEF_VALUE) {
            iconLeft.setImageResource(mLeftIconId);
        }
        if (mRightIconId != DEF_VALUE) {
            iconRight.setImageResource(mRightIconId);
        }
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable
                .SettingClickItem);
        mLeftIconId = typedArray.getResourceId(R.styleable.SettingClickItem_leftIcon, DEF_VALUE);
        mRightIconId = typedArray.getResourceId(R.styleable.SettingClickItem_rightIcon, DEF_VALUE);
        mContent = typedArray.getString(R.styleable.SettingClickItem_content);
        mDes = typedArray.getString(R.styleable.SettingClickItem_des);
        typedArray.recycle();
    }

    public void setDes(String des) {
        mDes = des;
        tvDes.setText(des);
    }

    public void setContent(String content) {
        mContent = content;
        tvContent.setText(content);
    }

    public void setLeftIcon(int iconId) {
        iconLeft.setImageResource(iconId);
    }
}
