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

public class UserChangeAdapter
        extends BaseQuickAdapter<GameList, BaseViewHolder> {

    public UserChangeAdapter(@Nullable List<GameList> paramList) {
        super(R.layout.item_user_pop, paramList);
    }

    @Override
    protected void convert(BaseViewHolder helper, GameList item) {
        helper.setText(R.id.tv_user_pop, item.getName());
    }


//    protected void convert(BaseViewHolder vh, GameList item) {
//        ImageView imageView = vh.getView(R.id.iv_head);
//        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//        if (position == 0) {
//            if (vh.getLayoutPosition() == 0) {
//                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_160);
//            }
//            imageView.setLayoutParams(layoutParams);
//            setRoundImage(Url.secodeClaseGameUrl + item.getH5ImgUrl(), imageView);
//        } else if (position == 1) {
//            layoutParams.height = (int) mContext.getResources().getDimension(R.dimen.dp_163);
//            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_220);
//            imageView.setLayoutParams(layoutParams);
//            setNormalImage(Url.secodeClaseGameUrl +item.getH5ImgUrl(), imageView);
//
////            getRequest(item.getUrl()).into(new ImageViewTarget<Drawable>(imageView) {
////                @Override
////                protected void setResource(@Nullable Drawable resource) {
////
////                }
////            });
//        }else {
//            layoutParams.height = (int) mContext.getResources().getDimension(R.dimen.dp_196);
//            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_141);
//            imageView.setLayoutParams(layoutParams);
//            setNormalImage(Url.secodeClaseGameUrl +item.getH5ImgUrl(), imageView);
//        }
//
//
//    }


}

