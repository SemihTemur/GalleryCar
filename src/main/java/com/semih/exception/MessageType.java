package com.semih.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004", "Record not found"),
    TOKEN_IS_EXPIRED("1005", "Token has expired"),
    USERNAME_NOT_FOUND("1006", "Username not found"),
    USERNAME_OR_PASSWORD_INVALID("1007", "Username or password is invalid"),
    REFRESH_TOKEN_NOT_FOUND("1008", "Refresh token not found"),
    REFRESH_TOKEN_EXPIRED("1009", "Refresh token has expired"),
    CURRENCY_RATES_ERROR("1010", "Currency rates could not be retrieved"),
    INSUFFICIENT_FUNDS("1011", "You don't have enough money to make this purchase."),
    CAR_STATUS_SOLD("1011", "Car is sold"),
    GENERAL_EXCEPTION("9999", "A general error occurred");


    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
