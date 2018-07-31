<%@page contentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=GBK" %>
<%response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("资源列表.xls", "UTF-8"));%>
<%@page import="java.util.List" %>

<%@page import="com.pms.rcm.device.vo.SrsDevice"%>
<%@page import="com.pms.rcm.device.vo.SrsDeviceCheckItem"%>
<%@page import="com.pms.rcm.device.vo.SrsItemType"%>
<%@page import="com.pms.rcm.device.vo.SrsParentItem"%>


<%@page language="java" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html lang="en">
<head> 
<script type="text/javascript" src="/static/ace/js/jquery.js"></script>  
 <style type="text/css">
 
 <!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
	.xl7731455
	{padding:0px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
	
.xl657881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl667881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl677881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl687881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:general;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl697881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl707881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#003366;
	mso-pattern:black none;
	white-space:normal;}
.xl717881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
.xl727881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:justify;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
.xl737881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:justify;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl747881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl757881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-diagonal-down:.5pt solid windowtext;
	background:#003366;
	mso-pattern:black none;
	white-space:normal;}
.xl767881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
.xl777881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
.xl787881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl797881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#003366;
	mso-pattern:black none;
	white-space:normal;}
.xl807881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:justify;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
.xl817881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:justify;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl827881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl837881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl847881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl857881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl867881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	background:white;
	mso-pattern:black none;
	white-space:normal;}
.xl877881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:white;
	mso-pattern:black none;
	white-space:normal;}
.xl887881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:white;
	mso-pattern:black none;
	white-space:normal;}
.xl897881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl907881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl917881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl927881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl937881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	background:white;
	mso-pattern:black none;
	white-space:normal;}
.xl947881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	background:white;
	mso-pattern:black none;
	white-space:normal;}
.xl957881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:red;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	background:white;
	mso-pattern:black none;
	white-space:normal;}
.xl967881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	background:#003366;
	mso-pattern:black none;
	white-space:normal;}
.xl977881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#003366;
	mso-pattern:black none;
	white-space:normal;}
.xl987881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#003366;
	mso-pattern:black none;
	white-space:normal;}
.xl997881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
.xl1007881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
.xl1017881
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#99CCFF;
	mso-pattern:black none;
	white-space:normal;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;}
-->
 <!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.font531216
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font631216
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.xl1531216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6731216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#17375D;
	mso-pattern:black none;
	white-space:normal;}
.xl6831216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#C5D9F1;
	mso-pattern:black none;
	white-space:normal;}
.xl6931216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:justify;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl7031216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#C5D9F1;
	mso-pattern:black none;
	white-space:normal;}
.xl7131216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl7231216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-diagonal-down:.5pt solid windowtext;
	background:#17375D;
	mso-pattern:black none;
	white-space:normal;}
.xl7331216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl7431216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:yellow;
	mso-pattern:black none;
	white-space:normal;}
.xl7531216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#C5D9F1;
	mso-pattern:black none;
	white-space:normal;}
.xl7631216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	background:#C5D9F1;
	mso-pattern:black none;
	white-space:normal;}
.xl7731216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#C5D9F1;
	mso-pattern:black none;
	white-space:normal;}
.xl7831216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	background:#17375D;
	mso-pattern:black none;
	white-space:normal;}
.xl7931216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#17375D;
	mso-pattern:black none;
	white-space:normal;}
.xl8031216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#17375D;
	mso-pattern:black none;
	white-space:normal;}
.xl8131216
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	background:#C5D9F1;
	mso-pattern:black none;
	white-space:normal;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:SimSun;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;}
-->
 </style>
</head>
<body class="no-skin">

	 <div id="巡检资源导入数据-0315_7881" align=center x:publishsource="Excel">

