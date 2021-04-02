package com.target11.test;

import com.target11.dao.LinkDAO;
import com.target11.vo.LinkVO;

public class TestRunner {

    public static void main(String[] args) {

        try {
            //String rawFilePath = "C:\\Users\\Mandeep Singh\\Desktop\\docker Certificate.pdf";
           /* String rawFilePath = "C:\\Users\\Mandeep Singh\\Desktop\\Instant Eyedropper.lnk";
            String fullFileName = ProcessUtility.findFullFileName(rawFilePath);
            System.out.println("nameWithExt :: "+ fullFileName);

            if ("lnk".equals(ProcessUtility.getFileExtension(fullFileName))) {
               String modifiedPath =  ProcessUtility.inkProperties(new File(rawFilePath));
                fullFileName = ProcessUtility.findFullFileName(modifiedPath);
            }

            System.out.println( (ProcessUtility.findNameAndExt(fullFileName))[0] );
            System.out.println( (ProcessUtility.findNameAndExt(fullFileName))[1] );*/

            new TestRunner().testUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUpdate() {

        LinkVO linkVO = new LinkVO();
        linkVO.setLinkId(1);
        linkVO.setLinkName("Design Image");
        linkVO.setFavorite(0);

        System.out.println(new LinkDAO().updateLink(linkVO));


    }


}
