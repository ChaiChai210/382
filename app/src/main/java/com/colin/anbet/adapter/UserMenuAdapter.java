package com.colin.anbet.adapter;

import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.UserMenuBean;

import java.util.List;

public class UserMenuAdapter
        extends BaseQuickAdapter<UserMenuBean, BaseViewHolder> {
    public UserMenuAdapter(@Nullable List<UserMenuBean> paramList) {
        super(R.layout.item_game_categor, paramList);
    }

    protected void convert(BaseViewHolder vh, UserMenuBean item) {
        ImageButton imageButton = vh.getView(R.id.image_button);
        int pos = item.getPosition();
        if (0 == pos) {
            imageButton.setImageResource(R.drawable.pc_grxx_select);
        } else if (1 == pos) {
            imageButton.setImageResource(R.drawable.pc_tzjl_select);
        } else if (2 == pos) {
            imageButton.setImageResource(R.drawable.pc_zhmx_select);
        } else if (3 == pos) {
            imageButton.setImageResource(R.drawable.pc_grbb_select);
        } else if (4 == pos) {
            imageButton.setImageResource(R.drawable.pc_vipxq_select);
        } else if (5 == pos) {
            imageButton.setImageResource(R.drawable.pc_change_select);
        } else if (6 == pos) {
            imageButton.setImageResource(R.drawable.pc_platform_select);
        }


        imageButton.setSelected(item.isSelected());
        imageButton.setTag(item.isSelected());

    }


}

