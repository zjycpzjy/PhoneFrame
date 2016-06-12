package phone.zjy.com.phoneframe.permission;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.List;

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
 *    华为note8 手机可以正常运行
 *
 */
public class PermissionCheckActivity extends AppActivity{
    @Bind(R.id.tv_permission_radio)
    TextView permission_radio;
    @Bind(R.id.tv_permission_location)
    TextView permission_location;
    @Bind(R.id.tv_permission_contact)
    TextView permission_contact;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    // 定位权限
    static final String[] PERMISSIONS_LOCATION = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
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
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );    //注册监听函数
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

//    @Override
//    protected void handleResult() {
//        super.handleResult();
//        locationMethod();
//    }


    public void locationMethod(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=500;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }
}
