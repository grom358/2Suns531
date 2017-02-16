package com.cameronzemek.workout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {
    private ListView historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final Context ctx = this;

        historyList = (ListView) findViewById(R.id.historyList);
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<WorkoutHistory> adapter = (ArrayAdapter<WorkoutHistory>) historyList.getAdapter();
                WorkoutHistory historyItem = adapter.getItem(position);
                Intent intent = new Intent(ctx, HistoryItemActivity.class);
                intent.putExtra("workoutId", historyItem.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        WorkoutHistoryDbHelper dbHelper = new WorkoutHistoryDbHelper(this);
        ArrayAdapter<WorkoutHistory> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.list_simple_item,
                dbHelper.getHistoryList());
        historyList.setAdapter(adapter);
    }
}
