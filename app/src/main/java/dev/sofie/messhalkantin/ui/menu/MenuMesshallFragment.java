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
import dev.sofie.messhalkantin.ui.report.MesshallReportActivity;
import dev.sofie.messhalkantin.ui.transaction.MesshallTransactionActivity;

import static dev.sofie.messhalkantin.helper.ChangePNGColor.changeColor;


public class MenuMesshallFragment extends Fragment implements View.OnClickListener {
    public static final  String MESSHALL_TRANSACTION = "messhall";

    private ImageView qrImage,reportImage;
    private CardView transactionCard,reportCard;
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
        qrImage = view.findViewById(R.id.qrImage);
        reportImage = view.findViewById(R.id.reportImage);
        changeColor(getActivity(),qrImage);
        changeColor(getActivity(),reportImage);

        reportCard = view.findViewById(R.id.reportCard);
        reportCard.setOnClickListener(this);

        transactionCard = view.findViewById(R.id.transactionCard);
        transactionCard.setOnClickListener(this);


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
                intent.putExtra(MesshallTransactionActivity.TRANSACTION_TYPE,MESSHALL_TRANSACTION);
                startActivity(intent);
                break;

            default:
                break;
        }
    }




}
