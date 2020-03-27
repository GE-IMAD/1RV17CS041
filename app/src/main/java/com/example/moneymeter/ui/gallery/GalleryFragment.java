package com.example.moneymeter.ui.gallery;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moneymeter.DbManager;
import com.example.moneymeter.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class GalleryFragment extends Fragment {


    private GalleryViewModel galleryViewModel;
    SimpleCursorAdapter cursorAdapter;


    int [] widgets = new int[] {
            R.id.l1,
            R.id.l2,
            R.id.l3,
            R.id.l4,
            R.id.l5,
            R.id.l6
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final DbManager dbManager=new DbManager(getContext());
        dbManager.open();
        final Cursor cursor = dbManager.fetch();
        String [] columns = dbManager.getAllColumns();
        cursorAdapter=new SimpleCursorAdapter(getContext(),R.layout.list_layout,cursor,columns,widgets,0);
        ListView listView = (ListView)root.findViewById(R.id.result_list);
        listView.setAdapter(cursorAdapter);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbManager.fetch();
                String [] columns = dbManager.getAllColumns();
                cursorAdapter=new SimpleCursorAdapter(getContext(),R.layout.list_layout,cursor,columns,widgets,0);
                ListView listView = (ListView)root.findViewById(R.id.result_list);
                listView.setAdapter(cursorAdapter);
                Snackbar.make(view, "Refreshing", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
        return root;

    }

}