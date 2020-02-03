package dev.sofie.messhalkantin.ui.report;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.sofie.messhalkantin.model.Overview;
import dev.sofie.messhalkantin.service.ApiRepository;

public class MesshallReportVM extends ViewModel {

    private ApiRepository apiRepository;
    private MutableLiveData<Overview> response;

    public MutableLiveData<Overview> getOverview(Context mcontext, String id, String bulan) {
        if (response == null) {
            response = new MutableLiveData<>();
            apiRepository = ApiRepository.getInstance(mcontext);
            response = apiRepository.getMesshallOverview(id, bulan);
        }
        return response;
    }
}
