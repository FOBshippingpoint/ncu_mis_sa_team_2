<%@page import="ncu.im3069.demo.app.Member"%>
<% 
Member authMember = null;
boolean isAdmin = false;
if(session.getAttribute("member") != null) {
	authMember = (Member) session.getAttribute("member");
	isAdmin = authMember.isAdmin();
}
%>