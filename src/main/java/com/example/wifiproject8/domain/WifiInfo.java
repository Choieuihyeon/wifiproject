package com.example.wifiproject8.domain;

public class WifiInfo {
    // 거리(Km) 관리번호 자치구(String) 와이파이명 도로명주소(String) 상세주소 설치위치(층) 설치유형 설치기관 서비스구분 망종류 설치년도 실내외구분 WIFI접속환경
    // X좌표 Y좌표 작업일자
    private String mgrNo;       // 관리번호
    private String wrdofc;      // 자치구
    private String mainNm;      // 와이파이명
    private String adres1;      // 도로명주소
    private String adres2;      // 상세주소
    private String instlFloor;  // 설치위치(층)
    private String instlTy;     // 설치유형
    private String instlMby;    // 설치기관
    private String svcSe;       // 서비스구분
    private String cmcwr;       // 망종류
    private String cnstcYear;   // 설치년도
    private String inoutDoor;   // 실내외구분
    private String remars3;     // WIFI 접속환경
    private String x;           // X좌표
    private String y;           // Y좌표
    private String workDttm;    // 작업일자

    public WifiInfo() {
    }

    public WifiInfo(String mgrNo, String wrdofc, String mainNm, String adres1, String adres2, String instlFloor, String instlTy, String instlMby, String svcSe, String cmcwr, String cnstcYear, String inoutDoor, String remars3, String x, String y, String workDttm) {
        this.mgrNo = mgrNo;
        this.wrdofc = wrdofc;
        this.mainNm = mainNm;
        this.adres1 = adres1;
        this.adres2 = adres2;
        this.instlFloor = instlFloor;
        this.instlTy = instlTy;
        this.instlMby = instlMby;
        this.svcSe = svcSe;
        this.cmcwr = cmcwr;
        this.cnstcYear = cnstcYear;
        this.inoutDoor = inoutDoor;
        this.remars3 = remars3;
        this.x = x;
        this.y = y;
        this.workDttm = workDttm;
    }

    public String getMgrNo() {
        return mgrNo;
    }

    public void setMgrNo(String mgrNo) {
        this.mgrNo = mgrNo;
    }

    public String getWrdofc() {
        return wrdofc;
    }

    public void setWrdofc(String wrdofc) {
        this.wrdofc = wrdofc;
    }

    public String getMainNm() {
        return mainNm;
    }

    public void setMainNm(String mainNm) {
        this.mainNm = mainNm;
    }

    public String getAdres1() {
        return adres1;
    }

    public void setAdres1(String adres1) {
        this.adres1 = adres1;
    }

    public String getAdres2() {
        return adres2;
    }

    public void setAdres2(String adres2) {
        this.adres2 = adres2;
    }

    public String getInstlFloor() {
        return instlFloor;
    }

    public void setInstlFloor(String instlFloor) {
        this.instlFloor = instlFloor;
    }

    public String getInstlTy() {
        return instlTy;
    }

    public void setInstlTy(String instlTy) {
        this.instlTy = instlTy;
    }

    public String getInstlMby() {
        return instlMby;
    }

    public void setInstlMby(String instlMby) {
        this.instlMby = instlMby;
    }

    public String getSvcSe() {
        return svcSe;
    }

    public void setSvcSe(String svcSe) {
        this.svcSe = svcSe;
    }

    public String getCmcwr() {
        return cmcwr;
    }

    public void setCmcwr(String cmcwr) {
        this.cmcwr = cmcwr;
    }

    public String getCnstcYear() {
        return cnstcYear;
    }

    public void setCnstcYear(String cnstcYear) {
        this.cnstcYear = cnstcYear;
    }

    public String getInoutDoor() {
        return inoutDoor;
    }

    public void setInoutDoor(String inoutDoor) {
        this.inoutDoor = inoutDoor;
    }

    public String getRemars3() {
        return remars3;
    }

    public void setRemars3(String remars3) {
        this.remars3 = remars3;
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

    public String getWorkDttm() {
        return workDttm;
    }

    public void setWorkDttm(String workDttm) {
        this.workDttm = workDttm;
    }
}
