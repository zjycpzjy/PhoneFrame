package phone.zjy.com.phoneframe.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import phone.zjy.com.phoneframe.R;
import phone.zjy.com.phoneframe.base.BaseFragment;

/**
 * Created by zhangjiaying on 16/5/26.
 */
public class SecondFragment extends BaseFragment {
    private static String name;
    private static String FIRSTTAG = "firstTag";
    @Bind(R.id.tv_second)
    TextView tv_second;
    public static SecondFragment newInstance(String name){
        SecondFragment secondFragment =  new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FIRSTTAG,name);
        secondFragment.setArguments(bundle);
        return secondFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            name = getArguments().getString(FIRSTTAG);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_second.setText(name);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    public void onClick(View v) {

    }
}
