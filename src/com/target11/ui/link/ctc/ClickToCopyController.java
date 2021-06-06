package com.target11.ui.link.ctc;

import com.jfoenix.controls.JFXTextField;
import com.target11.services.LinkService;
import com.target11.utility.AppConstant;
import com.target11.vo.LinkVO;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClickToCopyController {

    public static final Logger log = LoggerFactory.getLogger(ClickToCopyController.class);

    @FXML
    private Pane ctcPrimaryPane;

    @FXML
    private JFXTextField ctcName;

    @FXML
    private JFXTextField ctcValue;

     LinkVO nlinkVO = null;
    private LinkService linkService;

    public void saveCTC(){
        log.info("Start of saveCTC");
        LinkService linkService = getLinkService();
        nlinkVO = new LinkVO();
        nlinkVO.setLinkName(ctcName.getText());
        nlinkVO.setLinkPath(ctcValue.getText());
        nlinkVO.setLinkType(AppConstant.LINK_TYPE_CTC);
        nlinkVO.setFavorite(0);

        linkService.insertLink(nlinkVO);
        getStage().close();
        log.info("End of saveCTC");
    }

    private LinkService getLinkService() {
        if (null == linkService) {
            linkService = new LinkService();
        }
        return linkService;
    }

    private Stage getStage() {
        return (Stage) ctcPrimaryPane.getScene().getWindow();
    }

}
