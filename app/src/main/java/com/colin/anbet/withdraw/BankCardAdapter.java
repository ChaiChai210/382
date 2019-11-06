package com.colin.anbet.withdraw;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.BankCardItem;

import java.util.List;

public class BankCardAdapter
        extends BaseQuickAdapter<BankCardItem, BaseViewHolder> {
    BankCardAdapter(@Nullable List<BankCardItem> paramList) {
        super(R.layout.item_bank_card, paramList);
    }

    protected void convert(BaseViewHolder vh, BankCardItem item) {
        ImageView header = vh.getView(R.id.iv_ban_icon);
        Glide.with(this.mContext).load(item.getBankIcon()).into(header);


        vh.setText(R.id.tv_bank_name, item.getBankName());
        vh.setText(R.id.tv_bankNo, item.getBankNo());
    }
}


