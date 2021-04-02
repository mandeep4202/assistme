package com.target11.ui.sidemenu;

import com.jfoenix.controls.JFXButton;
import com.target11.utility.CommonUIUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SideMenuController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommonUIUtil.loadScreen(getClass().getResource("../launch/launchPage.fxml"));
    }


    @FXML
    private void loadLaunchPage(ActionEvent event) {
        CommonUIUtil.loadScreen(getClass().getResource("../launch/launchPage.fxml"));
    }

    @FXML
    private void loadAddLinkPage(ActionEvent event) {
        CommonUIUtil.loadScreen(getClass().getResource("../link/addLink.fxml"));
    }

    @FXML
    private void loadExit(ActionEvent event) {
        //  CommonUIUtil.loadWindow(getClass().getResource("/library/assistant/ui/listmember/member_list.fxml"), "Member List", null);
    }

    @FXML
    private void loadInNew(ActionEvent event) {
        // CommonUIUtil.loadWindow(getClass().getResource("../link/addLink.fxml"),"Testing", null);
    }

    @FXML
    private void loadPreferencesPage(ActionEvent event) {
        CommonUIUtil.loadScreen(getClass().getResource("../lot/lot.fxml"));
    }


    @FXML
    private void loadManageLink(ActionEvent event) {
        CommonUIUtil.loadScreen(getClass().getResource("../manageLink/linkmanager.fxml"));
    }

}
