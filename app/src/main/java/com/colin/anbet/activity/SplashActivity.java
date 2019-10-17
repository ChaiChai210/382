package com.colin.anbet.activity;


import android.content.Intent;
import android.media.MediaPlayer;

import com.colin.anbet.BaseActivity;
import com.colin.anbet.MainActivity;
import com.colin.anbet.R;
import com.colin.anbet.util.MediaPlayUtil;
import com.colin.anbet.util.SPUtils;


public class SplashActivity extends BaseActivity {

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
        gotoMainActivity();

    }

    private void gotoMainActivity() {
//        ARouter.getInstance().build("/user/LoginActivity").navigation();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
