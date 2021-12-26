package com.target11.utility;

import java.util.ArrayList;
import java.util.List;

public class ValidValues {

    public static List<String> linkType_List = null;

    static{
        load_linkType();
    }
    private  static void load_linkType(){
        linkType_List = new ArrayList<String>();
        linkType_List.add(AppConstant.LINK_TYPE_APP);
        linkType_List.add(AppConstant.LINK_TYPE_CTC);
        linkType_List.add(AppConstant.LINK_TYPE_DIR);
        linkType_List.add(AppConstant.LINK_TYPE_DOC);
        linkType_List.add(AppConstant.LINK_TYPE_OTHER);
        linkType_List.add(AppConstant.LINK_TYPE_URL_LAUNCHER );
 }




}
