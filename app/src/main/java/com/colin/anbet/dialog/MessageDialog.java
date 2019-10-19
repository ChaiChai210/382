package com.colin.anbet.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.R;
import com.colin.anbet.adapter.MessageAdapter;
import com.colin.anbet.entity.MessageItem;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.recharge.LeftMenuAdapter;
import com.colin.anbet.widget.MultipleStatusView;
import com.colin.anbet.widget.RecycleViewDivider;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class MessageDialog extends BaseDialogFragment {

    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.msv_dialog_msg)
    MultipleStatusView msvDialogMsg;
    @BindView(R.id.srl_dialog_msg)
    SmartRefreshLayout srlDialogMsg;

    private MessageAdapter messageAdapter;
    private List<MessageItem> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getMessage();
        View view = inflater.inflate(R.layout.dialog_message, container);
        return view;
    }


    @Override
    protected void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new RecycleViewDivider(this, 1));
        messageAdapter = new MessageAdapter(list);
        recyclerView.setAdapter(messageAdapter);
        messageAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
    }
    private void getMessage() {
        RxHttp.get(Url.listSiteMessage)
                .asParser(new CommonParser<List<MessageItem>>(new TypeToken<BaseResponseBean<List<MessageItem>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    list.clear();
                    list.addAll(s.getData());
                    messageAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }

    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        dismiss();
    }
}
