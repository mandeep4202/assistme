package com.target11.ui.launch;

import com.jfoenix.controls.JFXButton;
import com.target11.services.LinkService;
import com.target11.services.LotService;
import com.target11.utility.AppConstant;
import com.target11.utility.CommonUIUtil;
import com.target11.utility.ProcessUtility;
import com.target11.vo.LinkVO;
import com.target11.vo.LotVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LaunchPage implements Initializable {

  private static final Logger logger = LoggerFactory.getLogger(LaunchPage.class);
  LinkService linkService = null;

  LotService lotService = null;




  @FXML
  private VBox lotLauncher;

  @FXML
  private VBox appLauncher;

  @FXML
  private VBox docDirLauncher;

  @FXML
  private VBox otherLauncher;


  private List<LinkVO> linkVOList = null;

  //private LinkDAO linkDAO;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    logger.info("This is the initialize method of Launch Page");
    prepareAllLinksBtn();
    prepareLotsBtn();
  }


  private void prepareAllLinksBtn() {
    logger.info("Start of prepareAllLinksBtn()");
    linkService = getLinkService();
    linkVOList = linkService.fetchLink();
    JFXButton jfxBtn = null;
    for (LinkVO linkVO : linkVOList) {
      jfxBtn = new JFXButton();
      jfxBtn.setText(linkVO.getLinkName());
      jfxBtn.getStyleClass().add("button-raised");
      jfxBtn.getStylesheets().add(getClass()
          .getResource("launchButton.css")
          .toExternalForm());

      jfxBtn.getStyleClass().add("launch-button");

      if (AppConstant.LINK_TYPE_APP.equals(linkVO.getLinkType())) {
        CommonUIUtil.addTOPane(appLauncher, jfxBtn);
      } else if (AppConstant.LINK_TYPE_DOC.equals(linkVO.getLinkType()) || AppConstant.LINK_TYPE_DIR.equals(linkVO.getLinkType())) {
        CommonUIUtil.addTOPane(docDirLauncher, jfxBtn);
      } else if (AppConstant.LINK_TYPE_CTC.equals(linkVO.getLinkType())) {
        CommonUIUtil.addTOPane(otherLauncher, jfxBtn);
      }

      jfxBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
          event -> ProcessUtility.runProcess(linkVO.getLinkPath(), linkVO.getLinkType()));
    }
    logger.info("Exit of prepareAllLinksBtn()");
  }


  private void prepareLotsBtn() {
    lotService = getLotService();
    List<LotVO> lotVOList = lotService.fetchAllLot();
    for (LotVO lotVO : lotVOList) {
      JFXButton jfxBtn = new JFXButton();
      jfxBtn.setText(lotVO.getLotName());
      jfxBtn.getStyleClass().add("button-raised");
      jfxBtn.getStylesheets().add(getClass()
          .getResource("launchButton.css")
          .toExternalForm());

      jfxBtn.getStyleClass().add("launch-button");

      CommonUIUtil.addTOPane(lotLauncher, jfxBtn);
      jfxBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
          event -> runLotsLink(lotVO));
    }

  }

  public void runLotsLink(LotVO lotVO) {
    logger.info("Begin of runLotsLink ");
    linkVOList = lotVO.getLinkVOList();
    for (LinkVO linkVO : linkVOList) {
      ProcessUtility.runProcess(linkVO.getLinkPath(), linkVO.getLinkType());
    }
    logger.info("End of runLotsLink ");
  }


  private LinkService getLinkService() {
    if (null == linkService) {
      linkService = new LinkService();
    }
    return linkService;
  }

  private LotService getLotService() {
    if (null == lotService) {
      lotService = new LotService();
    }
    return lotService;
  }


}