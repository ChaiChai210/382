package com.colin.anbet.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.BetClassifyBean;

import java.util.List;

public class BetClassifyAdapter
        extends BaseQuickAdapter<BetClassifyBean, BaseViewHolder> {
    public BetClassifyAdapter(@Nullable List<BetClassifyBean> paramList) {
        super(R.layout.item_user_tab, paramList);
    }

    protected void convert(BaseViewHolder vh, BetClassifyBean item) {
        TextView tv_user_tab = vh.getView(R.id.tv_user_tab);
        vh.setText(R.id.tv_user_tab, item.getName());
        tv_user_tab.setSelected(item.isSelected());
        vh.itemView.setTag(item.isSelected());
    }


}

