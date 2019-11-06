package com.colin.anbet.activity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.colin.anbet.MainActivity;
import com.colin.anbet.R;
import com.colin.anbet.base.BaseActivity;
import com.colin.anbet.util.MediaPlayUtil;
import com.colin.anbet.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.ll_splash)
    LinearLayout llSplash;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

        int music = SPUtils.getInstance().getMusic(getMediaVolume());
        volumeBg = music / (float) getMaxVolume();
        volume = SPUtils.getInstance().getVolum(getMediaVolume()) / (float) getMaxVolume();
        if (isSilentMode()) {
            volume = 0.0F;
            volumeBg = 0.0F;
        }
        playBgMusic();
    }

    private void playBgMusic() {
        MediaPlayUtil.playSound(R.raw.music_game, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startSplashAnim();

    }


    private void startSplashAnim() {
        Animation anim = new AlphaAnimation(0.5F, 1.0F);
        anim.setDuration(1000);
        anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
        llSplash.startAnimation(anim);
    }


}
