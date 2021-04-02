package com.target11.ui.manageLink;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LinkVOTS extends RecursiveTreeObject<LinkVOTS> {


    StringProperty linkID;
    StringProperty linkName;
    StringProperty linkPath;
    StringProperty linkType;
    StringProperty favorite;


    public LinkVOTS(String linkID, String linkName, String linkPath, String linkType) {
        this.linkID = new SimpleStringProperty(linkID);
        this.linkName = new SimpleStringProperty(linkName);
        this.linkPath = new SimpleStringProperty(linkPath);
        this.linkType = new SimpleStringProperty(linkType);
    }


}
