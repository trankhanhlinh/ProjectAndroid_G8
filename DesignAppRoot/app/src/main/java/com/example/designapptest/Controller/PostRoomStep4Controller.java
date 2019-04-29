package com.example.designapptest.Controller;

import android.content.Context;

import com.example.designapptest.Model.RoomModel;

import java.util.List;

public class PostRoomStep4Controller {
    RoomModel roomModel;
    Context context;

    public PostRoomStep4Controller(Context context){
        this.roomModel = new RoomModel();
        this.context=context;
    }

    public void callAddRoomFromModel(RoomModel dataRoom, List<String> listConvenient,List<String> listPathImg,
                                     float electricBill, float warterBill, float InternetBill, float parkingBill){

        //Gọi hàm thêm phòng ở model
        roomModel.addRoom(dataRoom,listConvenient,listPathImg,electricBill,warterBill,InternetBill,parkingBill);
    }
}