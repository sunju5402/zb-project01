<%@page import="dto.WifiInfo"%>
<%@page import="java.util.List"%>
<%@page import="service.HttpService"%>
<%@page import="service.WifiInfoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
        WifiInfoService ws = new WifiInfoService();
        HttpService hs = new HttpService();
        List<WifiInfo> list = null;
        try {
        	list = hs.loadWifi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null) {
            for (WifiInfo wifi : list) {
                ws.saveWifiInfo(wifi);
                System.out.println(wifi.getX_SWIFI_MGR_NO());
            }
        }
    %>

    <h1 style="text-align: center;"><%=list != null ? list.size() : 0%>개의 WIFI 정보를 정상적으로 저장하였습니다</h1>
    <div style="text-align: center;">
        <a href="home.jsp">홈으로 가기</a>
    </div>
</body>
</body>
</html>