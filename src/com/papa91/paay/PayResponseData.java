package com.papa91.paay;

/**
 * Created by lala on 15/11/13.
 */
public class PayResponseData {

    private boolean is_buy;
    private String price_message;
    private String buy_error_message;
    private String result;
    private String error_message;
    private String status;
    private String error;
    private String join_message;
    private int money;
    private int create_money;
    private int join_money;


    public boolean isIs_buy() {
        return is_buy;
    }

    public void setIs_buy(boolean is_buy) {
        this.is_buy = is_buy;
    }

    public String getPrice_message() {
        return price_message;
    }

    public void setPrice_message(String price_message) {
        this.price_message = price_message;
    }

    public String getBuy_error_message() {
        return buy_error_message;
    }

    public void setBuy_error_message(String buy_error_message) {
        this.buy_error_message = buy_error_message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public boolean is_buy() {
        return is_buy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCreate_money() {
        return create_money;
    }

    public void setCreate_money(int create_money) {
        this.create_money = create_money;
    }

    public int getJoin_money() {
        return join_money;
    }

    public void setJoin_money(int join_money) {
        this.join_money = join_money;
    }

    public String getJoin_message() {
        return join_message;
    }

    public void setJoin_message(String join_message) {
        this.join_message = join_message;
    }
}