<table border=0 cellpadding=0 cellspacing=0 width=1370 class=xl697881
 style='border-collapse:collapse;table-layout:fixed;width:1030pt'>
 <col class=xl667881 width=104 style='mso-width-source:userset;mso-width-alt:
 3328;width:78pt'>
 <col class=xl677881 width=105 style='mso-width-source:userset;mso-width-alt:
 3360;width:79pt'>
 <col class=xl677881 width=77 style='mso-width-source:userset;mso-width-alt:
 2464;width:58pt'>
 <col class=xl677881 width=72 style='mso-width-source:userset;mso-width-alt:
 2304;width:54pt'>
 <col class=xl677881 width=77 style='mso-width-source:userset;mso-width-alt:
 2464;width:58pt'>
 <col class=xl677881 width=90 style='mso-width-source:userset;mso-width-alt:
 2880;width:68pt'>
 <col class=xl677881 width=77 span=2 style='mso-width-source:userset;
 mso-width-alt:2464;width:58pt'>
 <col class=xl677881 width=82 span=2 style='mso-width-source:userset;
 mso-width-alt:2624;width:62pt'>
 <col class=xl677881 width=122 style='mso-width-source:userset;mso-width-alt:
 3904;width:92pt'>
 <col class=xl677881 width=91 style='mso-width-source:userset;mso-width-alt:
 2912;width:68pt'>
 <col class=xl687881 width=91 style='mso-width-source:userset;mso-width-alt:
 2912;width:68pt'>
 <col class=xl677881 width=68 style='mso-width-source:userset;mso-width-alt:
 2176;width:51pt'>
 <col class=xl677881 width=91 style='mso-width-source:userset;mso-width-alt:
 2912;width:68pt'>
 <col class=xl697881 width=64 style='mso-width-source:userset;mso-width-alt:
 2048;width:48pt'>
 <tr height=18 style='height:13.5pt'>
  <td colspan=15 height=18 class=xl867881 width=1306 style='height:13.5pt;
  width:982pt'>基础信息（示例）</td>
  <td class=xl697881 width=64 style='width:48pt'></td>
 </tr>
 <tr height=48 style='height:36.0pt'>
  <td height=48 class=xl707881 width=104 style='height:36.0pt;border-top:none;
  width:78pt'>字段名称</td>
  <td class=xl707881 width=105 style='border-top:none;border-left:none;
  width:79pt'>空调编号</td>
  <td class=xl707881 width=77 style='border-top:none;border-left:none;
  width:58pt'>设备类型</td>
  <td class=xl707881 width=72 style='border-top:none;border-left:none;
  width:54pt'>设备名称</td>
  <td class=xl707881 width=77 style='border-top:none;border-left:none;
  width:58pt'>型号</td>
  <td class=xl707881 width=90 style='border-top:none;border-left:none;
  width:68pt'>设备序号</td>
  <td class=xl707881 width=77 style='border-top:none;border-left:none;
  width:58pt'>供应商</td>
  <td class=xl707881 width=77 style='border-top:none;border-left:none;
  width:58pt'>客户名称</td>
  <td class=xl707881 width=82 style='border-top:none;border-left:none;
  width:62pt'>客户联系人</td>
  <td class=xl707881 width=82 style='border-top:none;border-left:none;
  width:62pt'>客户联系人电话</td>
  <td class=xl707881 width=122 style='border-top:none;border-left:none;
  width:92pt'>所在地址 
    (具体到某街道或特定位置名称)</td>
  <td class=xl707881 width=91 style='border-top:none;border-left:none;
  width:68pt'>所在区域 
    (具体到某楼某层某机房)</td>
  <td class=xl797881 width=91 style='border-top:none;border-left:none;
  width:68pt'>区域编码</td>
  <td class=xl707881 width=68 style='border-top:none;border-left:none;
  width:51pt'>巡检标签</td>
  <td class=xl707881 width=91 style='border-top:none;border-left:none;
  width:68pt'>二维标签码</td>
  <td class=xl707881 width=64 style='border-left:none;width:48pt'>备注</td>
 </tr>
 <tr height=18 style='height:13.5pt'>
  <td height=18 class=xl717881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>字段类型</td>
  <td class=xl727881 width=105 style='border-top:none;border-left:none;
  width:79pt'>字符串</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>枚举</td>
  <td class=xl727881 width=72 style='border-top:none;border-left:none;
  width:54pt'>字符串</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>字符串</td>
  <td class=xl727881 width=90 style='border-top:none;border-left:none;
  width:68pt'>字符串</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>字符串</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>字符串</td>
  <td class=xl727881 width=82 style='border-top:none;border-left:none;
  width:62pt'>字符串</td>
  <td class=xl727881 width=82 style='border-top:none;border-left:none;
  width:62pt'>字符串</td>
  <td class=xl727881 width=122 style='border-top:none;border-left:none;
  width:92pt'>字符串</td>
  <td class=xl727881 width=91 style='border-top:none;border-left:none;
  width:68pt'>字符串</td>
  <td class=xl807881 width=91 style='border-top:none;border-left:none;
  width:68pt'>字符串</td>
  <td class=xl727881 width=68 style='border-top:none;border-left:none;
  width:51pt'>枚举</td>
  <td class=xl727881 width=91 style='border-top:none;border-left:none;
  width:68pt'>字符串</td>
  <td class=xl727881 width=64 style='border-top:none;border-left:none;
  width:48pt'>字符串</td>
 </tr>
 <tr height=18 style='height:13.5pt'>
  <td height=18 class=xl717881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>是否必填</td>
  <td class=xl727881 width=105 style='border-top:none;border-left:none;
  width:79pt'>是</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>是</td>
  <td class=xl727881 width=72 style='border-top:none;border-left:none;
  width:54pt'>是</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>是</td>
  <td class=xl727881 width=90 style='border-top:none;border-left:none;
  width:68pt'>是</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>是</td>
  <td class=xl727881 width=77 style='border-top:none;border-left:none;
  width:58pt'>是</td>
  <td class=xl727881 width=82 style='border-top:none;border-left:none;
  width:62pt'>是</td>
  <td class=xl727881 width=82 style='border-top:none;border-left:none;
  width:62pt'>是</td>
  <td class=xl727881 width=122 style='border-top:none;border-left:none;
  width:92pt'>是</td>
  <td class=xl727881 width=91 style='border-top:none;border-left:none;
  width:68pt'>是</td>
  <td class=xl807881 width=91 style='border-top:none;border-left:none;
  width:68pt'>是（如果有）</td>
  <td class=xl727881 width=68 style='border-top:none;border-left:none;
  width:51pt'>是(如有)</td>
  <td class=xl727881 width=91 style='border-top:none;border-left:none;
  width:68pt'>是(如有)</td>
  <td class=xl727881 width=64 style='border-top:none;border-left:none;
  width:48pt'>否</td>
 </tr>
 
 <%

	//-------数据开始



	List<SrsDevice> dataList = (List<SrsDevice>) request

			.getAttribute("srsDeviceList");

	for (int i = 0; dataList!=null && i < dataList.size(); i++) {

		SrsDevice srsDevice = dataList.get(i);		 

%>
 
 
 <tr height=18 style='height:13.5pt'>
  <td height=18 class=xl717881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'></td>
  <td class=xl737881 width=105 style='border-top:none;border-left:none;
  width:79pt'>
  
	<%=srsDevice.getDeviceCode()%>
  
  </td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>
  <%=srsDevice.getDeviceType()%>
  </td>
  <td class=xl737881 width=72 style='border-top:none;border-left:none;
  width:54pt'> <%=srsDevice.getDeviceName()%></td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>
 <%=srsDevice.getDeviceModelNumber()%>
</td>
  <td class=xl737881 width=90 style='border-top:none;border-left:none;
  width:68pt'><%=srsDevice.getDeviceSeq()%></td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>
<%=srsDevice.getDeviceProducer()%>
</td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>
  <%=srsDevice.getDeviceCustName()%>
  
  </td>
  <td class=xl737881 width=82 style='border-top:none;border-left:none;
  width:62pt'>  <%=srsDevice.getDeviceCustContact()%>
  </td>
  <td class=xl737881 width=82 style='border-top:none;border-left:none;
  width:62pt'> <%=srsDevice.getDeviceCustPhone()%></td>
  <td class=xl737881 width=122 style='border-top:none;border-left:none;
  width:92pt'><%=srsDevice.getDeviceAddr()%></td>
  <td class=xl737881 width=91 style='border-top:none;border-left:none;
  width:68pt'><%=srsDevice.getDeviceEreaAdd()%></td>
  <td class=xl817881 width=91 style='border-top:none;border-left:none;
  width:68pt'><%=srsDevice.getDeviceEreaAdd()%></td>
  <td class=xl737881 width=68 style='border-top:none;border-left:none;
  width:51pt'><%=srsDevice.getDeiviceFlagType()%></td>
  <td class=xl737881 width=91 style='border-top:none;border-left:none;
  width:68pt'><%=srsDevice.getDeviceFlagCode()%></td>
  <td class=xl737881 width=64 style='border-top:none;border-left:none;
  width:48pt'>　</td>
  
 </tr>
  <%

	}

