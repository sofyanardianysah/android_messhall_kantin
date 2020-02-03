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

}
