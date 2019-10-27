package com.colin.anbet.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.colin.anbet.R;
import com.colin.anbet.dialog.BaseDialogFragment;
import com.colin.anbet.util.SoundPoolUtil;
import com.colin.anbet.widget.LoadingDialog;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment
        extends Fragment {
    public static final String TAG = "BaseFragment";
    protected BaseActivity mContext;

    private Unbinder mUnbinder;

    protected LoadingDialog loadingDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
            mUnbinder = ButterKnife.bind(this, view);
            View loading = LayoutInflater.from(mContext).inflate(R.layout.layout_loading, null);
            this.loadingDialog = new LoadingDialog(Objects.requireNonNull(mContext), R.style.MobileDialog);
            this.loadingDialog.initDialog(loading);
            init();
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    protected abstract void init();

    protected abstract int getLayoutId();


    public void playMusic(int paramInt, float paramFloat) {
        SoundPoolUtil.play(paramInt, paramFloat);
    }


    public void stopMusic(int paramInt) {
        SoundPoolUtil.stop(paramInt);
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

    public void showFragment(BaseDialogFragment fm) {
        fm.show(getChildFragmentManager(), fm.getClass().getName());
    }

    public void showLoading() {
        loadingDialog.showDialog();
    }

    public void hideLoading() {
        loadingDialog.dismissDialog();
    }
}

