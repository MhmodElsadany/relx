package com.example.talbk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Model.Oreder;
import com.example.talbk.R;

public class CustomAdapterCheck extends RecyclerView.Adapter<CustomAdapterCheck.ViewHolderProduct> {
    Context mContext;

    public CustomAdapterCheck() {

    }

    public CustomAdapterCheck(Context mContext) {
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public CustomAdapterCheck.ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkout, viewGroup, false);

        return new CustomAdapterCheck.ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterCheck.ViewHolderProduct viewHolderProduct, int i) {
        Oreder order = ProductCustomAdapter.oreders.get(i);
        viewHolderProduct.name.setText(order.getProduct().getName() + "");
        viewHolderProduct.price.setText(order.getProduct().getPrice() + " ج.م");

        viewHolderProduct.totalPrice.setText(order.getPrice() + " ج.م");
        viewHolderProduct.quantity.setText(order.getQuantity() + "");

    }

    @Override
    public int getItemCount() {

        return ProductCustomAdapter.oreders.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        TextView name, quantity, price, totalPrice;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            price = (TextView) itemView.findViewById(R.id.price);
            totalPrice = (TextView) itemView.findViewById(R.id.total);


        }

    }
}
