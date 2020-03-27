package com.example.moneymeter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EnterIncome extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonselected;
    private Button button;
    private EditText amt;

    Context context = this;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_income);
        radioGroup=findViewById(R.id.radio_incomeGroup);
        button=findViewById(R.id.submit_IncomeAmt);
        amt=findViewById(R.id.incomeamt);
        final DbManager dbManager=new DbManager(context);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = amt.getText().toString();
                String a = "";
                double value = 0;
                if (!a.equals(temp)) {
                    value = Double.parseDouble(temp);
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButtonselected = findViewById(selectedId);

                    //Date date = Calendar.getInstance().getTime();
                    Date date = new Date();
                    String strDate = dateFormat.format(date);
                    String descp = radioButtonselected.getText().toString();
                    dbManager.open();
                    Cursor cursor = dbManager.fetch();
                    double oldbal = 0;
                    if (cursor != null && cursor.getCount() > 0) {
                        cursor.moveToLast();
                        oldbal = cursor.getDouble(cursor.getColumnIndex("Balance"));
                    }
                    double bal = oldbal + value;
                    Toast toast = Toast.makeText(context, "New Balance : " + bal + ".", Toast.LENGTH_LONG);
                    toast.show();
                    dbManager.insert(strDate, descp, value, "credit", bal);
                    Snackbar.make(v, "Rupees:" + value + " added to " + radioButtonselected.getText() + " successfully.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

    }
}


