package phone.zjy.com.phoneframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class LoginActivity extends AppActivity{
    private String userName;
    @Override
    protected BaseFragment getFirstFragment() {
        return FirstFragment.newInstance(userName) ;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
       Bundle bundle = intent.getExtras();
        if(bundle != null){
            userName = bundle.getString("username");
        }
    }


    @Override
    protected int getContentViewId() {
        return super.getContentViewId();
    }

    @Override
    protected int getFragmentContentId() {
        return super.getFragmentContentId();
    }
}
