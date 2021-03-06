package org.project.media_comment.domain;

import java.util.Date;

/**
 * Created by hs on 2017-07-22.
 */
public class UserVO {

    private int user_id;
    private String user_email;
    private String user_password;
    private int user_exp;
    private String user_nickname;

    public int getUser_exp() {
        return user_exp;
    }

    public void setUser_exp(int user_exp) {
        this.user_exp = user_exp;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public UserVO() {} // VO 만들때 default생성자는 항상 만들어줘야한다.

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_email(){return user_email;}

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
