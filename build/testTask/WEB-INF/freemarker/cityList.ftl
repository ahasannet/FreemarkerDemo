<html>
	<head>
		<title>City List</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>City List</h3>
				<p align="right"><a href="cityDetails.html?cityId=${0}">Add New City</a></p>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>Created</td>
						<td>Updated</td>
						<td>Details</td>
						<td>Company</td>
						<td>Office</td>
						<td>Employee</td>
						<td>Delete</td>
					</tr>
					<#list cityList as city>
						<tr align="center" class="<#if city_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${city.id}</td>
							<td>${city.name}</td>
							<td>${city.created}</td>
							<td>${city.updated?default("")}</td>
							<td><a href="cityDetails.html?cityId=${city.id}">Details</a></td>
							<td><a href="companyByCity.html?cityId=${city.id}&city=${city.name}">Company</a></td>
							<td><a href="officeByCity.html?cityId=${city.id}&city=${city.name}">Office</a></td>
							<td><a href="employeeByCity.html?cityId=${city.id}&city=${city.name}">Employee</a></td>
							<td><a href="delete.html?dbId=${city.id}&clazz=City&view=cityList.html">Delete</a></td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>