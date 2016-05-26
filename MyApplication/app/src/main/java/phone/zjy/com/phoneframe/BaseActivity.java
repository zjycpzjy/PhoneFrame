package phone.zjy.com.phoneframe;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public abstract class BaseActivity extends AppCompatActivity{
     //布局文件id
    protected abstract int getContentViewId();
    //布局中Fragment的id
    protected abstract int getFragmentContentId();

    //添加fragment

    protected void addFragment(BaseFragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(),fragment,fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除Fragment
    protected void removeFragment(){
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }
    }

    //返回键 返回事件

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode){
            if(getSupportFragmentManager().getBackStackEntryCount() == 1){
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
