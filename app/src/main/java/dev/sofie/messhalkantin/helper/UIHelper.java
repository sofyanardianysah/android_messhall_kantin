package dev.sofie.messhalkantin.helper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.Calendar;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.service.ApiRepository;
import dev.sofie.messhalkantin.ui.report.MesshallReportActivity;

public class UIHelper {

    @SuppressLint("ResourceAsColor")
    public static void showStatus(CardView cardView, TextView textView, String message, Context mContext, boolean status){
        if(status){

            cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorGreen));
        }else{
            cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorRed));
        }
        textView.setText(message);
        textView.setTextColor(mContext.getResources().getColor(R.color.whiteColor));
        cardView.setVisibility(View.VISIBLE);
    }






}
