package com.colin.anbet.userinfo;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.colin.anbet.R;
import com.colin.anbet.adapter.BetClassifyAdapter;
import com.colin.anbet.adapter.BetTimeAdapter;
import com.colin.anbet.adapter.BetTitleAdapter;
import com.colin.anbet.entity.BetClassifyBean;
import com.colin.anbet.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserReportFragment extends BaseFragment {


    @BindView(R.id.rv_report_tab)
    RecyclerView rvReportTab;
    @BindView(R.id.tv_report_time)
    TextView tvReportTime;
    @BindView(R.id.tv_report_total)
    TextView tvReportTotal;
    @BindView(R.id.tv_total_bets)
    TextView tvTotalBets;
    @BindView(R.id.tv_total_paicai)
    TextView tvTotalPaicai;
    @BindView(R.id.tv_total_fandian)
    TextView tvTotalFandian;
    @BindView(R.id.tv_total_yongjin)
    TextView tvTotalYongjin;
    BetClassifyAdapter mAdapter;
    BetTitleAdapter titleAdapter;
    private List<BetClassifyBean> classifyBeans = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private PopupWindow popupWindow;
    @Override
    protected void init() {
        initData();
        initView();
    }

    private void initView() {
        rvReportTab.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        rvReportTab.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BetClassifyAdapter(classifyBeans);
        rvReportTab.setAdapter(mAdapter);
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
        classifyBeans.add(new BetClassifyBean("棋牌报表", true));
        classifyBeans.add(new BetClassifyBean("电子报表", false));
        classifyBeans.add(new BetClassifyBean("捕鱼报表", false));
        classifyBeans.add(new BetClassifyBean("视讯报表", false));
        classifyBeans.add(new BetClassifyBean("体育报表", false));



    }

    private void getTimePopWindData() {
        List<String> time = new ArrayList<>();
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

        });
        initPopWind(container, tvReportTime);
    }


    private void initPopWind(View paramView, TextView paramTextView) {
        this.popupWindow = new PopupWindow(LayoutInflater.from(this.mContext).inflate(R.layout.item_user_pop, null), -2, -2);
        this.popupWindow.setContentView(paramView);
        this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.popupWindow.setFocusable(true);
        this.popupWindow.setOutsideTouchable(false);
        this.popupWindow.showAsDropDown(paramTextView);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_report;
    }


    @OnClick(R.id.tv_report_time)
    public void onViewClicked() {
        getTimePopWindData();
    }
}
