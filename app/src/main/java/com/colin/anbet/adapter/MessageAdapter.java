package com.colin.anbet.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.MessageItem;

import java.util.List;

public class MessageAdapter
        extends BaseQuickAdapter<MessageItem, BaseViewHolder> {
    public MessageAdapter(@Nullable List<MessageItem> paramList) {
        super(R.layout.item_message, paramList);
    }

    protected void convert(BaseViewHolder vh, MessageItem item) {


        vh.setText(R.id.tv_title, item.getMsgTypeName());
        vh.setText(R.id.tv_content, item.getMsgContent());

    }
}


