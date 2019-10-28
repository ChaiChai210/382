package com.colin.anbet.Safe;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.colin.anbet.R;
import com.colin.anbet.base.BaseFragment;
import com.colin.anbet.base.BindEventBus;
import com.colin.anbet.event.SafeEvent;
import com.colin.anbet.net.BalanceBean;
import com.colin.anbet.net.DepositBean;
import com.colin.anbet.net.LoginBean;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.EventBusHelper;
import com.colin.anbet.util.SPUtils;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.Utils;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.utils.LogUtil;

import static com.colin.anbet.util.Constants.LOGIN;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.Safe
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/15 16:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/15 16:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@BindEventBus
public class SafeQCFragment extends BaseFragment {
    @BindView(R.id.tv_safe_box_balance)
    TextView tvSafeBoxBalance;
    @BindView(R.id.tv_user_balance)
    TextView tvUserBalance;
    @BindView(R.id.et_input_account)
    EditText etInputAccount;
    @BindView(R.id.btn_clear)
    ImageButton btnClear;
    @BindView(R.id.ll_middle)
    LinearLayout llMiddle;
    @BindView(R.id.sb_seekbar)
    SeekBar sbSeekbar;
    @BindView(R.id.btn_max)
    ImageButton btnMax;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.ib_deposit)
    ImageButton ibDeposit;

    private double userBalance;
    private String safeMoney;

    @Override
    protected void init() {
        safeMoney = ((SafeBoxActivity) mContext).getSafeMoney();
        tvSafeBoxBalance.setText(safeMoney);
        getBalance();
        sbSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double safe = Double.parseDouble(safeMoney);
                double money = (safe * progress / 100);
                etInputAccount.setText(String.valueOf(money));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden && ((SafeBoxActivity) mContext).isNeedRefresh()){
            safeMoney = ((SafeBoxActivity) mContext).getSafeMoney();
            tvSafeBoxBalance.setText(safeMoney);
            tvUserBalance.setText(((SafeBoxActivity) mContext).getBalance());
        }
    }

    private void getBalance() {
        RxHttp.get(Url.findMemberBalance)
//                .add("dataStr", finalResult)
                .asObject(BalanceBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    if (s.getStatus() == 1) {
                        userBalance = Double.parseDouble((s.getAvailableFration()));
                        tvUserBalance.setText(s.getAvailableFration());
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_safe_take_out;
    }

    @OnClick({R.id.btn_clear, R.id.btn_max, R.id.ib_deposit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                etInputAccount.getText().clear();
                sbSeekbar.setProgress(0);
                break;
            case R.id.btn_max:
                etInputAccount.setText(String.valueOf(safeMoney));
                sbSeekbar.setProgress(100);
                break;
            case R.id.ib_deposit:
                String money = etInputAccount.getText().toString().trim();
                if (Float.parseFloat(money) > Float.parseFloat(safeMoney)) {
                    UIHelper.errorToastString("转出金额不能大于转入当前余额！");
                    return;
                }
                if (TextUtils.isEmpty(money)) {
                    UIHelper.errorToastString("转入金额不能为空！");
                    return;
                }
                if (Float.parseFloat(money) <= 0.0F) {
                    UIHelper.errorToastString("金额必须大于0！");
                    return;
                }
                if (Float.parseFloat(safeMoney) <= 0.0D) {
                    UIHelper.errorToastString("没有可转入的金额！");
                    return;
                }
                submit(money);
                break;
        }
    }

    private void submit(String money) {
        LoginBean loginBean = SPUtils.getInstance().getObject(LOGIN, LoginBean.class);
        String intMoney = String.valueOf(Double.valueOf(money) * -1);
        String finalResult = Utils.getSafeBase64(loginBean.getMemberId(), intMoney);
        LogUtil.setDebug(true);
        //后台接口get与post请求都能用，然后base64请求后会被转码，故这样处理
        RxHttp.get(Url.updateMemberSafeMoney + "?dataStr=" + finalResult)
//                .add("dataStr", finalResult)
                .asObject(DepositBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 0) {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    } else if (s.getStatus() == 1) {
                        tvSafeBoxBalance.setText(s.getMemberSafeMoney());
                        tvUserBalance.setText(s.getZmoney());
                        safeMoney = s.getMemberSafeMoney();
                        EventBusHelper.post(new SafeEvent(s.getMemberSafeMoney(), s.getZmoney()));
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onSafeEvent(SafeEvent event) {
////        needRefresh = true;
//        safeMoney = event.getSafeMoney();
//        userBalance = Double.parseDouble(event.getBalance());
//    }
}
