package com.target11.services;

import com.target11.dao.LotDAO;
import com.target11.utility.CommonUIUtil;
import com.target11.vo.LinkVO;
import com.target11.vo.LotVO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotService {

  public static final Logger logger = LoggerFactory.getLogger(LotService.class);
  LotDAO lotDAO = null;

  private LotDAO getLotDAO() {
    if (null == lotDAO) {
      lotDAO = new LotDAO();
    }
    return lotDAO;
  }

  public void updateLot(LotVO lotVO) {
    LotDAO lotDAO = getLotDAO();
    List<LinkVO> masterLotLinkList = lotDAO.fetchLotLink(lotVO.getLotId());
    logger.info("fetching the list for lot " + lotVO.getLotId());

    logger.info("Printing the list to masterLotLinkList {}", masterLotLinkList.size());
    List<LinkVO> linkToDelete = CommonUIUtil.filterList(masterLotLinkList, lotVO.getLinkVOList());
    lotDAO.deletingFromJunction(lotVO, linkToDelete);
    logger.info("Printing the list to delete {}", linkToDelete.size());

    linkToDelete.stream().forEach(System.out::println);

    List<LinkVO> linkToInsert = CommonUIUtil.filterList(lotVO.getLinkVOList(), masterLotLinkList);
    linkToInsert.stream().forEach(System.out::println);
    lotVO.getLinkVOList().clear();
    lotVO.setLinkVOList(linkToInsert);
    lotDAO.saveToJunction(lotVO);
    logger.info("Printing the list to update {}", linkToInsert);
  }

  public List<LotVO> fetchAllLot() {
    LotDAO lotDAO = getLotDAO();
    return lotDAO.fetchAllLot();
  }

}
