package kr.insungjung.pizzaorderapp_ex.datas;

import java.io.Serializable;

public class Store implements Serializable {

    public String storeNameTxt;
    public String openTimeTxt;
    public String phoneNumTxt;
    public String menuTxt;

    public Store(String storeNameTxt, String openTimeTxt, String phoneNumTxt, String menuTxt) {
        this.storeNameTxt = storeNameTxt;
        this.openTimeTxt = openTimeTxt;
        this.phoneNumTxt = phoneNumTxt;
        this.menuTxt = menuTxt;

    }
}
