package kr.insungjung.pizzaorderapp_ex.datas;

public class Pizza {

    public String pizzaImgUrl;
    public String pizzaName;
    public int pizzaCost;

    public Pizza(String pizzaImgUrl, String pizzaName, int pizzaCost) {
        this.pizzaImgUrl = pizzaImgUrl;
        this.pizzaName = pizzaName;
        this.pizzaCost = pizzaCost;
    }

    public String getPizzaImgUrl() {
        return pizzaImgUrl;
    }

    public void setPizzaImgUrl(String pizzaImgUrl) {
        this.pizzaImgUrl = pizzaImgUrl;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getPizzaCost() {
        return pizzaCost;
    }

    public void setPizzaCost(int pizzaCost) {
        this.pizzaCost = pizzaCost;
    }

}
