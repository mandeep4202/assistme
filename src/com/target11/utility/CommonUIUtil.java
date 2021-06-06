package com.target11.utility;

import com.jfoenix.controls.JFXButton;
import com.target11.ui.runner.MainController;
import com.target11.vo.LinkVO;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonUIUtil {
    public static final Logger log = LoggerFactory.getLogger(CommonUIUtil.class);
       static Map<String,String> linkTypeMap = new HashMap<>();
       private static void loadLinkType(){
           linkTypeMap.put("exe",AppConstant.LINK_TYPE_APP);
           linkTypeMap.put("doc",AppConstant.LINK_TYPE_DOC);
           linkTypeMap.put("docx",AppConstant.LINK_TYPE_DOC);
           linkTypeMap.put("jpeg",AppConstant.LINK_TYPE_DOC);
           linkTypeMap.put("pdf",AppConstant.LINK_TYPE_DOC);
           linkTypeMap.put("jpg",AppConstant.LINK_TYPE_DOC);
           linkTypeMap.put("DIR",AppConstant.LINK_TYPE_DIR);
       }

       static
       {
           loadLinkType();
       }


    /*public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            // setStageIcon(stage);
        } catch (IOException ex) {
            // Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }

*/
    public static Parent loadPane(URL loc) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            parent = loader.load();
            Stage stage = null;
//            MainController.getDisplayPane().getChildren().clear();
//            System.out.println(MainController.getDisplayPane().getChildren().size() );
//            MainController.getDisplayPane().getChildren().add(pane);
        } catch (IOException ex) {
            // Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return parent;
    }

    public static void loadScreen(URL loc) {
        Parent parent = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Pane pane = loader.load();
            Stage stage = null;
            MainController.getShareDisplayArea().getChildren().clear();
            //            System.out.println(MainController.getDisplayPane().getChildren().size() );
            MainController.getShareDisplayArea().getChildren().add(pane);
        } catch (IOException ex) {
            // Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }


    public static void addTOPane(Pane pane, Button btn) {
        pane.getChildren().addAll(btn);
    }


    public static String getLinkType(String ext) {

        String linkType =  linkTypeMap.get(ext);
        if(null == linkType )
        {
            linkType = "OTHER";
        }
        return linkType;
    }

    public static JFXButton createButton(String btnLabel){
        JFXButton jFXButton = new JFXButton(btnLabel);
        jFXButton.getStyleClass().add("button-raised");
        jFXButton.getStylesheets().add(CommonUIUtil.class
                .getResource("../ui/lot/lot.css")
                .toExternalForm());
        return jFXButton;
    }

    /**
     * Utility method which will return the List of objects which are not present in listB but present in listA.
     */
    public static List<LinkVO> filterList(List<LinkVO> listA, List<LinkVO> listB) {
        List<LinkVO> filterList = listA.stream()
            .filter(linkVO -> !listB.contains(linkVO))
            .collect(Collectors.toList());
        return filterList;
    }



    public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            //setStageIcon(stage);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
        return controller;

    }

        public static void setStageIcon(Stage stage) {
            stage.getIcons().add(new Image(""));
        }


        public static void showFlashNotification(String title, String content){
            Notifications notificationBuilder = Notifications.create()
                    .title(title)
                    .text(content)
                    .graphic(null)
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
        }


}
