package dev.sofie.messhalkantin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiResponse<T> {
    @SerializedName("message")
    private String msg;
    @SerializedName("status")
    private Boolean status;
    @SerializedName("data")
    private T data;

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status2) {
        this.status = status2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
