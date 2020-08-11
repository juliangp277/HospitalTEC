<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : crudCM
    Created on : 27-jul-2020, 16:06:09
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Center</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>EDITING MEDICAL CENTER!</h1>

        <form action="Servlet" method="POST">

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                select * from AttentionCenter
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

            Enter the new Medical Center Code: 
            <input type="text" name="codeMC" value="" />
            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the new Medical Center Name: 
            <input type="text" name="nameMC" value="" />
            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the capacity: 
            <input type="text" name="capacity" value="" />
            <br/>
            <br/>

            <sql:query var="result" dataSource="${snapshot}">
                select * from InfoCentType
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

            Enter the code of the type: 
            <input type="text" name="typeCen" value="" />

            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the province: 
            <input type="text" name="provi" value="" />

            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the canton: 
            <input type="text" name="canton" value="" />

            <br/>
            <br/>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            Enter the district: 
            <input type="text" name="distri" value="" />
            <br/>
            <br/>            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="CREATE NEW MEDICAL CENTER" name="btCreateMC" />

            <h1> Enter the code of the center to delete him: </h1>            <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

            <input type="text" name="codeDelete" value="" />
            <br/>
            <br/>            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
                <input type="submit" value="DELETE THIS MEDICAL CENTER" name="btDeleteMC" />



        </form>

    </body>
</html>
