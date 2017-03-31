package com.cfscr;

import java.util.TreeMap;

/**
 * Created by Sachi on 3/30/2017.
 */
public class Label {


    public static TreeMap<String, String> getLabelCompLevels() {
        //TODO get this from JSON
        //TODO determine whether levels are Strings or Ints or choice of options
        TreeMap<String, String> dummyList = new TreeMap<String, String>();
        dummyList.put("faculty", "exact");
        dummyList.put("initials", "exact");
        dummyList.put("line1", "partial");
        dummyList.put("line2", "partial");
        dummyList.put("nic", "exact");
        dummyList.put("studentIDCardID", "partial");
        dummyList.put("studentIDNo", "exact");
        dummyList.put("studentIDValidUntil", "exact");
        dummyList.put("surname", "exact");
        dummyList.put("university", "exact");
        dummyList.put("birthDate", "exact");
        dummyList.put("birthPlace", "partial");
        dummyList.put("nationality", "partial");
        dummyList.put("otherNames", "partial");
        dummyList.put("passportExpiryDate", "partial");
        dummyList.put("passportIssueDate", "partial");
        dummyList.put("passportNo", "exact");
        dummyList.put("photo", "partial");
        dummyList.put("profession", "partial");
        dummyList.put("sex", "exact");
        dummyList.put("NICCardId", "partial");
        dummyList.put("birthPlace", "partial");
        dummyList.put("fullname", "partial");
        dummyList.put("nicIssueDate", "partial");
        dummyList.put("degree", "partial");
        return dummyList;
    }

}
