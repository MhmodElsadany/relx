package com.example.talbk.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.talbk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDirect extends AppCompatActivity {
    @BindView(R.id.txt_dia)
    EditText txt_dia;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.exit)
    ImageView exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_direct);
        ButterKnife.bind(this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_dia.getText().toString().equals("")) {
                    Toast.makeText(OrderDirect.this, "من فضلك اضف طلب", Toast.LENGTH_SHORT).show();
                } else {
                    OrderD alert = new OrderD();
                    alert.showDialog(OrderDirect.this, "direct", txt_dia.getText().toString());
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
