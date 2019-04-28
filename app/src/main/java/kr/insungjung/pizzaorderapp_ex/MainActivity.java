package kr.insungjung.pizzaorderapp_ex;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.insungjung.pizzaorderapp_ex.adapter.StoreAdapter;
import kr.insungjung.pizzaorderapp_ex.databinding.ActivityMainBinding;
import kr.insungjung.pizzaorderapp_ex.datas.Store;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;
    List<Store> pizzaStores = new ArrayList<>();
    StoreAdapter mStoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        setupEvents();
        setValues();

        mStoreAdapter = new StoreAdapter(mContext, pizzaStores);
        act.storeListView.setAdapter(mStoreAdapter);

        String selectedPizza = getIntent().getStringExtra("선택한피자");
        Toast.makeText(mContext, String.format("%s 를 선택하셨습니다!", selectedPizza), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setupEvents() {

        act.storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Store clickedStoreData = pizzaStores.get(position);

                Intent intent = new Intent(MainActivity.this, StoreDetailActivity.class);
                intent.putExtra("가게정보", clickedStoreData);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

        setTitle("피자 주문 앱");

        fillPizzaStores();

        mStoreAdapter = new StoreAdapter(mContext, pizzaStores);
        act.storeListView.setAdapter(mStoreAdapter);
    }


    void fillPizzaStores() {

        pizzaStores.add(new Store("파파존스", "09:00~21:00", "010-1234-5678", "http://postfiles2.naver.net/20160530_65/ppanppane_1464617766007O9b5u_PNG/%C6%C4%C6%C4%C1%B8%BD%BA_%C7%C7%C0%DA_%B7%CE%B0%ED_%284%29.png?type=w966"));
        pizzaStores.add(new Store("피자헛", "09:00~21:00", "010-1234-5678", "https://upload.wikimedia.org/wikipedia/en/thumb/d/d2/Pizza_Hut_logo.svg/220px-Pizza_Hut_logo.svg.png"));
        pizzaStores.add(new Store("도미노피자", "09:00~21:00", "010-1234-5678", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Domino%27s_pizza_logo.svg/120px-Domino%27s_pizza_logo.svg.png"));
        pizzaStores.add(new Store("미스터피자", "09:00~21:00", "010-1234-5678", "http://postfiles12.naver.net/20160530_171/ppanppane_14646177044221JRNd_PNG/%B9%CC%BD%BA%C5%CD%C7%C7%C0%DA_%B7%CE%B0%ED_%281%29.png?type=w966"));
        pizzaStores.add(new Store("피자에땅", "09:00~21:00", "010-1234-5678", "https://mblogthumb-phinf.pstatic.net/20160530_214/ppanppane_1464617804922knKn4_PNG/%C7%C7%C0%DA%BF%A1%B6%A5_%B7%CE%B0%ED_%282%29.png?type=w800"));
        pizzaStores.add(new Store("피자스쿨", "09:00~21:00", "010-1234-5678", "https://modo-phinf.pstatic.net/20150501_269/1430484184544WKwLF_JPEG/mosa7NPaR2.jpeg?type=f320_320"));
        pizzaStores.add(new Store("피자나라 치킨공주", "09:00~21:00", "010-1234-5678", "http://www.newsworker.co.kr/news/photo/201810/22592_19806_5940.jpg"));
        pizzaStores.add(new Store("파파존스", "09:00~21:00", "010-1234-5678", "http://postfiles2.naver.net/20160530_65/ppanppane_1464617766007O9b5u_PNG/%C6%C4%C6%C4%C1%B8%BD%BA_%C7%C7%C0%DA_%B7%CE%B0%ED_%284%29.png?type=w966"));
        pizzaStores.add(new Store("피자헛", "09:00~21:00", "010-1234-5678", "https://upload.wikimedia.org/wikipedia/en/thumb/d/d2/Pizza_Hut_logo.svg/220px-Pizza_Hut_logo.svg.png"));
        pizzaStores.add(new Store("도미노피자", "09:00~21:00", "010-1234-5678", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Domino%27s_pizza_logo.svg/120px-Domino%27s_pizza_logo.svg.png"));
        pizzaStores.add(new Store("미스터피자", "09:00~21:00", "010-1234-5678", "http://postfiles12.naver.net/20160530_171/ppanppane_14646177044221JRNd_PNG/%B9%CC%BD%BA%C5%CD%C7%C7%C0%DA_%B7%CE%B0%ED_%281%29.png?type=w966"));
        pizzaStores.add(new Store("피자에땅", "09:00~21:00", "010-1234-5678", "https://mblogthumb-phinf.pstatic.net/20160530_214/ppanppane_1464617804922knKn4_PNG/%C7%C7%C0%DA%BF%A1%B6%A5_%B7%CE%B0%ED_%282%29.png?type=w800"));
        pizzaStores.add(new Store("피자스쿨", "09:00~21:00", "010-1234-5678", "https://modo-phinf.pstatic.net/20150501_269/1430484184544WKwLF_JPEG/mosa7NPaR2.jpeg?type=f320_320"));
        pizzaStores.add(new Store("피자나라 치킨공주", "09:00~21:00", "010-1234-5678", "http://www.newsworker.co.kr/news/photo/201810/22592_19806_5940.jpg"));

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

}