package kr.insungjung.pizzaorderapp_ex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.insungjung.pizzaorderapp_ex.R;
import kr.insungjung.pizzaorderapp_ex.datas.Store;

public class StoreAdapter extends ArrayAdapter<Store> {

    Context mContext;
    List<Store> mList;
    LayoutInflater inf;

    public StoreAdapter(Context context, List<Store> list) {

        super(context, R.layout.store_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.store_list_item, null);
        }


        Store storeData = mList.get(position);

        TextView storeNameTxt = row.findViewById(R.id.storeNameTxt);
        TextView openTimeTxt = row.findViewById(R.id.openTimeTxt);
        TextView priceOrInstalledTxt = row.findViewById(R.id.priceOrInstalledTxt);

//        0427_01

        ImageView star1 = row.findViewById(R.id.star1);
        ImageView star2 = row.findViewById(R.id.star2);
        ImageView star3 = row.findViewById(R.id.star3);
        ImageView star4 = row.findViewById(R.id.star4);
        ImageView star5 = row.findViewById(R.id.star5);

//        02
        List<ImageView> stars = new ArrayList<>();
        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);

        /* 03. 02번과 다른 for 문 만들어 본다. => stars 를 empty 로 만들어주는 코드 */
        for (ImageView star : stars) {
            star.setImageResource(R.drawable.star_empty);
        }
        /* 03 */

        for (int i = 0; i < appData.userRating; i++) {
            stars.get(i).setImageResource(R.drawable.star_fill); // 스타즈에게 니가 가진 것 중에 i 번째 것을 뽑아와
        }
//        02

        /* 이런식으로 하지 말고 for 문 돌려라
        if (appData.userRating == 1) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_empty);
            star3.setImageResource(R.drawable.star_empty);
            star4.setImageResource(R.drawable.star_empty);
            star5.setImageResource(R.drawable.star_empty);
        }*/
//        01

        // 등수와 제목을 세팅.
        rankAndTitleTxt.setText(String.format("%d. %s", appData.rank, appData.title));
        // 회사 이름은 가진 그대로 대입.
        companyNameTxt.setText(appData.companyName);
        // 만약 설치가 되었다면?  설치된 항목
        // 안되었다면? 가격을 띄워줌. 3,500,000원 의 양식.

        if (appData.isMine) {
//            내가 설치한 항목일 경우에는 반드시 설치된 항목이라고 띄우도록 명령.
//            재사용성 때문에 원하지 않는 데이터가 나올 수 있음.

            priceOrInstalledTxt.setText("설치된 항목");

        }
        else {
//            설치하지 않은 경우.
//            String.format 의 %,d 를 이용해 세자리마다 컴마 찍음.
            priceOrInstalledTxt.setText(String.format("%,d원", appData.price));
        }


        return row;
    }
}

