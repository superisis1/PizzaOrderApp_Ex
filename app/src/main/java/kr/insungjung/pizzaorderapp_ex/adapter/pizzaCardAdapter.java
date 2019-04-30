package kr.insungjung.pizzaorderapp_ex.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.insungjung.pizzaorderapp_ex.R;
import kr.insungjung.pizzaorderapp_ex.StoreDetailActivity;
import kr.insungjung.pizzaorderapp_ex.datas.Pizza;

public class PizzaCardAdapter extends RecyclerView.Adapter<PizzaCardAdapter.ViewHolder> {


    private final List<Pizza> mList;

    public PizzaCardAdapter(List<Pizza> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pizza_card_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pizza pizza = mList.get(position);

        holder.pizzaImg.setImageURI(Uri.parse(pizza.getPizzaImgUrl()));
        holder.pizzaName.setText(pizza.getPizzaName());
        holder.pizzaCost.setText(String.format("%,3d 원", pizza.getPizzaCost()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImg;
        TextView pizzaName;
        TextView pizzaCost;
        public ViewHolder(View itemView) {
            super(itemView);
            pizzaImg = itemView.findViewById(R.id.pizzaImg);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaCost = itemView.findViewById(R.id.pizzaCost);
        }

    }
}
