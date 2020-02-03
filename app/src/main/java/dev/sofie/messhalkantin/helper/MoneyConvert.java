package dev.sofie.messhalkantin.helper;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyConvert {
    public static String keRupiah(Double uang) {
        String result = "0";
        try {
            return NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(uang);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }
    }
}
