<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
	<head>
		<title>Department Details</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Department Details</h3>
				<@form.form action="" method="POST" commandName="command">
					<table cellspacing="5" cellpadding="5">
						<tr align="left">
							<th>ID</th>
							<td><@form.input path="id" readonly="true" /></td>
							<td><font color="#FF0000"><@form.errors path="id" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Company</th>
							<td>
								<@form.select path="company">
									<@form.option value="0" label="Select"/>
									<#list companyList as company>
										<@form.option value="${company.id}" label="${company.name}"/>
									</#list>
								</@form.select>
							</td>
							<td><font color="#FF0000"><@form.errors path="company" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Name</th>
							<td><@form.input path="name" maxlength="32" /></td>
							<td><font color="#FF0000"><@form.errors path="name" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th><a href="departmentList.html?companyId=${companyId?default(0)}">Department List</a></th>
							<td><input type="submit" value="Save"/></td>
						<tr>
					</table>
				</@form.form>
			</div>
		</div>
	</body>
</html> 