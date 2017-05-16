<html>
	<head>
		<title>Company List (${city})</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Company List (${city})</h3>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>City</td>
						<td>Created</td>
						<td>Updated</td>
					</tr>
					<#list companyList as company>
						<tr align="center" class="<#if company_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${company.id}</td>
							<td>${company.name}</td>
							<td><#if company.city ??>${company.city.name?default("")}<#else>&nbsp;</#if></td>
							<td>${company.created}</td>
							<td>${company.updated?default("")}</td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>