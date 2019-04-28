package kr.insungjung.pizzaorderapp_ex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

public class StoreDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        MaterialSpinner spinner = findViewById(R.id.menuSpinner);
        spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Toast.makeText(StoreDetailActivity.this, "스피너 클릭", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
