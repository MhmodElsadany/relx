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
import com.example.talbk.ViewModel.SearchVM;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
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
    SearchVM model;
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
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        data( ProductCustomAdapter.TotalPrice() + " ج.م ");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        builder = new AlertDialog.Builder(SearchActivity.this);
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, CartActivity.class));

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
                startActivity(new Intent(SearchActivity.this, CartActivity.class));
            }
        });


    }

    public void data(String totalPrice) {
        total.setText(totalPrice + "");
    }

    public void showData() {
        model = ViewModelProviders.of(SearchActivity.this).get(SearchVM.class);
        model.getResult(SearchActivity.this, search.getText().toString(),
                pb, builder).observe(SearchActivity.this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Product> s) {
                mCustomAdapter = new ProductCustomAdapter(SearchActivity.this, s, "search");
                recyclerView.setAdapter(mCustomAdapter);
            }
        });

    }

    public void bin() {
        number.setText(ProductCustomAdapter.oreders.size() + "");

    }


}
