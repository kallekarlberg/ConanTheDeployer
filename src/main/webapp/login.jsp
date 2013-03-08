<%@ include file="include.jsp" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="static/css/simple.css"/>"/>
</head>
<body>

<!--
            <td>conan</td>
            <td>kalle</td>

            <td>gracie</td>
            <td>pass</td>

            <td>bombata</td>
            <td>pass2</td>
-->

<form name="loginform" action="" method="post"  accept-charset="UTF-8">
    <table align="center" border="0" cellspacing="0" cellpadding="3">
    	<tr>
    		<td colspan="4"><img src="img/conan_logo.gif" alt="Logo" width="200" height="80"></td>
    	</tr>
        <tr>
            <td>U:</td>
            <td><input type="text" name="username" maxlength="30"></td>
        </tr>
        <tr>
            <td>P:</td>
            <td><input type="password" name="password" maxlength="30"></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="checkbox" name="rememberMe"><font size="2">Remember</font></td>
            <td colspan="2" align="right"><input type="submit" name="submit" value="->"></td>
        </tr>
    </table>
</form>

</body>
</html>
