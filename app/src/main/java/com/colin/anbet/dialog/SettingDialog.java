package com.colin.anbet.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.colin.anbet.R;
import com.colin.anbet.event.LoginEvent;
import com.colin.anbet.net.ChangeBean;
import com.colin.anbet.net.LoginBean;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.Constants;
import com.colin.anbet.util.EventBusHelper;
import com.colin.anbet.util.MediaPlayUtil;
import com.colin.anbet.util.SPUtils;
import com.colin.anbet.util.SoundPoolUtil;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.Utils;
import com.king.app.dialog.AppDialog;
import com.king.app.dialog.AppDialogConfig;
import com.king.app.updater.AppUpdater;
import com.rxjava.rxlife.RxLife;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.utils.LogUtil;

import static com.colin.anbet.util.Constants.LOGIN;

public class SettingDialog extends BaseDialogFragment {

    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.rBtn_setting_sound)
    RadioButton rBtnSettingSound;
    @BindView(R.id.rBtn_setting_password_replace)
    RadioButton rBtnSettingPasswordReplace;
    @BindView(R.id.rBtn_setting_app_replace)
    RadioButton rBtnSettingAppReplace;
    @BindView(R.id.rg_setting)
    RadioGroup rgSetting;
    @BindView(R.id.fl_setting_right)
    FrameLayout content;

    private boolean isLogin;

    private String account;
    private String lever;
    private LoginBean loginBean;

    private String version;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initData();
        View view = inflater.inflate(R.layout.dialog_setting, container);

        return view;
    }

    private void initData() {
//        account = getArguments().getString(Constants.ACOUNT);
//        lever = getArguments().getString(Constants.LEVER);
//        isLogin = getArguments().getBoolean(Constants.LOGIN);
        loginBean = SPUtils.getInstance().getObject(LOGIN, LoginBean.class);
        if (loginBean == null) {
            isLogin = false;
        } else {
            isLogin = true;
            account = loginBean.getMemberName();
            lever = loginBean.getVipLevelName();
        }

    }


    private void changePassword() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_setting_passwrod, content, false);
        ImageButton imageButton = view.findViewById(R.id.btn_replace_password);
        EditText edOldPassword = view.findViewById(R.id.ed_old_password);
        EditText edNewPassword = view.findViewById(R.id.ed_new_password);
        EditText ed_new_password2 = view.findViewById(R.id.ed_new_password2);
        TextView tv_app_version = view.findViewById(R.id.tv_app_version);
        tv_app_version.setText(version);


        imageButton.setOnClickListener(view1 -> {
            String old = edOldPassword.getText().toString().trim();
            String newPwd = edNewPassword.getText().toString().trim();
            if (TextUtils.isEmpty(old)) {
                UIHelper.errorToastString("请输入您的密码");
                return;
            }
            if (TextUtils.isEmpty(newPwd)) {
                UIHelper.errorToastString("请输入您的密码");
                return;
            }
            if (TextUtils.isEmpty(ed_new_password2.getText().toString().trim())) {
                UIHelper.errorToastString("请输入您的密码");
                return;
            }
            if (!ed_new_password2.getText().toString().trim().equals(newPwd)) {
                UIHelper.errorToastString("您两次输入的新密码不一样");
                return;
            }
            if (!Utils.isPwd(old) ||!Utils.isPwd(newPwd)||!Utils.isPwd(ed_new_password2.getText().toString().trim())) {
                UIHelper.errorToastString("请输入6-12位密码");
                return;
            }
            submitChangePwd(old, newPwd);
        });


        content.removeAllViews();
        content.addView(view);
    }

    private void submitChangePwd(String old, String newPwd) {
        String finalResult = Utils.getChangePwdBase64(loginBean.getMemberId(), old, newPwd);
        Log.e("chai", finalResult);
        LogUtil.setDebug(true);
        //后台接口get与post请求都能用，然后base64请求后会被转码，故这样处理
        RxHttp.get(Url.updateLoginPwd + "?dataStr=" + finalResult)
//                .add("dataStr", finalResult)
                .asObject(ChangeBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        UIHelper.okToast("修改成功");

                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    private void settingSoundFragment() {
        View rightContent;
        if (isLogin) {
            rightContent = LayoutInflater.from(mContext).inflate(R.layout.fragment_setting_sound, content, false);
        } else {
            rightContent = LayoutInflater.from(mContext).inflate(R.layout.fragment_setting_sound_nologin, content, false);
        }
        int volume = mContext.getMediaVolume();
        final int maxVolume = mContext.getMaxVolume();

        SeekBar sb_music = rightContent.findViewById(R.id.sb_music);
        SeekBar sb_vol = rightContent.findViewById(R.id.sb_vol);
        sb_music.setProgress(SPUtils.getInstance().getMusic(mContext.getMediaVolume()));
        sb_music.setMax(maxVolume);
        sb_vol.setProgress(SPUtils.getInstance().getVolum(mContext.getMediaVolume()));
        sb_vol.setMax(maxVolume);

        TextView tv_setting_name = rightContent.findViewById(R.id.tv_setting_name);
        tv_setting_name.setText(account);
        TextView tv_setting_id = rightContent.findViewById(R.id.tv_setting_id);
        tv_setting_id.setText(lever);
        TextView tv_app_version = rightContent.findViewById(R.id.tv_app_version);
        tv_app_version.setText(version);

        ImageView btn_logout = rightContent.findViewById(R.id.btn_logout);
        if (isLogin) {
            //todo退出登录
//            btn_logout.setOnClickListener();
        } else {
            btn_logout.setVisibility(View.GONE);
        }
        sb_music.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                SPUtils.getInstance().setMusic(progress);
                if (!mContext.isSilentMode()) {
                    MediaPlayUtil.setVolume((float) progress / maxVolume);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_vol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                SPUtils.getInstance().setVolume(progress);
                if (!mContext.isSilentMode())
                    SoundPoolUtil.setVolume2(SoundPoolUtil.mStreamId2, (float) progress / maxVolume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        content.removeAllViews();
        content.addView(rightContent);
    }


    @Override
    protected void init() {
        if (isLogin) {
            rBtnSettingPasswordReplace.setVisibility(View.VISIBLE);
        } else {
            rBtnSettingPasswordReplace.setVisibility(View.GONE);
        }
        settingSoundFragment();
    }

    @OnClick({R.id.btn_close, R.id.rBtn_setting_sound, R.id.rBtn_setting_password_replace, R.id.rBtn_setting_app_replace})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.rBtn_setting_sound:
                settingSoundFragment();
                break;
            case R.id.rBtn_setting_password_replace:
                changePassword();
                break;
            case R.id.rBtn_setting_app_replace:
                AppDialogConfig config = new AppDialogConfig();
                config.setTitle("简单弹框升级")
                        .setOk("升级")
                        .setContent("1、新增某某功能、\n2、修改某某问题、\n3、优化某某BUG、")
                        .setOnClickOk(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new AppUpdater.Builder()
                                        .serUrl("https://raw.githubusercontent.com/jenly1314/AppUpdater/master/app/release/app-release.apk")
                                        .setFilename("AppUpdater.apk")
                                        .build(getContext())
                                        .start();
                                AppDialog.INSTANCE.dismissDialog();
                            }
                        });
                AppDialog.INSTANCE.showDialog(getContext(), config);
                break;
        }
    }
}
