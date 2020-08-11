<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Hospitalizados
    Created on : 26-jul-2020, 11:35:11
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospitalized Patients</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>HOSPITALIZED PATIENTS!</h1>

        <form action="Servlet" method="POST">

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                select RegisterTrack.idRegi,RegisterTrack.regiDate, Hospitalized.ident, Hospitalized.idTreat, RegisterTrack.observ from RegisterTrack inner join Hospitalized on RegisterTrack.idFun = Hospitalized.idFun

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
            Enter the register code of the patient: <input type="text" name="idRegiP" value="" />

            <br/>
            <br/>
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            Enter the date: <input type="text" name="dateRegi" value="" />

            <br/>
            <br/>

            <sql:query var="result" dataSource="${snapshot}">
                select * from InfoTreatment
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
            Enter the treatment code: <input type="text" name="treatRegi" value="" />

            <br/>
            <br/>
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            Observations: <input type="text" name="observaRegi" value="" />

            <br/>
            <br/>
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="UPDATE REGISTER TRACKER" name="btUpdateRegi" />

        </form>

    </body>
</html>
