// CSIS 3175 Group Project : 300267900 Yunjie Gao ; 300280496 Hsueh-Cheng Liu
package com.dolanan.dev.tukaridr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.util.Log;


public class Downloader {

    public static void DownloadFromUrl(String URL, FileOutputStream fos){

        String myTag = "Currency";

        try{
            URL url = new URL(URL);

            long startTime = System.currentTimeMillis();
            Log.d(myTag, "Download beginning");

            URLConnection ucon = url.openConnection();
            Log.i(myTag, "Opened connection");

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Log.i(myTag, "Got InputStream and BufferedInputStream");

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            Log.i(myTag, "Got FileOutputStream and BufferedOutputStream");

            byte data[] = new byte[1024];

            int count;
            while((count = bis.read(data)) != -1){
                bos.write(data, 0, count);
                Log.i(myTag, "Count chunk wrote in BufferedOutputStream: " + count);
            }

            bos.flush();
            bos.close();

            Log.d(myTag, "Download ready in " + ((System.currentTimeMillis() - startTime)) + " milisec");

        }catch(Exception ex){
            Log.d(myTag, "Error: " + ex);
        }
    }
}
