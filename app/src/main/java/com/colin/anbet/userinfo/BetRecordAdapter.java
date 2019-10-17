package com.colin.anbet.userinfo;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.UserBetItem;

import java.util.List;

public class BetRecordAdapter extends BaseQuickAdapter<UserBetItem, BaseViewHolder> {
    public BetRecordAdapter(@Nullable List<UserBetItem> data) {
        super(R.layout.item_user_bet, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBetItem item) {
        helper.setText(R.id.tv_bet_time,item.getCreationTimeStr());
        helper.setText(R.id.tv_bet_num,item.getOrderNo());
        helper.setText(R.id.tv_bet_gname,item.getGameTypeName());
        helper.setText(R.id.tv_bet_amount,item.getBetAmount());
        helper.setText(R.id.tv_bet_paijiang,item.getRevenue());


    }
}
