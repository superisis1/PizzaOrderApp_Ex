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
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import kr.insungjung.pizzaorderapp_ex.adapter.PizzaAdapter;
import kr.insungjung.pizzaorderapp_ex.databinding.ActivityStoreDetailBinding;
import kr.insungjung.pizzaorderapp_ex.datas.Pizza;

public class StoreDetailActivity extends BaseActivity {

    PizzaAdapter pizzaAdapter;

    List<Pizza> pizza = new ArrayList<>();

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

        /* 스피너 아이템 */
        final MaterialSpinner spinner = findViewById(R.id.menuSpinner);
        spinner.setItems("페페로니 피자", "치즈 피자", "버섯 피자", "시금치 피자", "베이컨 체다치즈 피자");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               /* Object menuItem = adapterView.getItemAtPosition(position);
                if (menuItem != null) {
                    Toast.makeText(mContext, item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(mContext, "Selected",
                        Toast.LENGTH_SHORT).show();*/
            }

           /* @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }*/
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
