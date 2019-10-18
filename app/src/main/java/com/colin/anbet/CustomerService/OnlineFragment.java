package com.colin.anbet.CustomerService;

import android.util.Log;

import com.colin.anbet.R;
import com.colin.anbet.X5WebView;
import com.colin.anbet.entity.CustomerServiceList;
import com.colin.anbet.fragment.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.util.UrlHelper;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

/**
 * @ProjectName: Anbet
 * @Package: com.colin.anbet.CustomerService
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/18 16:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/18 16:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class OnlineFragment extends BaseFragment {

    @BindView(R.id.web_view)
    X5WebView webView;
    private String online;

    @Override
    protected void init() {
        getCustomerList();
        webView.setBackgroundColor(0);

    }

    private void getCustomerList() {
        RxHttp.get(Url.listCustomer)
                .add("parentId", "0")
                .asParser(new CommonParser<List<CustomerServiceList>>(new TypeToken<BaseResponseBean<List<CustomerServiceList>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (!s.getData().isEmpty()) {
                            for (CustomerServiceList item : s.getData()) {
                                if (item.getCustomerName().equals("在线客服")) {
                                    webView.loadUrl("http://" + item.getCustomerContent());
                                    break;
                                }
                            }
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
        return R.layout.fragment_cus_user_vip;
    }


    @OnClick(R.id.web_view)
    public void onViewClicked() {
    }
}
