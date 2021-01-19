package org.techtown.diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);
        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup viewGroup){
        // 인플레이션 후 xml 레이아웃 안의 위젯이나 레이아웃을 찾아 변수에 할당하는 코드 입력
    }
}
