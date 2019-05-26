package com.example.demo.utils;

import javax.ws.rs.core.Response;

public class ResponseWithData<T> {
    private boolean success;
    private String message;
    private T data;

    public ResponseWithData(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response buildResponse() {
        if (this.isSuccess()) {
            return Response.status(Response.Status.OK).entity(this).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(this).build();
        }
    }
}
