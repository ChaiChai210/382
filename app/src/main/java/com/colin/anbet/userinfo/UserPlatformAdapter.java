package com.colin.anbet.userinfo;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.PlatformAccountBean;

import java.util.List;

public class UserPlatformAdapter extends BaseQuickAdapter<PlatformAccountBean, BaseViewHolder> {
    public UserPlatformAdapter(@Nullable List<PlatformAccountBean> data) {
        super(R.layout.item_user_platform, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlatformAccountBean item) {
        helper.setText(R.id.tv_plat_name,item.getName());
        helper.setText(R.id.tv_bet_num,item.getMoney());


    }
}
