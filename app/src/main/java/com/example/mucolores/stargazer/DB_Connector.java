package com.example.mucolores.stargazer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DB_Connector {

    private static StringBuilder SB;

    public static String updatingData(String inputUrlString)
    {
        try {
            URL url = new URL(inputUrlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader,8);

            String tmpLine;
            SB = new StringBuilder();
            while((tmpLine = bufferedReader.readLine())!=null)
            {
                SB.append(tmpLine);
            }
            inputStream.close();
            httpURLConnection.disconnect();

        }
        catch (IOException e)
        {
            Log.e("SQL Error",e.toString());
        }

        if(SB.length() > 0) {
            Log.d("DB_Connector", "request: " + inputUrlString + "\n" +
                    "result: " + SB.toString());
        }
        else {
            Log.d("DB_Connector", "request: " + inputUrlString + "\n" +
                    "no result");
        }

        return SB.toString();

    }
}
