<%-- 
    Document   : menu
    Created on : 21-jul-2020, 22:35:33
    Author     : AndreyVC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Functionary Menu</title>
    </head>
    <body style="background-color:paleturquoise">
        <h1>Hello Secretary!</h1>

        <form action="Servlet" method="POST">
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="ASSING MEDICAL APPOINTMENT" name="asignarCita" />
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="CANCEL APPOINTMENT" name="cancelCitaFun" />
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="MEDICAL CENTERS" name="crudMC" />
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="HEALTH AREAS" name="crudArea" />
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="DIAGNOSIS" name="crudDiag" />
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="TREATMENTS" name="crudTrata" />

            <h1>Reports</h1>

            <input type="submit" value="APPOINMTENTS" name="allCitasS" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="HOSPITALIZATIONS" name="hospiDoctorS" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 

        </form>

    </body>
</html>
