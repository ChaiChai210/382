//package com.colin.anbet.net;
//
//import com.chai.colin.entity.UserBetBean;
//
//import java.io.IOException;
//
//import okhttp3.Response;
//import rxhttp.wrapper.parse.AbstractParser;
//
///**
// * @ProjectName: Colin
// * @Package: com.chai.colin.net
// * @Description:
// * @Author: czc
// * @CreateDate: 2019/10/9 15:06
// * @UpdateUser: 更新者
// * @UpdateDate: 2019/10/9 15:06
// * @UpdateRemark: 更新说明
// * @Version: 1.0
// */
//public class UserParser extends AbstractParser<UserBetBean> {
//    @Override
//    public UserBetBean onParse(Response response) throws IOException {
//        String content = getResult(response);
//        UserBetBean loginBean = GsonUtils.GsonToBean(content, UserBetBean.class);
//        return loginBean;
//    }
//
//}
