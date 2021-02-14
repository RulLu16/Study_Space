package com.clone.insta.util;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    // tag를 파싱해주는 클래스
    public static List<String> tagParser(String tags){
        String temp[] = tags.split("#");

        List<String> tagList = new ArrayList<String>();

        for(int i = 1; i < temp.length; i++){
            tagList.add(temp[i]);
        }

        return tagList;
    }
}
