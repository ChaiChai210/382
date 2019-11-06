package com.colin.anbet.customerService;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.colin.anbet.R;
import com.colin.anbet.entity.CustomerServiceList;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.widget.RecycleViewDivider;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import rxhttp.wrapper.param.RxHttp;

public class VxFragment extends Fragment {
    private static final String TAG = "QqFragment1";
    RecyclerView mRecyclerView;
    VxAdapter mAdapter;
    List<CustomerServiceList> mDatas = new ArrayList<>();
    private Context mContext;

    private void getCustomerList() {
        RxHttp.get(Url.listCustomer)
                .add("parentId", "8866")
                .asParser(new CommonParser<List<CustomerServiceList>>(new TypeToken<BaseResponseBean<List<CustomerServiceList>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                        if (!s.getData().isEmpty()) {
                            mDatas.clear();
                            mDatas.addAll(s.getData());
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_cus_qq);
        mAdapter = new VxAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(mContext, 0,
                (int) mContext.getResources().getDimension(R.dimen.dp_12), ContextCompat.getColor(mContext, R.color.transparent)));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1, RecyclerView.HORIZONTAL, false));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
    }


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = inflater.inflate(R.layout.fragment_cus_qq, paramViewGroup, false);
        mContext = inflater.getContext();
        getCustomerList();
        initView(view);
        return view;
    }

}
