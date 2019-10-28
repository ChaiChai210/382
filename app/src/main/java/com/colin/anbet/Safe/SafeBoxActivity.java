package com.colin.anbet.Safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.colin.anbet.MyApp;
import com.colin.anbet.base.BaseActivity;
import com.colin.anbet.R;
import com.colin.anbet.base.BindEventBus;
import com.colin.anbet.event.LoginEvent;
import com.colin.anbet.event.SafeEvent;
import com.colin.anbet.util.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.Safe
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/15 15:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/15 15:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@BindEventBus
public class SafeBoxActivity extends BaseActivity {
    @BindView(R.id.img_back_bg)
    ImageView imgBackBg;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.btn_zhuanru)
    RadioButton btnZhuanru;
    @BindView(R.id.btn_quchu)
    RadioButton btnQuchu;
    @BindView(R.id.btn_mingxi)
    RadioButton btnMingxi;
    @BindView(R.id.rg_safe)
    RadioGroup rgSafe;
    @BindView(R.id.fl_safe_contain)
    FrameLayout flSafeContain;
    Fragment currentFragment = new SafeZRFragment();
    Fragment zrFragment = new SafeZRFragment();
    Fragment qcFragment = new SafeQCFragment();
    Fragment mxFragment = new SafeMXFragment();


    private String safeMoney;
    private String balance;

    private boolean needRefresh = false;

    public String getSafeMoney() {
        return safeMoney;
    }

    public String getBalance() {
        return balance;
    }

    public static void gotoSafe(Context context, String memberSafeMoney) {
        Intent intent = new Intent(context, SafeBoxActivity.class);
        intent.putExtra(Constants.SAFE_MONEY, memberSafeMoney);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_safe_box;
    }

    @Override
    protected void initView() {
        safeMoney = getIntent().getStringExtra(Constants.SAFE_MONEY);
        playMusic(6, volume);
        imgTitle.setImageResource(R.drawable.ic_safe_box_title);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_safe_contain, currentFragment).commit();
    }


    @OnClick({R.id.img_back_bg, R.id.btn_zhuanru, R.id.btn_quchu, R.id.btn_mingxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back_bg:
                finish();
                break;
            case R.id.btn_zhuanru:
                switchFragment(zrFragment);
                break;
            case R.id.btn_quchu:
                switchFragment(qcFragment);
                break;
            case R.id.btn_mingxi:
                switchFragment(mxFragment);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic(6);
    }

    private void switchFragment(Fragment targetFragment) {
        if (currentFragment == targetFragment) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fl_safe_contain, targetFragment)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSafeEvent(SafeEvent event) {
        Log.e("chia", "ref");
        needRefresh = true;
        safeMoney = event.getSafeMoney();
        balance = event.getBalance();
        Log.e("chia", balance + "safe" + safeMoney);
    }

    public boolean isNeedRefresh() {
        return needRefresh;
    }
}
