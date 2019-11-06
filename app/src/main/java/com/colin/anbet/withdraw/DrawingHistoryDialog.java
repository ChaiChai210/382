package com.colin.anbet.withdraw;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.R;
import com.colin.anbet.dialog.BaseDialogFragment;
import com.colin.anbet.net.BaseResponseBean;
import com.colin.anbet.net.CommonParser;
import com.colin.anbet.net.Url;
import com.colin.anbet.recharge.LeftMenuBean;
import com.colin.anbet.util.ToastUtil;
import com.colin.anbet.widget.MultipleStatusView;
import com.google.gson.reflect.TypeToken;
import com.rxjava.rxlife.RxLife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rxhttp.wrapper.param.RxHttp;

public class DrawingHistoryDialog extends BaseDialogFragment {

    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_drawing_history)
    SmartRefreshLayout srlDrawingHistory;
    @BindView(R.id.msv_drawing_history)
    MultipleStatusView msvDrawingHistory;

    private int page = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_drawing_history, container);
        return view;
    }




    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    protected void init() {
        getHistory();
    }

    private void getHistory() {
        RxHttp.get(Url.listWithdrawalsRecord)
                .add("pageNo",page)
                .asParser(new CommonParser<List<LeftMenuBean>>(new TypeToken<BaseResponseBean<List<LeftMenuBean>>>() {
                }))
                .as(RxLife.asOnMain(this))//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    Log.e("请求成功", s.toString());
                    if (s.getStatus() == 1) {
                    } else {
                        ToastUtil.getInstance().showToast(s.getMsg());
                    }

                }, throwable -> {
                    Log.e("chai", throwable.getMessage());
                });
    }
}
