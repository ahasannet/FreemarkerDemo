<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
	<head>
		<title>Company Details</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Company Details</h3>
				<@form.form action="" method="POST" commandName="command">
					<table cellspacing="5" cellpadding="5">
						<tr align="left">
							<th>ID</th>
							<td><@form.input path="id" readonly="true" /></td>
							<td><font color="#FF0000"><@form.errors path="id" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Name</th>
							<td><@form.input path="name" maxlength="32" /></td>
							<td><font color="#FF0000"><@form.errors path="name" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>City</th>
							<td>
								<@form.select path="city">
									<@form.option value="0" label="Select"/>
									<#list cityList as city>
										<@form.option value="${city.id}" label="${city.name}"/>
									</#list>
								</@form.select>
							</td>
							<td><font color="#FF0000"><@form.errors path="city" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th><a href="companyList.html">Company List</a></th>
							<td><input type="submit" value="Save"/></td>
						<tr>
					</table>
				</@form.form>
			</div>
		</div>
	</body>
</html> 