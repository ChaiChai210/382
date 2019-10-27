package com.colin.anbet.activity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.base.BaseActivity;
import com.colin.anbet.R;
import com.colin.anbet.adapter.ActionAdapter;
import com.colin.anbet.entity.ActionItem;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.widget.MultipleStatusView;
import com.colin.anbet.widget.RecycleViewDivider;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

//showRedOpen()弹出领红包界面
public class HuoDongActivity extends BaseActivity {
    private static final String TAG = "HuoDongActivity";
    @BindView(R.id.img_back_bg)
    ImageView imgBackBg;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.btn_zonghe)
    RadioButton btnZonghe;
    @BindView(R.id.btn_qipai)
    RadioButton btnQipai;
    @BindView(R.id.btn_buyu)
    RadioButton btnBuyu;
    @BindView(R.id.btn_dianzi)
    RadioButton btnDianzi;
    @BindView(R.id.btn_shixun)
    RadioButton btnShixun;
    @BindView(R.id.btn_sports)
    RadioButton btnSports;
    @BindView(R.id.rg_huodong)
    RadioGroup rgHuodong;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multipleStatusView_default)
    MultipleStatusView multipleStatusViewDefault;
    @BindView(R.id.fl_act_contain)
    FrameLayout flActContain;
    @BindView(R.id.llayout_huodong)
    LinearLayout llayoutHuodong;

    private List<Boolean> booleanList = new ArrayList();
    //    private List<BankCardResult> cardList = new ArrayList();
    private boolean currentClick = true;

    private Context mContext = this;
    private MultipleStatusView multipleStatusView;
    private Map<String, Integer> paramas;


    private ActionAdapter actAdapter;
    private List<ActionItem> dataList = new ArrayList<>();
//    private void isCountBind() {
//        final CustomDialog localCustomDialog = new CustomDialog(this, 2131558575);
//        localCustomDialog.setContentView(2131361844);
//        ((Window) Objects.requireNonNull(localCustomDialog.getWindow())).setFlags(1024, 1024);
//        Object localObject = ((Window) Objects.requireNonNull(localCustomDialog.getWindow())).getAttributes();
//        ((WindowManager.LayoutParams) localObject).gravity = 17;
//        ((WindowManager.LayoutParams) localObject).width = -1;
//        ((WindowManager.LayoutParams) localObject).height = -1;
//        localObject = (ImageView) localCustomDialog.findViewById(2131230786);
//        ImageView localImageView = (ImageView) localCustomDialog.findViewById(2131230787);
//        ((ImageView) localObject).setOnClickListener(new OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                localCustomDialog.dismiss();
//            }
//        });
//        localImageView.setOnClickListener(new OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                paramAnonymousView = new Intent(HuoDongActivity.this, WithDrawActivity.class);
//                paramAnonymousView.putExtra("type", "huodong");
//                HuoDongActivity.this.startActivity(paramAnonymousView);
//                localCustomDialog.dismiss();
//            }
//        });
//        dialogSet(localCustomDialog);
//        if (!localCustomDialog.isShowing()) {
//            localCustomDialog.show();
//        }
//    }


//    public void expireToken() {
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getActionBankCard(ActionEvent paramActionEvent) {
//        paramActionEvent = MyAppcation.getInstance().getToken();
//        if (!TextUtils.isEmpty(paramActionEvent)) {
//            this.actionPresenter.getBankCard(paramActionEvent);
//        }
//    }
//
//
//    public void getBankCard(List<BankCardResult> paramList) {
//        this.cardList.clear();
//        this.cardList.addAll(paramList);
//        if ((paramList != null) && (paramList.size() > 0)) {
//            SPUtils.getInstance().setBankUserName(((BankCardResult) paramList.get(0)).getUserBankName());
//            getHuoDongData(this.marketingType, this.plaformId);
//        } else {
//            isCountBind();
//        }
//    }


    public void hideProgress() {
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_huo_dong;

    }

    @Override
    protected void initView() {
        playMusic(2, volume);
        imgTitle.setImageResource(R.drawable.ic_act_title);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.addItemDecoration(new RecycleViewDivider(this.mContext, 1, 20, 2131034153));
        actAdapter = new ActionAdapter(R.layout.item_activity_copy, dataList);
        recyclerView.setAdapter(actAdapter);

        getActivityList(1);
    }

    protected void onPause() {
        super.onPause();
        stopMusic(2);
    }

    protected void onResume() {
        super.onResume();
    }

    public void showProgress() {
    }

    private void getActivityList(int actionType) {
        RxHttp.get(Url.Action)
                .add("typeParent",actionType)
                .asParser(new CommonParser<List<ActionItem>>(new TypeToken<BaseResponseBean<List<ActionItem>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                        if (s.getStatus() == 1) {
                        if (s.getData().isEmpty()) {
                        } else {
                            dataList.clear();
                            dataList.addAll(s.getData());
                            actAdapter.notifyDataSetChanged();
                        }
                    } else {
                        UIHelper.errorToastString(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    @OnClick({R.id.img_back_bg, R.id.btn_zonghe, R.id.btn_qipai, R.id.btn_buyu, R.id.btn_dianzi, R.id.btn_shixun, R.id.btn_sports})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back_bg:
                finish();
                break;
            case R.id.btn_zonghe:
                getActivityList(1);
                break;
            case R.id.btn_qipai:
                getActivityList(2);
                break;
            case R.id.btn_buyu:
                getActivityList(3);
                break;
            case R.id.btn_dianzi:
                getActivityList(5);
                break;
            case R.id.btn_shixun:
                getActivityList(4);
                break;
            case R.id.btn_sports:
                break;
        }
    }
}



