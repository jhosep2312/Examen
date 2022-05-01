package com.fonyou.examen.core.events;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class ResponseEvent<T> {

    private ResponseCode code;
    private String message;
    private T data;

    public ResponseEvent<T> ok(String message) {
        return ok(message, null);
    }

    public ResponseEvent<T> ok(String message, T data) {
        setCode(ResponseCode.OK);
        setMessage(message);
        setData(data);
        return this;
    }

    public ResponseEvent<T> error(String message) {
        setCode(ResponseCode.ERROR);
        setMessage(message);
        return this;
    }

    public ResponseEvent<T> noContent(String message) {
        setCode(ResponseCode.NO_CONTENT);
        setMessage(message);
        return this;
    }

    public ResponseEvent<T> badRequest(String message) {
        setCode(ResponseCode.BAD_REQUEST);
        setMessage(message);
        return this;
    }

    public ResponseEvent<T> conflict(String message) {
        setCode(ResponseCode.CONFLICT);
        setMessage(message);
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
