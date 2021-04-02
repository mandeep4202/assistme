package com.target11.ui.runner;


import com.target11.utility.CommonUIUtil;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

  @FXML
  private AnchorPane root;

  @FXML
  private VBox sideMenuArea;

  @FXML
  private HBox headerArea;


  @FXML
  private Label main_clock_lb;

  @FXML
  private Pane displayArea;

  private static Pane shareDisplayArea;

  private static final DateTimeFormatter dateFormatterNew
      = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy - hh:mm:ss a");

  public static Pane getShareDisplayArea() {
    return shareDisplayArea;
  }

  public void setShareDisplayArea(Pane shareDisplayArea) {
    MainController.shareDisplayArea = shareDisplayArea;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    shareDisplayArea = displayArea;
    sideMenuArea.getChildren()
        .addAll(CommonUIUtil.loadPane(getClass().getResource("../sidemenu/sideMenu.fxml")));

    header_date();
  }


  private void header_date() {

    Thread timerThread = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(1000); //1 second
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Platform.runLater(() -> {
          main_clock_lb.setText(LocalDateTime.now().format(dateFormatterNew));
        });
      }
    });
    timerThread.start();

  }
}
