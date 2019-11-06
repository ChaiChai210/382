package com.colin.anbet.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.colin.anbet.base.BaseActivity;
import com.colin.anbet.R;
import com.colin.anbet.widget.X5WebView;
import com.colin.anbet.dialog.BackDialog;
import com.colin.anbet.entity.GameBean;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.MediaPlayUtil;
import com.colin.anbet.util.UIHelper;
import com.colin.anbet.util.Utils;
import com.colin.anbet.widget.DragButton;
import com.rxjava.rxlife.RxLife;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class WebviewGameActivity extends BaseActivity {
    @BindView(R.id.fl_game_webview)
    FrameLayout flGameWebview;
    @BindView(R.id.btn_home)
    DragButton btnHome;
    private X5WebView mWebView;

    private String code;
    private String gameType;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview_game;
    }

    @Override
    protected void initView() {
//        Intent intent = getIntent();
//        if(intent != null){
//            String kindId = intent.getStringExtra("webUrl");
//        }
        mWebView = new X5WebView(this, null);
        flGameWebview.addView(mWebView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        code = getIntent().getStringExtra("liveCode");
        gameType = getIntent().getStringExtra("gameType");
        getGameUrl();

    }

    private void getGameUrl() {
        RxHttp.get(Url.GAME_URL)
                .add("liveCode", code)
                .add("gameType", gameType)
                .asObject(GameBean.class)
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus().equals("1")) {
                        mWebView.loadUrl(s.getD().getUrl());
//        CookieSyncManager.createInstance(this);
//        CookieSyncManager.getInstance().sync();
                        mWebView.setWebViewClient(new WebViewClient() {
                            public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
//                if (TextUtils.equals(paramString, UrlHelper.BAIDU_URL)) {
//                    Log.e("chai", "shouldInterceptRequest: 百度返回，不跳转");
//                    finish();
//                    return true;
//                }
                                paramWebView.loadUrl(paramString);
                                return true;
                            }
                        });
                    } else {
                        UIHelper.errorToastString(s.getM());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    //    private void showBackDialog()
//    {
//        if (this.f == null)
//        {
//            this.f = new Dialog(this, 2131558575);
//            this.f.setContentView(2131361851);
//            ((Window) Objects.requireNonNull(this.f.getWindow())).setFlags(1024, 1024);
//            Object localObject = ((Window)Objects.requireNonNull(this.f.getWindow())).getAttributes();
//            ((WindowManager.LayoutParams)localObject).gravity = 17;
//            ((WindowManager.LayoutParams)localObject).width = -1;
//            ((WindowManager.LayoutParams)localObject).height = -1;
//            localObject = (ImageButton)this.f.findViewById(2131230774);
//            ((TextView)this.f.findViewById(2131231409)).setText("返回游戏大厅");
//            ((ImageButton)localObject).setOnClickListener(new View.OnClickListener()
//            {
//                public void onClick(View paramView)
//                {
//                    WebviewGameActivity.this.f.dismiss();
//                }
//            });
//            ((ImageButton)this.f.findViewById(2131230771)).setOnClickListener(new View.OnClickListener()
//            {
//                public void onClick(View paramView)
//                {
//                    WebviewGameActivity.this.f.dismiss();
//                }
//            });
//            ((ImageButton)this.f.findViewById(2131230822)).setOnClickListener(new View.OnClickListener()
//            {
//                public void onClick(View paramView)
//                {
//                    WebviewGameActivity.this.f.dismiss();
//                    EventBus.getDefault().postSticky(new RefreshVipInfo());
//                    WebviewGameActivity.this.finish();
//                }
//            });
//        }
//        this.f.show();
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
        MediaPlayUtil.resume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int i;
        if (Utils.isScreenAutoRotate(this) != 1) {
            i = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        } else {
            i = ActivityInfo.SCREEN_ORIENTATION_USER;
        }
        setRequestedOrientation(i);
    }


    @OnClick(R.id.btn_home)
    public void onViewClicked() {
//        showBackDialog();
        showFragment(new BackDialog());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
