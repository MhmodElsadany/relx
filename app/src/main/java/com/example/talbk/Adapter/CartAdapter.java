package com.example.talbk.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.talbk.Model.Oreder;
import com.example.talbk.R;
import com.example.talbk.UI.CartActivity;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolderProduct> {
    Context mContext;
    Typeface typefaceuser;

    public CartAdapter() {

    }

    public CartAdapter(Context mContext) {
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public CartAdapter.ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_info, viewGroup, false);

        return new CartAdapter.ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolderProduct viewHolderProduct, int i) {
        Oreder order = ProductCustomAdapter.oreders.get(i);
        viewHolderProduct.counter.setText(order.getQuantity() + "");
        viewHolderProduct.priceText.setText("الاجمالى " + order.getPrice() + " ج.م");
        viewHolderProduct.title.setText(order.getProduct().getName() + "");
        viewHolderProduct.description.setText(order.getProduct().getType() + "");
        viewHolderProduct.price.setText(order.getProduct().getPrice() + " ج.م/ ");
        Glide.with(mContext)
                .load("http://www.egyvenom.net/images/" + order.getProduct().getImage())
                .placeholder(R.drawable.load)
                .into(viewHolderProduct.imgpro);


    }

    @Override
    public int getItemCount() {
        return ProductCustomAdapter.oreders.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imgpro;
        ImageView minus, plus;
        TextView counter, priceText, description, price;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            minus = (ImageView) itemView.findViewById(R.id.minus);
            plus = (ImageView) itemView.findViewById(R.id.plus);
            imgpro = (ImageView) itemView.findViewById(R.id.imgpro);
            counter = (TextView) itemView.findViewById(R.id.textor);
            priceText = (TextView) itemView.findViewById(R.id.totalprice);
            description = (TextView) itemView.findViewById(R.id.description);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String type = ProductCustomAdapter.oreders.get(getAdapterPosition()).getProduct().getType() + "";
                    if (type.equals("كيلو")) {
                        ProductCustomAdapter.oreders.get(getAdapterPosition()).

                                setQuantity(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity() + 0.25f);
                    } else {
                        ProductCustomAdapter.oreders.get(getAdapterPosition()).

                                setQuantity(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity() + 1);

                    }


                    ProductCustomAdapter.oreders.get(getAdapterPosition()).
                            setPrice(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity()
                                    * Float.parseFloat(ProductCustomAdapter.oreders.get(getAdapterPosition())
                                    .getProduct().getPrice()) + "");
                    priceText.setText(ProductCustomAdapter.oreders.get(getAdapterPosition()).getPrice() + " ج.م");

                    counter.setText(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity() + "");
                    ProductCustomAdapter.TotalPrice();
                    if (mContext instanceof CartActivity) {
                        ((CartActivity) mContext).data(" الاجمالى" + ProductCustomAdapter.TotalPrice() + " ج.م");
                    }


                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String type = ProductCustomAdapter.oreders.get(getAdapterPosition()).getProduct().getType() + "";
                    if (type.equals("كيلو")) {
                        ProductCustomAdapter.oreders.get(getAdapterPosition()).
                                setQuantity(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity() - 0.25f);
                    } else {
                        ProductCustomAdapter.oreders.get(getAdapterPosition()).
                                setQuantity(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity() - 1);

                    }


                    ProductCustomAdapter.oreders.get(getAdapterPosition()).
                            setPrice(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity()
                                    * Float.parseFloat(ProductCustomAdapter.oreders.get(getAdapterPosition())
                                    .getProduct().getPrice()) + "");
                    priceText.setText("الاجمالى " + ProductCustomAdapter.oreders.get(getAdapterPosition()).getPrice() + " ج.م");
                    counter.setText(ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity() + "");
                    ProductCustomAdapter.TotalPrice();
                    if (mContext instanceof CartActivity) {
                        ((CartActivity) mContext).data(ProductCustomAdapter.TotalPrice() + " ج.م");
                    }
                    if (ProductCustomAdapter.oreders.get(getAdapterPosition()).getQuantity() == 0.0f) {
                        ProductCustomAdapter.oreders.remove(getAdapterPosition());
                        if (mContext instanceof CartActivity) {
                            ((CartActivity) mContext).changeRecyler();
                            ((CartActivity) mContext).bin();


                        }


                    }


                }
            });


        }

    }
}
