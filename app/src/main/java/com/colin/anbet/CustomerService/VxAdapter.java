package com.colin.anbet.CustomerService;

import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.CustomerServiceList;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.AppUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.UrlHelper;
import com.colin.anbet.util.Utils;

import java.util.List;

public class VxAdapter extends BaseQuickAdapter<CustomerServiceList, BaseViewHolder> {

    public VxAdapter(@Nullable List<CustomerServiceList> data) {
        super(R.layout.item_cus_vx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerServiceList item) {

        ImageView imageView = helper.getView(R.id.home_read_piv_iv);
        String imgurl = Url.customerServiceDir + item.getCustomerHead();
        Glide.with(mContext).load(imgurl).circleCrop().into(imageView);
        helper.setText(R.id.tv_account, item.getCustomerName());
        helper.setText(R.id.tv_vx, new StringBuilder("微信客服").append(helper.getAdapterPosition() + 1));
        helper.setText(R.id.tv_name, item.getCustomerContent());
        ImageButton ib = helper.getView(R.id.btn_vx_kefu);
        ib.setOnClickListener(view -> {
//            AppUtil.getWechatApi(mContext);
            Utils.copyToClipboard(mContext,  item.getCustomerContent());
            UIHelper.copySuccess("复制成功");
            AppUtil.startAPP(mContext, "com.tencent.mm");
        });
    }
}
