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
        <h1><u>Hello Doctor!</u></h1>
        
        <form action="Servlet" method="POST">

            <input type="submit" value="ASSIGN MEDICAL APPOINTMENT" name="asignarCita" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 

            <input type="submit" value="CANCEL APPOINTMENT" name="cancelCitaFun" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 

            <input type="submit" value="KEEP APPOINTMENTS" name="atenderCita" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            
            <input type="submit" value="HOSPITALIZED PATIENTS" name="hospi" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            
            <h1><u>Reports!</u></h1>
            
            <input type="submit" value="APPOINMTENTS" name="allCitas" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="DIAGNOSIS" name="allDiagnosis" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="TREATMENTS" name="allTreatments" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="AMOUNT OF APPOINTMENTS" name="countCitas" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="AMOUNT OF DIAGNOSIS" name="countDiagno" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="AMOUNTS OF TREATMENTS" name="countTreat" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="HOSPITALIZATIONS" name="hospiDoctor" />
          
        </form>

    </body>
</html>
