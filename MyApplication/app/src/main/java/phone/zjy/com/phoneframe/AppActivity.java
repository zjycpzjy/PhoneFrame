package phone.zjy.com.phoneframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public abstract class AppActivity extends BaseActivity{
    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取intent
    protected void handleIntent(Intent intent){};

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
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }
}
