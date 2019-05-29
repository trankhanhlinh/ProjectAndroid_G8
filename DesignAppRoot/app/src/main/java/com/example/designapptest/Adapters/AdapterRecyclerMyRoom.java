package com.example.designapptest.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designapptest.Model.RoomModel;
import com.example.designapptest.R;
import com.example.designapptest.Views.PopUpComment;
import com.example.designapptest.Views.PopUpViews;

import java.util.List;

public class AdapterRecyclerMyRoom extends RecyclerView.Adapter<AdapterRecyclerMyRoom.ViewHolder> {


    List<RoomModel> RoomModelList;
    //Là biến lưu giao diện custom của từng row
    int resource;
    // Linh thêm
    Context context;

    public AdapterRecyclerMyRoom(Context context, List<RoomModel> RoomModelList, int resource) {
        this.context = context;
        this.RoomModelList = RoomModelList;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTimeCreated, txtName,txtAddress,txtQuantityViews,txtQuantityComment;
        ImageView imgRoom,imgVerified;
        Button btnUpdate,btnViews,btnDelete,btnComment,btnChange;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTimeCreated = (TextView) itemView.findViewById(R.id.txt_timeCreated);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);

            txtAddress = (TextView) itemView.findViewById(R.id.txt_address);

            imgRoom = (ImageView) itemView.findViewById(R.id.img_room);

            imgVerified = (ImageView)itemView.findViewById(R.id.img_verified);

            txtQuantityViews = itemView.findViewById(R.id.txt_quantityViews);
            txtQuantityComment = itemView.findViewById(R.id.txt_quantityComment);

            btnUpdate =(Button)itemView.findViewById(R.id.btn_update);
            btnViews =(Button)itemView.findViewById(R.id.btn_views);
            btnDelete =(Button)itemView.findViewById(R.id.btn_delete);
            btnComment =(Button)itemView.findViewById(R.id.btn_comment);
            btnChange = itemView.findViewById(R.id.btn_change);

        }

    }

    @NonNull
    @Override
    public AdapterRecyclerMyRoom.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerMyRoom.ViewHolder viewHolder, int i) {
        final RoomModel roomModel = RoomModelList.get(i);

        viewHolder.txtName.setText(roomModel.getName());

        //Set address for room
        String longAddress = roomModel.getApartmentNumber() + " " + roomModel.getStreet() + ", "
                + roomModel.getWard() + ", " + roomModel.getCounty() + ", " + roomModel.getCity();
        viewHolder.txtAddress.setText(longAddress);
        //End Set address for room

        viewHolder.txtTimeCreated.setText(roomModel.getTimeCreated());

        //Hiển thị phòng đã được chứng thực
        if(roomModel.isAuthentication()){
            viewHolder.imgVerified.setVisibility(View.VISIBLE);
        }else {
            viewHolder.imgVerified.setVisibility(View.GONE);
        }
        //End hiển thị phòng đã được chúng thực

        viewHolder.txtQuantityComment.setText(roomModel.getListCommentRoom().size()+"");
        viewHolder.txtQuantityViews.setText(roomModel.getViews()+"");

        //Download ảnh dùng picaso cho đỡ lag, dùng thuộc tính fit() để giảm dung lượng xuống thấp nhất có thể
        roomModel.getCompressionImageFit().centerCrop().into(viewHolder.imgRoom);

        viewHolder.btnViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PopUpViews.class);
                intent.putExtra("phongtro", roomModel.getRoomID());
                (context).startActivity(intent);
            }
        });

        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi hàm update
                updateRoom(roomModel);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(roomModel.getRoomID(),i);
            }
        });

        viewHolder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PopUpComment.class);
                intent.putExtra("phongtro", roomModel.getRoomID());
                (context).startActivity(intent);
            }
        });

        viewHolder.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChange(roomModel.getRoomID(),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return RoomModelList.size();
    }

    //Chuyển sang màn hình update
    private void updateRoom(RoomModel roomModel){

    }

    private void showDialogChange(String RoomID,int position){

        RoomModel modelFunction = new RoomModel();

        Dialog changeDialog = new Dialog(context);
        changeDialog.setContentView(R.layout.change_state_room_dialog);

        ImageView imgClose = changeDialog.findViewById(R.id.img_close);

        RadioButton radClear,radRent;
        radClear = changeDialog.findViewById(R.id.rad_clear);
        radRent = changeDialog.findViewById(R.id.rad_rent);

        if(RoomModelList.get(position).getMaxNumber()==RoomModelList.get(position).getCurrentNumber()){
            radRent.setChecked(true);
        }else {
            radClear.setChecked(true);
        }

        radClear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Gọi hàm cho phòng trống ở model
                    modelFunction.changeState(RoomModelList.get(position).getRoomID());
                    //Thay đổi ở List hiện tại
                    RoomModelList.get(position).setCurrentNumber(0);
                }
            }
        });

        radRent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Gọi hàm cho thuê phòng từ model
                    modelFunction.changeState(RoomModelList.get(position).getRoomID(), (int) RoomModelList.get(position).getMaxNumber());
                    //Thay đổi ở lish hiện tại
                    RoomModelList.get(position).setCurrentNumber(RoomModelList.get(position).getMaxNumber());
                }
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDialog.dismiss();
            }
        });


        changeDialog.show();
    }

    private void showDialog(String RoomID,int position){
        Dialog deleteDialog = new Dialog(context);
        deleteDialog.setContentView(R.layout.delete_dialog);

        ImageView imgClose = deleteDialog.findViewById(R.id.img_close);
        Button btnDelete =deleteDialog.findViewById(R.id.btn_delete);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Remove khỏi list
                RoomModelList.remove(position);
                notifyDataSetChanged();
                //Gọi hàm xóa từ model
//                RoomModel roomModel1 = new RoomModel();
//                roomModel1.DeleteRoom(roomModel.getRoomID());
                deleteDialog.dismiss();
                Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
            }
        });

        deleteDialog.show();
    }

}
