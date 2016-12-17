package com.app.delayedautocompletetextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DelayedAutoCompleteTextView edSearch = (DelayedAutoCompleteTextView) findViewById(R.id.et_search);
        ProgressBar pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        edSearch.setThreshold(3);// Start search after these number of characters have been entered by the user
        edSearch.setAdapter(new SearchAutoCompleteAdapter(MainActivity.this));
        edSearch.setLoadingIndicator(pbLoading);//Set loading indicator
        edSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Get the value from search result and set it to the edit text.
                Book book = (Book) adapterView.getItemAtPosition(position);
                edSearch.setText(book.getBookName());
            }
        });
    }
}
