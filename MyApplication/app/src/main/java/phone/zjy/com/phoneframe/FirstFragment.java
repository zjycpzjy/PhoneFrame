package phone.zjy.com.phoneframe;

import android.os.Bundle;
import android.view.View;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class FirstFragment extends BaseFragment{
    private static String name;
    public static FirstFragment newInstance(String name){

        return new FirstFragment();
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    public void onClick(View v) {

    }
}
