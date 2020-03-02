package dev.sofie.messhalkantin.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String hourOnly(String time) {
        String result = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new SimpleDateFormat("HH:mm:ss ").format(dateFormat.parse(time));
        } catch (ParseException e) {
            return result;
        }
    }

    public static String dateOnly(String time) {
        String result = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return new SimpleDateFormat("dd MMM yyyy").format(dateFormat.parse(time));
        } catch (ParseException e) {
            return result;
        }catch (Exception e){
            return result;
        }


    }

    public static String dateOnlyNow(){
        return new SimpleDateFormat("d MMM yyyy").format(new Date());
    }


}