package com.example.a10_minuteschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.a10_minuteschool.adapter.ExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private ExpandableListViewAdapter expandableListViewAdapter;

    private List<String> listDataGroup;

    private HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();

    }


    /**
     * method to initialize the views
     */
    private void initViews() {

        expandableListView = findViewById(R.id.expandable_ListView);

    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataChild.get(
                                listDataGroup.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

      // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                int childcount=expandableListViewAdapter.getChildrenCount(groupPosition);
                String videopageTitle="Videos";
                String documentTitle="Documents";
                String documentAuthorName="Mr. XYZ";
                int videoIcon=R.drawable.icons8_video_24;
                int documentIcon=R.drawable.icons8_document_24;


                Intent intent=new Intent(getApplicationContext(),SimpleLayout.class);

                if(childcount<=0){
                    if(videopageTitle.equals(listDataGroup.get(groupPosition))){
                       intent.putExtra("title",videopageTitle);
                       intent.putExtra("icon",videoIcon);

                    }else{
                        intent.putExtra("title",documentTitle);
                        intent.putExtra("subTitle",documentAuthorName);
                        intent.putExtra("icon",documentIcon);
                    }
                    startActivity(intent);
                }


            }
        });

    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {

        // initializing the list of groups
        listDataGroup = new ArrayList<>();

        // initializing the list of child
        listDataChild = new HashMap<>();

        // initializing the adapter object
        expandableListViewAdapter = new ExpandableListViewAdapter(this, listDataGroup, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(expandableListViewAdapter);

    }

    /*
     * Preparing the list data
     *
     * Dummy Items
     */
    private void initListData() {


        // Adding group data
        listDataGroup.add(getString(R.string.chapter1));
        listDataGroup.add(getString(R.string.chapter2));
        listDataGroup.add(getString(R.string.videos));
        listDataGroup.add(getString(R.string.documents));

        // array of strings
        String[] array;

        // list of alcohol
        List<String> chapter_1_ItemList = new ArrayList<>();
        array = getResources().getStringArray(R.array.chapter_1_subitems);
        for (String item : array) {
            chapter_1_ItemList.add(item);
        }

        // list of coffee
        List<String> chapter_2_ItemList = new ArrayList<>();
        array = getResources().getStringArray(R.array.chapter_2_subitems);
        for (String item : array) {
            chapter_2_ItemList.add(item);
        }
        List<String> noexpandableList=new ArrayList<>();
        // Adding child data
        listDataChild.put(listDataGroup.get(0), chapter_1_ItemList);
        listDataChild.put(listDataGroup.get(1), chapter_2_ItemList);
        listDataChild.put(listDataGroup.get(2), noexpandableList);
        listDataChild.put(listDataGroup.get(3), noexpandableList);

        // notify the adapter
        expandableListViewAdapter.notifyDataSetChanged();
    }

}