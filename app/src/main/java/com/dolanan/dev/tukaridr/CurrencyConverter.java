// CSIS 3175 Group Project : 300267900 Yunjie Gao ; 300280496 Hsueh-Cheng Liu
package com.dolanan.dev.tukaridr;

public class CurrencyConverter {
    private String targetCurrency;
    private String exchangeRate;
    private String targetName;
    private int targetPic;

    public int getTargetPic() {
        return targetPic;
    }
    public void setTargetPic(int targetPic) {
        this.targetPic = targetPic;
    }
    public String getTargetCurrency() {
        return targetCurrency;
    }
    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
    public String getexchangeRate() {
        return exchangeRate;
    }
    public void setexchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    public String getTargetName() { return targetName; }
    public void setTargetName(String targetName) {this.targetName = targetName; }

    @Override
    public String toString() {
        return "CurrencyConverter [targetCurrency=" + targetCurrency + ", exchangeRate="
                + exchangeRate + ", targetName=" + targetName + "]";
    }
}
