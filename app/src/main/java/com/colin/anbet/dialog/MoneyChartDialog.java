package com.colin.anbet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.R;
import com.colin.anbet.widget.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class MoneyChartDialog extends BaseDialogFragment {


    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.rv_top_view)
    RecyclerView rvTopView;
    @BindView(R.id.tv_proxy_des)
    TextView tvProxyDes;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.msv_dialog_msg)
    MultipleStatusView msvDialogMsg;
    @BindView(R.id.srl_dialog_msg)
    SmartRefreshLayout srlDialogMsg;
    @BindView(R.id.tv_remake)
    TextView tvRemake;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_moneychart, container);
        return view;
    }


    @OnClick({R.id.btn_close, R.id.tv_remake})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.tv_remake:
                break;
        }
    }

    @Override
    protected void init() {

    }
}
