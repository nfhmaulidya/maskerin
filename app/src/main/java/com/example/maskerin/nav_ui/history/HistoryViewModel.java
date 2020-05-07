package com.example.maskerin.nav_ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HistoryViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is historyr fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}