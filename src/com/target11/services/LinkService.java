package com.target11.services;

import com.target11.dao.LinkDAO;
import com.target11.vo.LinkVO;
import java.util.List;


public class LinkService {

  LinkDAO linkDAO = null;

  private LinkDAO getLinkDAO() {

    if (null == linkDAO) {
      linkDAO = new LinkDAO();
    }
    return linkDAO;
  }

  public List<LinkVO> fetchLink() {
    LinkDAO linkDAO = getLinkDAO();
    return linkDAO.fetchLink();
  }

  public void insertLink(LinkVO linkVO) {
    LinkDAO linkDAO = getLinkDAO();
    linkDAO.insertLink(linkVO);
  }


}
