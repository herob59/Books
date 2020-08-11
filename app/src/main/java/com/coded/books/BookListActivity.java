package com.coded.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class BookListActivity extends AppCompatActivity {

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        try{
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);

        }
        catch (Exception e){
            Log.d("error", e.getMessage());
        }
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {
        /**
         * @deprecated
         */
        public BooksQueryTask() {
            super();
        }

        @Override
       protected String  doInBackground(URL... urls) {
           URL searchURL = urls[0];
           try{
               result = ApiUtil.getJson(searchURL);
           }
           catch (IOException e) {
               Log.e("Error", e.getMessage());
           }
           return result;
        }

        @Override
        protected void onPostExecute(String result){
            TextView tvResult = (TextView) findViewById(R.id.tvResponse);
            tvResult.setText(result);
        }
    }
}