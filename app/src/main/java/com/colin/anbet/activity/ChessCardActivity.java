package com.colin.anbet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.base.BaseActivity;
import com.colin.anbet.R;
import com.colin.anbet.adapter.HotGameAdapter;
import com.colin.anbet.entity.HotGameList;
import com.colin.anbet.entity.UserMenuBean;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.AppUtil;
import com.colin.anbet.util.Constants;
import com.colin.anbet.util.UIHelper;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class ChessCardActivity
        extends BaseActivity {


    @BindView(R.id.img_back_bg)
    ImageView imgBackBg;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.btn_xsj)
    RadioButton btnXsj;
    @BindView(R.id.btn_qly)
    RadioButton btnQly;
    @BindView(R.id.btn_ky)
    RadioButton btnKy;
    @BindView(R.id.btn_lucky)
    RadioButton btnLucky;
    @BindView(R.id.btn_wz)
    RadioButton btnWz;
    @BindView(R.id.rg_recharge)
    RadioGroup rgRecharge;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    UserBetFragment betFrg = new UserBetFragment();
//    UserAccountFragment accoutFrg = new UserAccountFragment();
//    UserReportFragment reportFrg = new UserReportFragment();
//    UserVipFragment vipFrg = new UserVipFragment();
//    UserPlatformFragment platformFragment = new UserPlatformFragment();

    //    private Fragment currentFragment = userInfoFrg;
    private HotGameAdapter mHotGameAdapter;
    private List<UserMenuBean> menus = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    String liveCode;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_qipai;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        liveCode = intent.getStringExtra(Constants.Game);
        playMusic(9, volume);
        //加载loading界面。
        imgTitle.setImageResource(R.drawable.ic_chess_card);
//        getSupportFragmentManager().beginTransaction().add(R.id.fl_user_info, userInfoFrg).commit();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }


    private void getHot() {
        RxHttp.get(Url.kyGameList)
                .add("liveCode", liveCode)
                .asParser(new CommonParser<List<HotGameList>>(new TypeToken<BaseResponseBean<List<HotGameList>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    if (s.getStatus() == 1) {
                        if (s.getData().isEmpty()) {
                        } else {
                            mHotGameAdapter = new HotGameAdapter(s.getData());
                            mHotGameAdapter.setOnItemClickListener((adapter, view, position) -> {
                                AppUtil.gotoWebView(ChessCardActivity.this, mHotGameAdapter.getItem(position).getLiveCode(), mHotGameAdapter.getItem(position).getGameType());
                            });
                            recyclerView.setAdapter(mHotGameAdapter);
                        }
                    } else {
                        UIHelper.errorToastString(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic(9);
    }



    @OnClick({R.id.img_back_bg, R.id.btn_xsj, R.id.btn_qly, R.id.btn_ky, R.id.btn_lucky, R.id.btn_wz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back_bg:
                finish();
                break;
            case R.id.btn_xsj:
                break;
            case R.id.btn_qly:
                break;
            case R.id.btn_ky:
                getHot();
                break;
            case R.id.btn_lucky:
                break;
            case R.id.btn_wz:
                break;
        }
    }
}


