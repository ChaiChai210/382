package com.colin.anbet.userinfo;


import com.colin.anbet.R;
import com.colin.anbet.widget.X5WebView;
import com.colin.anbet.base.BaseFragment;
import com.colin.anbet.util.UrlHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class UserVipFragment extends BaseFragment {


    @BindView(R.id.web_view)
    X5WebView webView;

    @Override
    protected void init() {
        webView.setBackgroundColor(0);
        webView.loadUrl(UrlHelper.URL_VIP);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cus_user_vip;
    }


    @OnClick(R.id.web_view)
    public void onViewClicked() {
    }
}
