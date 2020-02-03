package dev.sofie.messhalkantin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messhall implements Parcelable {
    public static final Creator<Messhall> CREATOR = new Creator<Messhall>() {
        public Messhall createFromParcel(Parcel in) {
            return new Messhall(in);
        }

        public Messhall[] newArray(int size) {
            return new Messhall[size];
        }
    };
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private Integer f98id;
    @SerializedName("id_akun")
    @Expose
    private Integer idAkun;
    @SerializedName("id_guest")
    @Expose
    private Object idGuest;
    @SerializedName("id_messhall")
    @Expose
    private Integer idMesshall;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("mp")
    @Expose

    /* renamed from: mp */
    private String f99mp;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    protected Messhall(Parcel in) {
        this.f98id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idMesshall = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idAkun = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idGuest = in.readValue(Object.class.getClassLoader());
        this.deletedAt = in.readValue(Object.class.getClassLoader());
        this.createdAt = (String) in.readValue(String.class.getClassLoader());
        this.updatedAt = (String) in.readValue(String.class.getClassLoader());
        this.f99mp = (String) in.readValue(String.class.getClassLoader());
        this.area = (String) in.readValue(String.class.getClassLoader());
        this.keterangan = (String) in.readValue(String.class.getClassLoader());
    }

    public Messhall() {
    }

    public Integer getId() {
        return this.f98id;
    }

    public void setId(Integer id) {
        this.f98id = id;
    }

    public Integer getIdMesshall() {
        return this.idMesshall;
    }

    public void setIdMesshall(Integer idMesshall2) {
        this.idMesshall = idMesshall2;
    }

    public Integer getIdAkun() {
        return this.idAkun;
    }

    public void setIdAkun(Integer idAkun2) {
        this.idAkun = idAkun2;
    }

    public Object getIdGuest() {
        return this.idGuest;
    }

    public void setIdGuest(Object idGuest2) {
        this.idGuest = idGuest2;
    }

    public Object getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Object deletedAt2) {
        this.deletedAt = deletedAt2;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt2) {
        this.createdAt = createdAt2;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt2) {
        this.updatedAt = updatedAt2;
    }

    public String getMp() {
        return this.f99mp;
    }

    public void setMp(String mp) {
        this.f99mp = mp;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area2) {
        this.area = area2;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan2) {
        this.keterangan = keterangan2;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.f98id);
        dest.writeValue(this.idMesshall);
        dest.writeValue(this.idAkun);
        dest.writeValue(this.idGuest);
        dest.writeValue(this.deletedAt);
        dest.writeValue(this.createdAt);
        dest.writeValue(this.updatedAt);
        dest.writeValue(this.f99mp);
        dest.writeValue(this.area);
        dest.writeValue(this.keterangan);
    }

    public int describeContents() {
        return 0;
    }
}
