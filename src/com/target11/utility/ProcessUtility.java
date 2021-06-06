package com.target11.utility;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import mslinks.ShellLink;
import mslinks.ShellLinkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtility {

  public static final Logger log = LoggerFactory.getLogger(ProcessUtility.class);

  public static String dirStructure(String filePath) {
    return filePath.replace("\\", "\\\\");
  }

  public static void runProcess(String path, String processType) {
    if (null != path) {

      try {
        if (AppConstant.LINK_TYPE_DIR .equals(processType)) {
          String completeCmd = "explorer.exe /select," + path;
          new ProcessBuilder(("explorer.exe " + completeCmd).split(" ")).start();
        } else if(AppConstant.LINK_TYPE_CTC.equals(processType)) {
          final ClipboardContent content = new ClipboardContent();
          content.put(DataFormat.PLAIN_TEXT, path );
          Clipboard.getSystemClipboard().setContent(content);
          CommonUIUtil.showFlashNotification("Copied","Copy to Clipboard");
} else {
          new ProcessBuilder("cmd", "/c", ProcessUtility.dirStructure(path)).start();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static String getActualPathFromShortcut(File shortcutFile) {
    String pathToExistingFile = "";
    try {
      pathToExistingFile = new ShellLink(shortcutFile).resolveTarget();
      log.info("pathToExistingFile {} ", pathToExistingFile);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ShellLinkException e) {
      e.printStackTrace();
    }
    System.out.println("String pathToExistingFile " + pathToExistingFile);
    return pathToExistingFile;

  }

  public static String getFileExtension(String fileName) {

    int lastIndexOf = fileName.lastIndexOf(".");
    if (lastIndexOf == -1) {
      return ""; // empty extension
    }
    return fileName.substring(lastIndexOf + 1);
  }


  public static String getFileNameWithExt(String filePath) {

    int lastIndexOf = filePath.lastIndexOf("\\");
    if (lastIndexOf == -1) {
      return ""; // empty extension
    }
    return filePath.substring(lastIndexOf + 1);
  }

  public static String[] splitFileNameAndExt(String filePath) {
    return filePath.split("\\.");
  }

  /**
   * Method will take the input of rawfile path, and return the list containing a properties of file name, extn, and path.
   * @param rawFilePath path detected during drag and drop
   * @return
   */
  public static List<String> processLink(String rawFilePath) {
    List<String> linkProperties = new ArrayList<>(3);
    try {
      String fileNameWithExt = ProcessUtility.getFileNameWithExt(rawFilePath);
      System.out.println("nameWithExt :: " + fileNameWithExt);
      String modifiedPath = rawFilePath;

      if (isShortCut(fileNameWithExt) ) {
        modifiedPath = ProcessUtility.getActualPathFromShortcut(new File(rawFilePath));
        fileNameWithExt = getFileNameWithExt(modifiedPath);
        log.info("modifiedPath Name {}", modifiedPath);
      }
     prepareLink(linkProperties,fileNameWithExt, modifiedPath);
      log.info("linkProperties {}", linkProperties.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return linkProperties;
  }

  /**
   * prepare the List containing file name, extn, path by splitting the filename from extn.
   * @param linkProperties
   * @param fileNameWithExt
   * @param modifiedPath
   */
  private static void prepareLink(List<String> linkProperties, String fileNameWithExt,String modifiedPath) {
    String[] properties = splitFileNameAndExt(fileNameWithExt);
    linkProperties.add(0, properties[0]);
    linkProperties.add(1, properties.length == 2 ? properties[1] : "DIR");
    linkProperties.add(2, modifiedPath);
  }

  /**
   * Method to check whether path is real path or shortcut link.
   * @param nameWithExt
   * @return
   */
  private static boolean isShortCut(String nameWithExt){
    return "lnk".equals(ProcessUtility.getFileExtension(nameWithExt));
  }

}
