package com.example.loginrelative;

import android.app.Application;

/**
 * @autor Yeray Ruiz
 */

public class LoginApplication extends Application {
    private User user;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        if (this.user != user)
            this.user = user;
    }
}
