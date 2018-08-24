package com.mhp.requestmicro.entity.export;/*
 * Created by Joseph Wednesday, 8/30/2017 at 10:01 PM.
 */

public class UserInfo {
    private String fullName;
    private double jan,feb,mar,apr,may,jun,jul,aug,sept,oct,nov,dec;
    private double remainingDays;

    private UserInfo(){}

    public UserInfo(String fullName, double jan, double feb, double mar, double apr, double may, double jun,
                    double jul, double aug, double sept, double oct, double nov, double dec, double remainingDays) {
        this.fullName = fullName;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sept = sept;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
        this.remainingDays = remainingDays;
    }

    public String getFullName() {
        return fullName;
    }

    public double getJan() {
        return jan;
    }

    public double getFeb() {
        return feb;
    }

    public double getMar() {
        return mar;
    }

    public double getApr() {
        return apr;
    }

    public double getMay() {
        return may;
    }

    public double getJun() {
        return jun;
    }

    public double getJul() {
        return jul;
    }

    public double getAug() {
        return aug;
    }

    public double getSept() {
        return sept;
    }

    public double getOct() {
        return oct;
    }

    public double getNov() {
        return nov;
    }

    public double getDec() {
        return dec;
    }

    public double getRemainingDays() {
        return remainingDays;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "fullName='" + fullName + '\'' +
                ", jan=" + jan +
                ", feb=" + feb +
                ", mar=" + mar +
                ", apr=" + apr +
                ", may=" + may +
                ", jun=" + jun +
                ", jul=" + jul +
                ", aug=" + aug +
                ", sept=" + sept +
                ", oct=" + oct +
                ", nov=" + nov +
                ", dec=" + dec +
                ", remainingDays=" + remainingDays +
                '}';
    }
}
