package dev.sofie.messhalkantin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guest implements Parcelable {
    public static final Creator<Guest> CREATOR = new Creator<Guest>() {
        public Guest createFromParcel(Parcel in) {
            return new Guest(in);
        }

        public Guest[] newArray(int size) {
            return new Guest[size];
        }
    };
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private Integer f95id;
    @SerializedName("id_akun")
    @Expose
    private Integer idAkun;
    @SerializedName("institusi")
    @Expose
    private String institusi;
    @SerializedName("member")
    @Expose
    private String member;
    @SerializedName("mulai")
    @Expose
    private String mulai;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nit")
    @Expose
    private String nit;
    @SerializedName("selesai")
    @Expose
    private String selesai;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    protected Guest(Parcel in) {
        this.f95id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nama = (String) in.readValue(String.class.getClassLoader());
        this.nit = (String) in.readValue(String.class.getClassLoader());
        this.institusi = (String) in.readValue(String.class.getClassLoader());
        this.member = (String) in.readValue(String.class.getClassLoader());
        this.status = (String) in.readValue(String.class.getClassLoader());
        this.mulai = (String) in.readValue(String.class.getClassLoader());
        this.selesai = (String) in.readValue(String.class.getClassLoader());
        this.idAkun = (Integer) in.readValue(Integer.class.getClassLoader());
        this.deletedAt = in.readValue(Object.class.getClassLoader());
        this.createdAt = in.readValue(Object.class.getClassLoader());
        this.updatedAt = in.readValue(Object.class.getClassLoader());
    }

    public Guest() {
    }

    public Integer getId() {
        return this.f95id;
    }

    public void setId(Integer id) {
        this.f95id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama2) {
        this.nama = nama2;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nit2) {
        this.nit = nit2;
    }

    public String getInstitusi() {
        return this.institusi;
    }

    public void setInstitusi(String institusi2) {
        this.institusi = institusi2;
    }

    public String getMember() {
        return this.member;
    }

    public void setMember(String member2) {
        this.member = member2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getMulai() {
        return this.mulai;
    }

    public void setMulai(String mulai2) {
        this.mulai = mulai2;
    }

    public String getSelesai() {
        return this.selesai;
    }

    public void setSelesai(String selesai2) {
        this.selesai = selesai2;
    }

    public Integer getIdAkun() {
        return this.idAkun;
    }

    public void setIdAkun(Integer idAkun2) {
        this.idAkun = idAkun2;
    }

    public Object getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Object deletedAt2) {
        this.deletedAt = deletedAt2;
    }

    public Object getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Object createdAt2) {
        this.createdAt = createdAt2;
    }

    public Object getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Object updatedAt2) {
        this.updatedAt = updatedAt2;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.f95id);
        dest.writeValue(this.nama);
        dest.writeValue(this.nit);
        dest.writeValue(this.institusi);
        dest.writeValue(this.member);
        dest.writeValue(this.status);
        dest.writeValue(this.mulai);
        dest.writeValue(this.selesai);
        dest.writeValue(this.idAkun);
        dest.writeValue(this.deletedAt);
        dest.writeValue(this.createdAt);
        dest.writeValue(this.updatedAt);
    }

    public int describeContents() {
        return 0;
    }
}
