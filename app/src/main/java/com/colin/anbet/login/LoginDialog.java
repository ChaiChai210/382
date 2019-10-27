package com.colin.anbet.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.colin.anbet.R;
import com.colin.anbet.dialog.BaseDialogFragment;
import com.colin.anbet.event.LoginEvent;
import com.colin.anbet.net.LoginBean;
import com.colin.anbet.util.EventBusHelper;
import com.colin.anbet.util.SPUtils;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.Utils;
import com.rxjava.rxlife.RxLife;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.utils.LogUtil;

import static com.colin.anbet.util.Constants.LOGIN;


public class LoginDialog extends BaseDialogFragment {
    public static LoginDialog newInstance(String account, String password) {
        LoginDialog frag = new LoginDialog();
        Bundle args = new Bundle();
        args.putString("account", account);
        args.putString("password", password);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String account = getArguments().getString("account");
        String password = getArguments().getString("password");
        View view = inflater.inflate(R.layout.dialog_login, container);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_close).setOnClickListener(view1 -> dismiss()
        );

        final EditText et_account = view.findViewById(R.id.et_account);
        final EditText et_pwd = view.findViewById(R.id.et_pwd);
        final CheckBox checkbox = view.findViewById(R.id.cb_account);
        TextView textview = view.findViewById(R.id.tv_forget_pwd);
        TextView textview1 = view.findViewById(R.id.tv_go_register);
        ImageButton imagebutton = view.findViewById(R.id.btn_login);
        textview.setOnClickListener(view12 -> {
            dismiss();
//                UIHelper.goCustomerServiceActivity(MainActivity.this);
        });

        textview1.setOnClickListener(view13 -> {
            dismiss();
            mContext.showFragment(new RegisterDialog());

//               registerDialog.(MainActivity.this).registerEnter();
        });
        imagebutton.setOnClickListener(view14 -> {
            String str = et_account.getText().toString().trim();
            String password = et_pwd.getText().toString().trim();
            if (TextUtils.isEmpty(str)) {
                UIHelper.errorToastString("请输入您的账号");
                return;
            }
            if (!Utils.isAccount(str)) {
                UIHelper.errorToastString("您输入的账号有误");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                UIHelper.errorToastString("请输入您的密码");
                return;
            }
            if (!Utils.isPwd(password)) {
                UIHelper.errorToastString("请输入6-12位密码");
                return;
            }
            if (checkbox.isChecked()) {
                SPUtils.getInstance().setAccount(str);
                SPUtils.getInstance().setPwd(password);
            }
            submit(str, password);

        });
    }

    private void submit(String str, String password) {
        String finalResult = Utils.getLoginBase64(str, password);
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
                        dismiss();
                        SPUtils.getInstance().putObject(LOGIN, s);
                        EventBusHelper.post(new LoginEvent(s));
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }


    @Override
    protected void init() {

    }
}
