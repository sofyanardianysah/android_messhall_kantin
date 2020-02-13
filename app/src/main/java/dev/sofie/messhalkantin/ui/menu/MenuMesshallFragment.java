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
import dev.sofie.messhalkantin.ui.report.MesshallReportActivity;
import dev.sofie.messhalkantin.ui.transaction.MesshallTransactionActivity;


public class MenuMesshallFragment extends Fragment implements View.OnClickListener {
    public static final  String MESSHALL_SBM = "sbm";
    public static final  String MESSHALL_MALLOMO = "mallomo";


    private CardView transactionCard,reportCard,sbmCard,ubahPasswordCard;
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

        sbmCard = view.findViewById(R.id.sbmCard);
        sbmCard.setOnClickListener(this);
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
                intent.putExtra(MesshallTransactionActivity.TRANSACTION_TYPE,MESSHALL_MALLOMO);
                startActivity(intent);
                break;
            case R.id.sbmCard:
                intent = new Intent(getActivity(), MesshallTransactionActivity.class);
                intent.putExtra(MesshallTransactionActivity.TRANSACTION_TYPE,MESSHALL_SBM);
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
