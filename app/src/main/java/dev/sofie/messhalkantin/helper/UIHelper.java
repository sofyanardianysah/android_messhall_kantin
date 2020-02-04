package dev.sofie.messhalkantin.helper;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import dev.sofie.messhalkantin.R;

public class UIHelper {

    @SuppressLint("ResourceAsColor")
    public static void showStatus(CardView cardView, TextView textView,String message, boolean status){
        if(status){
            cardView.setCardBackgroundColor(R.color.colorGreen);
        }else{
            cardView.setCardBackgroundColor(R.color.colorRed);
        }
        textView.setText(message);
        textView.setTextColor(R.color.whiteColor);
        cardView.setVisibility(View.VISIBLE);
    }
}
