package kr.insungjung.pizzaorderapp_ex;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.insungjung.pizzaorderapp_ex.adapter.StoreAdapter;
import kr.insungjung.pizzaorderapp_ex.databinding.ActivityMainBinding;
import kr.insungjung.pizzaorderapp_ex.datas.Store;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding act;
    List<Store> pizzaStores = new ArrayList<>();
    StoreAdapter mStoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        setupEvents();
        setValues();

        /* Drawer 메뉴에 해당하는 부분 */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        /* 선택한 피자를 토스트메세지로 띄움 */
        String selectedPizza = getIntent().getStringExtra("선택한피자");
        Boolean isTrue = getIntent().getBooleanExtra("실행허락", false);
        if (isTrue == true) {
            Toast.makeText(mContext, String.format("%s 를 선택하셨습니다!", selectedPizza), Toast.LENGTH_SHORT).show();
        }
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

        //setTitle("피자 주문 앱");

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    /* 액션바 메뉴 */
    // 추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.topMenu1:
                Toast.makeText(getApplicationContext(), "메뉴1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.topMenu2:
                Toast.makeText(getApplicationContext(), "메뉴2", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.topMenu3:
                Toast.makeText(getApplicationContext(), "메뉴3", Toast.LENGTH_SHORT).show();
                return true;

            default:
                //Toast.makeText(getApplicationContext(), "디폴트", Toast.LENGTH_SHORT).show();

                return super.onOptionsItemSelected(item);

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.coupons) {
            // Handle the camera action
        } else if (id == R.id.shopping_cart) {

        } else if (id == R.id.order_history) {

        } else if (id == R.id.like_store) {

        } else if (id == R.id.notice_menu) {

        } else if (id == R.id.events) {

        } else if (id == R.id.service_center) {

        } else if (id == R.id.personal_info) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}