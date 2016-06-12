package phone.zjy.com.phoneframe.login.model;

/**
 * Created by zhangjiaying on 16/6/12.
 */
public interface IUserBiz {
    public void login(String userName,String userPwd,UserLoginListener userLoginListener);
}
