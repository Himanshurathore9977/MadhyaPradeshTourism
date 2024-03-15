package com.mpt.MadhyaPradeshTourism.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mpt.MadhyaPradeshTourism.util.ConstantMessages;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class Response<T> {

    private int statusCode;
    private String message;
    boolean success = ConstantMessages.FALSE;
    /**
     * Can be a hashmap or list Spring will render a nice Json response :-)
     *
     */
    private T data;

    public Response(int statCode, String statusDesc) {
        statusCode = statCode;
        message = statusDesc;

        if (statusCode == HttpStatus.OK.value()) {
            success = ConstantMessages.TRUE;
        }

    }

    public Response() {
    }

    public static <T> Response<T> failedResponse(String message) {
        return failedResponse(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> Response<T> failedResponse(int statusCode, String message) {
        return failedResponse(statusCode, message, null);
    }

    public static <T> Response<T> failedResponse(String message, T data) {
        return failedResponse(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    public static <T> Response<T> failedResponse(int statusCode, String message, T data) {
        Response<T> response = new Response<>(statusCode, message);
        response.setSuccess(ConstantMessages.FALSE);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> successfulResponse(String message, T data) {
        return successfulResponse(HttpStatus.OK.value(), message, data);
    }

    public static <T> Response<T> successfulResponse(String message) {
        return successfulResponse(HttpStatus.OK.value(), message, null);
    }

    public static <T> Response<T> successfulResponse(int statusCode, String message, T data) {
        Response<T> response = new Response<>(statusCode, message);
        response.setSuccess(ConstantMessages.TRUE);
        response.setData(data);
        return response;
    }

}