package com.p2mj.mall.entity;

public class MallUserUpdateParam {

    private  String nickName;

    private String passwordMd5;

    private String introduceSign;

    private String address;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public String getIntroduceSign() {
        return introduceSign;
    }

    public void setIntroduceSign(String introduceSign) {
        this.introduceSign = introduceSign;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MallUserUpdateParam{" +
                "nickName='" + nickName + '\'' +
                ", passwordMd5='" + passwordMd5 + '\'' +
                ", introduceSign='" + introduceSign + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
