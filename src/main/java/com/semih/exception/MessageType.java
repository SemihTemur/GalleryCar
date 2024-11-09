package com.semih.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004","Kayıt Bulunamadı"),
    TOKEN_IS_EXPIRED("1005","Token is expired"),
    USERNAME_NOT_FOUND("1006","Username not found"),
    USERNAMR_OR_PASSWORD_INVALID("1007","Username or password is invalid"),
    REFRESH_TOKEN_NOT_FOUND("1008","Refresh token bulunamadı"),
    REFRESH_TOKEN_EXPIRED("1009","Refresh token expired"),
    CURRENY_RATES_IS_OCCURED("1010","Doviz kuru alinamadi"),
    GENERAL_EXCEPTION("9999","Genel bir hata oluştu");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
