package com.somoplay.eadate.view.tabme;

/**
 * Created by work on 2016-02-21.
 */
public class MePersonalInfo {

    private String changeItem;
    private String detail;

    public MePersonalInfo(String changeItem, String detail) {
        this.changeItem = changeItem;
        this.detail = detail;
    }

    public String getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
