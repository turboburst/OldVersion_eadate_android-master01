package com.somoplay.eadate.view;

/**
 * Created by yangjiachang on 15-10-15.
 */
public class Profile {

    private String displayName, imageUrl, sex, city, ownerId, province, age, introduction,
            education, bodytpye, income, smoking;

    public Profile() {
    }

    public Profile(String displayName, String sex, String city, String imageUrl, String ownerId, String province, String age,
                   String introduction, String education, String bodytpye, String income, String smoking) {
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.sex = sex;
        this.city = city;
        this.imageUrl = imageUrl;
        this.ownerId = ownerId;
        this.age = age;
        this.education = education;
        this.province = province;
        this.bodytpye = bodytpye;
        this.smoking = smoking;
        this.introduction = introduction;
        this.income = income;

    }

    public String getName() {
        return displayName;
    }

    public void setName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserImageUrl() {
        return imageUrl;
    }

    public void setUserImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {

        return province;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBodytpye() {
        return bodytpye;
    }

    public void setBodytpye(String bodytpye) {
        this.bodytpye = bodytpye;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }
}
