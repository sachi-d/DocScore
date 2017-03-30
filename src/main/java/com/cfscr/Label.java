package com.cfscr;

import java.util.TreeMap;

/**
 * Created by Sachi on 3/30/2017.
 */
public class Label {


    public TreeMap<String, String> getDummyLabelList() {
        //TODO determine whether levels are Strings or Ints or choice of options
        TreeMap<String, String> dummyList = new TreeMap<String, String>();
        dummyList.put("nic", "exact");
        dummyList.put("dateOfBirth", "exact");
        dummyList.put("line1", "partial");
        dummyList.put("name", "partial");
        dummyList.put("university", "exact");
        dummyList.put("indexNo", "exact");
        return dummyList;
    }

}
