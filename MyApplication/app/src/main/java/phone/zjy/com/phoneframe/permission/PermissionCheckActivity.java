package phone.zjy.com.phoneframe.permission;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import phone.zjy.com.phoneframe.R;
import phone.zjy.com.phoneframe.base.AppActivity;
import phone.zjy.com.phoneframe.base.BaseFragment;

/**
 * Created by zhangjiaying on 16/5/27.
 * 检测运行时权限
 * 1.在点击Button 的时候，来触发检测 请求 权限流程
 * 2.注意在MainFest里面声明 nomal和degerous权限
 * 3.注意权限组的概念
 * 4.注意 api的使用，
 * 5.对devicesSDK、tagarSdkVersion、的
 * 6.根据官方文档的规则来写  提前 把 项目用的 权限 整理出来
 *
 *
 * 7.需要适配 多种机型   例如：
 *    小米4  定位权限 和 联系人 权限。没有允许提示框（需要判断机型，来提示）。
 *    三星 s6 手机可以正常运行
 *
 */
public class PermissionCheckActivity extends AppActivity{
    @Bind(R.id.tv_permission_radio)
    TextView permission_radio;
    @Bind(R.id.tv_permission_location)
    TextView permission_location;
    @Bind(R.id.tv_permission_contact)
    TextView permission_contact;



    // 定位权限
    static final String[] PERMISSIONS_LOCATION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    // 录音权限
    static final String[] PERMISSIONS_AUDIO = new String[]{
                Manifest.permission.RECORD_AUDIO
    };

    // 联系人权限
    static final String[] PERMISSIONS_CONTANCT = new String[]{
            Manifest.permission.WRITE_CONTACTS
    };
    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    protected void setUpView(View view) {
        permission_radio.setOnClickListener(this);
        permission_location.setOnClickListener(this);
        permission_contact.setOnClickListener(this);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_permission;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_permission_radio:
                toCheckPermission(PERMISSIONS_AUDIO);
                break;
            case R.id.tv_permission_location:
                toCheckPermission(PERMISSIONS_LOCATION);
                break;
            case R.id.tv_permission_contact:
                toCheckPermission(PERMISSIONS_CONTANCT);
                break;
        }
    }
}
