package com.test.testactivedirectory.infrastructure.exception.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDto {
    private String message;
    private String error;
    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private Date date;

    public ErrorDto(String message, String error, int status, Date date) {
        super();
        this.message = message;
        this.error = error;
        this.status = status;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
