package dev.sofie.messhalkantin.helper;

import android.content.Context;
import android.content.SharedPreferences;

import dev.sofie.messhalkantin.model.User;


public class SharedPreferecesHelper {
    public Context mContext;
    public static String userPreferences = "user";
    public static String userIDPref = "id";
    public static String messhallIDPref = "id_messhall";
    public static String namaPref = "nama";
    public static String nikPref = "nik";
    public static String rolePref = "role";
    public SharedPreferecesHelper(Context context){
        this.mContext = context;
    }

    public static SharedPreferecesHelper newInstance(Context context){
        return new SharedPreferecesHelper(context);
    }

    public void setUser(User user){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(userPreferences, Context.MODE_PRIVATE).edit();
        editor.putInt(userIDPref, user.getId());
        editor.putString(namaPref, user.getNama());
        editor.putString(nikPref, user.getNik());
        editor.putString(rolePref, user.getRole());
        editor.putInt(messhallIDPref,user.getIdMesshall());
        editor.apply();
    }

    public User getUser(){
        User user = new User();
        SharedPreferences perf = mContext.getSharedPreferences(userPreferences, Context.MODE_PRIVATE);
        user.setId(perf.getInt(userIDPref,0));
        user.setNama(perf.getString(namaPref,""));
        user.setNik(perf.getString(nikPref,""));
        user.setRole(perf.getString(rolePref,""));
        user.setIdMesshall(perf.getInt(messhallIDPref,0));
        return user;
    }

    public void clearUser(){
        SharedPreferences.Editor editor = this.mContext.getSharedPreferences(userPreferences, 0).edit();
        editor.clear();
        editor.commit();
    }

}