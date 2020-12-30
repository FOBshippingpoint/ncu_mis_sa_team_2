<!-- 
上方工具列，給其他檔案共用的jsp
 -->
<!-- 設定UTF-8避免中文亂碼 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 匯入Member類別 -->
<%@page import="ncu.im3069.demo.app.Member"%>
<%!
/**
實作alert，使用方式：out.print(alert(message));過
*/
public String alert(String str) {
	return "<script>alert(\"" + str + "\")</script>";
}
/** 宣告會員角色 */
String memberRole = "";
%>
<%!
/** 跳脫字元處理，如<轉為&lt; */
public static String escape(String s) {
    StringBuilder builder = new StringBuilder();
    boolean previousWasASpace = false;
    for( char c : s.toCharArray() ) {
        if( c == ' ' ) {
            if( previousWasASpace ) {
                builder.append("&nbsp;");
                previousWasASpace = false;
                continue;
            }
            previousWasASpace = true;
        } else {
            previousWasASpace = false;
        }
        switch(c) {
            case '<': builder.append("&lt;"); break;
            case '>': builder.append("&gt;"); break;
            case '&': builder.append("&amp;"); break;
            case '"': builder.append("&quot;"); break;
            case '\n': builder.append("<br>"); break;
            // We need Tab support here, because we print StackTraces as HTML
            case '\t': builder.append("&nbsp; &nbsp; &nbsp;"); break;  
            default:
                if( c < 128 ) {
                    builder.append(c);
                } else {
                    builder.append("&#").append((int)c).append(";");
                }    
        }
    }
    return builder.toString();
}
%>

<%
/** 設定會員角色（如果已登入） */
if (request.getAttribute("memberIsAdmin") != null) {
	memberRole = (boolean) request.getAttribute("memberIsAdmin") ? "管理員" : "會員";
}
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery -->
<script src="/NCU_MIS_SA/statics/js/jquery-3.4.1.min.js" crossorigin="anonymous"></script>

<style>
.navbar {
	background-color: gray;
	text-align: right;
	padding: 10px;
}

.navbar a, span {
	color: white;
}

</style>

<div class="navbar">
	<span> <%
	/** 已登入則顯示下列資訊 */
	if (session.getAttribute("member") != null) {
	out.print("歡迎回來！");
	%> ${sessionScope.member.name} <%=memberRole%> 
				|<%
	}
	%>
	<a href="/NCU_MIS_SA/home.jsp">首頁</a> |
	<a href="/NCU_MIS_SA/register">註冊</a> |
	<a href="/NCU_MIS_SA/admin-pages/member-list">使用者列表</a> |
	<a href="/NCU_MIS_SA/edit-member">更改使用者資料</a> |
	<a href="/NCU_MIS_SA/admin-pages/add-movie">新增電影</a> |
	<a href="/NCU_MIS_SA/movie-list">電影列表</a>
	<%
		// 已登入	
	if (session.getAttribute("member") != null) {
		out.print("| <a href=\"/NCU_MIS_SA/logout\">登出</a>");
	} else {
		out.print("| <a href=\"/NCU_MIS_SA/login\">登入</a>");
	}
	%>
	</span>
</div>

<%
/** 若request含有message屬性即會跳出警告 */
if(request.getAttribute("message") != null) {
	out.print(alert(request.getAttribute("message").toString()));
}
%>