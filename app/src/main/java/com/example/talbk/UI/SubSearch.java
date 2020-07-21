package com.example.talbk.UI;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.ProductCustomAdapter;
import com.example.talbk.Model.Product;
import com.example.talbk.R;
import com.example.talbk.ViewModel.SearchSubVM;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubSearch extends AppCompatActivity {
    RecyclerView recyclerView;
    AlertDialog.Builder builder;
    ArrayList<Product> s = new ArrayList<>();
    ProductCustomAdapter mCustomAdapter;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.check)
    Button cart;
    @BindView(R.id.number)
    TextView number;
    SearchSubVM model;
    @BindView(R.id.progressBar1)
    ProgressBar pb;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.binre)
    RelativeLayout relativeLayout;

    @Override
    protected void onStart() {
        super.onStart();
        bin();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_search);
        ButterKnife.bind(this);
        data(" الاجمالى " + ProductCustomAdapter.TotalPrice() + " ج م ");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        builder = new AlertDialog.Builder(SubSearch.this);
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubSearch.this, CartActivity.class));

            }
        });
        search.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.equals("")) {
                    showData();
                }
            }


            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    showData();
                    return true;
                }
                return false;
            }
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubSearch.this, CartActivity.class));
            }
        });


    }

    public void data(String totalPrice) {
        total.setText(totalPrice + "");
    }

    public void showData() {
        model = ViewModelProviders.of(SubSearch.this).get(SearchSubVM.class);
        model.getResult(SubSearch.this, search.getText().toString(), getIntent().getStringExtra("subid"),
                pb, builder).observe(SubSearch.this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Product> s) {
                mCustomAdapter = new ProductCustomAdapter(SubSearch.this, s, "search");
                recyclerView.setAdapter(mCustomAdapter);
            }
        });

    }

    public void bin() {
        number.setText(ProductCustomAdapter.oreders.size() + "");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
