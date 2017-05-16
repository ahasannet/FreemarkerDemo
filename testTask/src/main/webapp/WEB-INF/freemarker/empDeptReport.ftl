<html>
	<head>
		<title>Employee List</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Employee List</h3>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>Office</td>
						<td>City</td>
						<td>Address</td>
						<td>Created</td>
						<td>Updated</td>
						<td>Assigned Departmets</td>
					</tr>
					<#list employeeList as employee>
						<tr align="center" class="<#if employee_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${employee.id}</td>
							<td>${employee.name}</td>
							<td>${employee.office.name?default("")}</td>
							<td><#if employee.city ??>${employee.city.name?default("")}<#else>&nbsp;</#if></td>
							<td>${employee.address1}</td>
							<td>${employee.created}</td>
							<td>${employee.updated?default("")}</td>
							<td><a href="empDeptList.html?companyId=0&officeId=0&employeeId=${employee.id}">Assigned Departments</a></td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>