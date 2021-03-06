package com.app.beer.beeradviser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button fab = (Button) findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Found Beer for You!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                getMyBeerList(view);
            }
        });

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

    // Finding beer method
    private BeerExpert b = new BeerExpert();
    public void getMyBeerList(View view) {
        TextView beer_obtained = (TextView) findViewById(R.id.textView2);
        Spinner beer_selected = (Spinner) findViewById(R.id.spinner);
        Button beer_share = (Button) findViewById(R.id.button2);
        beer_share.setVisibility(View.VISIBLE);
        List<String> listObtained = b.findingBeer(String.valueOf(beer_selected.getSelectedItem()));
        StringBuilder sb = new StringBuilder();
        for(String x:listObtained)
        {
            sb.append(x).append("\n");
        }
        beer_obtained.setText(sb);
    }

    public void shareMyBeerList(View view) {
        TextView obtained_list = (TextView) findViewById(R.id.textView2);
        Intent i = new Intent(this, ShareActivity.class);
        i.putExtra("textView4", obtained_list.getText().toString());
        startActivity(i);
    }
}
