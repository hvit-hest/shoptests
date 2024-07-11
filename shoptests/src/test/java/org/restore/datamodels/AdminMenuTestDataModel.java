package org.restore.datamodels;

import java.util.ArrayList;

public class AdminMenuTestDataModel {
    private String itemName;
    private String pageHeader;
    private ArrayList<AdminMenuTestDataModel> subMenu;

    public String getItemName() {return itemName; }

    public String getPageHeader() {return pageHeader; }

    public ArrayList<AdminMenuTestDataModel> getSubMenu() {return subMenu; }
}
