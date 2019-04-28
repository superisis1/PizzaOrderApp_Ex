package kr.insungjung.pizzaorderapp_ex;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import kr.insungjung.pizzaorderapp_ex.databinding.ActivityStoreDetailBinding;

public class StoreDetailActivity extends BaseActivity {

    ActivityStoreDetailBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();


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

        /* 스피너 클릭 */
        MaterialSpinner spinner = findViewById(R.id.menuSpinner);
        spinner.setItems("페페로니 피자", "치즈 피자", "버섯 피자", "시금치 피자", "베이컨 체다치즈 피자");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Toast.makeText(StoreDetailActivity.this, "스피너 클릭", Toast.LENGTH_SHORT).show();
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
}
