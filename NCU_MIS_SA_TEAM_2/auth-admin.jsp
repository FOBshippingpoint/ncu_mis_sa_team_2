<%
if (request.getAttribute("memberIsAdmin").toString() != "true") {
	response.sendRedirect("/NCU_MIS_SA/home.jsp");
}
%>