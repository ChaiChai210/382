package com.colin.anbet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.anbet.R;
import com.colin.anbet.util.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class ToastDialog extends BaseDialogFragment {

    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.tv_msg_content)
    TextView tvMsgContent;

    private String content;
    public static ToastDialog newInstance(String content) {

        Bundle args = new Bundle();
        args.putString(Constants.MESSAGE,content);
        ToastDialog fragment = new ToastDialog();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        content = getArguments().getString(Constants.MESSAGE);
        View view = inflater.inflate(R.layout.dialog_toast, container);
        return view;
    }


    @Override
    protected void init() {
        tvMsgContent.setText(content);
    }

    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        dismiss();
    }
}
