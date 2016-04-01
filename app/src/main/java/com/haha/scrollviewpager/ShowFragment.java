package com.haha.scrollviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kwls on 16/3/31.
 */
public class ShowFragment extends Fragment{

    int index = 0;

    public ShowFragment(int index){
        this.index = index;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_show, null);
        TextView textView = (TextView) mView.findViewById(R.id.tv_show);
        textView.setText("第"+index+"页哈哈哈");
        return mView;
    }

}
