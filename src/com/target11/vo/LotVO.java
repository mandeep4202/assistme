package com.target11.vo;

import java.util.ArrayList;
import java.util.List;

public class LotVO extends EnterpriseVO {

    private int lotId;
    private String lotName;
    private List<LinkVO> linkVOList = new ArrayList<>(1);

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public List<LinkVO> getLinkVOList() {
        return linkVOList;
    }

    public void setLinkVOList(List<LinkVO> linkVOList) {
        this.linkVOList = linkVOList;
    }

    @Override
    public String toString() {
        return "LotVO{" +
                "lotId=" + lotId +
                ", lotName='" + lotName + '\'' +
                ", linkVOList=" + ( linkVOList!=null && !linkVOList.isEmpty() ? linkVOList.size() : -1)  +
                '}';
    }
}
