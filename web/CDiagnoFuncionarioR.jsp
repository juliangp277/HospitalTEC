<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : CitasPacientesR
    Created on : Jul 28, 2020, 3:58:35 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Reports Diagnosis</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <form action="Servlet" method="POST">
            <header class="masthead bg-primary text-white text-center">
                <div class="container d-flex align-items-center flex-column">
                    <h1 class="masthead-heading text-uppercase mb-0">DIAGNOSIS COUNT</h1>
                    <div class="divider-custom divider-light">
                        <div class="divider-custom-line"></div>
                        <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                        <div class="divider-custom-line"></div>
                    </div>
                    <p class="masthead-subheading font-weight-light mb-0">Reports Functionaries</p>
                </div>
            </header>

            <br /><br />

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                select distinct patient.ident, HealthArea.area, diagnosis.leveld from hospitalized inner join patient on patient.ident= hospitalized.ident inner join HealthArea on HealthArea.idharea= hospitalized.idharea inner join diagnosis on diagnosis.iddiag= hospitalized.iddiag 

            </sql:query>

            <table border="5" align="center" width="80%" cellpadding="10"  bordercolor="gainsboro">
                <!-- column headers -->
                <tr align="center" bgcolor="lightblue">
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

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                select named, count(named) from hospitalized inner join diagnosis on diagnosis.iddiag= hospitalized.iddiag group by named

            </sql:query>

            <table border="5" align="center" width="80%" cellpadding="10"  bordercolor="gainsboro">
                <!-- column headers -->
                <tr align="center" bgcolor="lightblue">
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

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                select namep, firslastn, seclastname, count(named) from hospitalized inner join patient on patient.ident= hospitalized.ident inner join diagnosis on diagnosis.iddiag= hospitalized.iddiag group by namep, firslastn, seclastname

            </sql:query>

            <table border="5" align="center" width="80%" cellpadding="10"  bordercolor="gainsboro">
                <!-- column headers -->
                <tr align="center" bgcolor="lightblue">
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

            <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                               url = "jdbc:postgresql://localhost:5432/HospitalTEC"
                               user = "postgres"  password = "1234"/>

            <sql:query var="result" dataSource="${snapshot}">
                select area, count(named) from hospitalized inner join HealthArea on HealthArea.idharea= hospitalized.idharea inner join diagnosis on diagnosis.iddiag= hospitalized.iddiag group by area

            </sql:query>

            <table border="5" align="center" width="80%" cellpadding="10"  bordercolor="gainsboro">
                <!-- column headers -->
                <tr align="center" bgcolor="lightblue">
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
        </form>
    </body>
</html>
