<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="../resources/images/favicon.png" />
		<title>Analyzer</title>
		<link href="../resources/css/jquery.dataTables.css" rel="stylesheet" />
		<script src="../resources/js/jquery.js"></script>
		<script src="../resources/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#result').dataTable();
			} );
		</script>
	</head>
	<body class="win ff ff2" style="padding-top:50px !important;">
 		<table id="result">
 			<thead>
		        <tr>
		            <th width="10%">Page</th>
		            <th width="10%">Type</th>
		            <th width="60%">Message</th>
		            <th width="20%">Sentiment Type</th>
		        </tr>
		    </thead>
		    <tbody>
	 		<c:forEach items="${facebookFeeds}" var="facebookFeed">
		        <tr>
		            <td width="10%">Apple 5S</td>
		            <td width="10%">${facebookFeed.type}</td>
		            <td width="60%">${facebookFeed.message}</td>
		            <td width="20%">${facebookFeed.sentimentLabel}</td>
		        </tr>
			</c:forEach>
			</tbody>
		</table>
	</body>
</html>