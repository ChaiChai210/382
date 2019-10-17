package com.colin.anbet.userinfo;

import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.MyApp;
import com.colin.anbet.R;
import com.colin.anbet.entity.PlatformAccountBean;
import com.colin.anbet.fragment.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.Utils;
import com.colin.anbet.widget.MultipleStatusView;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class UserPlatformFragment extends BaseFragment {


    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_digit)
    TextView tvDigit;
    @BindView(R.id.tv_exchange_account)
    TextView tvExchangeAccount;
    @BindView(R.id.tv_platform_search)
    TextView tvPlatformSearch;
    @BindView(R.id.rv_user_bet)
    RecyclerView rvUserBet;
    @BindView(R.id.srl_user_bet)
    SmartRefreshLayout srlUserBet;
    @BindView(R.id.msv_user_bet)
    MultipleStatusView msvUserBet;

    private UserPlatformAdapter userPlatformAdapter;

    private List<PlatformAccountBean> platformAccountBeans = new ArrayList<>();

    @Override
    protected void init() {
        initData();
        initView();
    }


    private void initData() {

        RxHttp.get(Url.getAllGameBalance)
//                .add("dataStr", finalResult)
                .asParser(new CommonParser<List<PlatformAccountBean>>(new TypeToken<BaseResponseBean<List<PlatformAccountBean>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if(s.getData().isEmpty()){
                            msvUserBet.showEmpty();
                        }else {
                            platformAccountBeans.clear();
                            platformAccountBeans.addAll(s.getData());
                            msvUserBet.showContent();
                            userPlatformAdapter.notifyDataSetChanged();
                        }

                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    hideLoading();
                    Log.e("chai", throwable.getMessage());
                });
    }


    private void initView() {
        tvDigit.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));
        rvUserBet.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        rvUserBet.setItemAnimator(new DefaultItemAnimator());
        userPlatformAdapter = new UserPlatformAdapter(platformAccountBeans);
        rvUserBet.setAdapter(userPlatformAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_platform_account;

    }


    @OnClick(R.id.tv_exchange_account)
    public void onViewClicked() {
    }
}
