package com.target11;

import com.target11.utility.ProcessUtility;

public class Test {

  public static void main(String[] args) {
    new Test().runProcess();
  }

  private void processInk(){
    String name = "C:\\Users\\Mandeep Singh\\Desktop\\Search Everything.lnk";
    ProcessUtility.processLink(name);

  }


  private void runProcess(){

    ProcessUtility.runProcess("C:\\Program Files\\Everything\\Everything.exe", "APP");

  }

}
