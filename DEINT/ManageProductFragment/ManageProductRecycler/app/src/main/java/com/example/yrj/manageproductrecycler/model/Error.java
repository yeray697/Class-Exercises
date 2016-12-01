package com.example.yrj.manageproductrecycler.model;

import android.content.Context;

import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.utils.ErrorMapUtils;

/**
 * Created by usuario on 14/11/16.
 */

public class Error {
    public static final int OK = 0;
    public static final int PASSWORD_DIGIT = 10;
    public static final int PASSWORD_CASE = 11;
    public static final int PASSWORD_LENGTH = 12;
    public static final int DATA_EMPTY = 13;
    public static final int EMAIL_INVALIDATE = 14;
    public static final int COUNTY_EMPTY = 15;
    public static final int CITY_EMPTY = 16;
    public static final int BUSINESS_EMPTY = 17;
    public static final int PRIVACY_FALSE = 18;

    private int code;
    private int idView;

    public Error() {}
    public Error(int idView, int codeError) {
        setIdView(idView);
        setCode(codeError);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getIdView() {
        return idView;
    }

    public void setIdView(int idView) {
        this.idView = idView;
    }

    public static String getMessageError(Context context, int code) {
        String message = ErrorMapUtils.getErrorMap(context).get(String.valueOf(code));
        message = context.getResources().getString(context.getResources().getIdentifier(message, "string", context.getPackageName()));
        return message;
    }
}
