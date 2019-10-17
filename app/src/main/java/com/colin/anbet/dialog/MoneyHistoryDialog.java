package com.colin.anbet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.colin.anbet.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class MoneyHistoryDialog extends BaseDialogFragment {


    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.rl_content)
    RecyclerView rlContent;
    @BindView(R.id.srl_refesh)
    SmartRefreshLayout srlRefesh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_moneyhistory, container);
        return view;
    }


    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    protected void init() {

    }
}
