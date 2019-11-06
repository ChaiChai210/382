package com.colin.anbet.customerService;

import android.util.Log;
import android.widget.TextView;

import com.colin.anbet.R;
import com.colin.anbet.entity.CustomerServiceList;
import com.colin.anbet.base.BaseFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.util.List;

import butterknife.BindView;
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
public class FAQFragment extends BaseFragment {


    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void init() {
        getCustomerList();

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
                                if (item.getCustomerName().equals("常见问题")) {
//                                    textView.setText(Html.fromHtml(item.getCustomerContent()));
                                    textView.setText(item.getCustomerContent());
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
        return R.layout.fragment_customer_faq;
    }


}
