<html>
	<head>
		<title>Assigned Departments</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Assigned Departments</h3>
				<p align="right"><a href="empDeptDetails.html?companyId=${companyId}&officeId=${officeId}&employeeId=${employeeId}&empDeptId=0">Assign New Department</a></p>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
					<tr bgcolor="#999999" align="center">
						<td>ID</td>
						<td>Employee</td>
						<td>Department</td>
						<td>Created</td>
						<td>Updated</td>
						<td>Details</td>
						<td>Delete</td>
					</tr>
					<#list empDeptList as empDept>
						<tr align="center" class="<#if empDept_index%2==0>evenRow<#else>oddRow</#if>">
							<td>${empDept.id}</td>
							<td>${empDept.employee.name?default("")}</td>
							<td>${empDept.department.name?default("")}</td>
							<td>${empDept.created}</td>
							<td>${empDept.updated?default("")}</td>
							<td><a href="empDeptDetails.html?companyId=${companyId}&officeId=${officeId}&employeeId=${employeeId}&empDeptId=${empDept.id}">Details</a></td>
							<td><a href="deleteEmpDept.html?companyId=${companyId}&officeId=${officeId}&employeeId=${employeeId}&empDeptId=${empDept.id}">Delete</a></td>
						</tr>
					</#list>
				</table>
			</div>
		</div>
	</body>
</html>