package com.colin.anbet.Withdraw;

import android.os.Bundle;
import android.view.KeyEvent;
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


public class WithDrawActivity
        extends BaseActivity
        implements View.OnClickListener {
    private static final String TAG = "WithDrawActivity";
    WDBankFragment bankFragment = new WDBankFragment();
    WDBlanceFragment balanceFrg = new WDBlanceFragment();
    WDCardFragment cardFrg = new WDCardFragment();

    RadioButton btn_to_bank;
    @BindView(R.id.img_back_bg)
    ImageView imgBackBg;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.btn_to_bank)
    RadioButton btnToBank;
    @BindView(R.id.btn_blance)
    RadioButton btnBlance;
    @BindView(R.id.btn_card)
    RadioButton btnCard;
    @BindView(R.id.rg_draw)
    RadioGroup rgDraw;
    @BindView(R.id.fl_wd_contain)
    FrameLayout flWdContain;

    private Fragment currentFragment = bankFragment;
//    protected void onActivityResult(int paramInt1, int paramInt2, @Nullable Intent paramIntent) {
//        EventBus.getDefault().post(new CustomerEvent(paramInt1, paramIntent));
//    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            default:
                break;
            case R.id.img_back_bg:

                break;
            case R.id.btn_to_bank:
//                getSupportFragmentManager().beginTransaction().hide(this.c).hide(this.bankFragment).hide(this.balanceFrg).show(this.cardFrg).commitAllowingStateLoss();

                break;
        }
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
//        playMusic(7, volume);
        //加载loading界面。
        btn_to_bank = findViewById(R.id.btn_to_bank);
        btn_to_bank.performClick();
        //頭部客服
//        aq.id(R.id.img_title).image(R.drawable.ic_withdraw_title);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_wd_contain, bankFragment).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic(7);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            EventBus.getDefault().post(new RefreshBalanceEvent());
            finish();
        }
        return true;
    }

    public void switchFragment(Fragment targetFragment) {
        if (currentFragment == targetFragment) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fl_wd_contain, targetFragment)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back_bg, R.id.btn_to_bank, R.id.btn_blance, R.id.btn_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back_bg:
                finish();
                break;
            case R.id.btn_to_bank:
                switchFragment(bankFragment);
                break;
            case R.id.btn_blance:
//                switchFragment(balanceFrg);
                break;
            case R.id.btn_card:
                switchFragment(cardFrg);
                break;
        }
    }
}