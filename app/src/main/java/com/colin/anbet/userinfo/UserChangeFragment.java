package com.colin.anbet.userinfo;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import com.colin.anbet.R;
import com.colin.anbet.fragment.BaseFragment;

import butterknife.BindView;

public class UserChangeFragment extends BaseFragment {


    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_digit)
    TextView tvDigit;
    @BindView(R.id.tv_account_out)
    TextView tvAccountOut;
    @BindView(R.id.tv_account1)
    TextView tvAccount1;
    @BindView(R.id.tv_account_in)
    TextView tvAccountIn;
    @BindView(R.id.tv_account2)
    TextView tvAccount2;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_input_account)
    EditText tvInputAccount;
    @BindView(R.id.btn_clear)
    ImageButton btnClear;
    @BindView(R.id.ll_middle)
    LinearLayout llMiddle;
    @BindView(R.id.sb_seekbar)
    SeekBar sbSeekbar;
    @BindView(R.id.img_start)
    ImageView imgStart;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.ll_start)
    LinearLayout llStart;
    @BindView(R.id.img_split)
    ImageView imgSplit;
    @BindView(R.id.tv_half)
    TextView tvHalf;
    @BindView(R.id.ll_half)
    LinearLayout llHalf;
    @BindView(R.id.img_end)
    ImageView imgEnd;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.ll_end)
    LinearLayout llEnd;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    @BindView(R.id.btn_max)
    ImageButton btnMax;
    @BindView(R.id.ll_seekbar)
    LinearLayout llSeekbar;
    @BindView(R.id.ll_change)
    LinearLayout llChange;
    @BindView(R.id.btn_reset_data)
    Button btnResetData;
    @BindView(R.id.btn_submit_money)
    Button btnSubmitMoney;

    @Override
    protected void init() {
        initData();
        initView();
    }


    private void initData() {


    }


    private void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change;

    }


}
