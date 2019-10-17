package com.colin.anbet.Withdraw;

import android.app.Dialog;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colin.anbet.MyApp;
import com.colin.anbet.R;
import com.colin.anbet.entity.BankCardItem;
import com.colin.anbet.entity.WithdrawalRange;
import com.colin.anbet.fragment.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.LoginBean;
import com.colin.anbet.net.Url;
import com.colin.anbet.net.WithdrawResult;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.Utils;
import com.colin.anbet.widget.LoadingDialog;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedImageView;
import com.rxjava.rxlife.RxLife;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class WDBankFragment extends BaseFragment {
    private static final String TAG = "WDBankFragment";
    //    private DrawHistoryAdapter adapter;
    int minNum = 0;
    int maxNum = 0;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_yang)
    TextView tvYang;
    @BindView(R.id.tv_leftAmount)
    TextView tvLeftAmount;
    @BindView(R.id.tv_yuan)
    TextView tvYuan;
    @BindView(R.id.tv_tixiam_history)
    TextView tvTixiamHistory;
    @BindView(R.id.et_input_money)
    EditText edInputMoney;
    @BindView(R.id.ib_clear)
    ImageButton ibClear;
    @BindView(R.id.ll_input_area)
    LinearLayout llInputArea;
    @BindView(R.id.iv_union)
    ImageView ivUnion;
    @BindView(R.id.iv_to_bank)
    ImageView ivToBank;
    @BindView(R.id.ib_bind_bank)
    ImageButton ibBindBank;
    @BindView(R.id.rl_unbind)
    RelativeLayout rlUnbind;
    @BindView(R.id.img_bank_bg)
    RoundedImageView imgBankBg;
    @BindView(R.id.iv_ban_icon)
    ImageView ivBanIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bankNo)
    TextView tvBankNo;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.rl_bind)
    RelativeLayout rlBind;
    @BindView(R.id.tv_reminds)
    TextView tvReminds;
    @BindView(R.id.ib_ok)
    ImageButton ibOk;

    Dialog dialog;
    //    private BankAdapter bankAdapter;
    private int bankPostion = -1;
    private int bankcardId = -1;
    private int pageNum = 1;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    private int totalPage;
    private LoadingDialog loadingDialog;

    private List<BankCardItem> bankDataList = new ArrayList<>();
    private BankCardItem bankCardItem;
    private BankCardItem selectedBank;
    private List<String> mBankList = new ArrayList<>();

    @Override
    protected void init() {
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wd_bank;
    }

    private void initData() {
//        getRange();
        tvLeftAmount.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));
        getBankCardList();
    }

    private void getBankCardList() {
        RxHttp.get(Url.BANK_LIST)
                .asParser(new CommonParser<List<BankCardItem>>(new TypeToken<BaseResponseBean<List<BankCardItem>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        bankDataList.clear();
                        bankDataList.addAll(s.getData());
                        setUI(s.getData());

                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });

    }

    private void setUI(List<BankCardItem> data) {
        if (data.isEmpty()) {
            rlBind.setVisibility(View.GONE);
            rlUnbind.setVisibility(View.VISIBLE);
        } else {
            rlBind.setVisibility(View.VISIBLE);
            rlUnbind.setVisibility(View.GONE);
            bankCardItem = getdefault(data);
            if (bankCardItem != null) {
                tvBankName.setText(bankCardItem.getBankName());
                tvBankNo.setText(bankCardItem.getBankNo());
            }
        }
    }

    private BankCardItem getdefault(List<BankCardItem> bankDataList) {
        BankCardItem bankCardItem = null;
        for (BankCardItem item : bankDataList) {
            if (item.getIsDefault().equals("1")) {
                bankCardItem = item;
                break;
            }
        }
        return bankCardItem;
    }


    private void getRange() {
        //todo 请求
        WithdrawalRange range = null;
        if (null != range) {
            minNum = range.getMinWithdraw();
            maxNum = range.getMaxWithdraw();
        }
        String paramWithdrawalRange;
        if (maxNum == 0) {
            paramWithdrawalRange = String.format("请输入您的提现金额 单笔最低%1$s", new Object[]{Integer.valueOf(minNum)});
        } else {
            paramWithdrawalRange = String.format("请输入您的提现金额 单笔最低%1$s 最高%2$d", new Object[]{Integer.valueOf(minNum), Integer.valueOf(maxNum)});
        }
        Utils.setEditTextHintSize(this.edInputMoney, paramWithdrawalRange, 10);
    }


    private void initView(View view) {
//        View loading  = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_loading, null);
//        this.loadingDialog = new LoadingDialog((Context) Objects.requireNonNull(getContext()), R.style.MobileDialog);
//        this.loadingDialog.initDialog(loading);
//        loadingDialog.showDialog();
        tvLeftAmount.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));
        Utils.setEditTextHintSize(this.edInputMoney, "请输入您的提现金额", 10);
    }

    @OnClick({R.id.tv_tixiam_history, R.id.ib_bind_bank, R.id.rl_unbind, R.id.rl_bind, R.id.ib_ok, R.id.ib_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_clear:
                edInputMoney.getText().clear();
                break;
            case R.id.tv_tixiam_history:
                DrawingHistoryDialog fm2 = new DrawingHistoryDialog();
                fm2.show(getFragmentManager(), "drawing_history");
                break;
            case R.id.ib_bind_bank:
                break;
            case R.id.rl_unbind:

                break;
            case R.id.rl_bind:
                showBankListDialog();
                break;
            case R.id.ib_ok:
                String str = edInputMoney.getText().toString().trim();
                if (bankCardItem == null) {
                    UIHelper.errorToastString("请绑定银行卡！");
                    return;
                }
                if (TextUtils.isEmpty(str)) {
                    UIHelper.errorToastString("请输入提现金额！");
                    return;
                }
                double withdrawMoney = Double.parseDouble(str);
                double balance = MyApp.getInstance().getBalance();
                if (withdrawMoney > balance) {
                    UIHelper.errorToastString("账户余额不足！");
                    return;
                }
//                if (withdrawMoney < minNum) {
//                    UIHelper.errorToastString(String.format("提现金额必须大于%s元！", minNum));
//                    return;
//                }
//                if (withdrawMoney > maxNum) {
//                    UIHelper.errorToastString(String.format("提现金额必须小于%s元！", maxNum));
//                    return;
//                }
                if (balance - withdrawMoney < 1.0D) {
                    ToastUtil.getInstance().showToast("提现时余额至少必须留1元");
                    return;
                }
                edInputMoney.getText().clear();
                JSONObject jo = new JSONObject();
                //wangwu w12345678
                LoginBean bean = MyApp.getInstance().getLoginBean();
                if (bean == null) {
                    UIHelper.errorToastString("请先登录");
                }
                try {
                    jo.put("memberId", bean.getMemberId());
                    jo.put("bankName", bankCardItem.getBankName());
                    jo.put("bankNo", bankCardItem.getBankNo());
                    jo.put("recordAmount", withdrawMoney);
                    jo.put("userBankName", bankCardItem.getUserBankName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String result = "";
                try {
                    result = URLEncoder.encode(jo.toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String finalResult = Base64.encodeToString(result.getBytes(Charset.forName("UTF-8")), Base64.DEFAULT);
                Log.e("chia", finalResult);
                submit(finalResult);

                //      loginOutGame(MyApp.getInstance().getToken(), str);
                break;
        }
    }

    int choosePos = 0;

    private void showBankListDialog() {

        dialog = new Dialog(mContext, R.style.MobileDialog);
        dialog.setContentView(R.layout.dialog_chose_bank);
        dialog.getWindow().setFlags(1024, 1024);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.height = -2;
        attributes.width = -1;
        dialog.getWindow().setAttributes(attributes);
        ImageButton close = dialog.findViewById(R.id.btn_close);
        close.setOnClickListener(view -> dialog.dismiss());
        WheelView wheelView = dialog.findViewById(R.id.wheelView);
        wheelView.setCyclic(false);
        mBankList = getBankList(bankDataList);
        wheelView.setAdapter(new WheelAdapter<String>() {
            @Override
            public int getItemsCount() {
                return mBankList.size();
            }

            @Override
            public String getItem(int i) {
                return mBankList.get(i);
            }

            @Override
            public int indexOf(String s) {
                return mBankList.indexOf(s);
            }
        });


        wheelView.setOnItemSelectedListener(i -> {
            choosePos = i;
        });

        ImageButton sure = dialog.findViewById(R.id.iv_sure);
        sure.setOnClickListener(view1 -> {
            dialog.dismiss();
            tvBankName.setText(bankDataList.get(choosePos).getBankName());
            tvBankNo.setText(bankDataList.get(choosePos).getBankNo());
        });
        dialog.show();
    }

    private List<String> getBankList(List<BankCardItem> bankDataList) {
        List<String> list = new ArrayList<>();
        for (BankCardItem item : bankDataList) {
            list.add(item.getBankName());
        }
        return list;
    }

    private void submit(String data) {

        RxHttp.get(Url.txGenerateOrder + "?dataStr=" + data)
//                .add("dataStr",data)
                .asObject(WithdrawResult.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus().equals("1")) {
                        UIHelper.okToast("请求成功");
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }
                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }


}
