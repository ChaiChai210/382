package com.colin.anbet.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.colin.anbet.R;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BankListDialog extends BaseDialogFragment {


    @BindView(R.id.img_dialog_bg)
    ImageView imgDialogBg;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.v_split)
    View vSplit;
    @BindView(R.id.wheelView)
    WheelView wheelView;
    @BindView(R.id.iv_sure)
    ImageButton ivSure;

    private OnBankSelectedListener mSelectedListener;

    public interface OnBankSelectedListener {
        void onSelected(String bank);
    }

    public void setSelectedListener(OnBankSelectedListener mSelectedListener) {
        this.mSelectedListener = mSelectedListener;
    }

    private List<String> mBankList = new ArrayList<>();
    private String selectedBank;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_chose_bank, container);
    }


    @OnClick({R.id.btn_close, R.id.iv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.iv_sure:
                if (mSelectedListener != null) {
                    mSelectedListener.onSelected(selectedBank);
                }
                dismiss();
                break;
        }
    }

    @Override
    protected void init() {
        initData();
        wheelView.setCyclic(false);
        wheelView.setAdapter(new WheelAdapter<String>() {
            @Override
            public int getItemsCount() {
                return mBankList.size();
            }

            @Override
            public String getItem(int i) {
                return mBankList.get(i);
            }

            @Override
            public int indexOf(String s) {
             return mBankList.indexOf(s);
            }
        });
        wheelView.setOnItemSelectedListener(i -> {
            selectedBank = mBankList.get(i);
        });
    }



    private void initData() {
        mBankList.clear();
        mBankList.add("中国银行");
        mBankList.add("工商银行");
        mBankList.add("华夏银行");
        mBankList.add("建设银行");
    }
}
