package com.colin.anbet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.CustomerService.CustomerServiceActivity;
import com.colin.anbet.Withdraw.WithDrawActivity;
import com.colin.anbet.activity.ChessCardActivity;
import com.colin.anbet.activity.HuoDongActivity;
import com.colin.anbet.activity.XimaActivity;
import com.colin.anbet.adapter.CategoryGameAdapter;
import com.colin.anbet.adapter.HotGameAdapter;
import com.colin.anbet.adapter.RightGameAdapter;
import com.colin.anbet.dialog.SafeDialog;
import com.colin.anbet.dialog.SafePwdDialog;
import com.colin.anbet.dialog.SettingDialog;
import com.colin.anbet.entity.CategoryBean;
import com.colin.anbet.entity.GameList;
import com.colin.anbet.entity.HotGameList;
import com.colin.anbet.event.LoginEvent;
import com.colin.anbet.login.LoginDialog;
import com.colin.anbet.login.RegisterDialog;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.LoginBean;
import com.colin.anbet.net.Url;
import com.colin.anbet.promotion.NewExtensionActivity;
import com.colin.anbet.recharge.RechargeActivity;
import com.colin.anbet.userinfo.UserInfoActivity;
import com.colin.anbet.util.AppUtil;
import com.colin.anbet.util.Constants;
import com.colin.anbet.util.EventBusHelper;
import com.colin.anbet.util.SPUtils;
import com.colin.anbet.util.SoundPoolUtil;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.UrlHelper;
import com.colin.anbet.util.Utils;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.utils.LogUtil;

import static com.colin.anbet.util.Constants.LOGIN;

