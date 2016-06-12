package phone.zjy.com.phoneframe.login.model;

/**
 * Created by zhangjiaying on 16/6/12.
 */
public interface UserLoginListener {
    public void loginSucess(User user);
    public void loginFail(String error);
    public void loginValidateFail(String error);
}
