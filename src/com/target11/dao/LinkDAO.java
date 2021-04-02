package com.target11.dao;

import com.target11.database.DBHelper;
import com.target11.vo.LinkVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO {

    Connection conn = DBHelper.getConnection();

    public void insertLink(LinkVO linkVO) {

        String inset_Link = "INSERT INTO L_LINK_TB(L_NAME, L_PATH, L_TYPE, L_FAVORITE) values(?,?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(inset_Link);

            preparedStatement.setString(1, linkVO.getLinkName());
            preparedStatement.setString(2, linkVO.getLinkPath());
            preparedStatement.setString(3, linkVO.getLinkType());
            preparedStatement.setInt(4, linkVO.getFavorite());
//                    preparedStatement.setBigDecimal(2, new BigDecimal(799.88));
//                    preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); //1
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LinkVO> fetchLink() {
        String fetch_Links = "Select * from L_LINK_TB";
        List<LinkVO> linkVOList = new ArrayList<>();
        LinkVO linkVO = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(fetch_Links);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                linkVO = new LinkVO();
                linkVO.setLinkId(rs.getInt(1));
                linkVO.setLinkName(rs.getString(2));
                linkVO.setLinkPath(rs.getString(3));
                linkVO.setLinkType(rs.getString(4));
                linkVO.setFavorite(rs.getInt(5));
                linkVOList.add(linkVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkVOList;
    }


    public int updateLink(LinkVO linkVO) {

        String inset_Link = "update  L_LINK_TB set L_NAME = ? , L_TYPE= ? where L_LINK_ID = ?;";
        int row = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(inset_Link);
            preparedStatement.setString(1, linkVO.getLinkName());
            preparedStatement.setString(2, linkVO.getLinkType());
            preparedStatement.setInt(3, linkVO.getLinkId());
            row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); //1
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }





}
