<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : atenderPaciente
    Created on : 25-jul-2020, 12:00:59
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attending Patient</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>YOU ARE ATTENDING PATIENTS!</h1>

        <form action="Servlet" method="POST">

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                ${citasHospi}
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

            Enter the code of the appointment to assign: <input type="text" name="codigoCitaHospi" value="" />
            <br/>
            <br/>
            <sql:query var="result" dataSource="${snapshot}">
                select * from Diagnosis

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
            Enter the code of the diagnosis: <input type="text" name="codigoDiag" value="" />
            <br/>
            <br/>

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
            Enter the code of the treatment: <input type="text" name="codigoTrata" value="" />
            <br/>
            <br/>

            Observations: <input type="text" name="observacion" value="" />

            <br/>
            <br/>
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="Keep Appointment" name="botonAtender" />

            <br/>
            <br/>          
            <br/>

            <h1> HOSPITALIZE A PATIENT!</h1>

            <sql:query var="result" dataSource="${snapshot}">
                select AttentionCenter.idAttCen, InfoCentType.typeCen, AttentionCenter.nameCen from AttentionCenter inner join InfoCentType on AttentionCenter.idAttCen = InfoCentType.idCentType

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
            Enter the medical center code: <input type="text" name="idCentro" value="" />
            <br/>
            <br/>

            <sql:query var="result" dataSource="${snapshot}">
                select * from HealthArea

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
            Enter the medical area code: <input type="text" name="idArea" value="" />


            <br/>
            <br/>

            <sql:query var="result" dataSource="${snapshot}">
                select * from Diagnosis
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
            Enter the diagnosis code: <input type="text" name="idDiag" value="" />

            <br/>
            <br/>

            <sql:query var="result" dataSource="${snapshot}">
                select ident, nameP,firsLastN,secLastName from Patient

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
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            Enter the identity number: <input type="text" name="ceduPatient" value="" />
            <br/>
            <br/>

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
            Enter the treatment code: <input type="text" name="idTrata" value="" />
            <br/>
            <br/>

            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            Enter the beginning date: <input type="text" name="fechaInicio" value="" />
            <br/>
            <br/>
            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            Enter the end date: <input type="text" name="fechaFin" value="" />
            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 
            Observations: <input type="text" name="observaHospi" value="" />
            <br/>
            <br/>            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="HOSPITALIZE PATIENT" name="btnInternar" />
            <br/>
            <br/>
        </form>

    </body>
</html>
