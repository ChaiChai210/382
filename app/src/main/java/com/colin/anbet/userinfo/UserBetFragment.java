package com.colin.anbet.userinfo;

import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.colin.anbet.R;
import com.colin.anbet.adapter.BetClassifyAdapter;
import com.colin.anbet.adapter.BetPlatformAdapter;
import com.colin.anbet.adapter.BetTimeAdapter;
import com.colin.anbet.adapter.BetTitleAdapter;
import com.colin.anbet.entity.BetClassifyBean;
import com.colin.anbet.entity.GameList;
import com.colin.anbet.entity.UserBetItem;
import com.colin.anbet.entity.UserBetWholeBean;
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

public class UserBetFragment extends BaseFragment {
    @BindView(R.id.rv_bet_tab)
    RecyclerView rvBetTab;
    @BindView(R.id.tv_bet_time_flag)
    TextView tvBetTimeFlag;
    @BindView(R.id.tv_bet_report_time)
    TextView tvBetReportTime;
    @BindView(R.id.tv_bet_report_platform)
    TextView tvBetReportPlatform;
    @BindView(R.id.rv_bet_tab2)
    RecyclerView rvBetTab2;
    @BindView(R.id.rv_user_bet)
    RecyclerView rvUserBet;
    @BindView(R.id.srl_user_bet)
    SmartRefreshLayout srlUserBet;
    @BindView(R.id.msv_user_bet)
    MultipleStatusView msvUserBet;
    BetClassifyAdapter mAdapter;
    BetRecordAdapter betRecordAdapter;
    BetTitleAdapter titleAdapter;
    private List<BetClassifyBean> classifyBeans = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private PopupWindow popupWindow;

    private Map<String, String> params = new HashMap<>();
    private int parentId = 2; //2是棋牌，3是捕鱼，1是彩票，4是视讯
    private String choseDate = "month";
    private String gameCode = "";
    private int page = 1;
    private int totalPage = 1;

    private List<UserBetItem> records = new ArrayList<>();

    @Override
    protected void init() {
        initData();
        initView();
        initLitneer();
        getBetRecord();
    }

    private void initLitneer() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            int size = classifyBeans.size();

            if (!(Boolean) view.getTag()) {
                for (int i = 0; i < size; i++) {
                    if (i == position) {
                        classifyBeans.get(i).setSelected(true);
                    } else {
                        classifyBeans.get(i).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        classifyBeans.clear();
        classifyBeans.add(new BetClassifyBean("棋牌投注记录", true));
        classifyBeans.add(new BetClassifyBean("视讯投注记录", false));
        classifyBeans.add(new BetClassifyBean("体育投注记录", false));
        classifyBeans.add(new BetClassifyBean("电子投注记录", false));
        classifyBeans.add(new BetClassifyBean("捕鱼投注记录", false));
        titles.add("投注时间");
        titles.add("注单号");
        titles.add("游戏名称");
        titles.add("投注金额");
        titles.add("已派奖");


    }

    private void getTimePopWindData() {
        List<String> time = new ArrayList<>();
        time.add("所有时间");
        time.add("今天");
        time.add("昨天");
        time.add("一个月内");
        View container = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_short_pop, null);
        RecyclerView localRecyclerView = container.findViewById(R.id.rv_pop);
        localRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, RecyclerView.VERTICAL, false));
        BetTimeAdapter adapter = new BetTimeAdapter(time);
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
            getBetRecord();
        });
        initPopWind(container, tvBetReportTime);
    }

    private void initView() {
        rvBetTab.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        rvBetTab.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BetClassifyAdapter(classifyBeans);
        rvBetTab.setAdapter(mAdapter);

        rvBetTab2.setLayoutManager(new StaggeredGridLayoutManager(5, 1));
        titleAdapter = new BetTitleAdapter(titles);
        rvBetTab2.setAdapter(titleAdapter);


        rvUserBet.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        rvUserBet.setItemAnimator(new DefaultItemAnimator());
        betRecordAdapter = new BetRecordAdapter(records);
        rvUserBet.setAdapter(betRecordAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_bet;

    }

    private void initPopWind(View paramView, TextView paramTextView) {
        this.popupWindow = new PopupWindow(LayoutInflater.from(this.mContext).inflate(R.layout.item_user_pop, null), -2, -2);
        this.popupWindow.setContentView(paramView);
        this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.popupWindow.setFocusable(true);
        this.popupWindow.setOutsideTouchable(false);
        this.popupWindow.showAsDropDown(paramTextView);
    }

    @OnClick({R.id.tv_bet_report_time, R.id.tv_bet_report_platform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bet_report_time:
                getTimePopWindData();
                break;
            case R.id.tv_bet_report_platform:
                getPlatformList(parentId);
                break;
        }
    }

    private void getPlatformList(int parentId) {
        RxHttp.get(Url.GAME_SECOND)
                .add("parentId", parentId)
                .asParser(new CommonParser<List<GameList>>(new TypeToken<BaseResponseBean<List<GameList>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (s.getData().isEmpty()) {
                            UIHelper.errorToastString("暂无数据");
                        } else {
                            showPlatform(s.getData());
                        }
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    private void getBetRecord() {
        params.put("gamingTypeId", String.valueOf(parentId));
        if (!TextUtils.isEmpty(choseDate)) {
            params.put("dateId", choseDate);
        }
        if (!TextUtils.isEmpty(gameCode)) {
            params.put("gameCode", gameCode);
        }
        params.put("pageNo", String.valueOf(page));
        RxHttp.get(Url.listBetRecord)
                .add(params)
                .asObject(UserBetWholeBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus().equals("1")) {
                        totalPage = Integer.parseInt(s.getData().getTotalPages());
                        if (s.getData().getRows().isEmpty()) {
                            msvUserBet.showEmpty();
                        } else {
                            records.clear();
                            records.addAll(s.getData().getRows());
                            msvUserBet.showContent();
                            betRecordAdapter.notifyDataSetChanged();
                        }

                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    private void showPlatform(List<GameList> data) {
        View container = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_short_pop, null);
        RecyclerView localRecyclerView = container.findViewById(R.id.rv_pop);
        localRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, RecyclerView.VERTICAL, false));
        BetPlatformAdapter adapter = new BetPlatformAdapter(data);
        localRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((aadapter, view, position) -> {
            popupWindow.dismiss();
            gameCode = data.get(position).getCode();
            getBetRecord();
        });
        initPopWind(container, tvBetReportPlatform);
    }


}
