<%
if(session.getAttribute("member") == null) {
	response.sendRedirect("/NCU_MIS_SA/home.jsp");
}
%>