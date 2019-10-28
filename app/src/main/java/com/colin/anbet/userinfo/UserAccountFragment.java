package com.colin.anbet.userinfo;

import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.colin.anbet.R;
import com.colin.anbet.adapter.AccountStatusAdapter;
import com.colin.anbet.adapter.BetTimeAdapter;
import com.colin.anbet.adapter.BetTitleAdapter;
import com.colin.anbet.entity.TradeStatusBean;
import com.colin.anbet.entity.UserAccountItem;
import com.colin.anbet.entity.UserAccountWholeBean;
import com.colin.anbet.base.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.widget.MultipleStatusView;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class UserAccountFragment extends BaseFragment {


    @BindView(R.id.tv_account_time_flag)
    TextView tvAccountTimeFlag;
    @BindView(R.id.tv_account_time)
    TextView tvAccountTime;
    @BindView(R.id.tv_account_type)
    TextView tvAccountType;
    @BindView(R.id.rv_act_title)
    RecyclerView rvActTitle;
    @BindView(R.id.rv_user_act)
    RecyclerView rvUserAct;
    @BindView(R.id.srl_user_act)
    SmartRefreshLayout srlUserAct;
    @BindView(R.id.msv_user_act)
    MultipleStatusView msvUserAct;
    @BindView(R.id.tv_act_zc)
    TextView tvActZc;
    @BindView(R.id.tv_act_yh)
    TextView tvActYh;
    @BindView(R.id.tv_act_tx)
    TextView tvActTx;
    @BindView(R.id.tv_act_fs)
    TextView tvActFs;
    @BindView(R.id.tv_act_ye)
    TextView tvActYe;
    @BindView(R.id.tv_account_yuan)
    TextView tvAccountYuan;
    @BindView(R.id.ll_user_act)
    RelativeLayout llUserAct;

    private BetAccountAdapter betAccountAdapter;
    private BetTitleAdapter titleAdapter;
    private PopupWindow popupWindow;
    private View container;

    private String choseDate = "month";
    private int page = 1;
    private int totalPage = 1;
    private String statusId;
    private Map<String, String> params = new HashMap<>();
    private List<TradeStatusBean> statusBeans = new ArrayList<>();

    private List<UserAccountItem> accountItemList = new ArrayList<>();

    @Override

    protected void init() {
        initData();
        initView();
    }

    private void initView() {
        rvActTitle.setLayoutManager(new StaggeredGridLayoutManager(5, 1));
        titleAdapter = new BetTitleAdapter(getTitles());
        rvActTitle.setAdapter(titleAdapter);

        rvUserAct.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        betAccountAdapter = new BetAccountAdapter(accountItemList);
        rvUserAct.setAdapter(betAccountAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_account;
    }

    private void initData() {
        getTimes();
        getStates();
        getAccountDetail();
    }

    private void getAccountDetail() {
        if (!TextUtils.isEmpty(choseDate)) {
            params.put("dateId", choseDate);
        }
        if (!TextUtils.isEmpty(statusId)) {
            params.put("status", statusId);
        }
        params.put("pageNo", String.valueOf(page));
        RxHttp.get(Url.listAccountDetail)
                .add(params)
                .asObject(UserAccountWholeBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus().equals("1")) {
                        totalPage = Integer.parseInt(s.getData().getTotalPages());
                        if (s.getData().getRows().isEmpty()) {
                            msvUserAct.showEmpty();
                        } else {
                            accountItemList.clear();
                            accountItemList.addAll(s.getData().getRows());
                            msvUserAct.showContent();
                            betAccountAdapter.notifyDataSetChanged();
                        }

                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    private List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("全部时间");
        titles.add("全部状态");
        titles.add("支出");
        titles.add("收入");
        titles.add("余额");
        return titles;
    }

    private List<String> getTimes() {
        List<String> time = new ArrayList<>();
        time.add("所有时间");
        time.add("今天");
        time.add("昨天");
        time.add("一个月内");
        return time;
    }

    private void getStates() {
//        List<String> time = new ArrayList<>();
//        time.add("全部状态");
//        time.add("平台资金切换");
//        time.add("会员充值");
//        time.add("会员取款");
//        time.add("人工存入");
//        time.add("活动优惠");
//        time.add("人工取款");
//        time.add("洗码");
//        time.add("赠送彩金");
//        time.add("领取佣金");
//        time.add("保险箱记录");
//        time.add("结算入款");
//        time.add("红包");
//        return time;
        RxHttp.get(Url.listStatus)
                .add("classId", "交易状态")
                .asParser(new CommonParser<List<TradeStatusBean>>(new TypeToken<BaseResponseBean<List<TradeStatusBean>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (!s.getData().isEmpty()) {
                            statusBeans.clear();
                            statusBeans.addAll(s.getData());

                        }
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }
                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });

    }

    private void getTimePopWindData(List<String> data) {
        container = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_short_pop, null);
        RecyclerView localRecyclerView = container.findViewById(R.id.rv_pop);
        localRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, RecyclerView.VERTICAL, false));
        BetTimeAdapter adapter = new BetTimeAdapter(data);
        localRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((aadapter, view, position) -> {
            popupWindow.dismiss();
            if (position == 0 || position == 3) {
                choseDate = "month";
            } else if (position == 1) {
                choseDate = "day";
            } else if (position == 2) {
                choseDate = "yesterday";
            }
            getAccountDetail();
        });
        initPopWind(container, tvAccountTime);
    }

    private void getTypePopWindData(List<TradeStatusBean> data) {
        container = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_short_pop, null);
        RecyclerView localRecyclerView = container.findViewById(R.id.rv_pop);
        localRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, RecyclerView.VERTICAL, false));
        AccountStatusAdapter adapter = new AccountStatusAdapter(data);
        localRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((aadapter, view, position) -> {
            popupWindow.dismiss();
            tvAccountType.setText(statusBeans.get(position).getName());
            statusId = statusBeans.get(position).getId();
            getAccountDetail();
        });
        initPopWind(container, tvAccountType);
    }

    private void initPopWind(View paramView, TextView paramTextView) {
        this.popupWindow = new PopupWindow(LayoutInflater.from(this.mContext).inflate(R.layout.item_user_pop, null), -2, -2);
        this.popupWindow.setContentView(paramView);
        this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.popupWindow.setFocusable(true);
        this.popupWindow.setOutsideTouchable(false);
        this.popupWindow.showAsDropDown(paramTextView);
    }

    @OnClick({R.id.tv_account_time, R.id.tv_account_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //todo 用spinner做
            case R.id.tv_account_time:
                getTimePopWindData(getTimes());
                break;
            case R.id.tv_account_type:
                if (statusBeans.isEmpty()) {
                    UIHelper.errorToastString("当前没有可选择");
                } else {
                    getTypePopWindData(statusBeans);
                }
                break;
        }
    }
}
