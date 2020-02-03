package dev.sofie.messhalkantin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kantin implements Parcelable {
    public static final Creator<Kantin> CREATOR = new Creator<Kantin>() {
        public Kantin createFromParcel(Parcel in) {
            return new Kantin(in);
        }

        public Kantin[] newArray(int size) {
            return new Kantin[size];
        }
    };
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private Integer f96id;
    @SerializedName("id_akun")
    @Expose
    private Integer idAkun;
    @SerializedName("id_magang")
    @Expose
    private Object idMagang;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("mp")
    @Expose

    /* renamed from: mp */
    private String f97mp;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    protected Kantin(Parcel in) {
        this.f96id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.keterangan = (String) in.readValue(String.class.getClassLoader());
        this.idAkun = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idMagang = in.readValue(Object.class.getClassLoader());
        this.deletedAt = in.readValue(Object.class.getClassLoader());
        this.createdAt = (String) in.readValue(String.class.getClassLoader());
        this.updatedAt = in.readValue(Object.class.getClassLoader());
        this.f97mp = (String) in.readValue(String.class.getClassLoader());
    }

    public Kantin() {
    }

    public Integer getId() {
        return this.f96id;
    }

    public void setId(Integer id) {
        this.f96id = id;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan2) {
        this.keterangan = keterangan2;
    }

    public Integer getIdAkun() {
        return this.idAkun;
    }

    public void setIdAkun(Integer idAkun2) {
        this.idAkun = idAkun2;
    }

    public Object getIdMagang() {
        return this.idMagang;
    }

    public void setIdMagang(Object idMagang2) {
        this.idMagang = idMagang2;
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

    public Object getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Object updatedAt2) {
        this.updatedAt = updatedAt2;
    }

    public String getMp() {
        return this.f97mp;
    }

    public void setMp(String mp) {
        this.f97mp = mp;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.f96id);
        dest.writeValue(this.keterangan);
        dest.writeValue(this.idAkun);
        dest.writeValue(this.idMagang);
        dest.writeValue(this.deletedAt);
        dest.writeValue(this.createdAt);
        dest.writeValue(this.updatedAt);
        dest.writeValue(this.f97mp);
    }

    public int describeContents() {
        return 0;
    }
}
