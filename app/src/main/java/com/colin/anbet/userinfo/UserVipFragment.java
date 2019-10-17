package com.colin.anbet.userinfo;


import com.colin.anbet.R;
import com.colin.anbet.X5WebView;
import com.colin.anbet.fragment.BaseFragment;
import com.colin.anbet.util.UrlHelper;

import butterknife.BindView;

public class UserVipFragment extends BaseFragment {



    @Override
    protected void init() {
//        webView.setBackgroundColor(0);
//        webView.loadUrl(UrlHelper.URL_VIP);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cus_user_vip;
    }


}
