package com.colin.anbet.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.colin.anbet.base.BaseActivity;
import com.colin.anbet.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment extends DialogFragment {

    protected BaseActivity mContext;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MobileDialog);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        init();
    }

    protected abstract void init();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.height = -2;
        attributes.width = -1;
        getDialog().getWindow().setAttributes(attributes);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (BaseActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();

    }


}
