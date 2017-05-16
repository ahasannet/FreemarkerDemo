<#import "/spring.ftl" as spring />
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
	<head>
		<title>Employee Details</title>
	</head>
	<body>
		<div id="header">Constant Asia Test Task</div>
		<div id="container">
			<div id="menuLeft">
				<#include "/menu.html">
            </div>
            <div id="content">
				<h3>Employee Details</h3>
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
							<th>Office</th>
							<td>
								<@form.select path="office">
									<@form.option value="0" label="Select"/>
									<#list officeList as office>
										<@form.option value="${office.id}" label="${office.name}"/>
									</#list>
								</@form.select>
							</td>
							<td><font color="#FF0000"><@form.errors path="office" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Address 1</th>
							<td><@form.input path="address1" maxlength="64" /></td>
							<td><font color="#FF0000"><@form.errors path="address1" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Address 2</th>
							<td><@form.input path="address2" maxlength="64" /></td>
							<td><font color="#FF0000"><@form.errors path="address2" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Phone</th>
							<td><@form.input path="phone" maxlength="16" /></td>
							<td><font color="#FF0000"><@form.errors path="phone" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<th>Email</th>
							<td><@form.input path="email" maxlength="32" /></td>
							<td><font color="#FF0000"><@form.errors path="email" cssClass="error" /></font></td>
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
							<th>ZIP</th>
							<td><@form.input path="zip" maxlength="8" /></td>
							<td><font color="#FF0000"><@form.errors path="zip" cssClass="error" /></font></td>
						</tr>
						<tr align="left">
							<td><a href="employeeList.html?companyId=${companyId?default(0)}&officeId=${officeId?default(0)}">Employee List</a></td>
							<td><input type="submit" value="Save"/></td>
						<tr>
					</table>
				</@form.form>
			</div>
		</div>
	</body>
</html> 