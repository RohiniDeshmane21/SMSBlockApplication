package com.example.rupali.blocksms;

import android.app.ListActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends ListActivity {

    private CommentsDataSource datasource;
    EditText et ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        et = (EditText) findViewById(R.id.editText);

        final ListView ls = (ListView) findViewById(android.R.id.list);
        final Comment[][] comment = {new Comment[50]};

        datasource = new CommentsDataSource(this);
        datasource.open();
        final List<Comment> values = datasource.getAllComments();

        final ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
       getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        findViewById(R.id.btnBlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String item = et.getText().toString();
                comment[0][0] = datasource.createComment(item);
                adapter.add(comment[0][0]);
                et.setText("");

            }
        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int position, long l) {
                Comment a = values.get(position);

                if (a.isSelected) {
                    a.isSelected = false;
                   view1.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    a.isSelected = true;
                    view1.setBackgroundColor(Color.GRAY);
                }

            }
        });

        findViewById(R.id.btnUnblock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < values.size(); i++) {
                    Comment a = values.get(i);
                    if (a.isSelected == true) {
                        datasource.deleteComment(values.get(i));
                        adapter.remove(values.get(i));

                    }

                }
            }
        });


    }

}

