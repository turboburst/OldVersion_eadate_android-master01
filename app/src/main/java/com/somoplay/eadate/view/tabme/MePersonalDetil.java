package com.somoplay.eadate.view.tabme;

/**
 * Created by work on 2016-02-21.
 */
public class MePersonalDetil {

    private String name;
    private String id;
    private String qrcode;
    private String gender;
    private String address;
    private String age;
    private String marriage;
    private String signature;
    private String region;
    private String school;
    private String job;

    public MePersonalDetil(String name, String id, String qrcode,
                         String gender, String address, String age,
                         String marriage, String signature, String region,
                         String school, String job) {
        this.name = name;
        this.id = id;
        this.qrcode = qrcode;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.marriage = marriage;
        this.signature = signature;
        this.region = region;
        this.school = school;
        this.job = job;
    }

    public MePersonalDetil() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
