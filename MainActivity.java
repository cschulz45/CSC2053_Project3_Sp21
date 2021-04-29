package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    //creating variables for front end info fields
    private EditText msongInput;
    private TextView martistInput;
    public static TextView mlyricText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiating text views and edit text in front end
        msongInput = (EditText) findViewById(R.id.songInput);
        martistInput = (TextView) findViewById(R.id.artistInput);
        mlyricText = (TextView) findViewById(R.id.LyricText);

    }

    public void searchSongs(View v) {

        //formatting query string to be added at end of API url
        String queryString = martistInput.getText().toString() + "/" + msongInput.getText().toString();

        //replacing spaces with underscores so url is correct
        queryString.replace(" ", "_");

        //instantiating songLyrics object
        songLyrics sL = new songLyrics();

        //running songLyrics programs
        sL.execute(queryString);
    }

}
