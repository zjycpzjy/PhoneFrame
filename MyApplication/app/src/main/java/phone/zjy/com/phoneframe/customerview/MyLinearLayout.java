package phone.zjy.com.phoneframe.customerview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by zhangjiaying on 16/6/14.
 */
public class MyLinearLayout extends LinearLayout{
    ViewDragHelper  mViewDragHelper;
    VelocityTracker mVelocityTracker;
    ViewConfiguration viewConfiguration;
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewConfiguration= ViewConfiguration.get(context);
        initViewDragHelper();
    }

    //初始化ViewDragHelper
    private void initViewDragHelper() {

        mViewDragHelper = ViewDragHelper.create(this,1.0f,new ViewDragHelper.Callback(){
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int fixedLeft;
                View parent = (View) child.getParent();
                int leftBound = parent.getPaddingLeft();
                int rightBound = parent.getWidth() - child.getWidth() - parent.getPaddingRight();
                Log.i("child.getWidth()",child.getWidth()+"");
                Log.i("parent.getPaddingLeft()",parent.getPaddingLeft()+"");
                Log.i("parent.getWidth()",parent.getWidth()+"");
                Log.i("getPaddingRight()",parent.getPaddingRight()+"");
                Log.i("left",left+"");
                Log.i("rightBound",rightBound+"");


                if(left >=600){
                    child.setAlpha(0.3f);
                }else{
                    child.setAlpha(1.0f);
                }



                if (left < leftBound) {
                    fixedLeft = leftBound;
                } else if (left > rightBound) {
                    fixedLeft = rightBound;
                } else {
                    fixedLeft = left;
                }
//获取touchSlop。该值表示系统所能识别出的被认为是滑动的最小距离
                return fixedLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int fixedTop;
                View parent = (View) child.getParent();
                int topBound = getPaddingTop();
                int bottomBound = getHeight() - child.getHeight() - parent.getPaddingBottom();
                if (top < topBound) {
                    fixedTop = topBound;
                } else if (top > bottomBound) {
                    fixedTop = bottomBound;
                } else {
                    fixedTop = top;
                }
                return fixedTop;
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                switch (state) {
                    case ViewDragHelper.STATE_DRAGGING:
                        System.out.println("STATE_DRAGGING");
                        break;
                    case ViewDragHelper.STATE_IDLE:
                        System.out.println("STATE_IDLE");
                        break;
                    case ViewDragHelper.STATE_SETTLING:
                        System.out.println("STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                System.out.println("ViewCaptured");
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                stopVelocityTracker();
                System.out.println("ViewReleased");
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        startVelocityTracker(event);
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void startVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private int getScrollVelocity() {
        // 设置VelocityTracker单位.1000表示1秒时间内运动的像素
        mVelocityTracker.computeCurrentVelocity(1000);
        // 获取在1秒内X方向所滑动像素值
        int xVelocity = (int) mVelocityTracker.getXVelocity();
        Log.e("滑动像素值",xVelocity+"");
        return Math.abs(xVelocity);
    }

    private void stopVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }
}
