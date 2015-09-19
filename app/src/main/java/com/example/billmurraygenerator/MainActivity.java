package com.example.billmurraygenerator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomMurray(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void randomMurray(View view){
        Random rand = new Random();
        int pixels = 200 + rand.nextInt(301);

        String murrayURL = "http://www.fillmurray.com/" + pixels + "/" + pixels;
        new GetImages().execute(murrayURL);
    }

    private class GetImages extends AsyncTask<String, Void, Void> {

        Bitmap bmp;

        @Override
        protected Void doInBackground(String... urls) {
            try {
                InputStream in = new java.net.URL(urls[0]).openStream();
                bmp = BitmapFactory.decodeStream(in);
                return null;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setImage(bmp);
        }
    }

    public void setImage(Bitmap bmp){
        ((ImageView) findViewById(R.id.view_murray)).setImageBitmap(bmp);
    }

}
