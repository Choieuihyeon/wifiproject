package com.example.wifiproject8.domain;

public class HistoryInfo {
    // ID, X좌표, Y좌표, 조회일자, 비고
    private int id;     // ID
    private String x;   // X좌표
    private String y;   // Y좌표
    private String inqDate;

    public HistoryInfo() {
    }

    public HistoryInfo(int id, String x, String y, String inqDate) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.inqDate = inqDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getInqDate() {
        return inqDate;
    }

    public void setInqDate(String inqDate) {
        this.inqDate = inqDate;
    }
}
