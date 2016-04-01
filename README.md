# ScrollTarbar

可左右滑动的首页tab 
==========

#### 仿微信首页,渐变tab,左右滑动tab



模仿android版微信首页的效果，即首页tab项可以左右滑动，并且底部菜单项渐变透明。整个效果在一个fragment中实现，便于集成。

效果如下：

![image](https://github.com/wanyedaren/ScrollTarbar/blob/master/xiaoguo.gif)

---

 添加到项目中的代码：

```
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

```
  
  欢迎各位star，欢迎各位吐槽