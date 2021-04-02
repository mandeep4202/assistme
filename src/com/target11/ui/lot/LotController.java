package com.target11.ui.lot;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.target11.dao.LinkDAO;
import com.target11.dao.LotDAO;
import com.target11.services.LotService;
import com.target11.utility.CommonUIUtil;
import com.target11.vo.LinkVO;
import com.target11.vo.LotVO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.ListSelectionView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotController implements Initializable {

  public static final Logger log = LoggerFactory.getLogger(LotController.class);

  LotService lotService = null;

  private List<LinkVO> linkVOList;
  private LinkDAO linkDAO;
  private LotDAO lotDAO;

  @FXML
  private ListSelectionView<LinkVO> selectionView;

  @FXML
  private VBox prefrencesCP;

  @FXML
  private JFXButton saveLot;

  @FXML
  private VBox clScrollPaneList;

  @FXML
  private ScrollPane lotDispayArea;

  @FXML
  private JFXRadioButton updateLot;

  @FXML
  private JFXRadioButton newLot;

  List<LinkVO> masterAvailableList = null;

  LotVO selectedLot = null;

  JFXButton jfxButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadAllLink();
    loadAllLot();
  }

  private void loadAllLink() {
    linkDAO = new LinkDAO();
    lotDAO = new LotDAO();
    linkVOList = linkDAO.fetchLink();

    masterAvailableList = linkVOList;
    selectionView.getSourceItems().addAll(linkVOList);
    lotDispayArea.setVisible(false);

  }

  private void loadAllLot() {
    List<LotVO> lotVOList = lotDAO.fetchAllLot();
    clScrollPaneList.getChildren().clear();
    if (lotVOList != null) {
      for (LotVO lotVO : lotVOList) {
        log.info("THIs is loadAllLot method ");
        jfxButton = CommonUIUtil.createButton(lotVO.getLotName());
        jfxButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
            event -> fetchLinkOfLot(lotVO));
        clScrollPaneList.getChildren().addAll(jfxButton);
      }
    }
  }

  @FXML
  void saveOrUploadLot(ActionEvent event) {
    lotDAO = new LotDAO();
    lotService = getLotService();
    log.info("Firing the event in saveLot");
    List<LinkVO> linkVOList = selectionView.getTargetItems();
    LotVO lotVO = new LotVO();
    String lotNameT = "NoName";
    if (null != selectedLot) {
      lotNameT = selectedLot.getLotName();
      lotVO.setLotId(selectedLot.getLotId());
    }
    String lotName = inputLotName(lotNameT);
    if (null != lotName) {
      lotVO.setLotName(lotName);
      lotVO.setLinkVOList(linkVOList);

      if (updateLot.isSelected()) {

        lotService.updateLot(lotVO);
      } else if (newLot.isSelected()) {
        lotDAO.saveLot(lotVO);
      }

      loadAllLot();
    }
    log.info("Ending of saveOr upload Method");
  }

  public void fetchLinkOfLot(LotVO lotVO) {
    log.info(" This is fetchLinkOfLot :: {}", lotVO.getLotName());
    selectedLot = lotVO;
    lotDAO = new LotDAO();
    // Clearing the list so that duplicate data will not come
    lotVO.getLinkVOList().clear();
    LotVO lotVO1 = lotDAO.fetchLotLink(lotVO);
    selectionView.getTargetItems().clear();
    selectionView.getTargetItems().addAll(lotVO1.getLinkVOList());

    log.info("Size of fetched List  ::{} ", lotVO1.getLinkVOList().size());
    filterAvailableList();
  }

  private void filterAvailableList() {
    List<LinkVO> availableList = new ArrayList<>(masterAvailableList);
    log.info(" size of the available list {}", selectionView.getTargetItems().size());
    List<LinkVO> selectedList = selectionView.getTargetItems();

    log.info("availableList :: {} ", availableList.size());
    log.info("selectedList :: {} ", selectedList.size());

    List<LinkVO> diff = availableList.stream()
        .filter(linkVO -> !selectedList.contains(linkVO))
        .collect(Collectors.toList());

    log.info("diff After filter :: {} ", diff.size());
    selectionView.getSourceItems().clear();
    selectionView.getSourceItems().addAll(diff);
  }

  private String inputLotName(String lotName) {
    //String lotName = "No Name";
    TextInputDialog dialog = new TextInputDialog(lotName);
    dialog.setTitle("Name to Link");
    dialog.setHeaderText("Enter some text, or use default value.");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      lotName = result.get().toUpperCase();
    }
    return lotName;
  }


  @FXML
  void isUpdateOrAddMode(MouseEvent event) {
    String modeSelected = ((JFXRadioButton) event.getSource()).getId();
    log.info("{}", ((JFXRadioButton) event.getSource()).getId());
    log.info(" {} ", updateLot.isSelected());
    if ("updateLot".equalsIgnoreCase(modeSelected)) {
      lotDispayArea.setVisible(true);
      selectionView.getTargetItems().clear();
      selectionView.getSourceItems().clear();
      selectionView.getSourceItems().addAll(masterAvailableList);
    } else if ("newLot".equalsIgnoreCase(modeSelected)) {
      selectedLot = null;
      lotDispayArea.setVisible(false);
      selectionView.getSourceItems().clear();
      selectionView.getSourceItems().addAll(masterAvailableList);
      selectionView.getTargetItems().clear();
    }

  }

  private LotService getLotService() {

    return null != lotService ? lotService : new LotService();

  }


}
