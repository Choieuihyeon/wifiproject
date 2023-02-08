package com.example.wifiproject8.service;

import com.example.wifiproject8.dto.WifiDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class WifiService {
    String key = "6a777a416563656837314a4b4d7942";

    public String getWifi() throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

        urlBuilder.append("/" + URLEncoder.encode("6a777a416563656837314a4b4d7942", "UTF-8")); // 인증키
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); //요청파일타입
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); // 서비스명
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); // 요청시작위치
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); // 요청종료위치 (1000까지)  요청종료위치 - 요청시작위치 < 1000

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Responsce Code: " + conn.getResponseCode());

        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        return sb.toString();
    }

    public int totalCnt() {
        int totalCnt = 19268;
        return totalCnt;
    }

    public void insertWifiData() throws IOException {
        try {
            int totalCnt = totalCnt();
            int index = 1;

            for (int i = 0; i <= totalCnt / 1000; i++) {
                StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

                urlBuilder.append("/" + URLEncoder.encode("6a777a416563656837314a4b4d7942", "UTF-8")); // 인증키
                urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); //요청파일타입
                urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); // 서비스명
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(index), "UTF-8")); // 요청시작위치
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf((index + 999)), "UTF-8")); // 요청종료위치 (1000까지)  요청종료위치 - 요청시작위치 < 1000

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");

                BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
                }

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                // 1. 문자열 형태의 JSON을 파싱하기 위한 JSONParser 객체 생성.
                JsonParser jsonParser = new JsonParser();
                // 2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
                JsonObject obj = (JsonObject) jsonParser.parse(sb.toString());
                JsonObject data = (JsonObject) obj.get("TbPublicWifiInfo");
                // 3. 필요한 리스트 데이터 부분만 가져와 JSONArray로 저장.
                JsonArray row = (JsonArray) data.get("row");

                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                Connection connection = null;
                PreparedStatement preparedStatement = null;
                String dbUrl = "/C:/dev/IntelliJ IDEA 2022.3.1/wifiproject7/wifiservice.db";
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:" + dbUrl);

                    for (int j = 0; j < row.size(); j++) {
                        WifiDto wifiDto = new WifiDto();
                        JsonObject jsonObject = (JsonObject) row.get(j);

                        wifiDto.setMgrNo(String.valueOf(jsonObject.get("X_SWIFI_MGR_NO")));
                        wifiDto.setWrdofc(String.valueOf(jsonObject.get("X_SWIFI_WRDOFC")));
                        wifiDto.setMainNm(String.valueOf(jsonObject.get("X_SWIFI_MAIN_NM")));
                        wifiDto.setAdres1(String.valueOf(jsonObject.get("X_SWIFI_ADRES1")));
                        wifiDto.setAdres2(String.valueOf(jsonObject.get("X_SWIFI_ADRES2")));
                        wifiDto.setInstlFloor(String.valueOf(jsonObject.get("X_SWIFI_INSTL_FLOOR")));
                        wifiDto.setInstlTy(String.valueOf(jsonObject.get("X_SWIFI_INSTL_TY")));
                        wifiDto.setInstlMby(String.valueOf(jsonObject.get("X_SWIFI_INSTL_MBY")));
                        wifiDto.setSvcSe(String.valueOf(jsonObject.get("X_SWIFI_SVC_SE")));
                        wifiDto.setCmcwr(String.valueOf(jsonObject.get("X_SWIFI_CMCWR")));
                        wifiDto.setCnstcYear(String.valueOf(jsonObject.get("X_SWIFI_CNSTC_YEAR")));
                        wifiDto.setInoutDoor(String.valueOf(jsonObject.get("X_SWIFI_INOUT_DOOR")));
                        wifiDto.setRemars3(String.valueOf(jsonObject.get("X_SWIFI_REMARS3")));
                        wifiDto.setX(String.valueOf(jsonObject.get("LNT")));
                        wifiDto.setY(String.valueOf(jsonObject.get("LAT")));
                        wifiDto.setWorkDttm(String.valueOf(jsonObject.get("WORK_DTTM")));

                        String query = "INSERT INTO WIFI_INFO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                        preparedStatement = connection.prepareStatement(query);

                        preparedStatement.setString(1, wifiDto.getMgrNo());
                        preparedStatement.setString(2, wifiDto.getWrdofc());
                        preparedStatement.setString(3, wifiDto.getMainNm());
                        preparedStatement.setString(4, wifiDto.getAdres1());
                        preparedStatement.setString(5, wifiDto.getAdres2());
                        preparedStatement.setString(6, wifiDto.getInstlFloor());
                        preparedStatement.setString(7, wifiDto.getInstlTy());
                        preparedStatement.setString(8, wifiDto.getInstlMby());
                        preparedStatement.setString(9, wifiDto.getSvcSe());
                        preparedStatement.setString(10, wifiDto.getCmcwr());
                        preparedStatement.setString(11, wifiDto.getCnstcYear());
                        preparedStatement.setString(12, wifiDto.getInoutDoor());
                        preparedStatement.setString(13, wifiDto.getRemars3());
                        preparedStatement.setString(14, wifiDto.getX());
                        preparedStatement.setString(15, wifiDto.getY());
                        preparedStatement.setString(16, wifiDto.getWorkDttm());

                        preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (preparedStatement != null && !preparedStatement.isClosed()) {
                            preparedStatement.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                rd.close();
                conn.disconnect();
                index += 1000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WifiDto> nearWifi(String myX, String myY) {
        List<WifiDto> wifiList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String dbUrl = "/C:/dev/IntelliJ IDEA 2022.3.1/wifiproject7/wifiservice.db";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection("jdbc:sqlite:" + dbUrl);

            String query = "SELECT *," +
                    " round(6371*acos(cos(radians( ? ))*cos(radians(X))*cos(radians(Y)-radians( ? ))+sin(radians( ? ))*sin(radians(X))), 4)" +
                    " AS DISTANCE" +
                    " FROM WIFI_INFO" +
                    " ORDER BY DISTANCE" +
                    " LIMIT 20;";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, myX);
            preparedStatement.setString(2, myY);
            preparedStatement.setString(3, myX);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String mgrNo = resultSet.getString("X_SWIFI_MGR_NO");
                String wrdofc = resultSet.getString("X_SWIFI_WRDOFC");
                String mainNm = resultSet.getString("X_SWIFI_MAIN_NM");
                String adres1 = resultSet.getString("X_SWIFI_ADRES1");
                String adres2 = resultSet.getString("X_SWIFI_ADRES2");
                String instlFloor = resultSet.getString("X_SWIFI_INSTL_FLOOR");
                String instlTy = resultSet.getString("X_SWIFI_INSTL_TY");
                String instlMby = resultSet.getString("X_SWIFI_INSTL_MBY");
                String svcSe = resultSet.getString("X_SWIFI_SVC_SE");
                String cmcwr = resultSet.getString("X_SWIFI_CMCWR");
                String cnstcYear = resultSet.getString("X_SWIFI_CNSTC_YEAR");
                String inoutDoor = resultSet.getString("X_SWIFI_INOUT_DOOR");
                String remars3 = resultSet.getString("X_SWIFI_REMARS3");
                String x = resultSet.getString("X");
                String y = resultSet.getString("Y");
                String workDttm = resultSet.getString("WORK_DTTM");
                double distance = resultSet.getDouble("DISTANCE");

                WifiDto wifiDto = new WifiDto(mgrNo, wrdofc, mainNm, adres1, adres2, instlFloor, instlTy, instlMby, svcSe, cmcwr, cnstcYear, inoutDoor, remars3, x, y, workDttm, distance);
                wifiDto.setMgrNo(mgrNo);
                wifiDto.setWrdofc(wrdofc);
                wifiDto.setMainNm(mainNm);
                wifiDto.setAdres1(adres1);
                wifiDto.setAdres2(adres2);
                wifiDto.setInstlFloor(instlFloor);
                wifiDto.setInstlTy(instlTy);
                wifiDto.setInstlMby(instlMby);
                wifiDto.setSvcSe(svcSe);
                wifiDto.setCmcwr(cmcwr);
                wifiDto.setCnstcYear(cnstcYear);
                wifiDto.setInoutDoor(inoutDoor);
                wifiDto.setRemars3(remars3);
                wifiDto.setX(x);
                wifiDto.setY(y);
                wifiDto.setWorkDttm(workDttm);
                wifiDto.setDistance(distance);

                wifiList.add(wifiDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return wifiList;
    }

    public List<WifiDto> nearbyWifiList(String x, String y) {
        return nearWifi(x, y);
    }

    public static void main(String[] args) throws IOException {
        WifiService wifiService = new WifiService();
        System.out.println(wifiService.getWifi());
    }
}
