package com.colin.anbet.net;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Response;
import rxhttp.wrapper.parse.Parser;

public class CommonParser<T> implements Parser<BaseResponseBean<T>> {

    private TypeToken mTypeToken;

    public CommonParser(TypeToken typeToken) {
        mTypeToken = typeToken;
    }

//    private void startLogin() {
//        Intent exitIntent = new Intent();
//        exitIntent.setAction(CoreConfig.ACTION_EXIT_APP);
//        CoreConfig.getLocalBroadcastManager().sendBroadcast(exitIntent);
//        Intent intent = new Intent(XUI.getContext(), LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        XUI.getContext().startActivity(intent);
//    }

    @Override
    public BaseResponseBean<T> onParse(Response response) throws IOException {
        String content = getResult(response); //从Response中取出Http执行结果
        return GsonUtils.GsonToBean(content, mTypeToken.getType());
    }
}
