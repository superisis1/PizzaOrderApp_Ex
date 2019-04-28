package kr.insungjung.pizzaorderapp_ex;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        fillPizzas();

        pizzaAdapter = new PizzaAdapter(mContext, pizza);
        act.menuSpinner.setAdapter(pizzaAdapter);

        act.menuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Toast.makeText(mContext, String.format("%s를 선택하셨습니다.", pizza.get(position).pizzaName), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        act.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedPosition = act.menuSpinner.getSelectedItemPosition();

                String selectedPizzaName = pizza.get(selectedPosition).pizzaName;

                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("선택한피자", selectedPizzaName);
                startActivity(intent);
                finish();

            }
        });

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

    }

    @Override
    public void setValues() {

        ImageView logoImg = findViewById(R.id.logoCircleImg);
        TextView nameTxt = findViewById(R.id.storeNameTxt);
        // TextView openTimeTxt = findViewById(R.id.openTimeTxt);
        TextView phoneNumTxt = findViewById(R.id.phoneNumTxt);

        mStore = (Store) getIntent().getSerializableExtra("가게정보");

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
        pizza.add(new Pizza("페페로니"));
        pizza.add(new Pizza("치즈피자"));
        pizza.add(new Pizza("하와이안"));
        pizza.add(new Pizza("버섯피자"));
        pizza.add(new Pizza("시금치피자"));
        pizza.add(new Pizza("베이컨체다치즈 피자"));
        pizza.add(new Pizza("슈퍼슈프림"));
    }

}
