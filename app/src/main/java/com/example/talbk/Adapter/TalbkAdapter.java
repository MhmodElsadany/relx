package com.example.talbk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talbk.Model.UserOrder;
import com.example.talbk.R;

import java.util.ArrayList;

public class TalbkAdapter extends RecyclerView.Adapter<TalbkAdapter.ViewHolderProduct> {
    Context mContext;
    ArrayList<UserOrder> userOrders;
    Typeface typefaceuser;
    int number;

    public TalbkAdapter() {
    }

    public TalbkAdapter(Context mContext, ArrayList<UserOrder> userOrders) {
        this.mContext = mContext;
        this.userOrders = userOrders;

    }

    @NonNull
    @Override
    public TalbkAdapter.ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_user_info, viewGroup, false);
        return new TalbkAdapter.ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TalbkAdapter.ViewHolderProduct viewHolderProduct, int i) {
        number = userOrders.size();
        number = number - i;
        viewHolderProduct.number.setText("#" + number + "");
        viewHolderProduct.date.setText(userOrders.get(i).getOrder_date() + "");
        viewHolderProduct.totalPrice.setText(userOrders.get(i).getTotalprice() + " ج.م ");
    }

    @Override
    public int getItemCount() {
        return userOrders.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        TextView number;
        TextView date;
        TextView totalPrice;
        TextView contact;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
            date = (TextView) itemView.findViewById(R.id.order_date);
            totalPrice = (TextView) itemView.findViewById(R.id.total_price);
            contact = (TextView) itemView.findViewById(R.id.contact);
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:01221738737"));
                    mContext.startActivity(intent);
                }
            });

        }

    }
}
