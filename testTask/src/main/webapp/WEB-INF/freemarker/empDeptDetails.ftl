<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
	<head>
		<title>Assigned Department Details</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Assigned Department Details</h3>
				<@form.form action="" method="POST" commandName="command">
					<table cellspacing="5" cellpadding="5">
						<tr align="left">
							<th>ID</th>
							<td><@form.input path="id" readonly="true" /></td>
							<td><font color="#FF0000"><@form.errors path="id" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Employee</th>
							<td>
								<@form.select path="employee">
									<@form.option value="0" label="Select"/>
									<#list employeeList as employee>
										<@form.option value="${employee.id}" label="${employee.name}"/>
									</#list>
								</@form.select>
							</td>
							<td><font color="#FF0000"><@form.errors path="employee" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Department</th>
							<td>
								<@form.select path="department">
									<@form.option value="0" label="Select"/>
									<#list departmentList as department>
										<@form.option value="${department.id}" label="${department.name}"/>
									</#list>
								</@form.select>
							</td>
							<td><font color="#FF0000"><@form.errors path="department" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th><a href="empDeptList.html?companyId=${companyId?default(0)}&officeId=${officeId?default(0)}&employeeId=${employeeId?default(0)}">Assigned Departments</a></th>
							<td><input type="submit" value="Save"/></td>
						<tr>
					</table>
				</@form.form>
			</div>
		</div>
	</body>
</html> 