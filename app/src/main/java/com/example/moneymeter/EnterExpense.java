package com.example.moneymeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

public class EnterExpense extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonselected;
    private Button button;
    private EditText amt;

    Context context = this;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_expense);

        radioGroup=findViewById(R.id.radio_expenseGroup);
        button=findViewById(R.id.submit_ExpenseAmt);
        amt=findViewById(R.id.expenseamt);
        final DbManager dbManager=new DbManager(context);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp=amt.getText().toString();
                String a="";
                double value=0;
                if (!a.equals(temp)) {
                    value = Double.parseDouble(temp);
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButtonselected = findViewById(selectedId);

                    Date date = new Date();
                    String strDate = dateFormat.format(date);
                    String descp = radioButtonselected.getText().toString();
                    dbManager.open();
                    Cursor cursor = dbManager.fetch();
                    if(cursor!=null && cursor.getCount()>0) {
                        cursor.moveToLast();
                        double oldbal = cursor.getDouble(cursor.getColumnIndex("Balance"));
                        double bal = oldbal - value;
                        if (bal < 0) {
                            Toast toast = Toast.makeText(context, "Insufficient Balance.\nTransaction Failed", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            dbManager.insert(strDate, descp, value, "debit", bal);
                            Snackbar.make(v, "Rupees:" + value + " debited for " + radioButtonselected.getText() + " successfully." + "\nNew Balance : " + bal + ".", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            Toast toast = Toast.makeText(context, "\nNew Balance : " + bal + ".", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    else {
                        Snackbar.make(v, "Failed No money in the account.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
    }
}
