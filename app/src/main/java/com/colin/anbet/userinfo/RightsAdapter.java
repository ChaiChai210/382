package com.colin.anbet.userinfo;

import android.text.Html;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.VipList;
import com.colin.anbet.util.Utils;

import java.util.List;

public class RightsAdapter extends BaseQuickAdapter<VipList, BaseViewHolder> {
    public RightsAdapter(@Nullable List<VipList> data) {
        super(R.layout.item_userinfo_rights, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipList item) {
        helper.setText(R.id.tv_money1, Html.fromHtml(Utils.double2Decimal(Double.parseDouble(item.getPromotionGift()))));
        helper.setText(R.id.tv_money2, Html.fromHtml(Utils.double2Decimal(Double.parseDouble(item.getWeekBonus()))));
        helper.setText(R.id.tv_money3, Html.fromHtml(Utils.double2Decimal(Double.parseDouble(item.getMonthGiftMoney()))));
        helper.setText(R.id.tv_money4, Html.fromHtml(Utils.double2Decimal(Double.parseDouble(item.getWashingRatio()))));

        ImageButton ib_rights1 = helper.getView(R.id.ib_rights1);
        ImageButton ib_rights2 = helper.getView(R.id.ib_rights2);
        ImageButton ib_rights3 = helper.getView(R.id.ib_rights3);
        ImageButton ib_rights4 = helper.getView(R.id.ib_rights4);
//
//        String str3 = item.getStatus();
//        String str1 = item.getWeekStatus();
//        String str2 = item.getMonthStatus();
//        //todo 該類型設計有待改進
//
//        if ("1".equals(str3)) {
//            ib_rights1.setImageResource(R.drawable.had_received);
//        } else {
//            if ("0".equals(str3) && f4 > 0.0F) {
//                ib_rights1.setImageResource(R.drawable.now_receive);
//                ib_rights1.setEnabled(true);
//            }
//            ib_rights1.setImageResource(R.drawable.cannot_received);
//        }


    }
}
