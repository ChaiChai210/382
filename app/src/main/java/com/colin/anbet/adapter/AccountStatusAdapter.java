package com.colin.anbet.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.TradeStatusBean;

import java.util.List;

public class AccountStatusAdapter
        extends BaseQuickAdapter<TradeStatusBean, BaseViewHolder> {
    public AccountStatusAdapter(@Nullable List<TradeStatusBean> paramList) {
        super(R.layout.item_user_pop, paramList);
    }

    protected void convert(BaseViewHolder vh, TradeStatusBean item) {
//        TextView tv_user_tab = vh.getView(R.id.tv_item_user_bet);
        vh.setText(R.id.tv_user_pop, item.getName());
    }


}

