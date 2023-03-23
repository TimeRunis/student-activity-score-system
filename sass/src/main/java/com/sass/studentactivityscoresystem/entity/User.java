package com.sass.studentactivityscoresystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

@Component
public class User {
    @TableId(value = "user_id")
    private int userId;
    private String userName;
    private String realName;
    @JsonIgnore
    private String userPsw;
    private String userMail;
    private int gender;
    private String birthday;
    private int collegeId;
    private int authorityLevel;
    private int activityScore;
    private int totalActivityScore;
    private String createTime;
    private String updateTime;

    User(){
        this.birthday="1999-01-01";
        this.authorityLevel=1;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", relName='" + realName + '\'' +
                ", userPsw='" + userPsw + '\'' +
                ", userMail='" + userMail + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", collegeId=" + collegeId +
                ", authorityLevel=" + authorityLevel +
                ", activityScore=" + activityScore +
                ", totalActivityScore=" + totalActivityScore +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String relName) {
        this.realName = relName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public int getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    public int getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(int activityScore) {
        this.activityScore = activityScore;
    }

    public int getTotalActivityScore() {
        return totalActivityScore;
    }

    public void setTotalActivityScore(int totalActivityScore) {
        this.totalActivityScore = totalActivityScore;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
