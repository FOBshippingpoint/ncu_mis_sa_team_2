<!-- 
上方工具列，給其他檔案共用的jsp
 -->
<!-- 設定UTF-8避免中文亂碼 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 匯入Member類別 -->
<%@page import="ncu.im3069.demo.app.Member"%>
<%@ include file="auth.jsp"%>
<%!
/**
實作alert，使用方式：out.print(alert(message));過
*/
public String alert(String str) {
	return "<script>alert(\"" + str + "\")</script>";
}
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery -->
<script src="/NCU_MIS_SA/statics/js/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"
	integrity="sha512-UdIMMlVx0HEynClOIFSyOrPggomfhBKJE28LKl8yR3ghkgugPnG6iLfRfHwushZl1MOPSY6TsuBDGPK2X4zYKg=="
	crossorigin="anonymous"></script>

<style>
body {
	margin: 0;
}

.main {
	margin: 2%;
	margin-top: 60px;
}

.navbar {
	background-image: linear-gradient(to bottom, #4d4d4d 0%, #1a1a1a 100%);
	text-align: right;
	padding: 10px 0px;
    position: fixed;
    top: 0;
    z-index: 1;
    width: 100%;
}

.navbar a, span {
	color: white;
	text-decoration: none;
}

/* Dropdown Button */
.dropbtn {
    background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    color: white;
	font-size: 20px;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 100px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  display: block;
  text-decoration: none;
  padding: 2px 3px;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
	background-color: #ddd;
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
	background-color: #ff7e28;
}

.center {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 300px;
}

</style>
<div class="navbar">
	<span style="padding-right: 100px">
	<div class="dropdown">
		<a href="/NCU_MIS_SA/login">
			<button class="dropbtn">
				<a href="/NCU_MIS_SA/home.jsp">首頁</a>
			</button>
		</a>		
	</div>
	<div class="dropdown">
		<button class="dropbtn">
			<a href="/NCU_MIS_SA/movie-list">電影列表</a>
		</button>
	</div>
	<%
	if (null != authMember) {
	%>
		<div class="dropdown">
			<button class="dropbtn">
				<a href="/NCU_MIS_SA/order-list">訂單列表</a>
			</button>
		</div>
		<%
		if (authMember.isAdmin()) {
		%>
			<div class="dropdown">
				<button class="dropbtn">
					<a href="/NCU_MIS_SA/admin-pages/member-list">使用者列表</a>
				</button>
			</div>
			<div class="dropdown">
				<button class="dropbtn">
					<a href="/NCU_MIS_SA/admin-pages/add-movie">新增電影</a>
				</button>
			</div>
		<%
		}
		%>
		<div class="dropdown">
			<button class="dropbtn">您好，<%=authMember.getEmail()%><%=isAdmin?"（管理者）":"（會員）"%></button>
			<div class="dropdown-content">
			    <a href="/NCU_MIS_SA/edit-member">更改資料</a>
			    <a href="/NCU_MIS_SA/logout">登出</a>
		    </div>
		</div>
	<%
	} else {
	%>
		<div class="dropdown">
			<a href="/NCU_MIS_SA/login">
				<button class="dropbtn">登入</button>
			</a>		
		</div>
		<div class="dropdown">
			<a href="/NCU_MIS_SA/register">
				<button class="dropbtn">註冊</button>
			</a>		
		</div>
	<%
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