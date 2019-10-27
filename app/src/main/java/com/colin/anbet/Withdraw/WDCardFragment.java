package com.colin.anbet.Withdraw;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.R;
import com.colin.anbet.dialog.BankListDialog;
import com.colin.anbet.entity.BankCardItem;
import com.colin.anbet.base.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.widget.LoadingDialog;
import com.colin.anbet.widget.MultipleStatusView;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class WDCardFragment extends BaseFragment implements BankListDialog.OnBankSelectedListener {
    @BindView(R.id.v_center)
    View vCenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.msv_bank_card)
    MultipleStatusView msvBankCard;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.tv_input_name)
    EditText tvInputName;
    @BindView(R.id.tv_chose_bank)
    TextView tvChoseBank;
    @BindView(R.id.ed_input_chose_bank)
    EditText edInputChoseBank;
    @BindView(R.id.ib_chose)
    ImageButton ibChose;
    @BindView(R.id.tv_card_num)
    TextView tvCardNum;
    @BindView(R.id.ed_card_num)
    EditText edCardNum;
    @BindView(R.id.ed_open_addr)
    EditText edOpenAddr;
    @BindView(R.id.ll_input_msg)
    LinearLayout llInputMsg;
    @BindView(R.id.ib_add_card)
    ImageButton ibAddCard;
    @BindView(R.id.ib_upper)
    ImageButton ibUpper;
    @BindView(R.id.ib_bind)
    ImageButton ibBind;
    private LoadingDialog loadingDialog;

    private static final String TAG = "WDCardFragment";
    private BankCardAdapter bankCardAdapter;
    private List<BankCardItem> bankDataList = new ArrayList<>();

    //    String c;
//    String d;
//    private List<BankCardResult> dataBeans = new ArrayList();
//    int e = -1;
//    RecyclerView f;
//    EditText g;
//    EditText h;
//    String i;
//    private int index = 0;
//    Dialog j;
//    private BankCardPresenter presenter;


    @Override
    protected void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        bankCardAdapter = new BankCardAdapter(bankDataList);
        recyclerView.setAdapter(bankCardAdapter);

        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wd_card;
    }


    private void initData() {
        showLoading();
        RxHttp.get(Url.BANK_LIST)
//                .add("dataStr", finalResult)
                .asParser(new CommonParser<List<BankCardItem>>(new TypeToken<BaseResponseBean<List<BankCardItem>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    hideLoading();
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        bankDataList.clear();
                        bankDataList.addAll(s.getData());
                        bankCardAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    hideLoading();
                    Log.e("chai", throwable.getMessage());
                });
    }

    @OnClick({R.id.ib_add_card, R.id.ib_upper, R.id.ib_bind, R.id.ib_chose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_add_card:
                ibAddCard.setVisibility(View.GONE);
                ibUpper.setVisibility(View.VISIBLE);
                ibBind.setVisibility(View.VISIBLE);

                llInputMsg.setVisibility(View.VISIBLE);
                smartRefreshLayout.setVisibility(View.GONE);

                break;
            case R.id.ib_upper:
                showPageOne();

                break;
            case R.id.ib_bind:
                submitBankInfo();
                break;
            case R.id.ib_chose:
                BankListDialog bankListDialog = new BankListDialog();
                bankListDialog.setSelectedListener(this::onSelected);
                showFragment(bankListDialog);
                break;
        }
    }

    private void showPageOne() {
        llInputMsg.setVisibility(View.GONE);
        smartRefreshLayout.setVisibility(View.VISIBLE);

        ibUpper.setVisibility(View.GONE);
        ibBind.setVisibility(View.GONE);
        ibAddCard.setVisibility(View.VISIBLE);
    }

    private void submitBankInfo() {
        notifyEmpty();
        RxHttp.get(Url.ADD_BANK_CARD)
                .add("userBankName", getEdit(tvInputName))
                //todo 接口
                .add("bankCode", "")
                .add("bankNo", getEdit(edCardNum))
                .add("bankBranch", getEdit(edOpenAddr))
                .add("bankName", getEdit(edInputChoseBank))
                .asParser(new CommonParser<String>(new TypeToken<BaseResponseBean<String>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
//                    hideLoading();
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        showPageOne();
                        initData();
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
//                    hideLoading();
                    Log.e("chai", throwable.getMessage());
                });
    }

    private String getEdit(EditText tv) {
        return tv.getText().toString().trim();
    }

    private void notifyEmpty() {
        if (TextUtils.isEmpty(getEdit(tvInputName))) {
            UIHelper.errorToastString("姓名不能为空");
        }
        if (TextUtils.isEmpty(getEdit(edInputChoseBank))) {
            UIHelper.errorToastString("银行没选");
        }
        if (TextUtils.isEmpty(getEdit(edCardNum))) {
            UIHelper.errorToastString("请输入银行卡号");
        }
        if (TextUtils.isEmpty(getEdit(edOpenAddr))) {
            UIHelper.errorToastString("请输入开户地址");
        }

    }

    @Override
    public void onSelected(String bank) {
        edInputChoseBank.setText(bank);
    }
}
