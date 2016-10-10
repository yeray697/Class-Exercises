package com.example.logintextinputlayout;

import android.app.Application;

/**
 * Created by usuario on 6/10/16.
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
