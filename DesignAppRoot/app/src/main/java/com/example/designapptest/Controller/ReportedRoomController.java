package com.example.designapptest.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.designapptest.Controller.Interfaces.IReportedRoomModel;
import com.example.designapptest.Model.ReportedRoomModel;

public class ReportedRoomController {
    ReportedRoomModel reportedRoomModel;
    Context context;

    public ReportedRoomController(Context context) {
        this.context = context;
        this.reportedRoomModel = new ReportedRoomModel();
    }

    public void addReport(ReportedRoomModel reportedRoomModel, String roomId) {
        IReportedRoomModel iReportedRoomModel = new IReportedRoomModel() {
            @Override
            public void makeToast(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        };

        reportedRoomModel.addReport(reportedRoomModel, roomId, iReportedRoomModel);
    }
}
