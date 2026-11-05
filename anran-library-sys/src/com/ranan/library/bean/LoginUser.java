package com.ranan.library.bean;

public class LoginUser {
    private String name;
    private String keywords;

    /** 创建登录用户并保存登录名称与口令。 */
    public LoginUser(String name, String keywords) {
        this.name = name;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
