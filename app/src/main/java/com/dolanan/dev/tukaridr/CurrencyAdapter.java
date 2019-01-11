// CSIS 3175 Group Project : 300267900 Yunjie Gao ; 300280496 Hsueh-Cheng Liu
package com.dolanan.dev.tukaridr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class CurrencyAdapter extends ArrayAdapter<CurrencyConverter> {

    CurrencyAdapter(Context context, int txtViewResourceId, List<CurrencyConverter> currency){
        super(context, txtViewResourceId, currency);
        }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int pos, View convertView,@NonNull ViewGroup parent){
        RelativeLayout row = (RelativeLayout)convertView;
        Log.i("Currency", "getView pos = " + pos);

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            row = (RelativeLayout)inflater.inflate(R.layout.row_of_currency, null);
        }

        TextView targetCurrency = row.findViewById(R.id.txtTargetCurrency);
        TextView targetName = row.findViewById(R.id.txtTargetName);

        targetCurrency.setText("  " + Objects.requireNonNull(getItem(pos)).getTargetCurrency());
        targetName.setText(" - " + getItem(pos).getTargetName());

        //add flags
        targetCurrency.setGravity(Gravity.CENTER_VERTICAL);
        Drawable img = parent.getResources().getDrawable(Objects.requireNonNull(getItem(pos)).getTargetPic());
        img.setBounds(0,0,80,80);
        targetCurrency.setCompoundDrawables(img, null,null,null);

        return row;
    }
}
