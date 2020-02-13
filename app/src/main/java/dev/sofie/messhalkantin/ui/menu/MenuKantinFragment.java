package dev.sofie.messhalkantin.ui.menu;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.ui.pengaturan.PengaturanActivity;
import dev.sofie.messhalkantin.ui.report.CanteenReportActivity;
import dev.sofie.messhalkantin.ui.transaction.KantinTransactionActivity;



public class MenuKantinFragment extends Fragment implements View.OnClickListener {
    private CardView mTransactionCard,mReportCard,ubahPasswordCard;
    private ImageView mQRImage1,mReportImage;
    private Intent intent;

    public MenuKantinFragment() {
    }

    public static MenuKantinFragment newInstance() {
        MenuKantinFragment fragment = new MenuKantinFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_menu_kantin, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view){
        mTransactionCard = view.findViewById(R.id.transactionCard);
        mTransactionCard.setOnClickListener(this);

        mReportCard = view.findViewById(R.id.reportCard);
        mReportCard.setOnClickListener(this);

        mQRImage1 = view.findViewById(R.id.qrImage1);
        mReportImage = view.findViewById(R.id.reportImage);

        ubahPasswordCard = view.findViewById(R.id.ubahPasswordCard);
        ubahPasswordCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.transactionCard:
                intent = new Intent(getActivity(), KantinTransactionActivity.class);
                startActivity(intent);
                break;

            case R.id.reportCard:
                intent = new Intent(getActivity(), CanteenReportActivity.class);
                startActivity(intent);
                break;
            case R.id.ubahPasswordCard:
                intent = new Intent(getActivity(), PengaturanActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}
