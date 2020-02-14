package dev.sofie.messhalkantin.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import dev.sofie.messhalkantin.R;
import dev.sofie.messhalkantin.ui.pengaturan.PengaturanActivity;
import dev.sofie.messhalkantin.ui.report.MesshallReportActivity;
import dev.sofie.messhalkantin.ui.transaction.MesshallTransactionActivity;


public class MenuMesshallFragment extends Fragment implements View.OnClickListener {
    private CardView transactionCard,reportCard,ubahPasswordCard;
    private Intent intent;

    public MenuMesshallFragment() {
    }


    public static MenuMesshallFragment newInstance() {
        MenuMesshallFragment fragment = new MenuMesshallFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_menu_messhall, container, false);
        initUI(view);
        return view;
    }

    private  void initUI(View view){
        reportCard = view.findViewById(R.id.reportCard);
        reportCard.setOnClickListener(this);

        transactionCard = view.findViewById(R.id.transactionCard);
        transactionCard.setOnClickListener(this);

        ubahPasswordCard = view.findViewById(R.id.ubahPasswordCard);
        ubahPasswordCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reportCard:
                intent = new Intent(getActivity(), MesshallReportActivity.class);
                startActivity(intent);
                break;
            case R.id.transactionCard:
                intent = new Intent(getActivity(), MesshallTransactionActivity.class);
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
