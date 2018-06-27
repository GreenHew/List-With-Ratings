package com.example.matthew.mgreen_listwithratings_s18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class CommentActivity extends AppCompatActivity {

    String title;
    String comment;
    Float rating;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        title = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_TITLE);
        comment = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_COMMENT);
        rating = intent.getFloatExtra(MainActivity.EXTRA_MESSAGE_RATING, 0f);
        position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE_POSITION,0);
        setRatingAndText(rating, comment);

        getSupportActionBar().setTitle(title);
    }

    public void okButtonClicked(View view) {
        EditText editText = findViewById(R.id.editText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        String text = "You rated " + title + " " + ratingBar.getRating() + " stars.";
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        Intent returnIntent = getIntent();
        returnIntent.putExtra(MainActivity.EXTRA_MESSAGE_COMMENT, editText.getText().toString());
        returnIntent.putExtra(MainActivity.EXTRA_MESSAGE_RATING, ratingBar.getRating());
        returnIntent.putExtra(MainActivity.EXTRA_MESSAGE_POSITION, position);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void cancelButtonClicked(View view) {
        EditText editText = findViewById(R.id.editText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        editText.setText(R.string.review_message);
        ratingBar.setRating(0);
        setResult(RESULT_CANCELED);
        finish();
    }

    public void setRatingAndText(float rating, String text) {
        EditText editText = findViewById(R.id.editText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        editText.setText(text);
        ratingBar.setRating(rating);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

}
