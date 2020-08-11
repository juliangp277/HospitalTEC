<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : MenuPaciente
    Created on : 23-jul-2020, 15:44:31
    Author     : CR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Menu</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1><u>Menu Patient!</u></h1>
        
        <form action="Servlet" method="POST">
                        <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="REQUEST MEDICAL APPOINTMENT" name="btSoliCita">
                        <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="CANCEL APPOINTMENT" name="cancelCita" />
            
            <h1><u>Reports!</u></h1>
            
            <input type="submit" value="YOUR APPOINTMENTS" name="citasPaciente" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="YOUR DIAGNOSIS" name="diagPacientes" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="YOUR TREATMENTS" name="trataPacientes" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="YOUR HOSPITALIZATIONS" name="hospiPacie" />
            
        </form>

    </body>
</html>
