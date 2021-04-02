package com.target11.vo;

import java.util.Objects;

public class LinkVO extends EnterpriseVO {

    private int linkId;
    private String linkName;
    private String linkPath;
    private String linkType;
    private int favorite;


    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return linkName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkVO)) {
            return false;
        }
        LinkVO linkVO = (LinkVO) o;
        return getLinkId() == linkVO.getLinkId() &&
            getLinkName().equals(linkVO.getLinkName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLinkId(), getLinkName());
    }


}
