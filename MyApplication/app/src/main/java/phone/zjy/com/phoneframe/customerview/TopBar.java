package phone.zjy.com.phoneframe.customerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import phone.zjy.com.phoneframe.R;

/**
 * Created by zhangjiaying on 16/5/29.
 */
public class TopBar extends RelativeLayout{
   private Button leftButton,rightButton;
   private TextView tvTitle;

    private float titleSize;
    private int titleColor;
    private String titleName;
    private Drawable titleBackground;


    private float leftSize;
    private int leftColor;
    private String leftName;
    private Drawable leftBackground;

    private float rightSize;
    private int rightColor;
    private String rightName;
    private Drawable rightBackground;

    private LayoutParams titleParam,leftParam,rightParam;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs , R.styleable.topBar);


        titleSize = ta.getDimension(R.styleable.topBar_titleSize,0);
        titleColor = ta.getColor(R.styleable.topBar_titleColor,0);
        titleName = ta.getString(R.styleable.topBar_titleName);
        titleBackground = ta.getDrawable(R.styleable.topBar_titleBackground);
        
        leftSize = ta.getDimension(R.styleable.topBar_leftSize,0);
        leftColor = ta.getColor(R.styleable.topBar_leftColor,0);
        leftName = ta.getString(R.styleable.topBar_leftName);
        leftBackground = ta.getDrawable(R.styleable.topBar_leftBackground);
        
        rightSize = ta.getDimension(R.styleable.topBar_rightSize,0);
        rightColor = ta.getColor(R.styleable.topBar_rightColor,0);
        rightName = ta.getString(R.styleable.topBar_rightName);
        rightBackground = ta.getDrawable(R.styleable.topBar_rightBackground);

        ta.recycle();

       leftButton = new Button(context);
       rightButton = new Button(context);
       tvTitle = new TextView(context);

        tvTitle.setText(titleName);
        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(titleSize);
        tvTitle.setBackground(titleBackground);
        tvTitle.setGravity(Gravity.CENTER);

        rightButton.setText(rightName);
        rightButton.setTextColor(rightColor);
        rightButton.setTextSize(rightSize);
        rightButton.setBackground(rightBackground);

        leftButton.setText(leftName);
        leftButton.setTextColor(leftColor);
        leftButton.setTextSize(leftSize);
        leftButton.setBackground(leftBackground);
        setBackgroundColor(getResources().getColor(R.color.colorAccent));

        leftParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        leftParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton,leftParam);

        rightParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        rightParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,rightParam);

        titleParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        titleParam.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(tvTitle,titleParam);

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarOnClickListener.rightOnClick();
            }
        });

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarOnClickListener.leftOnClick();
            }
        });
    }


    public void setVisiableLeft(boolean flag){
        if(flag){
           leftButton.setVisibility(VISIBLE);
        }else {
            leftButton.setVisibility(GONE);
        }
    }

    public void setVisiableRight(boolean flag){
        if(flag){
            rightButton.setVisibility(VISIBLE);
        }else {
            rightButton.setVisibility(GONE);
        }
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    TopBarOnClickListener topBarOnClickListener;
    public interface TopBarOnClickListener{
       public void leftOnClick();
       public void rightOnClick();
    }

    public void setOnTopBarOnClickListener(TopBarOnClickListener topBarOnClickListener){
        this.topBarOnClickListener = topBarOnClickListener;
    }


}
