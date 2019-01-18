<%@ page contentType="text/plain; charset=UTF-8" %> 
 
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
 
<%@ page import="org.json.simple.*" %> 
 
<%@ page import="nation.web.tool.*" %>
<%@ page import="dev.mvc.reservation.*" %>
<% 
request.setCharacterEncoding("UTF-8"); 
String root = request.getContextPath();
%>
 
<%
ReservationProc reservationProc = new ReservationProc();
%>