package com.somoplay.eadate.view.tabme;

import java.util.ArrayList;

/**
 * Created by work on 2016-02-21.
 */
public class MeMenuList {

    private String status;
    private String type;
    private String service;

    private ArrayList<MeMenu> meMenuArray = new ArrayList<>();

    public void updateStoreTypeNameList2(ArrayList<MeMenu> arrayList)
    {
        status ="";
        type ="";
        service = "";
        int count = arrayList.size();

        meMenuArray.clear();
        for(int i=0;i<count;i++) {
            MeMenu meMenu = arrayList.get(i);
            meMenuArray.add(meMenu);
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public ArrayList<MeMenu> getMeMenuArray() {
        return meMenuArray;
    }

    public void setMeMenuArray(ArrayList<MeMenu> meMenuArray) {
        this.meMenuArray = meMenuArray;
    }

}