package com.example.moneymeter.ui.gallery;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymeter.DbManager;
import com.example.moneymeter.MainActivity;
import com.example.moneymeter.R;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("      ID           |Date              |Description  |Amount  |Type  |Balance        ");
    }

    public LiveData<String> getText() {
        return mText;
    }




}