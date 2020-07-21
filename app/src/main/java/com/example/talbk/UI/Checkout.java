package com.example.talbk.UI;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.CustomAdapterCheck;
import com.example.talbk.Adapter.ProductCustomAdapter;
import com.example.talbk.Model.Check;
import com.example.talbk.Model.ProductsCheck;
import com.example.talbk.R;
import com.example.talbk.ViewModel.CheckVM;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Checkout extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.totall)
    TextView totall;
    @BindView(R.id.check)
    Button check;
    CheckVM model;
    ProgressDialog pDialog;
    String comment;
    String userId;
    SharedPreferences sharedPreferences;
    Check checkUser;
    ArrayList<ProductsCheck> productsCheck = new ArrayList<>();
    String iduser;
    @BindView(R.id.exit)
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CustomAdapterCheck mCustomAdapter = new CustomAdapterCheck(Checkout.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(mCustomAdapter);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("من فضلك اننتظر...");
        comment = getIntent().getStringExtra("comment");
        userId = getIntent().getStringExtra("iduser");
        for (int i = 0; i < ProductCustomAdapter.oreders.size(); i++) {
            productsCheck.add(new ProductsCheck(ProductCustomAdapter.oreders.get(i).
                    getProduct().getId(), ProductCustomAdapter.oreders.get(i).getPrice(),
                    ProductCustomAdapter.oreders.get(i).getQuantity() + ""));
        }
        sharedPreferences = getSharedPreferences("DataID", MODE_PRIVATE);
        iduser = sharedPreferences.getString("id_user", "");
        checkUser = new Check(userId, productsCheck, comment, ProductCustomAdapter.TotalPrice() + "");

        totall.setText(ProductCustomAdapter.TotalPrice() + " ج م ");
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmOrder();
            }
        });

    }

    public void confirmOrder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Checkout.this);
        builder.setMessage("هل أنت متأكد من تاكيد الطلب ؟")
                .setCancelable(false)
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        model = ViewModelProviders.of(Checkout.this).get(CheckVM.class);
                        model.getResult(checkUser, Checkout.this, pDialog).observe(Checkout.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                if (s.equals("inserted")) {
                                    Toast.makeText(Checkout.this, "سيتم ارسال الطلب فى خلال دقائق ", Toast.LENGTH_SHORT).show();
                                    ProductCustomAdapter.oreders.clear();
                                    Intent intent = new Intent(Checkout.this, Main.class);
                                    intent.putExtra("iduser", iduser);
                                    startActivity(intent);
                                    finish();

                                }

                            }
                        });
                    }
                })
                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }

}
