package kr.insungjung.pizzaorderapp_ex.datas;

import java.io.Serializable;

public class Store implements Serializable {

    public String name;
    public String openTime;
    public String phoneNum;
    public String logoUrl;

    public Store(String name, String openTime, String phoneNum, String logoUrl) {
        this.name = name;
        this.openTime = openTime;
        this.phoneNum = phoneNum;
        this.logoUrl = logoUrl;
    }
}