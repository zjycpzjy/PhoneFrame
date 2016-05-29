package phone.zjy.com.phoneframe.customerview;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import phone.zjy.com.phoneframe.R;
import phone.zjy.com.phoneframe.base.AppActivity;
import phone.zjy.com.phoneframe.base.BaseFragment;

/**
 * Created by zhangjiaying on 16/5/29.
 */
public class CustomerViewActivity extends AppActivity implements TopBar.TopBarOnClickListener{
    @Bind(R.id.top_bar)
    TopBar top_bar;
    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    protected String[] getPermissions(String... str) {
        return super.getPermissions(str);
    }

    @Override
    protected void setUpView(View view) {
        top_bar.setOnTopBarOnClickListener(this);
        top_bar.setTitle("自定义");
        top_bar.setVisiableLeft(false);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_customerview;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void leftOnClick() {
        Toast.makeText(this,"左边点击",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rightOnClick() {
        Toast.makeText(this,"右边点击",Toast.LENGTH_SHORT).show();
    }
}
