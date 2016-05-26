package phoneframe.Permission;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查权限的工具类
 */
public class PermissionsChecker {
    private final Context mContext;

    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    // 判断权限集合
    public List<String>  lacksPermissions(String... permissions) {
        List<String> perssion = new ArrayList<String>();
        if(permissions != null && permissions.length != 0){
            Log.e("需要处理几个权限:", permissions.length+"");
            for (String permission : permissions) {
                Log.e("需要检测的权限:",permission+"");
                if (lacksPermission(permission)) {
                    perssion.add(permission);
                }
            }
        }
            return perssion;
    }

    // 判断是否缺少权限
    private boolean lacksPermission(String permission) {
        int targetSdkVersion = 0;
        try {
            final PackageInfo info = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

//        Log.e("获取的数据",ContextCompat.checkSelfPermission(mContext, permission)+"");
        Log.e("AS设置的targetSdkVersion:",targetSdkVersion+"");
        Log.e("设备的系统版本:",Build.VERSION.SDK_INT+"");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                Log.e("targetSdk大于23:", ContextCompat.checkSelfPermission(mContext, permission)+"");               // use Context#checkSelfPermission
                return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                //当点击 拒绝的 时候 返回 -2 SIGNATURE_SECOND_NOT_SIGNED 不知其原因
                Log.e("targetSdk小于23:", PermissionChecker.checkSelfPermission(mContext, permission)+"");
                return PermissionChecker.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
            }
        }else{
//            //if VERSION.SDK_INT< Android M 。should not make check.
//            Log.e("设备的系统版本 小于23:",PermissionChecker.checkSelfPermission(mContext, permission)+"");
//            return PermissionChecker.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
            return false;
        }
    }






    }




