package com.dolanan.dev.tukaridr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectCountriesActivity extends AppCompatActivity {
    private CurrencyConverter curUserChoose = null;
    public static List<CurrencyConverter> userChoose = new ArrayList<>();
    private CurrencyAdapter myAdapter;
    private ListView currencyList;
    private List<CurrencyConverter> tenCurrencyCountries = new ArrayList<>();
    private  List<Integer> checkColor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Currency", "onCreate()");
        setContentView(R.layout.activity_select_countries);

        currencyList = findViewById(R.id.listViewCountries);

        if(isNetworkAvailable()){
            Log.i("Currency", "Starting download task");
            SitesDownloadTask download = new SitesDownloadTask();
            download.execute();
        }
        else{
            //input the 10 currency (offline)
            tenCurrencyCountries = CurrencyXMLPullParser.getCurrencyFromFile(SelectCountriesActivity.this);
            myAdapter = new CurrencyAdapter(getApplicationContext(), -1,tenCurrencyCountries) ;
            currencyList.setAdapter(myAdapter);
            //when click list view(offline)
            onClickFunction();
        }

        Button btnNext = findViewById(R.id.btnNext);
        //make sure user choose at least one country
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userChoose.isEmpty()){
                    Toast.makeText(SelectCountriesActivity.this, "Please select a country", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(SelectCountriesActivity.this, MainActivity.class));
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @SuppressLint("StaticFieldLeak")
    private class SitesDownloadTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... argO) {
            //Download the file
            try{
                Downloader.DownloadFromUrl("http://www.floatrates.com/daily/idr.xml", openFileOutput("currency.xml", Context.MODE_PRIVATE));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //input the 10 currency (online)
            tenCurrencyCountries = CurrencyXMLPullParser.getCurrencyFromFile(SelectCountriesActivity.this);
            myAdapter = new CurrencyAdapter(SelectCountriesActivity.this, -1, tenCurrencyCountries);
            currencyList.setAdapter(myAdapter);
            Log.i("Currency", "Adapter size = "+ myAdapter.getCount());
            //when click list view(online)
            onClickFunction();
        }
    }

    private void onClickFunction(){
        currencyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int checkColorAddorNot = 0 ;
                //checkColor is a array. We will add the click position into it and set bg color when its the first time we click this position
                if(checkColor.size()==0){
                    currencyList.getChildAt(position).setBackgroundColor(Color.LTGRAY);
                    checkColor.add(position);
                }
                else{
                    //if we click this position before then we remove it, and set bg color transparent
                    for(int i=0;i<checkColor.size();i++){
                        if(checkColor.get(i)==position){
                            currencyList.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                            checkColor.remove(i);
                            //if we remove it, change check value to make sure we wont add it again
                            checkColorAddorNot = 1;
                            break;
                        }
                        else{
                            currencyList.getChildAt(position).setBackgroundColor(Color.LTGRAY);
                        }
                    }
                    //check we need to add or not
                    if(checkColorAddorNot == 0){
                        checkColor.add(position);
                    }
                }

                //same logic as checkColor, we add select country to userChoose
                curUserChoose = new CurrencyConverter();
                curUserChoose = tenCurrencyCountries.get(position);
                int checkUserChooseAddorNot = 0 ;
                if(userChoose.size()==0){
                    userChoose.add(curUserChoose);
                }
                else{
                    for(int i=0;i<userChoose.size();i++){
                        if(userChoose.get(i).equals(curUserChoose)){
                            userChoose.remove(curUserChoose);
                            checkUserChooseAddorNot = 1;
                            break;
                        }
                    }
                    if(checkUserChooseAddorNot == 0){
                        userChoose.add(curUserChoose);
                    }
                }
            }
        });
    }
}
