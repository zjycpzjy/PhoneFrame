package phone.zjy.com.phoneframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected BaseActivity mActivity;
    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取布局文件
    protected abstract int getLayoutId();

    //获取宿主activity
    protected BaseActivity getHoldingActivity(){
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity)activity;
    }

    protected void addFragment(BaseFragment fragment){
        if(fragment != null){
            getHoldingActivity().addFragment(fragment);
        }
    }

    protected void removeFragment(){
        getHoldingActivity().removeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,view);
        initView(view,savedInstanceState);
        return view;
    }
}
