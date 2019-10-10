package com.ashishdake.searchdialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ashishdake.dialogsearch.Model.DialogListener;
import com.ashishdake.dialogsearch.Model.FilterItem;
import com.ashishdake.dialogsearch.SearchDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button1,button2,button3,button4;
    SearchDialog searchDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Hotel> personList = new ArrayList<>();

                personList.add(new Hotel("1","Amit"));
                personList.add(new Hotel("2","Ashish"));
                personList.add(new Hotel("3","Krushna"));
                personList.add(new Hotel("4","Deepak"));

                searchDialog = new SearchDialog(MainActivity.this);

                searchDialog.setToolbarTitle("Model Filter");
                searchDialog.setSearchBoxHint("You can search");
                searchDialog.setList(personList);

                searchDialog.show("id", "name", new DialogListener.Single() {
                    @Override
                    public void onResult(FilterItem selectedItem) {
                        Toast.makeText(MainActivity.this, "Selected item: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        searchDialog.dispose();
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> stringList = new ArrayList<>();
                stringList.add("Item 1");
                stringList.add("Item 2");
                stringList.add("Item 3");
                stringList.add("Item 4");
                stringList.add("Item 5");
                stringList.add("Item 6");
                stringList.add("Item 7");

                searchDialog = new SearchDialog(MainActivity.this);

                searchDialog.setToolbarTitle("String Filter");
                searchDialog.setSearchBoxHint("You can search");
                searchDialog.setList(stringList);

                searchDialog.setOnCloseListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        searchDialog.dispose();
                    }
                });

                searchDialog.show(new DialogListener.Single() {
                    @Override
                    public void onResult(FilterItem selectedItem) {
                        Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        searchDialog.dispose();
                    }
                });
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> integerList = new ArrayList<>();
                integerList.add(1);
                integerList.add(2);
                integerList.add(3);
                integerList.add(4);
                integerList.add(5);
                integerList.add(6);
                integerList.add(7);

                searchDialog = new SearchDialog(MainActivity.this);

                searchDialog.setToolbarTitle("Integer Filter");
                searchDialog.setSearchBoxHint("You can search");
                searchDialog.setList(integerList);

                searchDialog.show(new DialogListener.Single() {
                    @Override
                    public void onResult(FilterItem selectedItem) {
                        Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        searchDialog.dispose();
                    }
                });
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Hotel> personList = new ArrayList<>();

                personList.add(new Hotel("1","Amit"));
                personList.add(new Hotel("2","Ashish"));
                personList.add(new Hotel("3","Krushna"));
                personList.add(new Hotel("4","Deepak"));

                searchDialog = new SearchDialog(MainActivity.this);

                searchDialog.setToolbarTitle("Model Filter");
                searchDialog.setSearchBoxHint("You can search");
                searchDialog.setSelectButtonText("Select");
                searchDialog.setList(personList);
                searchDialog.setSelectableCount(2);

                searchDialog.show("id", "name", new DialogListener.Multiple() {
                    @Override
                    public void onResult(List<FilterItem> selectedItems) {
                        for (int i=0;i<selectedItems.size();i++) {
                            Toast.makeText(MainActivity.this, "Selected is: " + selectedItems.get(i).getName(), Toast.LENGTH_SHORT).show();
                        }
                        searchDialog.dispose();
                    }
                });
            }
        });
    }
}
