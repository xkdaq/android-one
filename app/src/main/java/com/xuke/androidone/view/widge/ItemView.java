package com.xuke.androidone.view.widge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuke.androidone.R;

/**
 * Created by xuke on 2017/12/23.
 * 条目view
 */
public class ItemView extends LinearLayout {
    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivLeft;
    private ImageView ivRight;

    public ItemView(Context context) {
        super(context);
        init(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        String leftText = ta.getString(R.styleable.ItemView_ItemLeftText);
        String rightText = ta.getString(R.styleable.ItemView_ItemRightText);
        Drawable leftIcon = ta.getDrawable(R.styleable.ItemView_ItemLeftImage);
        Drawable rightIcon = ta.getDrawable(R.styleable.ItemView_ItemRightImage);
        boolean isPointShow = ta.getBoolean(R.styleable.ItemView_isPointShow, false);
        ta.recycle();

        LayoutInflater.from(context).inflate(R.layout.layout_itemview, this);
        ivLeft = (ImageView) findViewById(R.id.iv_left_icon);
        ivRight = (ImageView) findViewById(R.id.iv_right_icon);
        tvLeft = (TextView) findViewById(R.id.tv_left_text);
        tvRight = (TextView) findViewById(R.id.tv_right_text);
        if (leftIcon != null) {
            ivLeft.setVisibility(VISIBLE);
            ivLeft.setImageDrawable(leftIcon);
        }
        if (rightIcon != null) {
            ivRight.setVisibility(VISIBLE);
            ivRight.setImageDrawable(rightIcon);
        }
        tvLeft.setText(leftText);
        tvRight.setText(rightText);
        tvRight.setMaxEms(10);
        tvRight.setEllipsize(TextUtils.TruncateAt.END);
        tvRight.setMaxLines(2);

    }

    public void setLeftText(String leftText) {
        tvLeft.setText(leftText);
    }

    public void setRightText(String rightText) {
        tvRight.setText(rightText);
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public String getRightText() {
        return tvRight.getText().toString();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            return false;
        } else {
            return true;
        }
    }
}