@BindEventBus
public class MainActivity extends BaseActivity {


    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.btn_register)
    ImageButton btnRegister;
    @BindView(R.id.btn_login)
    ImageButton btnLogin;
    @BindView(R.id.img_vip_home)
    ImageView imgVipHome;
    @BindView(R.id.fl_user)
    RelativeLayout flUser;
    @BindView(R.id.tv_golden_account)
    TextView tvGoldenAccount;
    @BindView(R.id.img_golden)
    ImageView imgGolden;
    @BindView(R.id.img_fresh)
    ImageButton imgFresh;
    @BindView(R.id.img_website)
    ImageView imgWebsite;
    @BindView(R.id.btn_copy)
    ImageButton btnCopy;
    @BindView(R.id.btn_setting)
    ImageButton btnSetting;
    @BindView(R.id.recyclerViewLeft)
    RecyclerView recyclerViewLeft;
    @BindView(R.id.img_down_moredata)
    ImageView imgDownMoredata;
    @BindView(R.id.tv_notify)
    TextView tvNotify;
    @BindView(R.id.recyclerViewContent)
    RecyclerView recyclerViewContent;
    @BindView(R.id.btn_promotion)
    ImageButton btnPromotion;
    @BindView(R.id.btn_activity)
    RelativeLayout btnActivity;
    @BindView(R.id.tv_action_angle)
    TextView tvActionAngle;
    @BindView(R.id.btn_shuffle)
    RelativeLayout btnShuffle;
    @BindView(R.id.iv_msg_count)
    TextView ivMsgCount;
    @BindView(R.id.btn_message)
    RelativeLayout btnMessage;
    @BindView(R.id.btn_customer)
    RelativeLayout btnCustomer;
    @BindView(R.id.btn_safe_box)
    RelativeLayout btnSafeBox;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.btn_withdrawal)
    ImageButton btnWithdrawal;
    @BindView(R.id.btn_recharge)
    ImageButton btnRecharge;
    @BindView(R.id.root)
    LinearLayout root;
    private boolean accountBalance = false;
    private boolean isLogin = false;

    private RecyclerView mLeftRecycler;
    private List<CategoryBean> categoryBeans = new ArrayList<>();
    private CategoryGameAdapter categoryGameAdapter;
    private RecyclerView mRightRecycler;
    private List<GameList> contentList = new ArrayList();
    private RightGameAdapter mRightGameAdapter;
    private HotGameAdapter mHotGameAdapter;

    private int clickPos = 0;
    private static final Map<String, Integer> myMap = new HashMap<String, Integer>();

    static {
        myMap.put("VIP1", R.drawable.home_vip1);
        myMap.put("VIP2", R.drawable.home_vip2);
        myMap.put("VIP3", R.drawable.home_vip3);
        myMap.put("VIP4", R.drawable.home_vip4);
        myMap.put("VIP5", R.drawable.home_vip5);
        myMap.put("VIP6", R.drawable.home_vip6);
        myMap.put("VIP7", R.drawable.home_vip7);
        myMap.put("VIP8", R.drawable.home_vip8);
        myMap.put("VIP9", R.drawable.home_vip9);
        myMap.put("VIP10", R.drawable.home_vip10);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        playMusic(5, volume);
//        showLoading();
        initLeftMenu();
        submit();

    }

    private void submit() {
        JSONObject jo = new JSONObject();
        //wangwu w12345678
        try {
            jo.put("memberPwd", "w12345678");
            jo.put("memberName", "wangwu");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = "";
        try {
            result = URLEncoder.encode(jo.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String finalResult = Base64.getEncoder().encodeToString(result.getBytes());
        String finalResult = Base64.encodeToString(result.getBytes(Charset.forName("UTF-8")), Base64.DEFAULT);
//        String finalResult = "JTdCJTIybWVtYmVyUHdkJTIyJTNBJTIydzEyMzQ1Njc4JTIyJTJDJTIybWVtYmVyTmFtZSUyMiUzQSUyMndhbmd3dSUyMiU3RA==";
        Log.e("result1", result);
        Log.e("result2", finalResult);
        LogUtil.setDebug(true);
        //后台接口get与post请求都能用，然后base64请求后会被转码，故这样处理
        RxHttp.get("phone/memberManager/login" + "?dataStr=" + finalResult)
//                .add("dataStr", finalResult)
                .asObject(LoginBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 0) {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    } else {
                        SPUtils.getInstance().putObject(LOGIN, s);
                        EventBusHelper.post(new LoginEvent(s));
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
        String url = RxHttp.postJson("phone/memberManager/login").add("dataStr", finalResult).getUrl();
        Log.e("chai", url);
    }

    private void initRightContent(int clickPos) {
        mRightRecycler = findViewById(R.id.recyclerViewContent);
        mRightRecycler.setLayoutManager(getLayoutManager(clickPos));

        if (clickPos != 0) {
            mRightGameAdapter = new RightGameAdapter(contentList, clickPos);
            mRightRecycler.setAdapter(mRightGameAdapter);
            mRightGameAdapter.setOnItemClickListener((adapter, view, position) -> {
                Intent intent = new Intent(MainActivity.this, ChessCardActivity.class);
                intent.putExtra(Constants.Game, contentList.get(position).getCode());
                MainActivity.this.startActivity(intent);
//                if(isLogin){
//
//                }else {
//                    showFragment(LoginDialog.newInstance("", ""));
//                }

            });
        }


        initRightContentData(clickPos);

    }

    private void initRightContentData(int clickPos) {
        if (0 == clickPos) {
            getHot();
        } else {
            int parentId = getParentId(clickPos);
            RxHttp.get(Url.GAME_SECOND)
                    .add("parentId", parentId)
                    .asParser(new CommonParser<List<GameList>>(new TypeToken<BaseResponseBean<List<GameList>>>() {
                    }))
                    .as(RxLife.asOnMain(this))//返回String类型
                    .subscribe(s -> {          //订阅观察者，
                        //请求成功
                        Log.e("请求成功", s.toString());
                        if (s.getStatus() == 1) {
                            contentList.clear();
                            if (s.getData().isEmpty()) {

                            } else {
                                contentList.addAll(s.getData());
                                mRightGameAdapter.notifyDataSetChanged();
                            }
                        } else {
                            ToastUtil.getInstance().showToast(s.getMsg());
                        }

                    }, throwable -> {
                        Log.e("chai", throwable.getMessage());
                    });
        }


    }

    private int getParentId(int clickPos) {
        return categoryBeans.get(clickPos).getGamingTypeId();
    }

    private void getHot() {
        RxHttp.get(Url.HOT_GAME)
                .asParser(new CommonParser<List<HotGameList>>(new TypeToken<BaseResponseBean<List<HotGameList>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    setHot(s);

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    private void setHot(BaseResponseBean<List<HotGameList>> s) {
        if (s.getStatus() == 1) {
            if (s.getData().isEmpty()) {
            } else {
                mHotGameAdapter = new HotGameAdapter(s.getData());
                mHotGameAdapter.setOnItemClickListener((adapter, view, position) -> {
                    AppUtil.gotoWebView(MainActivity.this, mHotGameAdapter.getItem(position).getLiveCode(), mHotGameAdapter.getItem(position).getGameType());
                });
                mRightRecycler.setAdapter(mHotGameAdapter);
            }
        } else {
            UIHelper.errorToastString(s.getMsg());
        }
    }

    private RecyclerView.LayoutManager getLayoutManager(int pos) {
        int parentId = getParentId(pos);
        //真人视讯
        if (parentId == 4) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 2;
                    } else {
                        return 1;
                    }
                }
            });
            return gridLayoutManager;
        } else if (parentId == 2)  //棋牌
        {
            return new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
            return gridLayoutManager;
        }
    }

    private void initLeftMenu() {
        mLeftRecycler = findViewById(R.id.recyclerViewLeft);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mLeftRecycler.setLayoutManager(linearLayoutManager);
        initLeftMenuData();


    }

    private void initLeftMenuData() {
        showLoading();
        RxHttp.get(Url.GAME_CLASSIFY)
//                .add("dataStr", finalResult)
                .asParser(new CommonParser<List<CategoryBean>>(new TypeToken<BaseResponseBean<List<CategoryBean>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    hideLoading();
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        setLeftMenu(s.getData());
                        initRightContent(0);
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    hideLoading();
                    Log.e("chai", throwable.getMessage());
                });
    }

    private void setLeftMenu(List<CategoryBean> data) {
        categoryBeans.clear();
        categoryBeans.add(new CategoryBean(-1, true));
        categoryBeans.addAll(data);
        categoryGameAdapter = new CategoryGameAdapter(categoryBeans);
        categoryGameAdapter.setOnItemClickListener((adapter, view, position) -> {
            int size = categoryBeans.size();
            if (!(Boolean) view.getTag()) {
                for (int i = 0; i < size; i++) {
                    if (i == position) {
                        categoryBeans.get(i).setSelected(true);
                    } else {
                        categoryBeans.get(i).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
                initRightContent(position);
            }
        });
        mLeftRecycler.setAdapter(categoryGameAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundPoolUtil.stop(SoundPoolUtil.mStreamId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        hideLoading();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        LoginBean bean = event.getLoginBean();
        tvLogin.setText(bean.getMemberName());
        btnRegister.setVisibility(View.GONE);
        btnLogin.setVisibility(View.GONE);
        imgVipHome.setVisibility(View.VISIBLE);
        imgVipHome.setImageResource(myMap.get(bean.getVipLevelName()));
        tvGoldenAccount.setText(bean.getAvailableFration());

        MyApp.getInstance().setLogin(true);
        MyApp.getInstance().setLoginBean(bean);
        MyApp.getInstance().setBalance(Double.parseDouble(bean.getAvailableFration()));
        isLogin = true;

    }


    @OnClick({R.id.tv_login, R.id.btn_register, R.id.btn_login, R.id.img_vip_home, R.id.img_fresh, R.id.img_website, R.id.btn_copy, R.id.btn_setting, R.id.img_down_moredata, R.id.btn_activity, R.id.btn_shuffle, R.id.btn_message, R.id.btn_customer, R.id.btn_safe_box, R.id.btn_withdrawal, R.id.btn_recharge, R.id.btn_promotion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:

                break;
            case R.id.btn_register:
                RegisterDialog registerDialog = new RegisterDialog();
                registerDialog.setRegisterLiner((account, pwd) -> {

                });
                showFragment(registerDialog);


//                RegisterDialog fm3 = new RegisterDialog();
//                fm3.show(getSupportFragmentManager(), "register");
                break;
            case R.id.btn_login:
                showFragment(LoginDialog.newInstance("", ""));
                break;
            case R.id.img_vip_home:
                if (clickGapFilter()) {
                    startActivity(new Intent(this, UserInfoActivity.class));
                }
                break;
            case R.id.img_fresh:
                startActivity(new Intent(this, UserInfoActivity.class));
                break;
            case R.id.img_website:
                break;
            case R.id.btn_copy:
                Utils.copyToClipboard(this.mContext, UrlHelper.OFFICIAL_URL);
                UIHelper.copySuccess("复制成功");
                break;
            case R.id.btn_setting:
                SettingDialog fm2 = new SettingDialog();
                fm2.show(getSupportFragmentManager(), "setting");
                break;
            case R.id.img_down_moredata:
                break;
            case R.id.btn_activity:
                startActivity(new Intent(this, HuoDongActivity.class));
                break;
            case R.id.btn_shuffle:
                startActivity(new Intent(this, XimaActivity.class));
                break;
            case R.id.btn_message:
                break;
            case R.id.btn_customer:
                startActivity(new Intent(this, CustomerServiceActivity.class));
                break;
            case R.id.btn_safe_box:
                if (SPUtils.getInstance().getFirstEnter()) {
                    showFragment(new SafeDialog());
                } else {
                    showFragment(new SafePwdDialog());
                }
                break;
            case R.id.btn_withdrawal:
                if (isLogin) {
                    startActivity(new Intent(this, WithDrawActivity.class));
                } else {
                    showFragment(LoginDialog.newInstance("", ""));
                }
                break;
            case R.id.btn_recharge:
//                startActivity(new Intent(this, RechargeActivity.class));
                if (isLogin) {
                    startActivity(new Intent(this, RechargeActivity.class));
                } else {
                    showFragment(LoginDialog.newInstance("", ""));
                }
                break;
            case R.id.btn_promotion:
                if (clickGapFilter()) {
                    startActivity(new Intent(this, NewExtensionActivity.class));
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}