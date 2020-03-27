package com.example.moneymeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.versionedparcelable.ParcelField;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class ReportChart extends AppCompatActivity {

    SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        PieChart pieChart1 = findViewById(R.id.piechart1);
        PieChart pieChart2 = findViewById(R.id.piechart2);

        ArrayList<String> IncomeSource = new ArrayList<>();
        ArrayList<String> ExpenseReason = new ArrayList<>();

        final DbManager dbManager=new DbManager(this);
        dbManager.open();
        final Cursor cursor1 = dbManager.fetchIncome();
        final Cursor cursor2 = dbManager.fetchExpense();

        ArrayList Inc = new ArrayList();
        ArrayList Exp = new ArrayList();

        cursor1.moveToFirst();
        cursor2.moveToFirst();

        int i=0;
        for (cursor1.moveToFirst(); !cursor1.isAfterLast(); cursor1.moveToNext()) {
            IncomeSource.add(cursor1.getString(cursor1.getColumnIndex("Description")));
            Inc.add(new Entry(cursor1.getFloat(cursor1.getColumnIndex("i")),i));
            //IncomeSource.add(cursor1.getString(cursor1.getColumnIndex("Desprition")));
            i++;
        }
        i=0;
        for (cursor2.moveToFirst(); !cursor2.isAfterLast(); cursor2.moveToNext()) {
            ExpenseReason.add((cursor2.getString(cursor2.getColumnIndex("Description"))));
            Exp.add(new Entry(cursor2.getFloat(cursor2.getColumnIndex("s")),i));
            i++;
        }

        PieDataSet dataSet1 = new PieDataSet(Inc , "Income of this Month");
        PieDataSet dataSet2 = new PieDataSet(Exp , "Expense of this Month");


        PieData data1 = new PieData(IncomeSource, dataSet1);
        pieChart1.setData(data1);
        dataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
        pieChart1.animateXY(5000, 5000);

        PieData data2 = new PieData(ExpenseReason, dataSet2);
        pieChart2.setData(data2);
        dataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart2.animateXY(5000, 5000);

       // pieChart1.setHoleRadius(25f);
        //pieChart1.setTransparentCircleAlpha(0);

        pieChart1.setCenterTextSize(7);
        pieChart2.setCenterTextSize(7);

        dataSet1.setSliceSpace(2);
        dataSet1.setValueTextSize(7);
        dataSet2.setValueTextSize(7);



    }
}

 /*for (int i=0;i<7;i++) {
           //IncomeValues[i]=cursor1.getFloat(cursor1.getColumnIndex("Amount"));
          // ExpValues[i]=cursor2.getFloat(cursor2.getColumnIndex("Amount"));
           // Inc.add(new Entry(IncomeValues[i],i));
          //  Exp.add(new Entry(IncomeValues[i],i));
            Inc.add(new Entry(cursor1.getFloat(cursor1.getColumnIndex("i")),i));
            Exp.add(new Entry(cursor2.getFloat(cursor2.getColumnIndex("s")),i));
            cursor1.moveToNext();
            cursor2.moveToNext();
        }*/