package com.haha.scrollviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwls on 15/12/31.
 */
public class VPFragment extends Fragment implements View.OnClickListener {

    public static final String TAG_IMG = "tag_res_img";
    public static final String TAG_IMG_ACTIVE = "tag_res_img_active";
    public static final String TAG_TITLE = "tag_title";
    public static final String TAG_TITLE_ACTIVE = "tag_title_active";

    private static List<Fragment> mFragments = new ArrayList<>();
    private View mView = null;
    private View[] mTabViews = null;
    private LeftRightSwipyViewPager vp_main;
    private SelectPageInterface selectPageInterface;
    private int preIndex = 0;

    public static VPFragment newInstance(Bundle args, List<Fragment> fragments) {
        VPFragment fragment = new VPFragment();
        fragment.setArguments(args);
        mFragments = fragments;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_vp, null);
        initView();
        return mView;
    }

    private void initView() {
        int[] imgs = getArguments().getIntArray(TAG_IMG);
        int[] imgs_active = getArguments().getIntArray(TAG_IMG_ACTIVE);
        String[] titles = getArguments().getStringArray(TAG_TITLE);

        mTabViews = new View[imgs.length];

        LinearLayout ll_tabbar_content = (LinearLayout) mView.findViewById(R.id.ll_tabbar_content);
        for (int i = 0; i < imgs.length; i++) {
            View tabItem = View.inflate(getActivity(), R.layout.layout_tabitem, null);
            ImageView iv_tab = (ImageView) tabItem.findViewById(R.id.iv_tab);
            ImageView iv_tab_active = (ImageView) tabItem.findViewById(R.id.iv_tab_active);
            TextView tv_tab = (TextView) tabItem.findViewById(R.id.tv_tab);
            TextView tv_tab_active = (TextView) tabItem.findViewById(R.id.tv_tab_active);

            iv_tab.setImageResource(imgs[i]);
            iv_tab_active.setImageResource(imgs_active[i]);
            tv_tab.setText(titles[i]);
            tv_tab_active.setText(titles[i]);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ll_tabbar_content.addView(tabItem, params);

            mTabViews[i] = tabItem;
            tabItem.setId(i);
            tabItem.setOnClickListener(this);

            if (i>0){
                setViewAlpha(tabItem.findViewById(R.id.tab), 1);
                setViewAlpha(tabItem.findViewById(R.id.tab_active), 0);
            }

        }


        vp_main = (LeftRightSwipyViewPager) mView.findViewById(R.id.vp_main);
        FragmentSmallAdapter adapter = new FragmentSmallAdapter(getChildFragmentManager(), mFragments);
        vp_main.setOffscreenPageLimit(mFragments.size());
        vp_main.setAdapter(adapter);
        vp_main.setChangeViewCallback(new LeftRightSwipyViewPager.ChangeViewCallback() {
            @Override
            public void changeView(boolean left, boolean right, int positon, float positionOffset) {
                if (right && positon < mFragments.size()) {
                    setViewAlpha(mTabViews[positon].findViewById(R.id.tab), positionOffset);
                    setViewAlpha(mTabViews[positon].findViewById(R.id.tab_active), 1 - positionOffset);
                    setViewAlpha(mTabViews[positon + 1].findViewById(R.id.tab), 1 - positionOffset);
                    setViewAlpha(mTabViews[positon + 1].findViewById(R.id.tab_active), positionOffset);
                } else if (left && positon > -1) {
                    setViewAlpha(mTabViews[positon].findViewById(R.id.tab), positionOffset);
                    setViewAlpha(mTabViews[positon].findViewById(R.id.tab_active), 1 - positionOffset);
                    if (positon < mFragments.size() - 1) {
                        setViewAlpha(mTabViews[positon + 1].findViewById(R.id.tab), 1 - positionOffset);
                        setViewAlpha(mTabViews[positon + 1].findViewById(R.id.tab_active), positionOffset);
                    }
                }
            }

            @Override
            public void currentPageIndex(final int index) {
                if (selectPageInterface != null) {
                    selectPageInterface.seletePage(index);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetTab(index);
                    }
                }, 200);
            }
        });

        resetTab(0);
    }

    @Override
    public void onClick(View view) {
        vp_main.setCurrentItem(view.getId());
    }

    //重置tab
    private void resetTab(int index) {
        if (index!=preIndex){
            setViewAlpha(mTabViews[preIndex].findViewById(R.id.tab), 1);
            setViewAlpha(mTabViews[preIndex].findViewById(R.id.tab_active), 0);
        }

        setViewAlpha(mTabViews[index].findViewById(R.id.tab), 0);
        setViewAlpha(mTabViews[index].findViewById(R.id.tab_active), 1);

        preIndex = index;
    }

    //设置view的透明值
    private void setViewAlpha(View view, float alpha) {
        view.setAlpha(alpha);
    }

    public interface SelectPageInterface {
        void seletePage(int page);
    }

    public void setSelectPageInterface(SelectPageInterface selectPageInterface) {
        this.selectPageInterface = selectPageInterface;
    }
}
