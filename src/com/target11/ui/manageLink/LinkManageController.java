package com.target11.ui.manageLink;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.target11.dao.LinkDAO;
import com.target11.utility.AppConstant;
import com.target11.utility.ValidValues;
import com.target11.vo.LinkVO;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LinkManageController implements Initializable {

  @FXML
  private JFXTreeTableView<LinkVOTS> editTableView;
  private LinkDAO linkDAO;
  private List<LinkVO> linkVOList = null;


  @FXML
  private JFXTextField title;

  @FXML
  private JFXComboBox<String> linkTypeDD;

  @FXML
  private JFXTextField linkNameTF;
  @FXML
  private JFXTextField linkValueTF;

  @FXML
  private JFXButton updateLinkButton;
  private LinkVO linkVOFinal = new LinkVO();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    //linkTypeDD.getItems().addAll("APP", "DOC", "OTHER", "DIR","CTC");
    linkTypeDD.getItems().addAll(ValidValues.linkType_List);

    JFXTreeTableColumn<LinkVOTS, String> linkName = new JFXTreeTableColumn<>("Name");
    linkName.setPrefWidth(150);
    linkName.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<LinkVOTS, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<LinkVOTS, String> param) {
            return param.getValue().getValue().linkName;
          }
        });

    JFXTreeTableColumn<LinkVOTS, String> linkPath = new JFXTreeTableColumn<>("Path");
    linkPath.setPrefWidth(150);
    linkPath.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<LinkVOTS, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<LinkVOTS, String> param) {
            return param.getValue().getValue().linkPath;
          }
        });

    JFXTreeTableColumn<LinkVOTS, String> linkType = new JFXTreeTableColumn<>("Name");
    linkType.setPrefWidth(150);
    linkType.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<LinkVOTS, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<LinkVOTS, String> param) {
            return param.getValue().getValue().linkType;
          }
        });

    ObservableList<LinkVOTS> LinkVOTSs = FXCollections.observableArrayList();

    linkDAO = new LinkDAO();
    linkVOList = linkDAO.fetchLink();

    for (LinkVO linkVO : linkVOList) {
      LinkVOTSs.add(new LinkVOTS(String.valueOf(linkVO.getLinkId()), linkVO.getLinkName(),
          linkVO.getLinkPath(), linkVO.getLinkType()));
           /* editTableView.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    event -> System.out.println(linkVO.getLinkName()));*/

      editTableView.setOnMouseClicked((MouseEvent event) -> {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
          System.out.println(editTableView.getSelectionModel().getSelectedItem().getValue());
          LinkVOTS linkVOTSs = editTableView.getSelectionModel().getSelectedItem().getValue();
          displayForUpdate(linkVOTSs);
        }
      });


    }

    final TreeItem<LinkVOTS> root = new RecursiveTreeItem<>(LinkVOTSs,
        RecursiveTreeObject::getChildren);
    editTableView.getColumns().setAll(linkName, linkPath, linkType);
    editTableView.setRoot(root);
    editTableView.setShowRoot(false);
  }


  private void displayForUpdate(LinkVOTS linkVOTS) {
linkNameTF.setText(linkVOTS.linkName.getValue());
    linkTypeDD.getSelectionModel().select(linkVOTS.linkType.getValue());
    linkValueTF.setDisable(!AppConstant.LINK_TYPE_CTC.equals(linkVOTS.linkType.getValue()));
    linkValueTF.setText(linkVOTS.linkPath.getValue() );
   linkVOFinal.setLinkId(Integer.parseInt(linkVOTS.linkID.getValue()));

  }


  @FXML
  void updateLink(ActionEvent event) {
    linkVOFinal.setLinkName(linkNameTF.getText());
    linkVOFinal.setLinkType(linkTypeDD.getValue());
    linkVOFinal.setLinkPath(linkValueTF.getText());
    System.out.println("linkVOFinal :: " + linkVOFinal);
    linkDAO = new LinkDAO();
    int isUpdated = linkDAO.updateLink(linkVOFinal);
    System.out.println(" isUpdated " + isUpdated);
    resetUpdateForm();
  }

  private void resetUpdateForm() {
    linkTypeDD.getSelectionModel().clearSelection();
    linkTypeDD.getItems().addAll("APP", "DOC", "OTHER","DIR","CTC");
    linkNameTF.setText("");
    linkValueTF.setText("");
  }


}
