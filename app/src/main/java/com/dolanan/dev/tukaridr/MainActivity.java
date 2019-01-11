// CSIS 3175 Group Project : 300267900 Yunjie Gao ; 300280496 Hsueh-Cheng Liu
package com.dolanan.dev.tukaridr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int LIMITUSERINPUT = 12;
    private String userInput = "";
    private List<Double> userChooseListDouble;
    private Double valueDouble;
    private ListView myListView;
    private EditText myEditText;

    //softkeyboard variables
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn0;
    Button btnPoint;
    Button btnDelete;
    boolean addedPoint = false;

    //reset country variables
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Currency", "onCreate()");
        setContentView(R.layout.activity_main);

        //created a softkeyboard, the numbers user input can ge from the string userInput
        CreateSoftKeyboard();

        //reset Button click switch to select country activity
        reset = findViewById(R.id.btnReset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectCountriesActivity.userChoose.clear();
                startActivity(new Intent(MainActivity.this, SelectCountriesActivity.class));
            }
        });

        myListView = findViewById(R.id.listViewItems);
        //SelectCountriesActivity.userChoose => user choose currency
        final CurrencyAdapter myAdapter = new CurrencyAdapter(MainActivity.this, -1, SelectCountriesActivity.userChoose);
        myListView.setAdapter(myAdapter);

        //Get the user input from EditText
        myEditText = findViewById(R.id.editTextAmt);
        //setting up not show the default soft keyboard
        myEditText.setShowSoftInputOnFocus(false);
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GetEditValueAndExchangeRate();
                if(valueDouble == 0.00){
                    CurrencyAdapterAmt myAdapterAmt = new CurrencyAdapterAmt(0.00, userChooseListDouble,SelectCountriesActivity.userChoose,MainActivity.this);
                    myListView.setAdapter(myAdapterAmt);
                }
                else{
                    CurrencyAdapterAmt myAdapterAmt = new CurrencyAdapterAmt(valueDouble, userChooseListDouble,SelectCountriesActivity.userChoose,MainActivity.this);
                    myListView.setAdapter(myAdapterAmt);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Display travel advice page after user click the item selected
        final String FPURI = "https://travel.gc.ca/destinations/";
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String getName = myAdapter.getItem(position).getTargetCurrency();
                String dist = "";
                switch(getName){
                    case "CNY":
                        dist = "china";
                        break;
                    case "USD":
                        dist = "united-states";
                        break;
                    case "GBP":
                        dist = "united-kindom";
                        break;
                    case "JPY":
                        dist = "japan";
                        break;
                    case "TRY":
                        dist = "turkey";
                        break;
                    case "KRW":
                        dist = "korea-south";
                        break;
                    case "THB":
                        dist = "thailand";
                        break;
                    case "TWD":
                        dist = "taiwan";
                        break;
                    case "SGD":
                        dist = "singapore";
                        break;
                    case "MXN":
                        dist = "mexico";
                        break;
                    default:
                        dist = "";
                        break;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(FPURI + dist));
                startActivity(i);
            }
        });
    }

    //get the editText value and exchange rate from userChoose
    private void GetEditValueAndExchangeRate(){
        userChooseListDouble = new ArrayList<>();
        for(int i=0; i<SelectCountriesActivity.userChoose.size();i++){
            userChooseListDouble.add(Double.valueOf(SelectCountriesActivity.userChoose.get(i).getexchangeRate()));
        }
        try{
            String valueStr = myEditText.getText().toString();
            valueDouble = Double.valueOf(valueStr);
        }
        catch (Exception ex){
            valueDouble =0.00;
        }
    }

    private void CreateSoftKeyboard(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnPoint = findViewById(R.id.btnPoint);
        btnDelete = findViewById(R.id.btnDelete);
        myEditText = findViewById(R.id.editTextAmt);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "1";
                    myEditText.setText(userInput);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "2";
                    myEditText.setText(userInput);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "3";
                    myEditText.setText(userInput);
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "4";
                    myEditText.setText(userInput);
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "5";
                    myEditText.setText(userInput);
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "6";
                    myEditText.setText(userInput);
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "7";
                    myEditText.setText(userInput);
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "8";
                    myEditText.setText(userInput);
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "9";
                    myEditText.setText(userInput);
                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    userInput += "0";
                    myEditText.setText(userInput);
                }
            }
        });

        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<LIMITUSERINPUT){
                    if(!addedPoint){
                        userInput += ".";
                        myEditText.setText(userInput);
                    }
                    addedPoint = true;
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.length()<=0){
                    myEditText.setText("");
                }
                else
                {
                    if(userInput.charAt(userInput.length()-1) == '.')
                        addedPoint = false;
                    userInput = userInput.substring(0, userInput.length()-1);
                    myEditText.setText(userInput);
                }
            }
        });
    }
}
