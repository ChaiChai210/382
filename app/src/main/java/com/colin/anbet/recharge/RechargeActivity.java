package com.colin.anbet.recharge;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.BaseActivity;
import com.colin.anbet.MyApp;
import com.colin.anbet.R;
import com.colin.anbet.dialog.ChargeHistoryDialog;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.Utils;
import com.colin.anbet.widget.RecycleViewDivider;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

/**
 * 充值界面
 */
public class RechargeActivity
        extends BaseActivity {
    private static final String TAG = "CustomerServiceActivity";
    @BindView(R.id.img_back_bg)
    ImageView imgBackBg;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.tv_golden_account)
    TextView tvGoldenAccount;
    @BindView(R.id.btn_refresh)
    ImageButton btnRefresh;
    @BindView(R.id.btn_recharge_record)
    ImageButton btnRechargeRecord;
    @BindView(R.id.leftRecyclerView)
    RecyclerView leftRecyclerView;
    @BindView(R.id.fl_recharge_contain)
    FrameLayout flRechargeContain;
    ChargeBankFragment bankFragment = new ChargeBankFragment();
    private Fragment currentFragment = bankFragment;
    //    protected void onActivityResult(int paramInt1, int paramInt2, @Nullable Intent paramIntent) {
//        EventBus.getDefault().post(new CustomerEvent(paramInt1, paramIntent));
//    }
    private LeftMenuAdapter leftMenuAdapter;
    private LeftMenuBean leftMenuBean;
    private List<LeftMenuBean> leftMenuBeans = new ArrayList<>();
    private List list;

    private void refreshBalance() {
        Animation localAnimation = AnimationUtils.loadAnimation(this, R.anim.button_fresh_rotate);
        localAnimation.setInterpolator(new LinearInterpolator());
        btnRefresh.clearAnimation();
        btnRefresh.startAnimation(localAnimation);
        tvGoldenAccount.setText("刷新中...");
//        this.f.refreshBalance();

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initView() {
        playMusic(13, volume);
        //加载loading界面。
        tvGoldenAccount.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));
        imgTitle.setImageResource(R.drawable.ic_recharge_title);
        getLeftMenu();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        leftRecyclerView.setLayoutManager(linearLayoutManager);
        leftRecyclerView.addItemDecoration(new RecycleViewDivider(this, 1));
        leftMenuAdapter = new LeftMenuAdapter(leftMenuBeans);
        leftMenuAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
        leftRecyclerView.setAdapter(leftMenuAdapter);
        switchFragment(currentFragment);
    }

    private void getLeftMenu() {
        RxHttp.get(Url.listPayType)
                .asParser(new CommonParser<List<LeftMenuBean>>(new TypeToken<BaseResponseBean<List<LeftMenuBean>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        leftMenuBeans.clear();
                        leftMenuBeans.addAll(s.getData());
                        leftMenuAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopMusic(13);
    }


    @OnClick({R.id.img_back_bg, R.id.btn_refresh, R.id.btn_recharge_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back_bg:
                finish();
                break;
            case R.id.btn_refresh:
                refreshBalance();
                break;
            case R.id.btn_recharge_record:
                showFragment(new ChargeHistoryDialog());
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
                    .add(R.id.fl_user_info, targetFragment)
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


