package com.example.designapptest.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designapptest.Controller.Interfaces.IFindRoomAddModel;
import com.example.designapptest.Controller.Interfaces.IFindRoomModel;
import com.example.designapptest.Model.FindRoomModel;
import com.example.designapptest.Model.UserModel;
import com.example.designapptest.R;
import com.example.designapptest.Views.FindRoom;
import com.example.designapptest.Views.FindRoomAdd;
import com.squareup.picasso.Picasso;

public class FindRoomAddController {
    Context context;
    UserModel userModel;
    FindRoomModel findRoomModel;

    public FindRoomAddController(Context context) {
        this.context = context;
        userModel = new UserModel();
        findRoomModel = new FindRoomModel();
    }

    public void setUserOwnerFindRoom(final String idUser, ImageView imgAvatarUser,
                                     ImageView imgGenderUser, TextView txtNameUser, ProgressBar progressBarFindRoomAdd) {

        //Tạo interface để truyền dữ liệu lên từ model
        IFindRoomAddModel iFindRoomAddModel = new IFindRoomAddModel() {
            @Override
            public void getUserModel(UserModel valueUserModel) {
                // Lấy giá trị cần.

                userModel = valueUserModel;

                // Gán tên cho người dùng
                txtNameUser.setText(userModel.getName());

                //Gán hình cho giới tính cuả người tìm ở ghép
                if (userModel.isGender()) {
                    imgGenderUser.setImageResource(R.drawable.ic_svg_male_100);
                } else {
                    imgGenderUser.setImageResource(R.drawable.ic_svg_female_100);
                }
                //End Gán hình cho giới tính cuả người tìm ở ghép

                // Hiển thị hình ảnh người dùng.
                Picasso.get().load(userModel.getAvatar()).into(imgAvatarUser);

                progressBarFindRoomAdd.setVisibility(View.GONE);
            }
        };

        //Gọi hàm lấy dữ liệu trong model
        userModel.getUserModel(iFindRoomAddModel, idUser);
    }

    public void addNewFindRoom(final FindRoomModel findRoomModel) {

        //Tạo interface để truyền dữ liệu lên từ model
        IFindRoomModel iFindRoomModel = new IFindRoomModel() {
            @Override
            public void getListFindRoom(FindRoomModel valueRoom) {

            }

            @Override
            public void addSuccessNotify() {
                Toast.makeText(context, "Đăng tìm ở ghép thành công!", Toast.LENGTH_SHORT).show();

                // Chuyển về trang hiển thị danh sách file room
                ((AppCompatActivity)context).finish();
            }

            @Override
            public void getSuccessNotify() {

            }
        };

        //Gọi hàm lấy dữ liệu trong model
        findRoomModel.addFindRoom(findRoomModel, iFindRoomModel);
    }
}