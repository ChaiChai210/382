package com.colin.anbet.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.GameList;

import java.util.List;

public class BetPlatformAdapter
        extends BaseQuickAdapter<GameList, BaseViewHolder> {
    public BetPlatformAdapter(@Nullable List<GameList> paramList) {
        super(R.layout.item_user_pop, paramList);
    }

    protected void convert(BaseViewHolder vh, GameList item) {
//        TextView tv_user_tab = vh.getView(R.id.tv_item_user_bet);
        vh.setText(R.id.tv_user_pop, item.getName());
    }


}

