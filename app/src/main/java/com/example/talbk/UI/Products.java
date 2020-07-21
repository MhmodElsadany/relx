package com.example.talbk.UI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.ProductCustomAdapter;
import com.example.talbk.Model.Product;
import com.example.talbk.R;
import com.example.talbk.ViewModel.ProductVM;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Products extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> s = new ArrayList<>();
    ProductCustomAdapter mCustomAdapter;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.check)
    Button cart;
    @BindView(R.id.number)
    TextView number;
    ProductVM model;
    @BindView(R.id.progressBar1)
    ProgressBar pb;
    @BindView(R.id.binre)
    RelativeLayout relativeLayout;
    AlertDialog.Builder builder;
    @BindView(R.id.search)
    ImageView searchView;
    @BindView(R.id.background_dim)
    View viewprogress;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;
    GridLayoutManager linearLayoutManager;


    @Override
    protected void onStart() {
        super.onStart();
        bin();
        data( + ProductCustomAdapter.TotalPrice() + " ج م ");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products.this, CartActivity.class));

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        title.setText("المنتجات");
        recyclerView = findViewById(R.id.recycler_view);

        linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        builder = new AlertDialog.Builder(Products.this);
        builder.setTitle("خطأ");
        builder.setMessage("لا يوجد اتصال بالانترنت");
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        mCustomAdapter = new ProductCustomAdapter(Products.this, s, "products");
        recyclerView.setAdapter(mCustomAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 10);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > 20) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(Products.this);
                    Intent intent = new Intent(Products.this, SubSearch.class);
                    intent.putExtra("subid", getIntent().getStringExtra("id"));
                    startActivity(intent, options.toBundle());
                } else {
                    Intent intent = new Intent(Products.this, SubSearch.class);
                    intent.putExtra("subid", getIntent().getStringExtra("id"));
                    startActivity(intent);
                }

            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products.this, CartActivity.class));
            }
        });
        loadFirstPage();


    }

    public void data(String totalPrice) {
        total.setText(totalPrice + "");
    }

    public void bin() {
        number.setText(ProductCustomAdapter.oreders.size() + "");

    }

    private void loadFirstPage() {
        model = ViewModelProviders.of(this).get(ProductVM.class);
        model.getResult(this, getIntent().getStringExtra("id"), pb, builder, viewprogress, currentPage + "").observe(this, new Observer<ArrayList<Product>>() {

            @Override
            public void onChanged(@Nullable ArrayList<Product> s) {
                mCustomAdapter.addAll(s);
                if (s.size() > 0) mCustomAdapter.addLoadingFooter();
                else isLastPage = true;


            }


        });


    }

    private void loadNextPage() {
        Log.i("oooooo", currentPage + "");
        model = ViewModelProviders.of(this).get(ProductVM.class);
        model.getResult(this, getIntent().getStringExtra("id"), pb, builder
                , viewprogress, currentPage + "").observe(this, new Observer<ArrayList<Product>>() {

            @Override
            public void onChanged(@Nullable ArrayList<Product> s) {
                Log.i("pppppppp", s.size() + "");
                mCustomAdapter.removeLoadingFooter();
                isLoading = false;

                mCustomAdapter.addAll(s);
                if (s.size() > 0) mCustomAdapter.addLoadingFooter();
                else isLastPage = true;


            }


        });


    }


}
