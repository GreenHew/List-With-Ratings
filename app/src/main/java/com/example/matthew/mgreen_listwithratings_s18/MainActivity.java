package com.example.matthew.mgreen_listwithratings_s18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClicked {

    public static final String EXTRA_MESSAGE_TITLE = "com.example.matthew.mgreen_listwithratings.title";
    public static final String EXTRA_MESSAGE_RATING = "com.example.matthew.mgreen_listwithratings.rating";
    public static final String EXTRA_MESSAGE_COMMENT = "com.example.matthew.mgreen_listwithratings.comment";
    public static final String EXTRA_MESSAGE_POSITION = "com.example.matthew.mgreen_listwithratings.position";
    private MyAdapter adapter;
    ArrayList<ListItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setDataList();
        adapter = new MyAdapter(dataList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(this);

    }

    private void setDataList() {
        try {
            InputStream stream = this.getResources().openRawResource(R.raw.buglist);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            while(reader.ready()) {
                String title = reader.readLine();
                ListItem item = new ListItem(title);
                dataList.add(item);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CommentActivity.class);
        ListItem item = dataList.get(position);
        intent.putExtra(EXTRA_MESSAGE_TITLE, item.getTitle());
        intent.putExtra(EXTRA_MESSAGE_RATING, item.getRating());
        intent.putExtra(EXTRA_MESSAGE_COMMENT, item.getComment());
        intent.putExtra(EXTRA_MESSAGE_POSITION, position);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int position = data.getIntExtra(EXTRA_MESSAGE_POSITION, 0);
            float rating = data.getFloatExtra(EXTRA_MESSAGE_RATING, 0f);
            String comment = data.getStringExtra(EXTRA_MESSAGE_COMMENT);
            ListItem item = dataList.get(position);
            item.setComment(comment);
            item.setRating(rating);
            adapter.notifyDataSetChanged();
        }
    }
}
