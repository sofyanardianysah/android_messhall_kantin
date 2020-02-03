package dev.sofie.messhalkantin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Overview {


    @SerializedName("totalMeal")
    @Expose
    private Integer totalMeal;
    @SerializedName("totalTransaksiTamu")
    @Expose
    private Integer totalTransaksiTamu;

    @SerializedName("totalKasbon")
    @Expose
    private Double totalKasbon;
    @SerializedName("totalMagang")
    @Expose
    private Integer totalMagang;
    @SerializedName("totalLembur")
    @Expose
    private Integer totalLembur;

    public Integer getTotalMeal() {
        return totalMeal;
    }

    public void setTotalMeal(Integer totalMeal) {
        this.totalMeal = totalMeal;
    }

    public Integer getTotalTransaksiTamu() {
        return totalTransaksiTamu;
    }

    public void setTotalTransaksiTamu(Integer totalTransaksiTamu) {
        this.totalTransaksiTamu = totalTransaksiTamu;
    }

    public Double getTotalKasbon() {
        return totalKasbon;
    }

    public void setTotalKasbon(Double totalKasbon) {
        this.totalKasbon = totalKasbon;
    }

    public Integer getTotalMagang() {
        return totalMagang;
    }

    public void setTotalMagang(Integer totalMagang) {
        this.totalMagang = totalMagang;
    }

    public Integer getTotalLembur() {
        return totalLembur;
    }

    public void setTotalLembur(Integer totalLembur) {
        this.totalLembur = totalLembur;
    }

}
