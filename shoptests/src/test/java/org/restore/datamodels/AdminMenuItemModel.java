package org.restore.datamodels;

import java.util.ArrayList;

public class AdminMenuItemModel {
    private String itemName;
    private String pageHeader;
    private ArrayList<AdminMenuItemModel> subMenu;

    public String getItemName() {return itemName; }

    public String getPageHeader() {return pageHeader; }

    public ArrayList<AdminMenuItemModel> getSubMenu() {return subMenu; }
}
