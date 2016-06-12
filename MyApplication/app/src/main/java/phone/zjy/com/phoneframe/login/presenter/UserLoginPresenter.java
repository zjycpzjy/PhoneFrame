package phone.zjy.com.phoneframe.login.presenter;

import android.content.Context;
import android.os.Handler;

import phone.zjy.com.phoneframe.login.model.User;
import phone.zjy.com.phoneframe.login.model.UserBiz;
import phone.zjy.com.phoneframe.login.model.UserLoginListener;
import phone.zjy.com.phoneframe.login.view.IUserLoginView;

/**
 * Created by zhangjiaying on 16/6/12.
 */
public class UserLoginPresenter {
    private IUserLoginView userLoginView;
    private UserBiz userBiz;
    private Handler mHandler;
    public UserLoginPresenter(Context context,IUserLoginView userLoginView) {
            mHandler = new Handler();
            this.userLoginView = userLoginView;
            this.userBiz = new UserBiz();
    }

    public void login(){
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new UserLoginListener() {
            @Override
            public void loginSucess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFail(final String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError(error);
                        userLoginView.hideLoading();
                    }
                });
            }
            @Override
            public void loginValidateFail(final String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError(error);
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }
}
