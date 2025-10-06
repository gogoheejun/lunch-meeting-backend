package com.hj.lunchExpedition.common;
import lombok.Getter;

@Getter
public class BizResponse<T> {
    private boolean success;
    private T data;
    private String errorCode;
    private String message;

    public BizResponse(boolean success, T data, String errorCode, String message) {
        this.success = success;
        this.data = data;
        this.errorCode = errorCode;
        this.message = message;
    }

    // 성공 응답용 static 메서드
    public static BizResponse success() {
        return new BizResponse(true, null, null, "요청성공");
    }

    // 성공 응답용 static 메서드
    public static <T> BizResponse<T> success(T data) {
        return new BizResponse<>(true, data, null, "요청성공");
    }

    // 실패 응답용 static 메서드
    public static BizResponse fail(String errorCode, String message) {
        return new BizResponse(false, null, errorCode, message);
    }
}
