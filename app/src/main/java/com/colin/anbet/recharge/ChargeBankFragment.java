package com.colin.anbet.recharge;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.colin.anbet.R;
import com.colin.anbet.fragment.BaseFragment;
import com.colin.anbet.widget.RecycleViewDivider;

import butterknife.BindView;

public class ChargeBankFragment extends BaseFragment {


    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_gathering_bank)
    ImageView ivGatheringBank;
    @BindView(R.id.tv_gathering_bank)
    TextView tvGatheringBank;
    @BindView(R.id.iv_bank_icons)
    ImageView ivBankIcons;
    @BindView(R.id.tv_input_name)
    TextView tvInputName;
    @BindView(R.id.ib_copy_bank)
    ImageButton ibCopyBank;
    @BindView(R.id.v_line1)
    View vLine1;
    @BindView(R.id.tv_payee_name)
    TextView tvPayeeName;
    @BindView(R.id.tv_input_payee_name)
    TextView tvInputPayeeName;
    @BindView(R.id.ib_copy_payee_name)
    ImageButton ibCopyPayeeName;
    @BindView(R.id.v_line2)
    View vLine2;
    @BindView(R.id.tv_payee_account)
    TextView tvPayeeAccount;
    @BindView(R.id.tv_input_payee_account)
    TextView tvInputPayeeAccount;
    @BindView(R.id.ib_copy_payee_account)
    ImageButton ibCopyPayeeAccount;
    @BindView(R.id.v_line3)
    View vLine3;
    @BindView(R.id.tv_open_addr)
    TextView tvOpenAddr;
    @BindView(R.id.tv_input_open_addr)
    TextView tvInputOpenAddr;
    @BindView(R.id.ib_copy_open_addr)
    ImageButton ibCopyOpenAddr;
    @BindView(R.id.v_line4)
    View vLine4;
    @BindView(R.id.rl_gathering)
    RelativeLayout rlGathering;
    @BindView(R.id.v_center)
    View vCenter;
    @BindView(R.id.iv_deposit_info)
    ImageView ivDepositInfo;
    @BindView(R.id.tv_deposit_amount)
    TextView tvDepositAmount;
    @BindView(R.id.ed_input_deposit_amount)
    EditText edInputDepositAmount;
    @BindView(R.id.tv_deposit_info)
    TextView tvDepositInfo;
    @BindView(R.id.ed_input_deposit_info)
    EditText edInputDepositInfo;
    @BindView(R.id.tv_tips_title)
    TextView tvTipsTitle;
    @BindView(R.id.tv_tips_content)
    TextView tvTipsContent;
    @BindView(R.id.rl_deposit)
    RelativeLayout rlDeposit;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.ib_go)
    ImageButton ibGo;
    @BindView(R.id.rl_other_content)
    RelativeLayout rlOtherContent;


    @Override
    protected void init() {
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, 1,
                (int) mContext.getResources().getDimension(R.dimen.dp_12), ContextCompat.getColor(mContext, R.color.transparent)));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_net_bank_charge;
    }

}
