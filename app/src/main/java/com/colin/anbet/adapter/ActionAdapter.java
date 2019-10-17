package com.colin.anbet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.ActionItem;
import com.colin.anbet.net.Url;

import java.util.List;

public class ActionAdapter extends BaseQuickAdapter<ActionItem, BaseViewHolder> {
    public ActionAdapter(int layoutResId, @Nullable List<ActionItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActionItem item) {
        helper.setText(R.id.tv_act_title, item.getActivityName());
        ImageView content = helper.getView(R.id.iv_act_img);
        String imageUrl = Url.promotionDie + item.getImageName();
        String detailUrl = Url.promotionDie + item.getImageNameDetail();
        Glide.with(mContext).load(imageUrl).into(content);
        ImageView detail = helper.getView(R.id.iv_detail);
        RelativeLayout left = helper.getView(R.id.rl_content);
        left.setOnClickListener(view -> {
            if(detail.getVisibility() == View.GONE){
                detail.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(detailUrl).placeholder(R.drawable.placeholder).into(detail);
            }else {
                detail.setVisibility(View.GONE);
            }
        });
    }
}
