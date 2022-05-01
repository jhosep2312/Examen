package com.fonyou.examen.core.events;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Map;

@Data
public class RequestEvent<E> {

    private E request;
    private Map<String, String> params;

    public RequestEvent(E request) {
        this.request = request;
    }

    public RequestEvent(E request, Map<String, String> params) {
        this.request = request;
        this.params = params;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
