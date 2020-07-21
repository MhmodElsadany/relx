package com.example.talbk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.talbk.Model.Oreder;
import com.example.talbk.Model.Product;
import com.example.talbk.R;
import com.example.talbk.UI.Offers;
import com.example.talbk.UI.Products;
import com.example.talbk.UI.SearchActivity;
import com.example.talbk.UI.SubSearch;

import java.util.ArrayList;
import java.util.List;


public class ProductCustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static Context mContext;
    ArrayList<Product> productModels;
    public static ArrayList<Oreder> oreders = new ArrayList<>();
    String type;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;


    public ProductCustomAdapter(Context mContext, ArrayList<Product> productModels, String type) {
        this.mContext = mContext;
        this.type = type;
        if (type.equals("products")) {
            this.productModels = new ArrayList<>();
        } else {
            this.productModels = productModels;

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (i) {
            case ITEM:
                if (type.equals("offers")) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offers_info, viewGroup, false);
                    viewHolder = new ProductCustomAdapter.ViewHolderProduct(view);
                } else {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_info, viewGroup, false);
                    viewHolder = new ProductCustomAdapter.ViewHolderProduct(view);
                }
                break;
            case LOADING:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_progress, viewGroup, false);
                viewHolder = new LoadingVH(view);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolderProduct, int i) {

        switch (getItemViewType(i)) {
            case ITEM:
                final ViewHolderProduct viewHolderProduct1 = (ViewHolderProduct) viewHolderProduct;
                viewHolderProduct1.name.setText(productModels.get(i).getName() + "");
                viewHolderProduct1.type.setText(productModels.get(i).getType() + "");
                viewHolderProduct1.priceText.setText(productModels.get(i).getPrice() + " ج.م");
                Glide.with(mContext.getApplicationContext())
                        .load("http://www.egyvenom.net/images/pic1.png")
                        .placeholder(R.drawable.load)
                        .into(viewHolderProduct1.img);
                if (oreders.size() > 0) {
                    boolean flag = false;
                    for (int j = 0; j < oreders.size(); j++) {
                        if (oreders.get(j).getProduct().getId().equals(productModels.get(i).getId())) {
                            viewHolderProduct1.counter.setText(oreders.get(j).getQuantity() + "");
                            flag = true;
                        } else {

                        }
                        if (flag == false) {
                            viewHolderProduct1.counter.setText(0.0 + "");

                        }

                    }
                }

                break;

            case LOADING:
//                Do nothing
                break;


        }

    }


    @Override
    public int getItemCount() {

        return productModels == null ? 0 : productModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == productModels.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(Product r) {
        productModels.add(r);
        notifyItemInserted(productModels.size() - 1);
    }

    public void addAll(List<Product> moveResults) {
        for (Product result : moveResults) {
            add(result);
        }
    }

    public void remove(Product r) {
        int position = productModels.indexOf(r);
        if (position > -1) {
            productModels.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Product());

    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = productModels.size() - 1;
        Product result = getItem(position);

        if (result != null) {
            productModels.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Product getItem(int position) {
        return productModels.get(position);
    }


    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        TextView counter, priceText, name, type;
        ImageView minus, plus, img;


        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            counter = (TextView) itemView.findViewById(R.id.textor);
            priceText = (TextView) itemView.findViewById(R.id.price);
            minus = (ImageView) itemView.findViewById(R.id.minus);
            plus = (ImageView) itemView.findViewById(R.id.plus);
            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);
            type = (TextView) itemView.findViewById(R.id.type);

            if (mContext instanceof Products) {
                ((Products) mContext).data(TotalPrice() + " ج.م");
            } else if (mContext instanceof SearchActivity) {
                ((SearchActivity) mContext).data(TotalPrice() + " ج.م ");

            } else if (mContext instanceof Offers) {
                ((Offers) mContext).data(TotalPrice() + " ج.م ");

            }
            else if (mContext instanceof SubSearch) {
                ((SubSearch) mContext).data(TotalPrice() + " ج.م ");
            }


            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Oreder order = new Oreder();
                    boolean flag = false;
                    for (int i = 0; i < oreders.size(); i++) {
                        if (oreders.get(i).getProduct().getId().equals(productModels.get(getAdapterPosition()).getId())) {
                            flag = true;
                            order = oreders.get(i);
                            String type = productModels.get(getAdapterPosition()).getType() + "";
                            if (type.equals("كيلو")) {
                                oreders.get(i).setQuantity((float) (oreders.get(i).getQuantity() + 0.25));

                            } else {
                                oreders.get(i).setQuantity(oreders.get(i).getQuantity() + 1);
                            }

                            counter.setText(oreders.get(i).getQuantity() + "");
                            order.setPrice(String.valueOf(Float.parseFloat(productModels.get(getAdapterPosition()).getPrice())
                                    * oreders.get(i).getQuantity()));
                            TotalPrice();

                        }
                    }

                    if (flag == false) {

                        String type = productModels.get(getAdapterPosition()).getType() + "";
                        if (type.equals("كيلو")) {
                            order.setQuantity(0.25f);
                            oreders.add(new Oreder(productModels.get(getAdapterPosition()),
                                    String.valueOf(Float.parseFloat(productModels.get(getAdapterPosition()).getPrice()) * 0.25), 0.25f));
                            if (mContext instanceof SearchActivity) {
                                ((SearchActivity) mContext).bin();
                            } else if (mContext instanceof Products) {
                                ((Products) mContext).bin();
                            } else if (mContext instanceof Offers) {
                                ((Offers) mContext).bin();
                            }
                            else if (mContext instanceof SubSearch) {
                                ((SubSearch) mContext).bin();
                            }


                        } else {
                            order.setQuantity(1);
                            oreders.add(new Oreder(productModels.get(getAdapterPosition()),
                                    productModels.get(getAdapterPosition()).getPrice(), 1));

                            if (mContext instanceof SearchActivity) {
                                ((SearchActivity) mContext).bin();
                            } else if (mContext instanceof Products) {
                                ((Products) mContext).bin();
                            } else if (mContext instanceof Offers) {
                                ((Offers) mContext).bin();
                            } else if (mContext instanceof SubSearch) {
                                ((SubSearch) mContext).bin();
                            }

                        }


                        order.setPrice(productModels.get(getAdapterPosition()).getPrice());
                        counter.setText(order.getQuantity() + "");
                        TotalPrice();

                    }

                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!counter.getText().toString().equals("0.0")) {
                        if (oreders.size() > 0) {

                            for (int i = 0; i < oreders.size(); i++) {
                                if (oreders.get(i).getProduct().getId().equals(productModels.get(getAdapterPosition()).getId())) {

                                    String type = productModels.get(getAdapterPosition()).getType() + "";
                                    if (type.equals("كيلو")) {
                                        oreders.get(i).setQuantity((float) (oreders.get(i).getQuantity() - 0.25));

                                    } else {
                                        oreders.get(i).setQuantity(oreders.get(i).getQuantity() - 1);
                                    }

                                    oreders.get(i).setPrice(String.valueOf(Float.parseFloat(productModels.get(getAdapterPosition()).getPrice())
                                            * oreders.get(i).getQuantity()));
                                    counter.setText(oreders.get(i).getQuantity() + "");
                                    if (counter.getText().toString().equals("0.0")) {
                                        oreders.remove(i);
                                        if (mContext instanceof SearchActivity) {
                                            ((SearchActivity) mContext).bin();
                                        } else if (mContext instanceof Products) {
                                            ((Products) mContext).bin();
                                        } else if (mContext instanceof Offers) {
                                            ((Offers) mContext).bin();
                                        } else if (mContext instanceof SubSearch) {
                                            ((SubSearch) mContext).bin();
                                        }
                                    }
                                }
                            }
                        }

                        TotalPrice();


                    } else {

                    }
                }
            });

        }

    }

    static public float TotalPrice() {
        float result = 0;
        for (int i = 0; i < ProductCustomAdapter.oreders.size(); i++) {
            result += Float.parseFloat(ProductCustomAdapter.oreders.get(i).getPrice());
        }
        if (mContext instanceof Products) {
            ((Products) mContext).data(+result + " ج.م ");
        } else if (mContext instanceof SearchActivity) {
            ((SearchActivity) mContext).data(+result + " ج.م ");

        } else if (mContext instanceof Offers) {
            ((Offers) mContext).data(result + " ج.م ");

        } else if (mContext instanceof SubSearch) {
            ((SubSearch) mContext).data(result + " ج.م ");
        }


        return result;
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

}

