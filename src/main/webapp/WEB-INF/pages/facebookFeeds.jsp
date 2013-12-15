<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<body class="win ff ff2" style="padding-top:50px !important;">
 		<c:forEach items="${facebookFeeds}" var="facebookFeed">
			<span>${facebookFeed}</span><br/>
		</c:forEach>
	</body>
</html>