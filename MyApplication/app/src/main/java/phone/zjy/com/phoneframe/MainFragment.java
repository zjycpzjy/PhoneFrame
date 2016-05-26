package phone.zjy.com.phoneframe;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class MainFragment extends BaseFragment{
    @Bind(R.id.btn_login)
    Button mainBtn;
    @Bind(R.id.btn_back)
    Button backBtn;



    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mainBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Bundle bundle =  new Bundle();
                bundle.putString("username","jerry");
                Intent intent = new Intent(mActivity,LoginActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btn_back:
                addFragment(SecondFragment.newInstance("从首页跳转过来"));
                break;
        }
    }


}
