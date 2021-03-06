package com.colin.anbet.promotion;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.bean383.MyPerformanceBean;

import java.util.List;

public class MyPerformanceAdapter
        extends BaseQuickAdapter<MyPerformanceBean.ResultBean, BaseViewHolder> {
    MyPerformanceAdapter(@NonNull List<MyPerformanceBean.ResultBean> paramList) {
        super(R.layout.item_performance, paramList);
    }

    protected void convert(BaseViewHolder vh, MyPerformanceBean.ResultBean item) {
        LinearLayout localLinearLayout = vh.getView(R.id.ll_item);
        String str;
        if (vh.getLayoutPosition() % 2 == 1) {
            str = "#1d1c1e";
        } else {
            str = "#201f21";
        }
        localLinearLayout.setBackgroundColor(Color.parseColor(str));

        if (!TextUtils.isEmpty(item.getBatch())) {
            vh.setText(R.id.tv_date, item.getBatch());
        }
        vh.setText(R.id.tv_directly_new, String.valueOf(item.getChildNodeNewNum()));
        if (!TextUtils.isEmpty(item.getOwnerPerformance())) {
            vh.setText(R.id.tv_directly_performance, item.getOwnerPerformance());
        }
        vh.setText(R.id.tv_team_new, String.valueOf(item.getTeamNewNum()));
        if (!TextUtils.isEmpty(item.getTeamPerformance())) {
            vh.setText(R.id.tv_team_performance, item.getTeamPerformance());
        }
        if (!TextUtils.isEmpty(item.getAllCommission())) {
            vh.setText(R.id.tv_personal_money, item.getSelfCommission());
        }
    }
}


