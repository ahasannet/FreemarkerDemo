<html>
	<head>
		<title>Company List</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Company List</h3>
				<p align="right"><a href="companyDetails.html?companyId=${0}">Add New Company</a></p>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>City</td>
						<td>Created</td>
						<td>Updated</td>
						<td>Details</td>
						<td>Department</td>
						<td>Office</td>
						<td>Employee</td>
						<td>Delete</td>
					</tr>
					<#list companyList as company>
						<tr align="center" class="<#if company_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${company.id}</td>
							<td>${company.name}</td>
							<td><#if company.city ??>${company.city.name?default("")}<#else>&nbsp;</#if></td>
							<td>${company.created}</td>
							<td>${company.updated?default("")}</td>
							<td><a href="companyDetails.html?companyId=${company.id}">Details</a></td>
							<td><a href="departmentList.html?companyId=${company.id}">Department</a></td>
							<td><a href="officeList.html?companyId=${company.id}">Office</a></td>
							<td><a href="employeeList.html?companyId=${company.id}&officeId=${0}">Employee</a></td>
							<td><a href="delete.html?dbId=${company.id}&clazz=Company&view=companyList.html">Delete</a></td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>