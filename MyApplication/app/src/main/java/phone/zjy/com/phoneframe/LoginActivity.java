package phone.zjy.com.phoneframe;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class LoginActivity extends AppActivity{
    private String userName;
    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

//    static final String[] PERMISSIONS = new String[]{
//                Manifest.permission.RECORD_AUDIO
//    };

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

    @Override protected void onResume() {
        super.onResume();
    }


    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == AppActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }


    @Override
    protected String[] getPermissions(String... str) {
        return PERMISSIONS;
    }

    @Override
    public void onClick(View v) {

    }
}
