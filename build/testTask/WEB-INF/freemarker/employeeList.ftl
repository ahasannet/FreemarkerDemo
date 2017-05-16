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
				<p align="right"><a href="employeeDetails.html?companyId=${companyId}&officeId=${officeId}&employeeId=${0}">Add New Employee</a></p>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>Office</td>
						<td>City</td>
						<td>Address</td>
						<td>Details</td>
						<td>Assigned Departmets</td>
						<td>Delete</td>
					</tr>
					<#list employeeList as employee>
						<tr align="center" class="<#if employee_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${employee.id}</td>
							<td>${employee.name}</td>
							<td>${employee.office.name?default("")}</td>
							<td><#if employee.city ??>${employee.city.name?default("")}<#else>&nbsp;</#if></td>
							<td>${employee.address1}</td>
							<td><a href="employeeDetails.html?companyId=${companyId}&officeId=${officeId}&employeeId=${employee.id}">Details</a></td>
							<td><a href="empDeptList.html?companyId=${companyId}&officeId=${officeId}&employeeId=${employee.id}">Assigned Departments</a></td>
							<td><a href="deleteEmployee.html?companyId=${companyId}&officeId=${officeId}&employeeId=${employee.id}">Delete</a></td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>