package com.colin.anbet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colin.anbet.R;

import butterknife.BindView;
import butterknife.OnClick;

public class GetCommissionDialog extends BaseDialogFragment {


    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_count)
    TextView tvMoneyCount;
    @BindView(R.id.tv_leftAmount)
    TextView tvLeftAmount;
    @BindView(R.id.tv_leftAmount_count)
    TextView tvLeftAmountCount;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yang)
    TextView tvYang;
    @BindView(R.id.et_input_money)
    EditText edInputMoney;
    @BindView(R.id.tv_all_out)
    TextView tvAllOut;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.ib_clear)
    ImageButton ibClear;
    @BindView(R.id.rl_money)
    RelativeLayout rlMoney;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_getCommission)
    TextView tvGetCommission;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_get_money, container);
        return view;
    }


    @OnClick({R.id.btn_close, R.id.ib_clear, R.id.tv_getCommission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.ib_clear:
                break;
            case R.id.tv_getCommission:
                break;
        }
    }

    @Override
    protected void init() {

    }
}
