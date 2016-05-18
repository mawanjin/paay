package com.papa91.paay;

/**
 * Created by lala on 15/11/13.
 */
public class PayResponse {
    private int error;
    private PayResponseData data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public PayResponseData getData() {
        return data;
    }

    public void setData(PayResponseData data) {
        this.data = data;
    }
}
