package com.example.yrj.manageproduct.interfaces;

/**
 * @author Yeray Ruiz
 */

public interface ILoginMvp {
    int CORRECT = 0;
    int PASSWORD_DIGIT = 1;
    int PASSWORD_CASE = 2;
    int PASSWORD_LENGTH = 3;
    int DATA_EMPTY = 4;
    int MINLENGTH = 8;

    interface View{
        void setMessageError(String messageError, int idView);
    }
    interface Presenter{
        void validateCredentials(String user, String pass);
    }
}
