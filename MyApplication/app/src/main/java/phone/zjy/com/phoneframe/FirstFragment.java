package phone.zjy.com.phoneframe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class FirstFragment extends BaseFragment{
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
                 addFragment(new SecondFragment().newInstance("确认登录跳转"));
                 break;
             case R.id.tv_regist:
                 addFragment(new SecondFragment().newInstance("登录跳转需要注册"));
                 break;
             case R.id.tv_forget:
                 removeFragment();
                 break;
         }
    }
}
