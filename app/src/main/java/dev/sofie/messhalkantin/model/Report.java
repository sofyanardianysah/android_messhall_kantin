package dev.sofie.messhalkantin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("transaksi")
    @Expose
    private Integer transaksi;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("day")
    @Expose
    private String day;

    public Integer getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Integer transaksi) {
        this.transaksi = transaksi;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}