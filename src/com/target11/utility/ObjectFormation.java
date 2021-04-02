package com.target11.utility;

import com.target11.vo.LinkVO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectFormation {

    public static LinkVO formLinkVO(ResultSet rs ) throws SQLException {

        LinkVO linkVO = new LinkVO();
        linkVO.setLinkId(rs.getInt(1));
        linkVO.setLinkName(rs.getString(2));
        linkVO.setLinkPath(rs.getString(3));
        linkVO.setLinkType(rs.getString(4));
        linkVO.setFavorite(rs.getInt(5));

        return linkVO;
    }

}
