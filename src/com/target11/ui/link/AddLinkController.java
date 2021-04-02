package com.target11.ui.link;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.target11.services.LinkService;
import com.target11.utility.CommonUIUtil;
import com.target11.utility.ProcessUtility;
import com.target11.vo.LinkVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public class AddLinkController {

    public static final Logger log = LoggerFactory.getLogger(AddLinkController.class);
    LinkService linkService = null;
    @FXML
    private JFXListView<Label> listView;
// @FXML
// private AnchorPane anchorPane;

    @FXML
    private ListView addLinkViewId = new ListView();

    private JFXPopup popup;

    @FXML
    private Label dragArea;

    @FXML
    void handleOnDragOver(DragEvent event) {
        log.info(" onDragOver Is not firing " + event.getDragboard().getContentTypes());
        // if (event.getDragboard().hasFiles()) {
        event.acceptTransferModes(TransferMode.ANY);
        // }

    }

    @FXML
    void onDragDone(DragEvent event) throws FileNotFoundException {
        log.info("Entering into onDragDone Method");
        List<File> files = event.getDragboard().getFiles();
        String originalPath = files.get(0).getPath();
        List<String> linkProperties = ProcessUtility.processLink(originalPath);

        LinkService linkService = getLinkService();
        String linkName = linkProperties.get(0);

        //String linkExt = linkProperties.size() >= 2 ? linkProperties.get(1) : "DIR";

        String linkExt = linkProperties.get(1);
        String modifiedPath = linkProperties.get(2);
        log.info("THis is after linkProperties" );
        System.out.println("Control is coming here " + files.get(0));
        System.out.println("linkExt :: " + linkExt);
        try {
            TextInputDialog dialog = getInputDialog(linkName);
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                linkName = result.get().toUpperCase();
                Label label = new Label(linkName + " :: " + modifiedPath);
                listView.getItems().add(label);
                listView.setExpanded(true);
                listView.depthProperty().set(1);

                LinkVO nlinkVO = new LinkVO();
                nlinkVO.setLinkName(linkName);
                nlinkVO.setLinkPath(originalPath);
                nlinkVO.setLinkType(CommonUIUtil.getLinkType(linkExt));
                nlinkVO.setFavorite(0);
                nlinkVO.setLinkPath(modifiedPath);
                linkService.insertLink(nlinkVO);
                System.out.println("entered " + linkName);
            }
            System.out.println("Is control coming here");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("existing from onDragDone Method");
    }

    private TextInputDialog getInputDialog(String linkName) {
        TextInputDialog dialog = new TextInputDialog(linkName);
        dialog.setTitle("Name to Link");
        dialog.setHeaderText("Enter some text, or use default value.");
        return dialog;
    }

  /*  private void label() {

        popup = new JFXPopup();
        JFXButton jf1 = new JFXButton("Btn 1");
        JFXButton jf2 = new JFXButton("Btn 2");
        JFXButton jf3 = new JFXButton("Btn 3");

        jf1.setPadding(new Insets(10));
        jf2.setPadding(new Insets(10));
        jf3.setPadding(new Insets(10));

        VBox vbox = new VBox(jf1, jf2, jf3);
        popup.setPopupContent(vbox);
    }*/

    @FXML
    void showPopup(MouseEvent event) {
        try {
            if (event.getButton() == MouseButton.SECONDARY) {
                popup.show(listView, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LinkService getLinkService() {
        if (null == linkService) {
            linkService = new LinkService();
        }
        return linkService;
    }




}