package com.example.talbk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.talbk.Model.CateogryModel;
import com.example.talbk.R;
import com.example.talbk.UI.OrderDirect;
import com.example.talbk.UI.SubCategoryActivity;

import java.util.ArrayList;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolderProduct> {
    Context mContext;
    ArrayList<CateogryModel> productModels;

    public CustomAdapter() {
    }

    public CustomAdapter(Context mContext, ArrayList<CateogryModel> productModels) {
        this.mContext = mContext;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categori_info, viewGroup, false);
        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct viewHolderProduct, int i) {

        viewHolderProduct.title.setText(productModels.get(i).getName());
        setAnimation(viewHolderProduct.img, i);
        Glide.with(mContext)
                .load("http://www.egyvenom.net/images/" + productModels.get(i).getImage())
                .placeholder(R.drawable.load)
                .into(viewHolderProduct.img);
        setAnimation(viewHolderProduct.img, i);
    }

    protected int mLastPosition = -1;

    protected void setAnimation(View viewToAnimate, int position) {
        if (position > mLastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            mLastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titl);
            img = (ImageView) itemView.findViewById(R.id.img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (productModels.get(getAdapterPosition()).getFlag().equals("true")
                            || productModels.get(getAdapterPosition()).getFlag().equals("True")) {

                        Intent mIntent = new Intent(mContext, SubCategoryActivity.class);
                        mIntent.putExtra("id", productModels.get(getAdapterPosition()).getId());
                        mIntent.putExtra("title", productModels.get(getAdapterPosition()).getName());

                        mContext.startActivity(mIntent);
                    } else {
                        mContext.startActivity(new Intent(mContext, OrderDirect.class));

                    }

                }
            });

        }

    }
}
