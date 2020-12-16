package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {
    private Integer credentialId;
    private String url;
    private String userName;
    private String keya;
    private String password;
    private Integer userId;


    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKeya() {
        return keya;
    }

    public void setKeya(String keya) {
        this.keya = keya;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Credential(Integer credentialId, String url, String userName, String keya, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.keya = keya;
        this.password = password;
        this.userId = userId;
    }

    public Credential() {
    }
}
