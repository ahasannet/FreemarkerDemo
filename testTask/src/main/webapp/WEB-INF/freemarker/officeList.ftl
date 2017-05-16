<html>
	<head>
		<title>Office List</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Office List</h3>
				<p align="right"><a href="officeDetails.html?companyId=${companyId}&officeId=${0}">Add New Office</a></p>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>Company</td>
						<td>City</td>
						<td>Address</td>
						<td>Details</td>
						<td>Employee</td>
						<td>Delete</td>
					</tr>
					<#list officeList as office>
						<tr align="center" class="<#if office_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${office.id}</td>
							<td>${office.name}</td>
							<td>${office.company.name?default("")}</td>
							<td><#if office.city ??>${office.city.name?default("")}<#else>&nbsp;</#if></td>
							<td>${office.address1}</td>
							<td><a href="officeDetails.html?companyId=${companyId}&officeId=${office.id}">Details</a></td>
							<td><a href="employeeList.html?companyId=${companyId}&officeId=${office.id}">Employee</a></td>
							<td><a href="delete.html?dbId=${office.id}&clazz=Office&view=officeList.html?companyId=${companyId}">Delete</a></td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>