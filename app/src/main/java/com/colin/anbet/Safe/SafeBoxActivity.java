package com.colin.anbet.Safe;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.colin.anbet.BaseActivity;
import com.colin.anbet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_safe_box;
    }

    @Override
    protected void initView() {
        imgTitle.setImageResource(R.drawable.ic_title_safe_box);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_safe_contain, currentFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
}
