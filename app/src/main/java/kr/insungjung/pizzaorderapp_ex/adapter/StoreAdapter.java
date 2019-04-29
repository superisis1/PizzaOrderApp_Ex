package kr.insungjung.pizzaorderapp_ex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.insungjung.pizzaorderapp_ex.R;
import kr.insungjung.pizzaorderapp_ex.datas.Store;

public class StoreAdapter extends ArrayAdapter<Store> {

    Context mContext;
    List<Store> mList;
    LayoutInflater inf;


    public StoreAdapter(Context context, List<Store> list) {
        super(context, R.layout.store, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.store, null);
        }

        Store storeData = mList.get(position);

        ImageView logoImg = row.findViewById(R.id.logoCircleImg);
        TextView nameTxt = row.findViewById(R.id.storeNameTxt);
        TextView openTimeTxt = row.findViewById(R.id.openTimeTxt);

        Glide.with(mContext).load(storeData.logoUrl).into(logoImg);

        nameTxt.setText(storeData.name);
        openTimeTxt.setText(String.format("영업시간 : %s",storeData.openTime));


        return row;
    }
}