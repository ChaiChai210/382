package com.colin.anbet.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;

import java.util.List;

public class BetTitleAdapter
        extends BaseQuickAdapter<String, BaseViewHolder> {
    public BetTitleAdapter(@Nullable List<String> paramList) {
        super(R.layout.item_user_bet_title, paramList);
    }

    protected void convert(BaseViewHolder vh, String item) {
//        TextView tv_user_tab = vh.getView(R.id.tv_item_user_bet);
        vh.setText(R.id.tv_item_user_bet, item);
    }


}

