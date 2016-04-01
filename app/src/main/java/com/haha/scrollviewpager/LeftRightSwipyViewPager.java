package com.haha.scrollviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;


/**
 * 重写，添加了判定滑动方向的方法
 *
 * @author zxy
 */
public class LeftRightSwipyViewPager extends ViewPager {
    private boolean left = false;
    private boolean right = false;
    private boolean isScrolling = false;
    private int lastValue = -1;
    private ChangeViewCallback changeViewCallback = null;

    public LeftRightSwipyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeftRightSwipyViewPager(Context context) {
        super(context);
        init();
    }

    /**
     * init method .
     */
    private void init() {

    }

    /**
     * listener ,to get move direction .
     */
    public OnPageChangeListener listener = new OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int arg0) {
            if (arg0 == 1) {
                isScrolling = true;
            } else {
                isScrolling = false;
            }
            if (arg0 == 2) {
                right = left = false;
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (lastValue < positionOffsetPixels) {
                // 递增，向右侧滑动
                right = true;
                left = false;
            } else if (lastValue > positionOffsetPixels) {
                // 递减，向左侧滑动
                right = false;
                left = true;
            } else if (lastValue == positionOffsetPixels) {
                right = left = false;
            }
            lastValue = positionOffsetPixels;

            changeViewCallback.changeView(left, right, position, positionOffset);

        }

        @Override
        public void onPageSelected(int arg0) {
            if (changeViewCallback != null) {
                changeViewCallback.currentPageIndex(arg0);
            }
        }
    };

    /**
     * 得到是否向右侧滑动
     *
     * @return true 为右滑动
     */
    public boolean getMoveRight() {
        return right;
    }

    /**
     * 得到是否向左侧滑动
     *
     * @return true 为左做滑动
     */
    public boolean getMoveLeft() {
        return left;
    }

    /**
     * 滑动状态改变回调
     *
     * @author zxy
     */
    public interface ChangeViewCallback {
        /**
         * 切换视图 ？决定于left和right 。
         *
         * @param left
         * @param right
         */
        public void changeView(boolean left, boolean right, int positon, float positionOffset);

        public void currentPageIndex(int index);
    }

    /**
     * set ...
     *
     * @param callback
     */
    public void setChangeViewCallback(ChangeViewCallback callback) {
        changeViewCallback = callback;
        addOnPageChangeListener(listener);
    }
}
