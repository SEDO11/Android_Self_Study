package org.techtown.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Mainfragment extends Fragment {
    private static final String TAG = "MainFragment";

//    onCreateView 메서드는 프래그먼트가 화면을 생성이 아닌 구성할 때, 즉 생성된 이후에 호출되는 역할을 합니다. 안에서는 inflate()메서드를 이용하여 fragment_main.xml로 연결합니다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        initUI(rootView);

        return rootView;
    }

//    initUI()메서드는 layoutManager와 어댑터를 이용하여 리사이클러뷰를 사용하는 역할을 할 것입니다.
    private void initUI(ViewGroup rootView) {

    }
}
