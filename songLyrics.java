package com.example.project3;

import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class songLyrics extends AsyncTask<String, Void, String> {

    //creating variables for lyrics data, both json String and parsed json string
    String data;
    String dataParsed="";

    @Override
    protected String doInBackground(String... strings) {
        try {
            //creating string for API
            String apiURL = "https://api.lyrics.ovh/v1/";

            //concatenating queryString onto end of url
            apiURL += strings[0];

            //creating url from string
            URL requestURL = new URL(apiURL);

            //opening connection to API
            HttpURLConnection httpURLConnection = (HttpURLConnection) requestURL.openConnection();

            //creating input stream
            InputStream inputStream = httpURLConnection.getInputStream();

            //creating buffered reader for input stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //loop to put data into json string
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data += line;
            }

            //parsing data string
            data = data.substring(4);

            //creating JSONObject variable and parsing data
            JSONObject lyrics = new JSONObject(data);
            dataParsed += lyrics.get("lyrics");

            //replacing \r with \n to appear properly on front end
            dataParsed = dataParsed.replace("\r","\n");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //setting front end text to newly parsed data
        MainActivity.mlyricText.setText(this.dataParsed);

        //enabling scrolling to read all lyrics
        MainActivity.mlyricText.setMovementMethod(new ScrollingMovementMethod());
    }
}



