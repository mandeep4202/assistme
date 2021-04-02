package com.target11.ui.lot.lotPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LotManagerPane {

    @FXML
    private Label lotLinkNameId;

    @FXML
    private Button lot_button;

    @FXML
    private Button lot_button1;

    public Label getLotLinkNameId() {
        return lotLinkNameId;
    }

    public void setLotLinkNameId(Label lotLinkNameId) {
        this.lotLinkNameId = lotLinkNameId;
    }
}
