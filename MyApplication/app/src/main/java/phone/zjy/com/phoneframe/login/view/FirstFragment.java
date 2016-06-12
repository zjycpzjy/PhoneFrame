package phone.zjy.com.phoneframe.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import phone.zjy.com.phoneframe.R;
import phone.zjy.com.phoneframe.base.BaseFragment;
import phone.zjy.com.phoneframe.login.model.User;
import phone.zjy.com.phoneframe.login.presenter.UserLoginPresenter;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class FirstFragment extends BaseFragment implements IUserLoginView{
    private static String name;
    private static String FIRSTTAG = "firstTag";
    @Bind(R.id.et_username)
    EditText username;
    @Bind(R.id.et_usersecret)
    EditText secret;
    @Bind(R.id.tv_confirm)
    TextView confirm;
    @Bind(R.id.tv_regist)
    TextView regist;
    @Bind(R.id.tv_forget)
    TextView forget;
    @Bind(R.id.progress)
    ProgressBar progress;
   private UserLoginPresenter userLoginPresenter;


    public static FirstFragment newInstance(String name){
        FirstFragment firstFragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FIRSTTAG,name);
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            name = getArguments().getString(FIRSTTAG);
        }
        userLoginPresenter = new UserLoginPresenter(getActivity(),this);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        username.setText(name);
        confirm.setOnClickListener(this);
        regist.setOnClickListener(this);
        forget.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.tv_confirm:
                 userLoginPresenter.login();
                 break;
             case R.id.tv_regist:
                 addFragment(new SecondFragment().newInstance("登录跳转需要注册"));
                 break;
             case R.id.tv_forget:
                 removeFragment();
                 break;
         }
    }

    @Override
    public String getUserName() {
        return username.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return secret.getText().toString().trim();
    }

    @Override
    public void showFailedError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toMainActivity(User user) {
        addFragment(new SecondFragment().newInstance("确认登录跳转"+user.getUserName()));
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearPassword() {
        secret.setText("");
    }

    @Override
    public void clearUserName() {
        username.setText("");
    }


}
