package com.colin.anbet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.R;
import com.colin.anbet.widget.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class ChargeHistoryDialog extends BaseDialogFragment {


    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_charge_history)
    SmartRefreshLayout srlChargeHistory;
    @BindView(R.id.msv_charge_history)
    MultipleStatusView msvChargeHistory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_charge_history, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    protected void init() {

    }

    private void initData() {
        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        msvChargeHistory.showLoading(R.layout.msv_loading_view,ll);
    }


    @OnClick({R.id.btn_close, R.id.srl_charge_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.srl_charge_history:
                break;
        }
    }
}
