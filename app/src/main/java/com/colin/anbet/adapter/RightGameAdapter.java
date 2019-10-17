package com.colin.anbet.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.anbet.R;
import com.colin.anbet.entity.GameList;
import com.colin.anbet.net.Url;

import java.util.List;

public class RightGameAdapter
        extends BaseQuickAdapter<GameList, BaseViewHolder> {
    private int position;

    public RightGameAdapter(@Nullable List<GameList> paramList, int pos) {
        super(R.layout.item_girl, paramList);
        this.position = pos;
    }

    private void setRoundImage(String url, ImageView imageView) {
        Glide.with(this.mContext).load(url).dontAnimate().skipMemoryCache(true).placeholder(R.drawable.placeholder).circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }

    private void setNormalImage(String url, ImageView imageView) {
        Glide.with(this.mContext).load(url).dontAnimate().skipMemoryCache(true).placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }

    protected void convert(BaseViewHolder vh, GameList item) {
        ImageView imageView = vh.getView(R.id.iv_head);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (position == 0) {
            if (vh.getLayoutPosition() == 0) {
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_160);
            }
            imageView.setLayoutParams(layoutParams);
            setRoundImage(Url.secodeClaseGameUrl + item.getH5ImgUrl(), imageView);
        } else if (position == 1) {
            layoutParams.height = (int) mContext.getResources().getDimension(R.dimen.dp_163);
            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_220);
            imageView.setLayoutParams(layoutParams);
            setNormalImage(Url.secodeClaseGameUrl +item.getH5ImgUrl(), imageView);

//            getRequest(item.getUrl()).into(new ImageViewTarget<Drawable>(imageView) {
//                @Override
//                protected void setResource(@Nullable Drawable resource) {
//
//                }
//            });
        }else {
            layoutParams.height = (int) mContext.getResources().getDimension(R.dimen.dp_180);
            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_200);
            imageView.setLayoutParams(layoutParams);
            setNormalImage(Url.secodeClaseGameUrl +item.getH5ImgUrl(), imageView);
        }


    }


}

