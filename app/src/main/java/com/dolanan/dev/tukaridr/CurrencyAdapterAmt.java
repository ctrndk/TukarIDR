// CSIS 3175 Group Project : 300267900 Yunjie Gao ; 300280496 Hsueh-Cheng Liu
package com.dolanan.dev.tukaridr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CurrencyAdapterAmt extends BaseAdapter {

    private List<Double> valueExchangeRate;
    private Double valueAmt;
    private Context context;
    private List<CurrencyConverter> userChooseCurrency;

    CurrencyAdapterAmt(Double anyValueAmt,
                       List<Double> anyExchangeRate,
                       List<CurrencyConverter> anyUserChooseCurrency,
                       Context anyContext){
        valueAmt = anyValueAmt;
        valueExchangeRate = anyExchangeRate;
        userChooseCurrency = anyUserChooseCurrency;
        context = anyContext;
    }

    @Override
    public int getCount() {
        return SelectCountriesActivity.userChoose.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater myLayoutInflater = LayoutInflater.from(context);
            convertView = myLayoutInflater.inflate(R.layout.row_of_currency,parent,false);
        }
        Double valueAmtcal = valueExchangeRate.get(position) * valueAmt;
        TextView amt = convertView.findViewById(R.id.txtExchangeRate);
        amt.setText(String.format("%.2f",valueAmtcal));

        TextView targetCurrency = convertView.findViewById(R.id.txtTargetCurrency);
        TextView targetName = convertView.findViewById(R.id.txtTargetName);

        targetCurrency.setText("  " + userChooseCurrency.get(position).getTargetCurrency());
        targetName.setText(" - " + userChooseCurrency.get(position).getTargetName());

        //add flags
        targetCurrency.setGravity(Gravity.CENTER_VERTICAL);
        Drawable img = parent.getResources().getDrawable(userChooseCurrency.get(position).getTargetPic());
        img.setBounds(0,0,80,80);
        targetCurrency.setCompoundDrawables(img, null,null,null);

        return convertView;
    }
}
