package com.example.talbk.UI;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Adapter.CartAdapter;
import com.example.talbk.Adapter.ProductCustomAdapter;
import com.example.talbk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    CartAdapter mCustomAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.notfound)
    TextView notfound;
    @BindView(R.id.check)
    Button check;
    @BindView(R.id.number)
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (ProductCustomAdapter.oreders.size() <= 0) {
            notfound.setVisibility(View.VISIBLE);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        title.setText("السلة");
        total.setText(ProductCustomAdapter.TotalPrice() + " ج.م ");
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ProductCustomAdapter.oreders.size() > 0) {

                    OrderD alert = new OrderD();
                    alert.showDialog(CartActivity.this, "nodirect", "nodirect");
                } else {
                    Toast.makeText(CartActivity.this, "عفوا لا يوجد اى منتجات فى السله", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mCustomAdapter = new CartAdapter(this);
        recyclerView.setAdapter(mCustomAdapter);
        int resId = R.anim.layout_animation;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setLayoutAnimation(animation);

    }

    public void data(String totalPrice) {
        total.setText(totalPrice + "");
    }

    public void changeRecyler() {
        mCustomAdapter.notifyDataSetChanged();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void bin() {
        number.setText(ProductCustomAdapter.oreders.size() + "");

    }

    @Override
    protected void onStart() {
        super.onStart();
        bin();
    }
}
