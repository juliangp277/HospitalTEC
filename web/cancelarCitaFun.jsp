<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : cancelarCitaFun
    Created on : 24-jul-2020, 18:35:30
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancel Appointment</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>APPOINTMENTS!</h1>

        <form action="Servlet" method="POST">
            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>
            <sql:query var="result" dataSource="${snapshot}">
                ${instruccionCitaFun}
            </sql:query>

            <table border="1">
                <!-- column headers -->
                <tr>
                    <c:forEach var="columnName" items="${result.columnNames}">
                        <th><c:out value="${columnName}"/></th>
                        </c:forEach>
                </tr>
                <!-- column data -->
                <c:forEach var="row" items="${result.rowsByIndex}">
                    <tr>
                        <c:forEach var="column" items="${row}">
                            <td><c:out value="${column}"/></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <br/>
            ENTER THE CODE  OF THE APPOINTMENT TO CANCEL:             <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            <input type="text" name="idCitFum" value="" />
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="CANCEL APPOINTMENT" name="btCancelCitaFun" />


        </form>
    </body>
</html>
