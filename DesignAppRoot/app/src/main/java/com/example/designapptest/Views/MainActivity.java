package com.example.designapptest.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.designapptest.Controller.MainActivityController;
import com.example.designapptest.R;

import java.util.ArrayList;

public class MainActivity extends Activity{

    //Qui thêm vào
    RecyclerView recyclerMainRoom;
    RecyclerView recyclerGridMainRoom;
    MainActivityController mainActivityController;
    ProgressBar progressBarMain;
    //End Qui thêm vào

   // GridView grVRoom;
    GridView grVLocation;

    //ListView lstVRoom;
    ListView lstVSuggest;
    ListView lstVSearch;

    ArrayList<roomModel> mydata;
    ArrayList<locationModel> datalocation;

    roomAdapter roomAdapterGid;
    roomAdapter roomAdapterList;
    com.example.designapptest.Views.locationAdapter locationAdapter;
    com.example.designapptest.Views.searchAdapter searchAdapter;
    suggestAdapter suggestAdapterList;

    Button btnChooseSearch;
    Button btnPostRoom;
    //Qui them vao
    Button btnMapView;

    String[] dataSearch = {"Vị trí", "Giá cả", "Số người", "Tiện nghi", "Map"};
    EditText edTSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControl();

        initDataRoom();

        initDataLocation();

        adapter();

        search();

        elementRoom();

        postRoom();
    }

    private void initControl() {
        //grVRoom = (GridView) findViewById(R.id.grV_room);
        grVLocation = (GridView) findViewById(R.id.grV_location);

        btnChooseSearch = (Button) findViewById(R.id.btn_choose_search);
        btnPostRoom = (Button) findViewById(R.id.btn_postRoom_main_room);

        //qui them vao
        btnMapView =(Button)findViewById(R.id.btn_Map_View);

        //lstVRoom = (ListView) findViewById(R.id.lstV_room);
        lstVSearch = (ListView) findViewById(R.id.lstV_search);
        lstVSuggest = (ListView) findViewById(R.id.lstV_suggest);

        edTSearch = (EditText) findViewById(R.id.edT_search);

        //Qui them vào
        recyclerMainRoom = (RecyclerView)findViewById(R.id.recycler_Main_Room);
        recyclerGridMainRoom = (RecyclerView)findViewById(R.id.recycler_Grid_Main_Room);
        progressBarMain = (ProgressBar)findViewById(R.id.Progress_Main);
    }

    private void initDataRoom() {
        mydata = new ArrayList<>();

        mydata.add(new roomModel(R.drawable.avt_jpg_room, "Cho thuê phòng trọ giá rẻ", "2.5 triệu/phòng", "54 Âu Cơ, Bình Thạnh, TP Hồ Chí Minh", 8, 256, "PHÒNG TRỌ"));
        mydata.add(new roomModel(R.drawable.avt_jpg_room, "Cho thuê phòng trọ giá rẻ", "3.5 triệu/phòng", "54 Âu Cơ, Quận 11, TP Hồ Chí Minh", 6, 18, "PHÒNG TRỌ"));
        mydata.add(new roomModel(R.drawable.avt_jpg_room, "Cho thuê phòng trọ giá rẻ", "2.5 triệu/phòng", "54 Âu Cơ, Bình Thạnh, TP Hồ Chí Minh", 5, 365, "CHUNG CƯ"));
        mydata.add(new roomModel(R.drawable.avt_jpg_room, "Cho thuê phòng trọ giá rẻ", "3.5 triệu/phòng", "54 Âu Cơ, Quận 11, TP Hồ Chí Minh", 4, 256, "PHÒNG TRỌ"));
        mydata.add(new roomModel(R.drawable.avt_jpg_room, "Cho thuê phòng trọ giá rẻ", "2.5 triệu/phòng", "54 Âu Cơ, Bình Thạnh, TP Hồ Chí Minh", 6, 28, "KÍ TÚC XÁ"));
        mydata.add(new roomModel(R.drawable.avt_jpg_room, "Cho thuê phòng trọ giá rẻ", "3.5 triệu/phòng", "54 Âu Cơ, Quận 11, TP Hồ Chí Minh", 7, 147, "PHÒNG TRỌ"));
    }

    private void initDataLocation() {
        datalocation = new ArrayList<>();

        datalocation.add(new locationModel(R.drawable.avt_jpg_room, "Bình Thạnh", "6856 phòng"));
        datalocation.add(new locationModel(R.drawable.avt_jpg_room, "Quận 1", "4875 phòng"));
        datalocation.add(new locationModel(R.drawable.avt_jpg_room, "Thủ Đức", "4229 phòng"));
    }

    private void adapter() {
       // roomAdapterGid = new roomAdapter(this, R.layout.room_element_grid_view, mydata);
        //roomAdapterList = new roomAdapter(this, R.layout.room_element_list_view, mydata);
        suggestAdapterList = new suggestAdapter(this, R.layout.suggest_element_list_view, mydata);
        locationAdapter = new locationAdapter(this, R.layout.row_element_grid_view_location, datalocation);
        searchAdapter = new searchAdapter(this, R.layout.search_element_list_view, dataSearch);

        //grVRoom.setAdapter(roomAdapterGid);
        //lstVRoom.setAdapter(roomAdapterList);
        grVLocation.setAdapter(locationAdapter);
        lstVSearch.setAdapter(searchAdapter);
        lstVSuggest.setAdapter(suggestAdapterList);
    }

    private void search() {
        btnChooseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstVSearch.setVisibility(View.VISIBLE);
            }
        });

        lstVSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lstVSearch.setVisibility(View.INVISIBLE);
                switch (position) {
                    case 0: {
                        btnChooseSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_svg_location_search_24px, 0, 0, 0);
                        break;
                    }
                    case 1: {
                        btnChooseSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_svg_coin_24px, 0, 0, 0);
                        break;
                    }
                    case 2: {
                        btnChooseSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_svg_group_24px, 0, 0, 0);
                        break;
                    }
                    case 3: {
                        btnChooseSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_svg_location_search_24px, 0, 0, 0);
                        break;
                    }
                    case 4: {
                        btnChooseSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_svg_map_24, 0, 0, 0);
                        break;
                    }
                }
            }
        });

        edTSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edTSearch.getText().length() == 0) {
                    lstVSuggest.setVisibility(View.INVISIBLE);
                } else {
                    lstVSuggest.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void elementRoom() {
//        lstVRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), detailRoom.class);
//                startActivity(intent);
//            }
//        });

//        grVRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), detailRoom.class);
//                startActivity(intent);
//            }
//        });
    }

    private void postRoom() {
        btnPostRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), postRoomAdapter.class);
                startActivity(intent);
            }
        });

        //Qui them vao de test
        btnMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapView.class);
                startActivity(intent);
            }
        });
    }

    private void test() {

    }

    //Load dữ liệu vào List danh sách trong lần đầu chạy
    @Override
    protected void onStart() {
        super.onStart();

        mainActivityController = new MainActivityController(this);
        mainActivityController.ListMainRoom(recyclerMainRoom,recyclerGridMainRoom,progressBarMain);

    }
    //End load dữ liệu vào danh sách trong lần đầu chạy
}