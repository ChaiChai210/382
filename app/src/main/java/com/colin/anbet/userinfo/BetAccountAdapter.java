package com.colin.anbet.userinfo;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.UserAccountItem;

import java.util.List;

public class BetAccountAdapter extends BaseQuickAdapter<UserAccountItem, BaseViewHolder> {
    public BetAccountAdapter(@Nullable List<UserAccountItem> data) {
        super(R.layout.item_user_bet, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserAccountItem item) {
        helper.setText(R.id.tv_bet_time, item.getCreationTimeStr());
        helper.setText(R.id.tv_bet_num, item.getTradingStatusName());
        helper.setText(R.id.tv_bet_gname, item.getExpenditure());
        helper.setText(R.id.tv_bet_amount, item.getIncome());
        helper.setText(R.id.tv_bet_paijiang, item.getBalance());


    }
}
