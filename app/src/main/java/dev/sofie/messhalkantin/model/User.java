package dev.sofie.messhalkantin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @SerializedName("id_messhall")
    @Expose
    private Integer idMesshall;
    @SerializedName("aktif")
    @Expose
    private Integer aktif;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("direktorat")
    @Expose
    private String direktorat;
    @SerializedName("divisi")
    @Expose
    private String divisi;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("id")
    @Expose
    /* renamed from: id */
    private Integer f100id;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;
    @SerializedName("meal")
    @Expose
    private Integer meal;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    protected User(Parcel in) {
        this.idMesshall = (Integer)in.readValue(Integer.class.getClassLoader());
        this.f100id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nik = (String) in.readValue(String.class.getClassLoader());
        this.nama = (String) in.readValue(String.class.getClassLoader());
        this.jabatan = (String) in.readValue(String.class.getClassLoader());
        this.department = (String) in.readValue(String.class.getClassLoader());
        this.divisi = (String) in.readValue(String.class.getClassLoader());
        this.direktorat = (String) in.readValue(String.class.getClassLoader());
        this.aktif = (Integer) in.readValue(Integer.class.getClassLoader());
        this.meal = (Integer) in.readValue(Integer.class.getClassLoader());
        this.email = in.readValue(Object.class.getClassLoader());
        this.emailVerifiedAt = in.readValue(Object.class.getClassLoader());
        this.role = (String) in.readValue(String.class.getClassLoader());
        this.deletedAt = in.readValue(Object.class.getClassLoader());
        this.createdAt = in.readValue(Object.class.getClassLoader());
        this.updatedAt = in.readValue(Object.class.getClassLoader());
    }

    public User() {
    }

    public Integer getId() {
        return this.f100id;
    }

    public void setId(Integer id) {
        this.f100id = id;
    }

    public String getNik() {
        return this.nik;
    }

    public void setNik(String nik2) {
        this.nik = nik2;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama2) {
        this.nama = nama2;
    }

    public String getJabatan() {
        return this.jabatan;
    }

    public void setJabatan(String jabatan2) {
        this.jabatan = jabatan2;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department2) {
        this.department = department2;
    }

    public String getDivisi() {
        return this.divisi;
    }

    public void setDivisi(String divisi2) {
        this.divisi = divisi2;
    }

    public String getDirektorat() {
        return this.direktorat;
    }

    public void setDirektorat(String direktorat2) {
        this.direktorat = direktorat2;
    }

    public Integer getAktif() {
        return this.aktif;
    }

    public void setAktif(Integer aktif2) {
        this.aktif = aktif2;
    }

    public Integer getMeal() {
        return this.meal;
    }

    public void setMeal(Integer meal2) {
        this.meal = meal2;
    }

    public Object getEmail() {
        return this.email;
    }

    public void setEmail(Object email2) {
        this.email = email2;
    }

    public Object getEmailVerifiedAt() {
        return this.emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt2) {
        this.emailVerifiedAt = emailVerifiedAt2;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role2) {
        this.role = role2;
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
        dest.writeValue(this.idMesshall);
        dest.writeValue(this.f100id);
        dest.writeValue(this.nik);
        dest.writeValue(this.nama);
        dest.writeValue(this.jabatan);
        dest.writeValue(this.department);
        dest.writeValue(this.divisi);
        dest.writeValue(this.direktorat);
        dest.writeValue(this.aktif);
        dest.writeValue(this.meal);
        dest.writeValue(this.email);
        dest.writeValue(this.emailVerifiedAt);
        dest.writeValue(this.role);
        dest.writeValue(this.deletedAt);
        dest.writeValue(this.createdAt);
        dest.writeValue(this.updatedAt);
    }

    public int describeContents() {
        return 0;
    }

    public Integer getIdMesshall() {
        return idMesshall;
    }

    public void setIdMesshall(Integer idMesshall) {
        this.idMesshall = idMesshall;
    }
}
