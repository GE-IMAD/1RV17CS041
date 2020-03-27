package com.example.moneymeter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    final DbManager dbManager=new DbManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No contact found.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        CardView incomeCard,expenseCard,chartCard,saveCard,ideaCard;
        //hereme
        incomeCard =(CardView) findViewById(R.id.incomeId);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void about(MenuItem item) {
      Toast toast = Toast.makeText(this, "Hello! This app helps you track your money and thereby,increase your savings. ", Toast.LENGTH_LONG);
        toast.show();

    }

    public void income(View view) {
        Intent intent=new Intent(this,EnterIncome.class);
        startActivity(intent);
    }

    public void expense(View view) {
        Intent intent=new Intent(this, EnterExpense.class);
        startActivity(intent);
    }

    public void report(View view) {
        Intent intent=new Intent(this,ReportChart.class);
        startActivity(intent);
    }

    public void saved(View view) {
        double bal=0;
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        if(cursor!=null&& cursor.getCount()>0){
        cursor.moveToLast();
        bal=cursor.getDouble(cursor.getColumnIndex("Balance"));}
        Snackbar.make(view, "Your Balance is : "+bal, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void idea(View view) {
        Toast.makeText(this, "Tip: USE 50/30/20 rule of saving.\n50% for essential expenses.\n30% for lifestyle expenses\n20% debts and savings.\n", Toast.LENGTH_LONG).show();
    }


}
