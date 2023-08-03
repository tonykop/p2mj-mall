package com.p2mj.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 轮播图实体
 */
@Data
public class Carousel {

    private Integer carouselId;

    private String carouselUrl;

    private String redirectUrl;

    private Integer carouselRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS",timezone = "GMT+8")
    private Date createTime;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS",timezone = "GMT+8")
    private Date updateTime;

    private Integer updateUser;

//    public Integer getCarouselId() {
//        return carouselId;
//    }
//
//    public void setCarouselId(Integer carouselId) {
//        this.carouselId = carouselId;
//    }
//
//    public String getCarouselUrl() {
//        return carouselUrl;
//    }
//
//    public void setCarouselUrl(String carouselUrl) {
//        this.carouselUrl = carouselUrl == null ? null : carouselUrl.trim();
//    }
//
//    public String getRedirectUrl() {
//        return redirectUrl;
//    }
//
//    public void setRedirectUrl(String redirectUrl) {
//        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
//    }
//
//    public Integer getCarouselRank() {
//        return carouselRank;
//    }
//
//    public void setCarouselRank(Integer carouselRank) {
//        this.carouselRank = carouselRank;
//    }
//
//    public Byte getIsDeleted() {
//        return isDeleted;
//    }
//
//    public void setIsDeleted(Byte isDeleted) {
//        this.isDeleted = isDeleted;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Integer getCreateUser() {
//        return createUser;
//    }
//
//    public void setCreateUser(Integer createUser) {
//        this.createUser = createUser;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public Integer getUpdateUser() {
//        return updateUser;
//    }
//
//    public void setUpdateUser(Integer updateUser) {
//        this.updateUser = updateUser;
//    }
//
//    @Override
//    public String toString() {
//        return "Carousel{" +
//                "carouselId=" + carouselId +
//                ", carouselUrl='" + carouselUrl + '\'' +
//                ", redirecturl='" + redirectUrl + '\'' +
//                ", carouselRank=" + carouselRank +
//                ", isDeleted=" + isDeleted +
//                ", createTime=" + createTime +
//                ", createUser=" + createUser +
//                ", updateTime=" + updateTime +
//                ", updateUser=" + updateUser +
//                '}';
//    }
}
