package com.colin.anbet.activity;

import android.widget.ImageView;

import com.colin.anbet.BaseActivity;
import com.colin.anbet.R;


public class XimaActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_xima;
    }

    @Override
    protected void initView() {
        ImageView imageView = findViewById(R.id.img_back_bg);
        imageView.setOnClickListener(v -> finish());
    }
}
