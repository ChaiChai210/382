package com.colin.anbet.userinfo;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.R;
import com.colin.anbet.entity.VipList;
import com.colin.anbet.fragment.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.widget.CustomScrollViewPager;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class UserInfoFragment extends BaseFragment {

    public static final int MONTH = 2;
    private static final String TAG = "UserInfoFragment";
    public static final int WEEK = 1;
    SimpleDateFormat A = new SimpleDateFormat("MM-dd", Locale.getDefault());
    SimpleDateFormat B = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    Map<String, Object> C = new HashMap();
    String[] D = {"男", "女"};

    boolean flag = true;
    @BindView(R.id.tv_level_privilege)
    TextView tvLevelPrivilege;
    @BindView(R.id.iv_head_vip)
    ImageView ivHeadVip;
    @BindView(R.id.tv_current_level)
    TextView tvCurrentLevel;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.iv_level_start)
    ImageView ivLevelStart;
    @BindView(R.id.iv_level_end)
    ImageView ivLevelEnd;
    @BindView(R.id.pb_level)
    ProgressBar pbLevel;
    @BindView(R.id.tv_upper_diff)
    TextView tvUpperDiff;
    @BindView(R.id.iv_up_vip)
    ImageView ivUpVip;
    @BindView(R.id.iv_next_vip)
    ImageView ivNextVip;
    @BindView(R.id.tv_vip_title)
    TextView tvVipTitle;
    @BindView(R.id.rl_vip_title)
    RelativeLayout rlVipTitle;
    @BindView(R.id.viewPager)
    CustomScrollViewPager viewPager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_edit_base)
    TextView tvEditBase;
    @BindView(R.id.rl_base_msg)
    RelativeLayout rlBaseMsg;
    @BindView(R.id.iv_usr_icon)
    ImageView ivUsrIcon;
    @BindView(R.id.tv_pc_account)
    TextView tvPcAccount;
    @BindView(R.id.tv_input_account)
    TextView tvInputAccount;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.ed_nick_name)
    EditText edNickName;
    @BindView(R.id.tv_show_nick_name)
    TextView tvShowNickName;
    @BindView(R.id.tv_pc_name)
    TextView tvPcName;
    @BindView(R.id.tv_input_name)
    TextView tvInputName;
    @BindView(R.id.tv_pc_sex)
    TextView tvPcSex;
    @BindView(R.id.tv_edit_sex)
    TextView tvEditSex;
    @BindView(R.id.tv_show_sex)
    TextView tvShowSex;
    @BindView(R.id.tv_pc_birthday)
    TextView tvPcBirthday;
    @BindView(R.id.tv_edit_birthday)
    TextView tvEditBirthday;
    @BindView(R.id.tv_show_birthday)
    TextView tvShowBirthday;
    @BindView(R.id.tv_edit_contact)
    TextView tvEditContact;
    @BindView(R.id.rl_concat)
    RelativeLayout rlConcat;
    @BindView(R.id.v_center)
    View vCenter;
    @BindView(R.id.tv_pc_mail)
    TextView tvPcMail;
    @BindView(R.id.ed_edit_mail)
    EditText edEditMail;
    @BindView(R.id.tv_show_email)
    TextView tvShowEmail;
    @BindView(R.id.tv_pc_qq)
    TextView tvPcQq;
    @BindView(R.id.ed_edit_qq)
    EditText edEditQq;
    @BindView(R.id.tv_show_qq)
    TextView tvShowQq;
    @BindView(R.id.tv_pc_phone)
    TextView tvPcPhone;
    @BindView(R.id.ed_edit_phone)
    EditText edEditPhone;
    @BindView(R.id.tv_show_phone)
    TextView tvShowPhone;
    @BindView(R.id.tv_pc_wechat)
    TextView tvPcWechat;
    @BindView(R.id.ed_edit_wechat)
    EditText edEditWechat;
    @BindView(R.id.tv_show_wechat)
    TextView tvShowWechat;
    private int pos = 0;
    private Map<String, Integer> headIcons = new HashMap<>();
    private Map<String, Integer> nextIcons = new HashMap<>();
    private List<String> nextIndex = new ArrayList<>();
    private Map<String, Integer> upperIcons = new HashMap<>();
    private List<String> upperIndex = new ArrayList<>();

    private RightsAdapter rightsAdapter;
    private List<VipList> rightsBeanList = new ArrayList<>();


    @Override
    protected void init() {
        loadIcons();
        initView();
        getVipInfo();
    }

    private void getVipInfo() {
        RxHttp.get(Url.listVipGrade)
                .asParser(new CommonParser<List<VipList>>(new TypeToken<BaseResponseBean<List<VipList>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (s.getData().isEmpty()) {

                        } else {
                            rightsBeanList.clear();
                            rightsBeanList.addAll(s.getData());
                            rightsAdapter.notifyDataSetChanged();
                        }
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }
                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }


    private void initView() {
//        View loading  = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_loading, null);
//        this.loadingDialog = new LoadingDialog((Context) Objects.requireNonNull(getContext()), R.style.MobileDialog);
//        this.loadingDialog.initDialog(loading);
//        loadingDialog.showDialog();

        rightsAdapter = new RightsAdapter(rightsBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.rightsAdapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
//        recyclerView.setOnScrollChangeListener();

    }


    private void loadIcons() {
        int i2;
        for (int i = 1; i <= 10; i++) {
            int i3 = getResources().getIdentifier("ic_pc_vip" + i, "drawable", mContext.getPackageName());
            i2 = getResources().getIdentifier("ic_up_vip" + i, "drawable", mContext.getPackageName());
            this.headIcons.put("VIP" + i, i3);
            this.upperIndex.add("VIP" + i);
            this.upperIcons.put("VIP" + i, i2);
        }
        for (int j = 2; j <= 10; j++) {
            i2 = getResources().getIdentifier("ic_next_vip" + j, "drawable", mContext.getPackageName());
            this.nextIcons.put("VIP" + j, i2);
            this.nextIndex.add("VIP" + j);
        }

//        for (int i = 0; i <=10; i++){
//            int left = getResources().getIdentifier("ic_up_vip" + i, "drawable", mContext.getPackageName());
//            leftIcons.put(i,left);
//            int right = getResources().getIdentifier("ip_next_jip" + i, "drawable", mContext.getPackageName());
//        }
    }


    @OnClick({R.id.iv_up_vip, R.id.iv_next_vip, R.id.tv_edit_base, R.id.tv_edit_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_up_vip:
                if (pos == 0) {
                    ToastUtil.getInstance().showToast("已经到头了···");
                } else {
                    pos -= 1;
                    ivNextVip.setVisibility(View.VISIBLE);
                    getData(pos);
                }

                break;
            case R.id.iv_next_vip:
                if (pos >= rightsBeanList.size() - 2) {
                    ivUpVip.setImageResource(upperIcons.get(upperIndex.get(pos + 1)));
                    ivNextVip.setVisibility(View.GONE);
                } else {
                    pos += 1;
                    getData(pos);
                }


//                ivNextVip.setVisibility(View.VISIBLE);
//                setVipTitle(str1);
                break;
            case R.id.tv_edit_base:
                break;
            case R.id.tv_edit_contact:
                break;
        }
    }

    private void getData(int pos) {
        recyclerView.smoothScrollToPosition(pos);
        String upper = upperIndex.get(pos);
        String next = nextIndex.get(pos);
        ivUpVip.setImageResource(upperIcons.get(upper));
        ivNextVip.setImageResource(nextIcons.get(next));
    }

    private void setVipTitle(String title) {
        tvVipTitle.setText(String.format("尊敬的%1$s会员，您享受以下权益", title));
    }
}
