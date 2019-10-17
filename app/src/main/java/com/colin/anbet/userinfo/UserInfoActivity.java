package com.colin.anbet.userinfo;

import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.colin.anbet.BaseActivity;
import com.colin.anbet.R;
import com.colin.anbet.adapter.UserMenuAdapter;
import com.colin.anbet.entity.UserMenuBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity
        extends BaseActivity {
    @BindView(R.id.img_back_bg)
    ImageView imgBackBg;
    @BindView(R.id.img_title)
    ImageView imgTitle;
    @BindView(R.id.recyclerViewLeft)
    RecyclerView recyclerViewLeft;
    @BindView(R.id.fl_user_info)
    FrameLayout flUserInfo;


    UserInfoFragment userInfoFrg = new UserInfoFragment();
//    UserBetFragment betFrg = new UserBetFragment();
//    UserAccountFragment accoutFrg = new UserAccountFragment();
//    UserReportFragment reportFrg = new UserReportFragment();
//    UserVipFragment vipFrg = new UserVipFragment();
//    UserPlatformFragment platformFragment = new UserPlatformFragment();

    private Fragment currentFragment = userInfoFrg;
    private UserMenuAdapter userMenuAdapter;
    private List<UserMenuBean> menus = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        playMusic(9, volume);
        //加载loading界面。
        imgTitle.setImageResource(R.drawable.ic_user_info);

        userMenuAdapter = new UserMenuAdapter(menus);
        userMenuAdapter.setOnItemClickListener((adapter, view, position) -> {
            int size = menus.size();
            if (!(Boolean) view.getTag()) {
                for (int i = 0; i < size; i++) {
                    if (i == position) {
                        menus.get(i).setSelected(true);
                    } else {
                        menus.get(i).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
                switchFragment(fragments.get(position));
            }
        });
        recyclerViewLeft.setAdapter(userMenuAdapter);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_user_info, userInfoFrg).commit();
    }

    @Override
    protected void initData() {
        super.initData();
        menus.clear();
        menus.add(new UserMenuBean(0, true));
        menus.add(new UserMenuBean(1, false));
        menus.add(new UserMenuBean(2, false));
        menus.add(new UserMenuBean(3, false));
        menus.add(new UserMenuBean(4, false));
        menus.add(new UserMenuBean(5, false));
        menus.add(new UserMenuBean(6, false));
        fragments.clear();
        fragments.add(new UserInfoFragment());
        fragments.add(new UserBetFragment());
        fragments.add(new UserAccountFragment());
        fragments.add(new UserReportFragment());
        fragments.add(new UserVipFragment());
        fragments.add(new UserChangeFragment());
        fragments.add(new UserPlatformFragment());
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopMusic(9);
    }

    private void switchFragment(Fragment targetFragment) {
        if (currentFragment == targetFragment) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fl_user_info, targetFragment)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }


    @OnClick(R.id.img_back_bg)
    public void onViewClicked() {
        finish();
    }
}


