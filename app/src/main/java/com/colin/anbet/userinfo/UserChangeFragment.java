package com.colin.anbet.userinfo;

import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.MyApp;
import com.colin.anbet.R;
import com.colin.anbet.adapter.BetTimeAdapter;
import com.colin.anbet.adapter.UserChangeAdapter;
import com.colin.anbet.entity.GameList;
import com.colin.anbet.fragment.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.MoneyBean;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.Utils;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

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
    //    @BindView(R.id.btn_reset_data)
//    Button btnResetData;
    @BindView(R.id.btn_submit_money)
    Button btnSubmitMoney;

    List<GameList> contentList = new ArrayList<>();
    private UserChangeAdapter mUserChangeAdapter;
    private boolean transIn = true;

    private int clickPos = -1;
    private PopupWindow popupWindow;
    private View container;

    @Override
    protected void init() {
        initData();
        initView();
    }


    private void initData() {

        RxHttp.get(Url.GAME_SECOND)
                .asParser(new CommonParser<List<GameList>>(new TypeToken<BaseResponseBean<List<GameList>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (!s.getData().isEmpty()) {
                            contentList.clear();
                            contentList.addAll(s.getData());
                        }
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }


    private void initView() {
        tvDigit.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change;

    }

    private void initPopWind(View paramView, TextView paramTextView) {
        this.popupWindow = new PopupWindow(LayoutInflater.from(this.mContext).inflate(R.layout.item_user_pop, null), -2, -2);
        this.popupWindow.setContentView(paramView);
        this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.popupWindow.setFocusable(true);
        this.popupWindow.setOutsideTouchable(false);
        this.popupWindow.showAsDropDown(paramTextView);
    }

    private void getOutPop(List<GameList> data) {
        container = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_short_pop, null);
        RecyclerView localRecyclerView = container.findViewById(R.id.rv_pop);
        localRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, RecyclerView.VERTICAL, false));
        UserChangeAdapter mUserChangeAdapter = new UserChangeAdapter(data);
        localRecyclerView.setAdapter(mUserChangeAdapter);
        mUserChangeAdapter.setOnItemClickListener((aadapter, view, position) -> {
            transIn = false;
            clickPos = position;
            popupWindow.dismiss();
            getBalance();
            tvAccountOut.setText(data.get(position).getName() + "余额");
            tvAccountIn.setText("账户余额");
            tvAccount2.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));
        });
        initPopWind(container, tvAccountOut);
    }

    private void getBalance() {
        RxHttp.get(Url.getGameBalance)
                .add("gameCode", contentList.get(clickPos))
                .asObject(MoneyBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (transIn) {
                            tvAccount2.setText(s.getMoney());
                        } else {
                            tvAccount1.setText(s.getMoney());
                        }

                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    private void getInPop(List<GameList> data) {
        container = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_short_pop, null);
        RecyclerView localRecyclerView = container.findViewById(R.id.rv_pop);
        localRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, RecyclerView.VERTICAL, false));
        UserChangeAdapter mUserChangeAdapter = new UserChangeAdapter(data);
        localRecyclerView.setAdapter(mUserChangeAdapter);
        mUserChangeAdapter.setOnItemClickListener((aadapter, view, position) -> {
            transIn = true;
            clickPos = position;
            popupWindow.dismiss();
            getBalance();
            tvAccountIn.setText(data.get(position).getName() + "余额");
            tvAccountOut.setText("账户余额");
            tvAccount1.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));
        });
        initPopWind(container, tvAccountIn);
    }

    @OnClick({R.id.tv_account_out, R.id.tv_account_in, R.id.btn_clear, R.id.btn_max, R.id.btn_submit_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_account_out:
                getOutPop(contentList);

                break;
            case R.id.tv_account_in:
                getInPop(contentList);

                break;
            case R.id.btn_clear:
                tvInputAccount.getText().clear();
                break;
            case R.id.btn_max:
                if (!transIn) {
                    tvInputAccount.setText(Utils.double2Decimal(MyApp.getInstance().getBalance()));
                }
                break;
            case R.id.btn_submit_money:
                String mount = tvInputAccount.getText().toString().trim();
                if (TextUtils.isEmpty(mount)) {
                    UIHelper.errorToastString("输入为空");
                    return;
                } else {
                    if (transIn) {

                    }
                }
                break;
        }
    }


    private void transferOut() {
        String money = tvInputAccount.getText().toString().trim();
        if (Integer.valueOf(money) < 0 || Integer.valueOf(money) > (int) MyApp.getInstance().getBalance()) {
            UIHelper.errorToastString("请输入正确值");
            return;
        }
        RxHttp.get(Url.creditConversion)
                .add("yezc", contentList.get(clickPos))
                .add("yezr","other")
                .add("changeMoney",money)
                .asObject(MoneyBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (transIn) {
                            tvAccount2.setText(s.getMoney());
                        } else {
                            tvAccount1.setText(s.getMoney());
                        }

                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }
}
