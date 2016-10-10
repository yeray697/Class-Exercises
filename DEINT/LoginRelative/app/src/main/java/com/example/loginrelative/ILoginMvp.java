package com.example.loginrelative;

/**
 * Created by usuario on 6/10/16.
 */

public interface ILoginMvp {
    int CORRECT = 0;
    int PASSWORD_DIGIT = 1;
    int PASSWORD_CASE = 2;
    int PASSWORD_LENGTH = 3;
    int DATA_EMPTY = 4;
    int MINLENGTH = 8;

    interface View{
        public void setMessageError(String messageError, int idView);
    }
    interface Presenter{
        public void validateCredentials(String user, String pass);
    }
}