%>

<tr height=18 style='height:13.5pt'>
  <td height=18 class=xl667881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>　</td>
  <td class=xl737881 width=105 style='border-top:none;border-left:none;
  width:79pt'>　</td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>　</td>
  <td class=xl737881 width=72 style='border-top:none;border-left:none;
  width:54pt'>　</td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>　</td>
  <td class=xl737881 width=90 style='border-top:none;border-left:none;
  width:68pt'>　</td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>　</td>
  <td class=xl737881 width=77 style='border-top:none;border-left:none;
  width:58pt'>　</td>
  <td class=xl737881 width=82 style='border-top:none;border-left:none;
  width:62pt'>　</td>
  <td class=xl737881 width=82 style='border-top:none;border-left:none;
  width:62pt'>　</td>
  <td class=xl737881 width=122 style='border-top:none;border-left:none;
  width:92pt'>　</td>
  <td class=xl737881 width=91 style='border-top:none;border-left:none;
  width:68pt'>　</td>
  <td class=xl817881 width=91 style='border-top:none;border-left:none;
  width:68pt'>　</td>
  <td class=xl737881 width=68 style='border-top:none;border-left:none;
  width:51pt'>　</td>
  <td class=xl737881 width=91 style='border-top:none;border-left:none;
  width:68pt'>　</td>
  <td class=xl737881 width=64 style='border-top:none;border-left:none;
  width:48pt'>　</td>
 </tr>
    
 
 <tr height=18 style='mso-height-source:userset;height:13.5pt'>
  <td height=18 class=xl707881 width=104 style='height:13.5pt;width:78pt'>类别</td> 
      <%

	//-------数据开始



	 SrsDevice  device = (SrsDevice) request

			.getAttribute("device");

	for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 
