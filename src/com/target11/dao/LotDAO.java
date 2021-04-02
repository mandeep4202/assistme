package com.target11.dao;

import com.target11.database.DBHelper;
import com.target11.ui.lot.LotController;
import com.target11.utility.ObjectFormation;
import com.target11.vo.LinkVO;
import com.target11.vo.LotVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LotDAO {

  public static final Logger log = LoggerFactory.getLogger(LotController.class);
  Connection conn = DBHelper.getConnection();

  public void saveLot(LotVO lotVO) {
    conn = DBHelper.getConnection();
    String inset_Lot = "INSERT INTO L_LOT_TB(L_LOT_NAME) values(?);";

    try {
      PreparedStatement ps = conn.prepareStatement(inset_Lot);
      ps.setString(1, lotVO.getLotName());

      if (ps.executeUpdate() > 0) {
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (null != generatedKeys && generatedKeys.next()) {
          System.out.println(generatedKeys);
          log.info("Printing using get Lst Row {} ", generatedKeys.getInt(1));
          lotVO.setLotId(generatedKeys.getInt(1));
          saveToJunction(lotVO);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }  finally {
      DBHelper.close();
    }
  }


  public List<LotVO> fetchAllLot() {
    List<LotVO> lotVOList = new ArrayList<>();
    lotVOList = fetchLot(lotVOList);
    for (LotVO lotVO : lotVOList) {

      fetchLotLink(lotVO);
    }
    return lotVOList;

  }


  private List<LotVO> processRSTOLotVO(ResultSet rs, List<LotVO> lotVOList) throws SQLException {
    conn = DBHelper.getConnection();

    LotVO lotVO;

    while (rs.next()) {
      lotVO = new LotVO();
      lotVO.setLotId(rs.getInt(1));
      lotVO.setLotName(rs.getString(2));
      lotVOList.add(lotVO);
    }
    return lotVOList;
  }

  private List<LotVO> fetchLot(List<LotVO> lotVOList) {
    String fetchAllLots = "select * from L_LOT_TB";
    try {
      PreparedStatement ps = conn.prepareStatement(fetchAllLots);
      ResultSet rs = ps.executeQuery();
      lotVOList = processRSTOLotVO(rs, lotVOList);
      log.info("printing lotVO list {}",
          (lotVOList != null && !lotVOList.isEmpty()) ? lotVOList.get(0) : -1);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBHelper.close();
    }
    return lotVOList;
  }

  public LotVO fetchLotLink(LotVO lotVO) {

    String fetchLotLink =
        "select link.L_LINK_ID, link.L_NAME,link.L_PATH,link.L_TYPE,link.l_favorite \n" +
            "\t from LOT_LINK_JUNCTION jn\n" +
            "\t inner join L_LINK_TB link on link.L_LINK_ID = jn.L_LINK_ID\n" +
            "\t where jn.L_LOT_ID = ? ;";
    try {
      PreparedStatement ps = conn.prepareStatement(fetchLotLink);
      ps.setInt(1, lotVO.getLotId());
      ResultSet rs = ps.executeQuery();
      LinkVO linkVO;
      while (rs.next()) {
        linkVO = ObjectFormation.formLinkVO(rs);
        lotVO.getLinkVOList().add(linkVO);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBHelper.close();
    }
    return lotVO;
  }


  /*public void updateLot(LotVO lotVO) {
    conn = DBHelper.getConnection();
    String update_Lot = "UPDATE L_LOT_TB set L_LOT_NAME=? where L_LOT_ID = ?;";

    try {
      conn.setAutoCommit(false);
      PreparedStatement ps = conn.prepareStatement(update_Lot);
      ps.setString(1, lotVO.getLotName());
      ps.setInt(2, lotVO.getLotId());
      int i = ps.executeUpdate();

      LotVO lotVOTemp = fetchLotLink(lotVO);
      List<LinkVO> lotLinkList = lotVOTemp.getLinkVOList();

      List<LinkVO> linkToDelete = null;
      deletingFromJunction(lotVO, linkToDelete);
      saveToJunction(lotVO);
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBHelper.close();
    }
  }*/


  public int saveToJunction(LotVO lotVO) {
    log.info("Begin of method savingToJunction {} ", lotVO.getLotId());
    int[] inserted = null;
    conn = DBHelper.getConnection();
    String compiledQuery = "INSERT INTO LOT_LINK_JUNCTION(L_LOT_ID, L_LINK_ID )" +
        " VALUES (?, ?)";
    PreparedStatement ps;
    try {
      ps = conn.prepareStatement(compiledQuery);

      for (LinkVO linkVO : lotVO.getLinkVOList()) {
        ps.setInt(1, lotVO.getLotId());
        ps.setInt(2, linkVO.getLinkId());
        ps.addBatch();
      }
      inserted = ps.executeBatch();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    log.info("inserted link :: {}", null != inserted ? inserted.length : 0);
    return null != inserted ? inserted.length : 0;
  }


  public int deletingFromJunction(LotVO lotVO, List<LinkVO> linkToDelete) {
    log.info("Begin of deletingFromJunction ::  {}", lotVO.getLotId());
    int[] deleted = null;
    conn = DBHelper.getConnection();
    PreparedStatement ps;
    String deleteQueries = "delete from LOT_LINK_JUNCTION where L_LOT_ID= ? and L_link_ID= ? ";
    try {
      ps = conn.prepareStatement(deleteQueries);
      for (LinkVO linkVO : linkToDelete) {
        ps.setInt(1, lotVO.getLotId());
        ps.setInt(2, linkVO.getLinkId());
        ps.addBatch();
      }
      deleted = ps.executeBatch();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null != deleted ? deleted.length : 0;
  }


  public List<LinkVO> fetchLotLink(int lotId) {
    List<LinkVO> linkVOList = new ArrayList<>();
    String fetchLotLink =
        "select link.L_LINK_ID, link.L_NAME,link.L_PATH,link.L_TYPE,link.l_favorite \n" +
            "\t from LOT_LINK_JUNCTION jn\n" +
            "\t inner join L_LINK_TB link on link.L_LINK_ID = jn.L_LINK_ID\n" +
            "\t where jn.L_LOT_ID = ? ;";
    try {
      PreparedStatement ps = conn.prepareStatement(fetchLotLink);
      ps.setInt(1, lotId);
      ResultSet rs = ps.executeQuery();
      LinkVO linkVO;
      while (rs.next()) {
        linkVO = ObjectFormation.formLinkVO(rs);
        linkVOList.add(linkVO);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBHelper.close();
    }
    return linkVOList;
  }

}