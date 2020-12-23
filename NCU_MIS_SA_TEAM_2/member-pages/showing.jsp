<%@page import="ncu.im3069.demo.app.ShowingHelper"%>
<%@page import="ncu.im3069.demo.app.Showing"%>
<label for="showing-time">場次</label>
<select name="showing-time" id="showing-time">
			<%
			for (Showing showing : ShowingHelper.getHelper().getShowingsByMovie(movie)) {
			%>
			<option value="<%=showing.getId()%>">
				<%=showing.getStartString()%>
			</option>
			<%
			}
			%>
</select>