package phone.zjy.com.phoneframe.login.model;

import android.text.TextUtils;

/**
 * Created by zhangjiaying on 16/6/12.
 */
public class UserBiz implements IUserBiz{
    @Override
    public void login(final String userName, final String userPwd, final UserLoginListener userLoginListener) {
           new Thread(){
               @Override
               public void run() {
                   try {
                       sleep(2000);
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }
                   if(TextUtils.isEmpty(userName)){
                       userLoginListener.loginValidateFail("用户名为空");
                       return;
                   }
                   if(TextUtils.isEmpty(userPwd)){
                       userLoginListener.loginValidateFail("密码为空");
                       return;
                   }
                   if(userPwd.length() > 16){
                       userLoginListener.loginValidateFail("密码应小于16位");
                       return;
                   }
                   //模拟登录成功
                   if ("zjy".equals(userName) && "123".equals(userPwd))
                   {
                       User user = new User();
                       user.setUserName(userName);
                       user.setUserPwd(userPwd);
                       userLoginListener.loginSucess(user);
                   } else
                   {
                       userLoginListener.loginFail("用户名和密码不正确");
                   }
               }
           }.start();
    }

}
