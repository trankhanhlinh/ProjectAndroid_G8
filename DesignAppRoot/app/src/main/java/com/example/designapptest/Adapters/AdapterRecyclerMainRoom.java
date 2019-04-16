package com.example.designapptest.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.designapptest.Model.RoomModel;
import com.example.designapptest.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerMainRoom extends RecyclerView.Adapter<AdapterRecyclerMainRoom.ViewHolder> {

    List<RoomModel> RoomModelList;
    //Là biến lưu giao diện custom của từng row
    int resource;

    public AdapterRecyclerMainRoom(List<RoomModel> RoomModelList, int resource) {
        this.RoomModelList = RoomModelList;
        this.resource = resource;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtMaxNumber, txtPrice, txtAddress, txtArea, txtQuantityComment;
        ImageView imgRoom,imgGender;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtMaxNumber = (TextView) itemView.findViewById(R.id.txt_quantityMember);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address);
            txtArea = (TextView) itemView.findViewById(R.id.txt_area);
            txtQuantityComment = (TextView) itemView.findViewById(R.id.txt_quantityComment);
            imgRoom = (ImageView)itemView.findViewById(R.id.img_room);
            imgGender = (ImageView)itemView.findViewById(R.id.img_gender);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerMainRoom.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerMainRoom.ViewHolder viewHolder, int i) {
        //Lấy giá trị trong list
        RoomModel roomModel = RoomModelList.get(i);

        //Gán các giá trị vào giao diện
        viewHolder.txtName.setText(roomModel.getName());
        viewHolder.txtMaxNumber.setText(String.valueOf((int) roomModel.getMaxNumber()));
        viewHolder.txtAddress.setText(roomModel.getAddress());
        viewHolder.txtPrice.setText(String.valueOf(roomModel.getRentalCosts()));
        viewHolder.txtArea.setText(roomModel.getLength()+"x"+roomModel.getWidth());
        viewHolder.txtQuantityComment.setText("5");

        //Gán hình cho giới tính
        if(roomModel.isGender()==true){
            viewHolder.imgGender.setImageResource(R.drawable.ic_svg_male_100);
        }else{
            viewHolder.imgGender.setImageResource(R.drawable.ic_svg_female_100);
        }
        //End Gán giá trị cho giới tính

        //Dowload hình ảnh cho room
        if(roomModel.getListImageRoom().size()>0){

            StorageReference storageReference = FirebaseStorage
                    .getInstance().getReference()
                    .child("Images")
                    .child(roomModel.getListImageRoom().get(0));

            final long ONE_MEGABYTE = 1024 * 1024;
            storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    //Tạo ảnh bitmap từ byte
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    viewHolder.imgRoom.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }

        //End Dowload hình ảnh cho room
    }

    @Override
    public int getItemCount() {
        return RoomModelList.size();
    }
}
