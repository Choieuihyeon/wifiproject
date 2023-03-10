<%@ page import="java.util.List" %>
<%@ page import="com.example.wifiproject8.domain.HistoryInfo" %>
<%@ page import="com.example.wifiproject8.service.HistoryService" %>
<%@ page import="com.example.wifiproject8.service.WifiService" %>
<%@ page import="com.example.wifiproject8.dto.WifiDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        th {
            padding-top: 9px;
            padding-bottom: 9px;
            background-color: #04AA6D;
            color: white;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
        table {
            width: 100%;
            border-collapse : collapse;
        }
        tr:nth-child(odd) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        td {
            border: 1px solid #ddd;
            padding: 7px;
            text-align: left;
        }
        ul{
            list-style-type: none;
            margin: 0;
            padding: 0;
        }
        li {display: inline; }
        .nav_list {
            width: fit-content;
            padding: 5px;
            margin-bottom: 10px;
        }
        li a {
            padding: 5px;
            font-weight: bold;
        }
        .container {
            margin-bottom: 10px;
        }
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<div class="container">
    <h1>&nbsp;와이파이 정보 구하기</h1>
    <div class="nav_list">
        <ul>
            <li>
                <a href="index.jsp">홈</a>|
            </li>
            <li>
                <a href="history.jsp">위치 히스토리 목록</a>|
            </li>
            <li>
                <a href="load.jsp">Open API 와이파이 정보 가져오기</a>
            </li>
        </ul>
    </div>
    <form method="get" action="./">
        <label for="lat">LAT:
            <input type="text" id="lat" name="lat" value='0.0'>
        </label>

        <label for="lnt">LNT:
            <input type="text" id="lnt" name="lnt" value='0.0'>
        </label>

        <button type="button" id="getLocationButton" onclick="navigator.geolocation
            .getCurrentPosition(getLocation, showLocationError);">내 위치 가져오기</button>
        <button type="submit" id="getWifiButton" value="">근처 WIFI 정보 가져오기</button>
    </form>
</div>
<%
    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");
%>
<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (lat == null && lnt == null) {
    %>
    <tr>
        <td colspan="17" style = "text-align: center;"> 위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    <%
    } else {
        HistoryInfo historyInfo = new HistoryInfo();
        historyInfo.setX(lat);
        historyInfo.setY(lnt);
        HistoryService historyService = new HistoryService();
        historyService.insertHistory(historyInfo);
        WifiService wifiService = new WifiService();
        List<WifiDto> wifiList = wifiService.nearbyWifiList(lat, lnt);
        for(WifiDto wifiDto: wifiList) {
    %>
    <tr>
        <td><%=wifiDto.getDistance()%></td>
        <td><%=wifiDto.getMgrNo()%></td>
        <td><%=wifiDto.getWrdofc()%></td>
        <td><%=wifiDto.getMainNm()%></td>
        <td><%=wifiDto.getAdres1()%></td>
        <td><%=wifiDto.getAdres2()%></td>
        <td><%=wifiDto.getInstlFloor()%></td>
        <td><%=wifiDto.getInstlTy()%></td>
        <td><%=wifiDto.getInstlMby()%></td>
        <td><%=wifiDto.getSvcSe()%></td>
        <td><%=wifiDto.getCmcwr()%></td>
        <td><%=wifiDto.getCnstcYear()%></td>
        <td><%=wifiDto.getInoutDoor()%></td>
        <td><%=wifiDto.getRemars3()%></td>
        <td><%=wifiDto.getX()%></td>
        <td><%=wifiDto.getY()%></td>
        <td><%=wifiDto.getWorkDttm()%></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<script>
    let lat = 0;
    let lnt = 0;
    function getLocation(position) {
        lat = position.coords.latitude;
        lnt = position.coords.longitude;
        document.getElementById("lat").value = lat;
        document.getElementById("lnt").value = lnt;
    }
    function showLocationError() {
        if (!navigator.geolocation) {
            throw "위치 정보가 지원되지 않습니다.";
        }
    }
</script>
</body>
</html>