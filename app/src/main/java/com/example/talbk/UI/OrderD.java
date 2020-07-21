package com.example.talbk.UI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.talbk.Model.User;
import com.example.talbk.R;
import com.example.talbk.ViewModel.GetUserVM;

import static android.content.Context.MODE_PRIVATE;

public class OrderD {
    TextView name;
    TextView phone;
    TextView address;
    TextView change;
    GetUserVM model;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    String id;
    ImageView exit;
    ProgressBar pb;
    EditText comment;

    public void showDialog(final Activity activity, String orderDirect, String order) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.your_info);
        name = dialog.findViewById(R.id.name);
        phone = dialog.findViewById(R.id.phone);
        comment = dialog.findViewById(R.id.txt_dia);
        pb = dialog.findViewById(R.id.progressBar1);
        address = dialog.findViewById(R.id.address);
        change = dialog.findViewById(R.id.edit);
        exit = dialog.findViewById(R.id.exit);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        mSharedPreferences = activity.getSharedPreferences("DataID", MODE_PRIVATE);
        id = mSharedPreferences.getString("id_user", "");


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UserEdit.class);
                intent.putExtra("iduser", id);
                intent.putExtra("edit", "info");
                activity.startActivity(intent);
                dialog.dismiss();

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        model = ViewModelProviders.of((FragmentActivity) activity).get(GetUserVM.class);
        model.getResult(id, activity, pb, builder).observe((LifecycleOwner) activity, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User s) {
                name.setText(s.getName());
                phone.setText(s.getPhone());
                address.setText(s.getAddress());
            }


        });


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, Checkout.class);
                if (orderDirect.equals("direct")) {
                    intent.putExtra("comment", order + comment.getText() + "انتهى الطلب");

                } else {
                    intent.putExtra("comment", comment.getText().toString());
                }
                intent.putExtra("iduser", id);
                activity.startActivity(intent);
                dialog.dismiss();

            }
        });
        dialog.show();

    }


}



