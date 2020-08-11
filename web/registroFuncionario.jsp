<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : registro
    Created on : 22-jul-2020, 14:41:19
    Author     : AndreyVC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Sign Up Functionary</title>
    </head>

    <body style="background-color:paleturquoise">
        <div class="container" id="siFunc">
            <h2>Sign Up</h2>
            <form action="Servlet" method="POST">
                <div class="form-group">
                    <label for="id">Identification</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" name="cedFun" value="${ced}" class="form-control" placeholder="" id="idF">
                </div>
                <div class="form-group">
                    <label for="id">Password</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" value="**********" class="form-control" placeholder="" id="pass">
                </div>
                <div class="form-group">
                    <label for="last-name">Name</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" name="nameFun" value="${name}" class="form-control" placeholder="" id="Name">
                </div>
                <div class="form-group">
                    <label for="country">First Last Name</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" name="firstLNFun" value="${first}" class="form-control" placeholder="" id="firstLNF">
                </div>
                <div class="form-group">
                    <label for="number">Second Last name</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" name="secondLNFun" value="${second}" class="form-control" placeholder="" id="SecondLastNamePLastNameF">
                </div>
                <div class="form-group">
                    <label for="text">Start date</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" value=" / /" name="startDate" class="form-control" placeholder="" id="starDate">
                </div>
                <div class="form-group">
                    <br />
                    <br />
                    <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                                       url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                                       user = "postgres"  password = "1234"/>

                    <sql:query var="result" dataSource="${snapshot}">
                        SELECT idharea as ID, area as Area FROM HealthArea
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
                    <label for="email"> ID Area</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" name="idArea" value="" class="form-control" placeholder="" id="area">
                    <br />
                    <br />
                    <sql:query var="result" dataSource="${snapshot}">
                        SELECT idfuntype as ID, typefun as Function FROM InfoFuncType
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
                    <label for="email">ID Work</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Nor other type of var
--%> 

                    <input type="text" name="idWork" value="" class="form-control" placeholder="" id="work">
                    <br />
                    <br />
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
                    <div class="clearfix"></div>
                    <input type="submit" class="btn btn-info btn-lg btn-responsive" id="search"
                           value="Create account" name="createFunc"> 
                    </form>
                </div>
                </body>    

                </html>
