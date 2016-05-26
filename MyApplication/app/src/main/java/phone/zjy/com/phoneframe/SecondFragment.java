package phone.zjy.com.phoneframe;

import android.os.Bundle;
import android.view.View;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class SecondFragment extends BaseFragment {
    private static String name;
    public static SecondFragment newInstance(String name){

        return new SecondFragment();
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }
}
