<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : soliCita
    Created on : 23-jul-2020, 21:33:46
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Appointment</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>Request your appointment!</h1>

        <form action="Servlet" method="POST">
            <%-- 
Enters: Instruction
Outs: Table 
Restrictions: String intrucition
            --%> 
            <h3> Enter the code of the health area where you want to be treated </h3>

            <br /> 

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                SELECT idharea as ID, area FROM HealthArea
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
            <br />
            <br />
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
            --%> 
            Enter the code: <input type="text" name="codeArea" value="" />
            <br />
            <br />
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
            --%> 
            Date: <input type="text" name="dateAppo" value="" />
            <br/>
            <br/>
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
            --%> 
            Hour: <input type="text" name="horaAppo" value="" />
            <br/>
            <br/>
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
            --%> 
            Telephone: <input type="text" name="telP " value="${tel}" />
            <br/>
            <br/>
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
            --%> 
            E-mail: <input type="text" name="email" value="" />
            <br/>
            <br/>
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
            --%> 
            <input type="submit" value="Register Appointment" name="btRegiCita" />
        </form>
    </body>
</html>
