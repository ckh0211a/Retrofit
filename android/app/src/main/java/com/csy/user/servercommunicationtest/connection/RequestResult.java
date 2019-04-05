package com.csy.user.servercommunicationtest.connection;

public class RequestResult {
    public Model data;
    public String message;
    public boolean result;
    public int code;

    public Model getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "RequestResult{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", code=" + code +
                '}';
    }
}
