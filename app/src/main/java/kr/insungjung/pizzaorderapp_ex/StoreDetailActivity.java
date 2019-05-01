package kr.insungjung.pizzaorderapp_ex;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import kr.insungjung.pizzaorderapp_ex.adapter.PizzaAdapter;
import kr.insungjung.pizzaorderapp_ex.adapter.PizzaCardAdapter;
import kr.insungjung.pizzaorderapp_ex.databinding.ActivityStoreDetailBinding;
import kr.insungjung.pizzaorderapp_ex.datas.Pizza;
import kr.insungjung.pizzaorderapp_ex.datas.Store;

public class StoreDetailActivity extends BaseActivity {

    PizzaAdapter pizzaAdapter;

    List<Pizza> pizza = new ArrayList<>();

    Store mStore;

    ActivityStoreDetailBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();

        RecyclerView pizzaCards = findViewById(R.id.pizzaRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        pizzaCards.setLayoutManager(layoutManager);

        // https://stackoverflow.com/questions/22813484/java-error-actual-and-formal-argument-lists-differ-in-length
        PizzaCardAdapter pizzaCardAdapter = new PizzaCardAdapter(pizza, mContext);
        pizzaCards.setAdapter(pizzaCardAdapter);



    }

    @Override
    public void setupEvents() {

        /* 전화걸기 */
        act.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Uri uri = Uri.parse(String.format("tel:%s", act.phoneNumTxt.getText().toString()));
                        Intent intent = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(mContext, "전화 권한이 거부되어 통화 실패", Toast.LENGTH_SHORT).show();
                    }
                };

                TedPermission.with(mContext)
                        .setPermissionListener(permissionListener)
                        .setDeniedMessage("전화권한을 거부하면 통화가 불가합니다. [설정]에서 활성화 해주세요.")
                        .setPermissions(Manifest.permission.CALL_PHONE)
                        .check();
            }
        });

        /* 문자보내기 */
        act.smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Uri uri = Uri.parse(String.format("smsto:%s", act.phoneNumTxt.getText().toString()));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setType("vnd.android-dir/mms-sms");
                        intent.setData(uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(mContext, "문자전송 권한이 거부되어 실패", Toast.LENGTH_SHORT).show();
                    }
                };

                TedPermission.with(mContext)
                        .setPermissionListener(permissionListener)
                        .setDeniedMessage("문자전송 권한을 거부하면 문자전송이 불가합니다. [설정]에서 활성화 해주세요.")
                        .setPermissions(Manifest.permission.SEND_SMS)
                        .check();
            }
        });

        /* 연락처 추가 */
        act.savePhoneNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //Uri uri = Uri.parse(String.format("tel:%s", act.phoneNumTxt.getText().toString()));
                        Intent intent = new Intent(Intent.ACTION_INSERT);
                        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                        intent.putExtra(ContactsContract.Intents.Insert.NAME, act.storeNameTxt.getText().toString());
                        intent.putExtra(ContactsContract.Intents.Insert.PHONE, act.phoneNumTxt.getText().toString());
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(mContext, "연락처 접근 권한이 거부되어 실패", Toast.LENGTH_SHORT).show();
                    }
                };

                TedPermission.with(mContext)
                        .setPermissionListener(permissionListener)
                        .setDeniedMessage("연락처 접근 권한을 거부하면 연락처 저장이 불가합니다. [설정]에서 활성화 해주세요.")
                        .setPermissions(Manifest.permission.READ_CONTACTS)
                        .check();
            }
        });


        fillPizzas();

        pizzaAdapter = new PizzaAdapter(mContext, pizza);
        act.menuSpinner.setAdapter(pizzaAdapter);

        /* 스피너 아이템 클릭 */
        act.menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Toast.makeText(mContext, String.format("%s를 선택하셨습니다.", pizza.get(position).pizzaName), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* ok 버튼 */
        act.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedPosition = act.menuSpinner.getSelectedItemPosition();

                String selectedPizzaName = pizza.get(selectedPosition).pizzaName;

                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("선택한피자", selectedPizzaName);
                intent.putExtra("실행허락", true);
                startActivity(intent);
                finish();
            }

        });

    }

    @Override
    public void setValues() {

        ImageView logoImg = findViewById(R.id.logoCircleImg);
        TextView nameTxt = findViewById(R.id.storeNameTxt);
        // TextView openTimeTxt = findViewById(R.id.openTimeTxt);
        TextView phoneNumTxt = findViewById(R.id.phoneNumTxt);

        mStore = (Store) getIntent().getSerializableExtra("가게정보");

        /* up 버튼 */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* 타이틀 */
        setTitle(String.format("%s 상세정보", mStore.name));

        Glide.with(mContext).load(mStore.logoUrl).into(logoImg);
        nameTxt.setText(mStore.name);
        // openTimeTxt.setText(mStore.openTime);
        phoneNumTxt.setText(mStore.phoneNum);

    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_store_detail);
    }

    void fillPizzas() {
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_TD5SQjfJ.jpg","페페로니", 20000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_m1G6UO53.jpg","치즈피자", 18000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_03clpqNq.jpg","하와이안", 24000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_hodWdX2e.jpg","베이컨체다치즈", 34000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20190131_w8469814.jpg","우리고구마", 20000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_w1WcPp2o.jpg","콰트로 치즈 퐁듀", 26000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_WqsN9t16.jpg","슈퍼디럭스", 22000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_7v9YHcEK.jpg","불고기", 22000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20190226_R7d7qxaL.jpg","더블크러스트 이베리코", 36000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20190226_WtefcsQd.jpg","블랙앵거스 스테이크", 32000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20181116_DwpeAY8n.jpg","글램핑 바베큐", 28000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_6UbajoYY.jpg","블랙타이거 슈림프", 36000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_ji6cyfry.jpg","와규 앤 비스테카", 34000));
        pizza.add(new Pizza("https://cdn.dominos.co.kr/admin/upload/goods/20180827_ZIna5r8b.jpg","직화 스테이크", 29000));
    }

}