%>
  <td colspan=<%=srsItemType.getColspan()%> class=xl967881  width=104 style='height:13.5pt;width:78pt'><%=srsItemType.getTypeName()%></td>
 <%}%>
 
 </tr>
  </tr>
 <tr height=48 style='height:36.0pt'>
  <td height=48  class=xl707881  style='border-right:.5pt solid black;
  border-left:none;'> 巡检项/
    字段名称</td>
     <%

	//-------数据开始


 

	for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem item=srsItemType.getPitems().get(ii);
%>
  <td colspan=<%=item.getColspan()%> class=xl707881 width=90 style='height:13.5pt;width:78pt'><%=item.getItemName()%></td>
 <%}}%>
 
 </tr>
	 
	 
	 <tr height=48 style='height:36.0pt'>
  <td height=18 class=xl707881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>子项</td>
     <%

	//-------数据开始


 
	for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem srsParentItem=srsItemType.getPitems().get(ii);
 
		 	for (int iii = 0; srsParentItem.getItems()!=null && iii < srsParentItem.getItems().size(); iii++) {
			SrsDeviceCheckItem item=srsParentItem.getItems().get(iii);
%>
  <td   class=xl707881  width=90  style='border-right:.5pt solid black;
  border-left:none;'><%=item.getItemChildName()%></td>
 <%}}}%>
   
 </tr>
 
 
  <tr height=48 style='height:36.0pt'>
  <td height=18 class=xl767881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>巡检项编号</td>
     <%

	//-------数据开始


 
