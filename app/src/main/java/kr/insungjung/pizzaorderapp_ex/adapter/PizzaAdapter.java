package kr.insungjung.pizzaorderapp_ex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kr.insungjung.pizzaorderapp_ex.R;
import kr.insungjung.pizzaorderapp_ex.datas.Pizza;

public class PizzaAdapter extends ArrayAdapter<Pizza> {

    private Context mContext;
    private List<Pizza> mList;
    private LayoutInflater inf;

    public PizzaAdapter(Context context, List<Pizza> list) {

        super(context, R.layout.pizza_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.pizza_item, null);
        }

        Pizza pizzaData = mList.get(position);

        TextView menuTxt = row.findViewById(R.id.pizzaNameTxt1);

        menuTxt.setText(pizzaData.pizzaName);

        return row;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.pizza_list_item, null);
        }

        Pizza pizzaData = mList.get(position);

        TextView menuTxt = row.findViewById(R.id.pizzaNameTxt2);

        menuTxt.setText(pizzaData.pizzaName);

        return row;

    }
}





