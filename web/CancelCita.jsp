<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : CancelCita
    Created on : 24-jul-2020, 0:04:34
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
        <h1>Your Appointments!</h1>

        <form action="Servlet" method="POST">

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                ${instruccionCita}

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
           <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            Enter the appointment ID <input type="text" name="idCitacion" value="" />

            <input type="submit" value="Cancel appointment" name="validarCancel"/>

            <h1>The LOG of your Appointments!</h1>

            <sql:query var="result" dataSource="${snapshot}">
                ${instruccionCitaLOG}
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
        </form>

    </body>
</html>