for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem srsParentItem=srsItemType.getPitems().get(ii);
 
		 	for (int iii = 0; srsParentItem.getItems()!=null && iii < srsParentItem.getItems().size(); iii++) {
			SrsDeviceCheckItem item=srsParentItem.getItems().get(iii);
%>
  <td   class=xl767881  width=90 style='border-right:.5pt solid black;
  border-left:none;'><%=item.getItemNo()%></td>
 <%}}}%>
 
 </tr>
 
  <tr height=48 style='height:36.0pt'>
  <td height=18 class=xl767881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>字段类型</td>
     <%

	//-------数据开始


 
for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem srsParentItem=srsItemType.getPitems().get(ii);
 
		 	for (int iii = 0; srsParentItem.getItems()!=null && iii < srsParentItem.getItems().size(); iii++) {
			SrsDeviceCheckItem item=srsParentItem.getItems().get(iii);
%>
  <td   class=xl777881  width=90  style='border-right:.5pt solid black;
  border-left:none;'><%=item.getItemColumnType()%></td>
 <%}}}%>
  
 </tr>
 
   <tr height=48 style='height:36.0pt'>
  <td height=18  width=90 class=xl767881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>正常参考值</td>
     <%

	//-------数据开始


 

for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem srsParentItem=srsItemType.getPitems().get(ii);
 
		 	for (int iii = 0; srsParentItem.getItems()!=null && iii < srsParentItem.getItems().size(); iii++) {
			SrsDeviceCheckItem item=srsParentItem.getItems().get(iii);
%>
  <td   class=xl737881  width=90 style='border-right:.5pt solid black;
  border-left:none;'><%=item.getItemValueRange()%></td>
 <%}}}%>
 
 </tr>
 
 
  
   <tr height=48 style='height:36.0pt'>
  <td height=18 class=xl767881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>判定依据</td>
     <%

	//-------数据开始


 

for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem srsParentItem=srsItemType.getPitems().get(ii);
 
		 	for (int iii = 0; srsParentItem.getItems()!=null && iii < srsParentItem.getItems().size(); iii++) {
			SrsDeviceCheckItem item=srsParentItem.getItems().get(iii);
%>
  <td  class=xl7731455 width=77 style='border-top:none;border-left:none;
  width:58pt'><%=item.getItemDesc()%></td>
 <%}}}%>
 
 </tr>
	 
	 
	   
   <tr height=48 style='height:36.0pt'>
  <td height=18 class=xl767881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>是否作为判定标准</td>
     <%

	//-------数据开始


for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem srsParentItem=srsItemType.getPitems().get(ii);
 
		 	for (int iii = 0; srsParentItem.getItems()!=null && iii < srsParentItem.getItems().size(); iii++) {
			SrsDeviceCheckItem item=srsParentItem.getItems().get(iii);
%>
  <td   class=xl777881  width=90 style='border-right:.5pt solid black;
  border-left:none;'><%=item.getIsJudge()==1?"是":"否"%></td>
 <%}}}%>
 
 </tr>
 
 
 	   
   <tr height=48 style='height:36.0pt'>
  <td height=18  width=90 class=xl767881 width=104 style='height:13.5pt;border-top:none;
  width:78pt'>是否弹性项</td>
     <%

	//-------数据开始

for (SrsItemType srsItemType: device.getSrsItemTypeSet() ) {
 
		 	for (int ii = 0; srsItemType.getPitems()!=null && ii < srsItemType.getPitems().size(); ii++) {
			SrsParentItem srsParentItem=srsItemType.getPitems().get(ii);
 
		 	for (int iii = 0; srsParentItem.getItems()!=null && iii < srsParentItem.getItems().size(); iii++) {
			SrsDeviceCheckItem item=srsParentItem.getItems().get(iii);
%>
  <td   class=xl777881  width=90  style='border-right:.5pt solid black;
  border-left:none;'><%=item.getIsChange()==1?"是":"否"%></td>
 <%}}}%>
 
 </tr>
 
</table>

							

</div>
	 
</body>
</html>