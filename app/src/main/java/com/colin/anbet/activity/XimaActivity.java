package com.colin.anbet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.BaseActivity;
import com.colin.anbet.R;
import com.colin.anbet.widget.MultipleStatusView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class XimaActivity extends BaseActivity {

    @BindView(R.id.img_back_bg)
    ImageView imgBackBg;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.btn_qipai)
    RadioButton btnQipai;
    @BindView(R.id.btn_zhenren)
    RadioButton btnZhenren;
    @BindView(R.id.btn_sport)
    RadioButton btnSport;
    @BindView(R.id.btn_dianzi)
    RadioButton btnDianzi;
    @BindView(R.id.btn_buyu)
    RadioButton btnBuyu;
    @BindView(R.id.tv_touzhu)
    TextView tvTouzhu;
    @BindView(R.id.tv_yang2)
    TextView tvYang2;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_xima_history)
    TextView tvXimaHistory;
    @BindView(R.id.ll_xima_proportion)
    LinearLayout llXimaProportion;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.msv_activity_xima)
    MultipleStatusView msvActivityXima;
    @BindView(R.id.tv_last_time)
    TextView tvLastTime;
    @BindView(R.id.tv_xima_amount)
    TextView tvXimaAmount;
    @BindView(R.id.tv_yang)
    TextView tvYang;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_wash_code)
    TextView tvWashCode;
    @BindView(R.id.ll_xima_contain)
    LinearLayout llXimaContain;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_xima;
    }

    @Override
    protected void initView() {
        imgTitle.setImageResource(R.drawable.ic_xima_title);
    }



    @OnClick({R.id.img_back_bg, R.id.btn_qipai, R.id.btn_zhenren, R.id.btn_sport, R.id.btn_dianzi, R.id.btn_buyu, R.id.tv_wash_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back_bg:
                finish();
                break;
            case R.id.btn_qipai:
                break;
            case R.id.btn_zhenren:
                break;
            case R.id.btn_sport:
                break;
            case R.id.btn_dianzi:
                break;
            case R.id.btn_buyu:
                break;
            case R.id.tv_wash_code:
                break;
        }
    }
}
