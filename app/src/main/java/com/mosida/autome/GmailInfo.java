package com.mosida.autome;

/**
 * Created by mosida on 5/15/17.
 */

public class GmailInfo {

    public String email;
    public String gid;
    public String password;
    public String comment;
    public String packageName;
    public String appCreator;
    public String review;

    public String getEmail() {
        return email;
    }

    public String getGid() {
        return gid;
    }

    public String getPassword() {
        return password;
    }

    public String getComment() {
        return comment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getAppCreator() {
        return appCreator;
    }

    public String getReview() {
        return review;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setAppCreator(String appCreator) {
        this.appCreator = appCreator;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
