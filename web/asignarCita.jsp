<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : asignarCita
    Created on : 25-jul-2020, 10:38:08
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assign Appointment</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>ENTER THE CODE OF THE APPOINTMENT TO ASSIGN!</h1>

        <form action="Servlet" method="POST">

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                ${instruccionCitaFunAsing}
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
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            Enter the code: <input type="text" name="codigoCita" value="" />
                        <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="ASSIGN APPOINTMENT" name="codeAsing" />
            
        </form>

    </body>
</html>
