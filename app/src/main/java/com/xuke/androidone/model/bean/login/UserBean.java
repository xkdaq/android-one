package com.xuke.androidone.model.bean.login;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xuke on 2018/4/24.
 * <p>用户</p>
 */

@Entity
public class UserBean {

    private String position;
    private String birthday;
    private String alumni_id;
    private String sex;
    private String groupName;
    private String picture_xd;
    private String profession;
    @Id
    private String accountNum;
    private String phoneNum;
    private String intrestType;
    private String token;
    private String name;
    private String workUtil;
    private String pictureRT;
    private String departName;
    private String authenticated;
    private String isChangedSex;
    private String hobby;
    private String channels;
    private String refreshToken;
    private String baseInfoId;
    private String sign;
    private String picture;
    private String email;
    private String address;
    @Generated(hash = 1726547176)
    public UserBean(String position, String birthday, String alumni_id, String sex,
            String groupName, String picture_xd, String profession,
            String accountNum, String phoneNum, String intrestType, String token,
            String name, String workUtil, String pictureRT, String departName,
            String authenticated, String isChangedSex, String hobby,
            String channels, String refreshToken, String baseInfoId, String sign,
            String picture, String email, String address) {
        this.position = position;
        this.birthday = birthday;
        this.alumni_id = alumni_id;
        this.sex = sex;
        this.groupName = groupName;
        this.picture_xd = picture_xd;
        this.profession = profession;
        this.accountNum = accountNum;
        this.phoneNum = phoneNum;
        this.intrestType = intrestType;
        this.token = token;
        this.name = name;
        this.workUtil = workUtil;
        this.pictureRT = pictureRT;
        this.departName = departName;
        this.authenticated = authenticated;
        this.isChangedSex = isChangedSex;
        this.hobby = hobby;
        this.channels = channels;
        this.refreshToken = refreshToken;
        this.baseInfoId = baseInfoId;
        this.sign = sign;
        this.picture = picture;
        this.email = email;
        this.address = address;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getAlumni_id() {
        return this.alumni_id;
    }
    public void setAlumni_id(String alumni_id) {
        this.alumni_id = alumni_id;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getPicture_xd() {
        return this.picture_xd;
    }
    public void setPicture_xd(String picture_xd) {
        this.picture_xd = picture_xd;
    }
    public String getProfession() {
        return this.profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getAccountNum() {
        return this.accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public String getPhoneNum() {
        return this.phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getIntrestType() {
        return this.intrestType;
    }
    public void setIntrestType(String intrestType) {
        this.intrestType = intrestType;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWorkUtil() {
        return this.workUtil;
    }
    public void setWorkUtil(String workUtil) {
        this.workUtil = workUtil;
    }
    public String getPictureRT() {
        return this.pictureRT;
    }
    public void setPictureRT(String pictureRT) {
        this.pictureRT = pictureRT;
    }
    public String getDepartName() {
        return this.departName;
    }
    public void setDepartName(String departName) {
        this.departName = departName;
    }
    public String getAuthenticated() {
        return this.authenticated;
    }
    public void setAuthenticated(String authenticated) {
        this.authenticated = authenticated;
    }
    public String getIsChangedSex() {
        return this.isChangedSex;
    }
    public void setIsChangedSex(String isChangedSex) {
        this.isChangedSex = isChangedSex;
    }
    public String getHobby() {
        return this.hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public String getChannels() {
        return this.channels;
    }
    public void setChannels(String channels) {
        this.channels = channels;
    }
    public String getRefreshToken() {
        return this.refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getBaseInfoId() {
        return this.baseInfoId;
    }
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }
    public String getSign() {
        return this.sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getPicture() {
        return this.picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    
}
