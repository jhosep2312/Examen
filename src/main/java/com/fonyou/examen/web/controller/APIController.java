package com.fonyou.examen.web.controller;

import com.fonyou.examen.core.events.ResponseCode;
import com.fonyou.examen.core.events.ResponseEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class APIController {

    public static <T> ResponseEntity<ResponseEvent<T>> buildHttpResponse(ResponseEvent<T> responseEvent) {

        final var httpStatus = parseResponseCode(responseEvent.getCode());
        return new ResponseEntity<>(responseEvent, httpStatus);
    }

    public static HttpStatus parseResponseCode(final ResponseCode code) {
        switch (code) {
            case OK:
                return HttpStatus.OK;

            case NO_CONTENT:
                return HttpStatus.NO_CONTENT;

            case BAD_REQUEST:
                return HttpStatus.BAD_REQUEST;

            case CONFLICT:
                return HttpStatus.CONFLICT;

            case ERROR:
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
