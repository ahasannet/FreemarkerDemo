<html>
	<head>
		<title>Office List (${city})</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Office List (${city})</h3>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>Company</td>
						<td>City</td>
						<td>Address</td>
						<td>Created</td>
						<td>Updated</td>
					</tr>
					<#list officeList as office>
						<tr align="center" class="<#if office_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${office.id}</td>
							<td>${office.name}</td>
							<td>${office.company.name?default("")}</td>
							<td><#if office.city ??>${office.city.name?default("")}<#else>&nbsp;</#if></td>
							<td>${office.address1}</td>
							<td>${office.created}</td>
							<td>${office.updated?default("")}</td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>