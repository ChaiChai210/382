package com.colin.anbet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.colin.anbet.R;

import butterknife.BindView;
import butterknife.OnClick;

public class BackDialog extends BaseDialogFragment {

    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.btn_cancel)
    ImageButton btnCancel;
    @BindView(R.id.btn_sure)
    ImageButton btnSure;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_logout, container);
        return view;
    }



    @OnClick({R.id.btn_close, R.id.btn_cancel, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_sure:
                dismiss();
//                EventBus.getDefault().postSticky(new RefreshVipInfo());
                mContext.finish();
                break;
        }
    }

    @Override
    protected void init() {

    }
}
