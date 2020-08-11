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
        <h1>Hello Nurse!</h1>
        
        <form action="Servlet" method="POST">
            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="ASSIGN MEDICAL APPOINTMENT" name="asignarCita" />
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
            <input type="submit" value="HOSPITALIZED PATIENTS" name="hospi" />
            
            <h1>Reports</h1>
            
            <input type="submit" value="APPOINMTENTS" name="allCitasN" />
                        <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="DIAGNOSIS" name="allDiagnosisN" />
                        <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="TREATMENTS" name="allTreatmentsN" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="AMOUNT OF APPOINTMENTS" name="countCitasN" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="AMOUNT OF DIAGNOSIS" name="countDiagnoN" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="AMOUNTS OF TREATMENTS" name="countTreatN" />            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
            <input type="submit" value="HOSPITALIZATIONS" name="hospiDoctorN" />
                        <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
        </form>

    </body>
</html>
