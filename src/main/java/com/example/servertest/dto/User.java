package com.example.servertest.dto;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigInteger;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID ;

    private String userName;

    private String userEmail;

    private String blogName;
    private String nickName;
    private String blogAddress;
    private String oauthServerId;
    private String oauthServer;
    private String porfileImage;
    private String userInfo;
    private String social1;
    private String social12;
    private String social13	;
    private String social14	;
    private Boolean reviewAlert = false;
    private Boolean updateAlert = false;
    private String mp3File;
    private Boolean autoPlay = false;


    private int Point = 10000;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public String getOauthServerId() {
        return oauthServerId;
    }

    public void setOauthServerId(String oauthServerId) {
        this.oauthServerId = oauthServerId;
    }

    public String getOauthServer() {
        return oauthServer;
    }

    public void setOauthServer(String oauthServer) {
        this.oauthServer = oauthServer;
    }

    public String getPorfileImage() {
        return porfileImage;
    }

    public void setPorfileImage(String porfileImage) {
        this.porfileImage = porfileImage;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getSocial1() {
        return social1;
    }

    public void setSocial1(String social1) {
        this.social1 = social1;
    }

    public String getSocial12() {
        return social12;
    }

    public void setSocial12(String social12) {
        this.social12 = social12;
    }

    public String getSocial13() {
        return social13;
    }

    public void setSocial13(String social13) {
        this.social13 = social13;
    }

    public String getSocial14() {
        return social14;
    }

    public void setSocial14(String social14) {
        this.social14 = social14;
    }

    public Boolean getReviewAlert() {
        return reviewAlert;
    }

    public void setReviewAlert(Boolean reviewAlert) {
        this.reviewAlert = reviewAlert;
    }

    public Boolean getUpdateAlert() {
        return updateAlert;
    }

    public void setUpdateAlert(Boolean updateAlert) {
        this.updateAlert = updateAlert;
    }

    public String getMp3File() {
        return mp3File;
    }

    public void setMp3File(String mp3File) {
        this.mp3File = mp3File;
    }

    public Boolean getAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(Boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        this.Point = point;
    }


}
