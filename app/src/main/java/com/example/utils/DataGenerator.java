package com.example.utils;

import android.support.v4.app.Fragment;

import com.example.fragment.ONE;
import com.example.fragment.THREE;
import com.example.fragment.TWO;

public class DataGenerator {

    public static Fragment[] getFragments(){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = ONE.newInstance();
        fragments[1] = TWO.newInstance();
        fragments[2] = THREE.newInstance();
        return fragments;
    }

}