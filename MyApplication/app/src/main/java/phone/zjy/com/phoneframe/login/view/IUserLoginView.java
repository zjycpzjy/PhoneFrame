package phone.zjy.com.phoneframe.login.view;

import phone.zjy.com.phoneframe.login.model.User;

/**
 * Created by zhangjiaying on 16/6/12.
 */
public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError(String error);
}
