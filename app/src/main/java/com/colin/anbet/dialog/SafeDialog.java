package com.colin.anbet.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;

import com.colin.anbet.R;
import com.colin.anbet.net.DepositBean;
import com.colin.anbet.net.LoginBean;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.SPUtils;
import com.colin.anbet.util.SoftKeyboardUtil;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.Utils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.imebugfixer.ImeDelBugFixedEditText;
import com.rxjava.rxlife.RxLife;

import java.lang.reflect.Field;

import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.utils.LogUtil;

import static com.colin.anbet.util.Constants.LOGIN;

public class SafeDialog extends BaseDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_password, container);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_close).setOnClickListener(view1 -> dismiss()
        );

        GridPasswordView pswViewSet = view.findViewById(R.id.pswViewSet);
        GridPasswordView pswViewConfirm = view.findViewById(R.id.pswViewConfirm);
        ImageView iv_confirm_commit = view.findViewById(R.id.iv_confirm_commit);

        setClickShowKeyBoard(pswViewConfirm, getImeEdittext(pswViewConfirm));
        setClickShowKeyBoard(pswViewSet, getImeEdittext(pswViewSet));
        pswViewConfirm.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                SoftKeyboardUtil.hideSoftKeyboard(pswViewConfirm);
            }
        });
        iv_confirm_commit.setOnClickListener(view12 -> {
            String passWord1 = pswViewSet.getPassWord();
            String passWord2 = pswViewConfirm.getPassWord();
            if (!passWord1.equals(passWord2)) {
                ToastUtil.getInstance().showToast("密码不一致哦！");
                return;
            }
            if (passWord1.length() < 4) {
                ToastUtil.getInstance().showToast("密码位数不足");
            }
            setSafeBoxPwd(passWord1);

        });
        setObjByReflect(pswViewSet);
        setObjByReflect(pswViewConfirm);
    }

    private void setSafeBoxPwd(String safeBoxPwd) {
        LoginBean loginBean = SPUtils.getInstance().getObject(LOGIN, LoginBean.class);
        String finalResult = Utils.getSafePwdBase64(loginBean.getMemberId(), safeBoxPwd);
        LogUtil.setDebug(true);
        //后台接口get与post请求都能用，然后base64请求后会被转码，故这样处理
        RxHttp.get(Url.updateMemberSafePwd + "?dataStr=" + finalResult)
//                .add("dataStr", finalResult)
                .asObject(DepositBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 0) {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }else if(s.getStatus() == 1){
                        SPUtils.getInstance().setFirstEnter(false);
                        dismiss();
                        mContext.showFragment(new SafePwdDialog());
//                        IntentUtil.startActivity(mContext, SafeBoxActivity.class);
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
//        SPUtils.getInstance().setSafeBoxPwd(md5);
//        UIHelper.okToast("设置密码成功");

    }

    private void setObjByReflect(GridPasswordView paramGridPasswordView) {
        try {
            getImeEdittext(paramGridPasswordView).setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ImeDelBugFixedEditText getImeEdittext(GridPasswordView paramGridPasswordView) {
        try {
            Field localField = Class.forName("com.jungly.gridpasswordview.GridPasswordView").getDeclaredField("mInputView");
            localField.setAccessible(true);
            return (ImeDelBugFixedEditText) localField.get(paramGridPasswordView);
//            return paramGridPasswordView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setClickShowKeyBoard(GridPasswordView paramGridPasswordView, final ImeDelBugFixedEditText paramImeDelBugFixedEditText) {
        paramGridPasswordView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
//                Log.e("MainActivity", "onClick: 点击");
                boolean bool = SoftKeyboardUtil.isSoftShowing(mContext);
//                paramAnonymousView = new StringBuilder();
//                paramAnonymousView.append("onClick: softShowing==");
//                paramAnonymousView.append(bool);
//                Log.i("MainActivity", paramAnonymousView.toString());
                if (!bool) {
                    SoftKeyboardUtil.showKeyBoard(mContext);
                    if (paramGridPasswordView != null) {
                        paramGridPasswordView.setFocusable(true);
                        paramImeDelBugFixedEditText.setFocusableInTouchMode(true);
                        paramImeDelBugFixedEditText.requestFocus();
                    }
                }
            }
        });
    }

    @Override
    protected void init() {

    }
}
