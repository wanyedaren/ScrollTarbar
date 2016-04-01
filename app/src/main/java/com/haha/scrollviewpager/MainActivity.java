package com.haha.scrollviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){

        //把需要显示的fragment加入到list中
        List<Fragment> fragments = new ArrayList<>();
        ShowFragment showFragment1 = new ShowFragment(1);
        ShowFragment showFragment2 = new ShowFragment(2);
        ShowFragment showFragment3 = new ShowFragment(3);
        fragments.add(showFragment1);
        fragments.add(showFragment2);
        fragments.add(showFragment3);

        //设置tab的非选中状态的图片
        int[] imgs = new int[3];
        imgs[0] = R.drawable.all_game;
        imgs[1] = R.drawable.my_game;
        imgs[2] = R.drawable.setting;

        //设置tab的选中状态的图片
        int[] imgsActive = new int[3];
        imgsActive[0] = R.drawable.all_game_active;
        imgsActive[1] = R.drawable.my_game_active;
        imgsActive[2] = R.drawable.setting_active;

        //设置tab的文字
        String[] titles = new String[3];
        titles[0] = "游戏";
        titles[1] = "历史";
        titles[2] = "设置";

        //把图片文字放入到bundle中去
        Bundle args = new Bundle();
        args.putIntArray(VPFragment.TAG_IMG, imgs);
        args.putIntArray(VPFragment.TAG_IMG_ACTIVE, imgsActive);
        args.putStringArray(VPFragment.TAG_TITLE, titles);

        //显示vpfragment
        VPFragment vpFragment = VPFragment.newInstance(args, fragments);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, vpFragment);
        transaction.commit();

        //监听tab选中状态
        vpFragment.setSelectPageInterface(new VPFragment.SelectPageInterface() {
            @Override
            public void seletePage(int page) {

            }
        });



        setTitle(getString(R.string.app_name));
    }
}
