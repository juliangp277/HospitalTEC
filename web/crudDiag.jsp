<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : crudDiag
    Created on : 27-jul-2020, 18:24:31
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Diagnosis</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>CREATING DIAGNOSIS!</h1>

        <form action="Servlet" method="POST">

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>
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
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the code of the new diagnosis:<input type="text" name="codeDiag" value="" />

            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the name of the new diagnosis:<input type="text" name="nameDiag" value="" />

            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the level of the new diagnosis:<input type="text" name="levelDiag" value="" />

            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Observations:<input type="text" name="observaD" value="" />
            <br/>
            <br/>            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="CREATE DIAGNOSIS" name="createDiag" />
                      <br/>
                <br/>
                <h1> DELETE DIAGNOSIS </h1>
                
                Enter the code of the diagnosis to delete him:<input type="text" name="diagDelete" value="" />
                    <br/>
            <br/>            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="DELETE DIAGNOSIS" name="deleteDiag" />
        </form>

    </body>
</html>
