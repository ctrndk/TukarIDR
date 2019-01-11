// CSIS 3175 Group Project : 300267900 Yunjie Gao ; 300280496 Hsueh-Cheng Liu
package com.dolanan.dev.tukaridr;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CurrencyXMLPullParser {
    private static final String KEY_ITEM = "item";
    private static final String KEY_TARGETCURRENCY = "targetCurrency";
    private static final String KEY_EXCHANGERATE = "exchangeRate";
    private static final String KEY_TARGETNAME = "targetName";

    public static List<CurrencyConverter> getCurrencyFromFile(Context context){
        List<CurrencyConverter> currency = new ArrayList<>();
        CurrencyConverter curCurrency = null;
        String curText = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            //Get the downloaded currency xml file and read the content
            FileInputStream fis = context.openFileInput("currency.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            Log.i("Currency", "Code reached BufferReader");

            xpp.setInput(reader);

            int eventType = xpp.getEventType();

            //Pull out the text needed by matching the name of the tag
            while(eventType!=XmlPullParser.END_DOCUMENT){
                //Get all the tag names
                String tagname = xpp.getName();
                //Log.i("Currency", "tagname:"+tagname+".");
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if(tagname.equals(KEY_ITEM)){
                            curCurrency = new CurrencyConverter();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        curText = xpp.getText();
                        //Log.i("Currency", "Current Text: "+curText);
                        break;

                    //get the 10 countries data from the XML
                    case XmlPullParser.END_TAG:
                        if(tagname.equals(KEY_TARGETCURRENCY)){
                            if(curText.equals("USD")||curText.equals("GBP")||curText.equals("JPY")||curText.equals("CNY")||curText.equals("TWD")||curText.equals("TRY")||curText.equals("MXN")||curText.equals("KRW")||curText.equals("SGD")||curText.equals("THB")){
                                currency.add(curCurrency);
                                assert curCurrency != null;
                                curCurrency.setTargetCurrency(curText);
                                if(curText.equals("USD"))
                                    curCurrency.setTargetPic(R.drawable.unitedstatesofamerica);
                                else if(curText.equals("GBP"))
                                    curCurrency.setTargetPic(R.drawable.unitedkingdom);
                                else if(curText.equals("JPY"))
                                    curCurrency.setTargetPic(R.drawable.japan);
                                else if(curText.equals("TRY"))
                                    curCurrency.setTargetPic(R.drawable.turkey);
                                else if(curText.equals("CNY"))
                                    curCurrency.setTargetPic(R.drawable.china);
                                else if(curText.equals("KRW"))
                                    curCurrency.setTargetPic(R.drawable.koreasouth);
                                else if(curText.equals("THB"))
                                    curCurrency.setTargetPic(R.drawable.thailand);
                                else if(curText.equals("TWD"))
                                    curCurrency.setTargetPic(R.drawable.taiwan);
                                else if(curText.equals("SGD"))
                                    curCurrency.setTargetPic(R.drawable.singapore);
                                else if(curText.equals("MXN"))
                                    curCurrency.setTargetPic(R.drawable.mexico);
                                //Log.i("Currency", "curText: "+curText);
                            }
                        }else if(tagname.equals(KEY_TARGETCURRENCY)){
                            assert curCurrency != null;
                            curCurrency.setTargetCurrency(curText);
                            //Log.i("Currency", "curText: "+curText);
                        }else if(tagname.equals(KEY_EXCHANGERATE)){
                            assert curCurrency != null;
                            curCurrency.setexchangeRate(curText);
                        }else if(tagname.equals(KEY_TARGETNAME)){
                            //Log.i("Currency", "curText: "+curText);
                            assert curCurrency != null;
                            curCurrency.setTargetName(curText);
                        }
                        break;

                    default:
                        break;
                }

                eventType = xpp.next();
            }
            //Log.i("Currency", "First item in currency List: "+currency.get(0));
            Log.i("Currency", "EventType: " + eventType);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return currency;
    }
}
