<html>
	<head>
		<title>Department List</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Department List</h3>
				<p align="right"><a href="departmentDetails.html?companyId=${companyId}&departmentId=${0}">Add New Deartment</a></p>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Name</td>
						<td>Company</td>
						<td>Created</td>
						<td>Updated</td>
						<td>Details</td>
						<td>Employee</td>
						<td>Delete</td>
					</tr>
					<#list departmentList as department>
						<tr align="center" class="<#if department_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${department.id}</td>
							<td>${department.name}</td>
							<td>${department.company.name?default("")}</td>
							<td>${department.created}</td>
							<td>${department.updated?default("")}</td>
							<td><a href="departmentDetails.html?companyId=${companyId}&departmentId=${department.id}">Details</a></td>
							<td><a href="employeeByDepartment.html?departmentId=${department.id}&department=${department.name}">Employee</a></td>
							<td><a href="delete.html?dbId=${department.id}&clazz=Department&view=departmentList.html?companyId=${companyId}">Delete</a></td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>