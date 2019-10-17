package com.colin.anbet;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.colin.anbet.net.LocalCookieJar;
import com.colin.anbet.net.LoginBean;
import com.colin.anbet.util.MediaPlayUtil;
import com.colin.anbet.util.SPUtils;
import com.colin.anbet.util.SoundPoolUtil;
import com.colin.anbet.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import rxhttp.wrapper.param.RxHttp;

public class MyApp extends Application {
    private static final String TAG = "MyAppcation";
    private static MyApp mInstance;
    boolean accountBalance;
    boolean isLogin;
    private List<Activity> activities = new ArrayList<>();
    private int appCount;
    double balance;
    private long exitTime = 0L;
    private boolean isRunInBackground;
    String token;
    private LoginBean loginBean;

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
        mInstance = this;
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .cookieJar(new LocalCookieJar())  //为OkHttp设置自动携带Cookie的功能
                .build();
        RxHttp.init(client);
        SoundPoolUtil.getInstance(this);
        MediaPlayUtil.getInstance(this);
        SPUtils.getInstance().init(this);
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                // TODO Auto-generated method stub
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                // TODO Auto-generated method stub
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(), cb);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
//                if ((activity instanceof WebviewGameActivity)) {
//                    MediaPlayUtil.resume();
//                }
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

                appCount++;
                Log.e("start", String.valueOf(appCount));
                if (isRunInBackground) {
                    back2App(activity);
                }
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
//                if ((activity instanceof WebviewGameActivity)) {
//                    MediaPlayUtil.pause();
//                }
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                appCount--;
                Log.e("stop", String.valueOf(appCount));
                if (appCount == 0) {
                    leaveApp(activity);
                }
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    private void leaveApp(Activity activity) {
        isRunInBackground = true;
        MediaPlayUtil.pause();
    }

    private void back2App(Activity activity) {
        isRunInBackground = false;
        MediaPlayUtil.resume();
    }

    public static Context getContext() {
        return mInstance;
    }

    public static MyApp getInstance() {
        return mInstance;
    }

    public boolean getAccountBalance() {
        return this.accountBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getToken() {
        return this.token;
    }


    public void setAccountBalance(boolean paramBoolean) {
        this.accountBalance = paramBoolean;
    }

    public void setBalance(double paramDouble) {
        this.balance = paramDouble;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }


}
