package kr.insungjung.pizzaorderapp_ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import kr.insungjung.pizzaorderapp_ex.adapter.StoreAdapter;
import kr.insungjung.pizzaorderapp_ex.databinding.ActivityMainBinding;
import kr.insungjung.pizzaorderapp_ex.datas.Store;

public class MainActivity extends AppCompatActivity {

    StoreAdapter mStoreAdapter;

    List<Store> storeList = new ArrayList<>();

    ActivityMainBinding act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
