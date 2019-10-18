package com.colin.anbet.CustomerService;

import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.CustomerServiceList;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.AppUtil;

import java.util.List;

public class QqAdapter extends BaseQuickAdapter<CustomerServiceList, BaseViewHolder> {

    public QqAdapter(@Nullable List<CustomerServiceList> data) {
        super(R.layout.item_cus, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerServiceList item) {

        ImageView imageView = helper.getView(R.id.home_read_piv_iv);
        String imgurl = Url.customerServiceDir + item.getCustomerHead();
        Glide.with(mContext).load(imgurl).circleCrop().into(imageView);
        helper.setText(R.id.tv_account, item.getCustomerContent());
        helper.setText(R.id.tv_name, item.getCustomerName());
        ImageButton ib = helper.getView(R.id.btn_lianxi_kefu);
        ib.setOnClickListener(view -> {
//            AppUtil.getWechatApi(mContext);
            AppUtil.luncherQQ(mContext, item.getCustomerContent());
        });


    }
}
