package com.example.talbk.UI;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.ProductCustomAdapter;
import com.example.talbk.Model.Product;
import com.example.talbk.R;
import com.example.talbk.ViewModel.OffersVM;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Offers extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> s = new ArrayList<>();
    ProductCustomAdapter mCustomAdapter;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.check)
    Button cart;
    OffersVM model;
    @BindView(R.id.progressBar1)
    ProgressBar pb;
    @BindView(R.id.binre)
    RelativeLayout relativeLayout;

    @Override
    protected void onStart() {
        super.onStart();
        AlertDialog.Builder builder = new AlertDialog.Builder(Offers.this);
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        data(" الاجمالى " + ProductCustomAdapter.TotalPrice() + " ج.م ");
        bin();
        int resId = R.anim.layout_animation;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Offers.this, CartActivity.class));
            }
        });
        model = ViewModelProviders.of(this).get(OffersVM.class);
        model.getResult(this, pb, builder).observe(this, new Observer<ArrayList<Product>>() {

            @Override
            public void onChanged(@Nullable ArrayList<Product> s) {
                mCustomAdapter = new ProductCustomAdapter(Offers.this, s, "offers");
                recyclerView.setAdapter(mCustomAdapter);
                recyclerView.setLayoutAnimation(animation);
            }


        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        title.setText("العروض");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Offers.this, CartActivity.class));
            }
        });

    }

    public void data(String totalPrice) {
        total.setText(totalPrice + "");
    }

    public void bin() {
        number.setText(ProductCustomAdapter.oreders.size() + "");

    }

}
