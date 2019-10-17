package com.colin.anbet.net;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @ProjectName: Colin
 * @Package: com.chai.colin.net
 * @Description:
 * @Author: czc
 * @CreateDate: 2019/10/12 14:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/12 14:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LocalCookieJar implements CookieJar {
    List<Cookie> cookies;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        //这里可以做过滤处理
        this.cookies = cookies;
        //存储当前cookie,用于webview中同步cookie
//        CookiesManager.getInstance().cookieMap.put(url,cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (cookies != null)
            return cookies;
        return new ArrayList<>();
    }
}
