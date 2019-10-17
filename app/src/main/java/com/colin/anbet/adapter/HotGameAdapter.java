package com.colin.anbet.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.HotGameList;
import com.colin.anbet.net.Url;

import java.util.List;

public class HotGameAdapter
        extends BaseQuickAdapter<HotGameList, BaseViewHolder> {

    public HotGameAdapter(@Nullable List<HotGameList> paramList) {
        super(R.layout.item_game_common, paramList);
    }

    private void setRoundImage(String url, ImageView imageView) {
        Glide.with(this.mContext).load(url).dontAnimate().skipMemoryCache(true).placeholder(R.drawable.placeholder).circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }

    private void setNormalImage(String url, ImageView imageView) {
        Glide.with(this.mContext).load(url).dontAnimate().skipMemoryCache(true).placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }

    protected void convert(BaseViewHolder vh, HotGameList item) {
        ImageView imageView = vh.getView(R.id.iv_head);
//        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//        if (vh.getLayoutPosition() == 0) {
//            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_160);
//        }
//        imageView.setLayoutParams(layoutParams);
        setRoundImage(Url.chessCardDir + item.getImageName(), imageView);
    }


}

