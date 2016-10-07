package com.example.loginrelative;

/**
 * Created by usuario on 6/10/16.
 */

public class User {
    private String user;
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        if(this.user != user)
            this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (this.password != password)
            this.password = password;
    }
}
