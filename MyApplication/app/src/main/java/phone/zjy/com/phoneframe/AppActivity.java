package phone.zjy.com.phoneframe;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import phoneframe.Permission.PermissionsChecker;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public abstract class AppActivity extends BaseActivity implements View.OnClickListener{

    public static final int PERMISSIONS_GRANTED = 0; // 权限授权
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝
    final private int SDK_PERMISSION_REQUEST = 122;
    private static final int PERMISSION_REQUEST_CODE = 0; // 系统权限管理页面的参数
    private static final String EXTRA_PERMISSIONS =
            "me.chunyu.clwang.permission.extra_permission"; // 权限参数
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    private PermissionsChecker mChecker; // 权限检测器
    private boolean isRequireCheck; // 是否需要系统权限检测, 防止和系统提示框重叠


    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取intent
    protected void handleIntent(Intent intent){};

    // 返回传递的权限参数
    protected String[] getPermissions(String ... str) {
        return str;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        if(null != getIntent()){
            handleIntent(getIntent());
        }

        //避免重复添加 fragment
        if(null == getSupportFragmentManager().getFragments()){
           BaseFragment firstFragment = getFirstFragment();
            if(null != firstFragment){
                addFragment(firstFragment);
            }
        }

        mChecker = new PermissionsChecker(this);
        isRequireCheck = true;

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }


    @Override protected void onResume() {
        super.onResume();
        if (isRequireCheck) {
            final String[] permissions = getPermissions();
            List<String> permissionList = mChecker.lacksPermissions(permissions);
            if (permissionList != null && permissionList.size() > 0){
                Log.e("在activity里面检测",permissionList.size()+"");
                //在请求requestPermissions前，我们需要检查是否需要展示请求权限的提示通过activity的shouldShowRequestPermissionRationale

//                for(String permission :permissionList){
//                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this,permission)) {
                        requestPermissions(permissionList); // 请求权限

    //                    showMessageOKCancel("You need to allow access to Contacts",
    //                            new DialogInterface.OnClickListener() {
    //                                @Override
    //                                public void onClick(DialogInterface dialog, int which) {
    //                                    ActivityCompat.requestPermissions(PermissionsActivity.this,permissions,
    //                                            PERMISSION_REQUEST_CODE);
    //                                }
    //                            });
//                    }
//                }
            } else {
//                allPermissionsGranted(); // 全部权限都已获取
            }
        } else {
            isRequireCheck = true;
        }
    }



    // 请求权限兼容低版本
    private void requestPermissions(List<String> permissions) {
        ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), PERMISSION_REQUEST_CODE);
    }

//    // 全部权限均已获取
//    private void allPermissionsGranted() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            setResult(PERMISSIONS_GRANTED);
//        }
//        finish();
//    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE){
            if(hasAllPermissionsGranted(grantResults)) {
                isRequireCheck = true;
//                allPermissionsGranted();  走业务逻辑
            } else {
                for( int i = 0; i < permissions.length; i++ ) {
                    if( grantResults[i] == PackageManager.PERMISSION_GRANTED ) {
                        Log.e( "Permissions", "Permission Granted: " + permissions[i] );
                    } else if( grantResults[i] == PackageManager.PERMISSION_DENIED ) {
                        isRequireCheck = false;
                        showMissingPermissionDialog(permissions[i]);
                        Log.e( "Permissions", "Permission Denied: " + permissions[i] );
                    }
                }
            }
        }
    }

    // 含有全部的权限
    private boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    // 显示缺失权限提示
    private void showMissingPermissionDialog(String permissions) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AppActivity.this);
        builder.setTitle(R.string.help);
        String tip = permissions+"\n"+getResources().getString(R.string.string_help_text);
        builder.setMessage(tip);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
//                setResult(PERMISSIONS_DENIED);
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startAppSettings();

            }
        });

        builder.setCancelable(true);
        Dialog dialog = builder.show();
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(AppActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }




    @TargetApi(23)
    public void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
                return;
            }
        }
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case SDK_PERMISSION_REQUEST:
//
//                Map<String, Integer> perms = new HashMap<String, Integer>();
//                // Initial
//                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
//                // Fill with results
//                for (int i = 0; i < permissions.length; i++)
//                    perms.put(permissions[i], grantResults[i]);
//                // Check for ACCESS_FINE_LOCATION
//                if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                    // All Permissions Granted
//
//                } else {
//                    // Permission Denied
//                    Toast.showToast(this, "需要手动设置定位权限");
//                }
//                break;
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

}
