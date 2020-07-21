package com.example.talbk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.talbk.Model.SubCatregoryModel;
import com.example.talbk.R;
import com.example.talbk.UI.Products;

import java.util.ArrayList;

public class SubCustomAdapter extends RecyclerView.Adapter<SubCustomAdapter.ViewHolderProduct> {
    Context mContext;
    ArrayList<SubCatregoryModel> productModels;
    Typeface typefaceuser;

    public SubCustomAdapter() {
    }

    public SubCustomAdapter(Context mContext, ArrayList<SubCatregoryModel> productModels) {
        this.mContext = mContext;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public SubCustomAdapter.ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_info, viewGroup, false);
        return new SubCustomAdapter.ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCustomAdapter.ViewHolderProduct viewHolderProduct, int i) {

        viewHolderProduct.title.setText(productModels.get(i).getName());

        Glide.with(mContext)
                .load("http://www.egyvenom.net/images/" + productModels.get(i).getImage())
                .placeholder(R.drawable.load)
                .into(viewHolderProduct.img);

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView title;
        ImageView img;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            img = (ImageView) itemView.findViewById(R.id.immgs);
            mCardView = (CardView) itemView.findViewById(R.id.cart);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent = new Intent(mContext, Products.class);
                    mIntent.putExtra("id", productModels.get(getAdapterPosition()).getId());
                    mContext.startActivity(mIntent);


                }
            });
            typefaceuser = Typeface.createFromAsset(mContext.getAssets(), "fonts/Xerox Serif Wide Bold Italic.ttf");
            title.setTypeface(typefaceuser);

        }

    }
}
