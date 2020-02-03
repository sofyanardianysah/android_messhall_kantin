package dev.sofie.messhalkantin.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            return new SimpleDateFormat("dd MMMM yyyy").format(dateFormat.parse(time));
        } catch (ParseException e) {
            return result;
        }catch (Exception e){
            return result;
        }


    }

    public static String dateOnlyNow(){
        return new SimpleDateFormat("d MMM Y").format(new Date());
    }

    public static String getDateNow(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("Y-M-d").format(date);
    }
}