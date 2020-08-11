<%-- 
    Document   : registroPaciente
    Created on : 22-jul-2020, 16:27:31
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
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="cedulaPa" class="form-control" placeholder="" id="idP" value="${ced}">
                </div>
                <div class="form-group">
                    <label for="id">Password</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="pass" class="form-control" placeholder="" id="pass" value="*******">
                </div>
                <div class="form-group">
                    <label for="last-name">Name</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="name" class="form-control" placeholder="" id="nameP" value="${name}">
                </div>
                <div class="form-group">
                    <label for="country">First Last Name</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="primApe" class="form-control" placeholder="" id="FirstLastNameP" value="${first}">
                </div>
                <div class="form-group">
                    <label for="number">Second Last name</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="segApe" class="form-control" placeholder="" id="SecondLastNameP" value="${second}">
                </div>
                <div class="form-group">
                    <label for="text">Birth Date</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="birth" class="form-control" placeholder="" id="birth" value=" / / ">
                </div>
                <div class="form-group">
                    <label for="age">Province</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="province" class="form-control" placeholder="" id="province" value="${provi}">
                </div>
                <div class="form-group">
                    <label for="text">Blood type</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="blood" class="form-control" placeholder="" id="blood">
                </div>
                <div class="form-group">
                    <label for="text">Nacionality</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="nacion" class="form-control" placeholder="" id="nacion">
                </div>
                <div class="form-group">
                    <label for="text">Phone Number</label>
                                <%-- 
Enters: String with the information
Outs: String 
Restrictions: Not number or other type of var
--%> 
                    <input type="text" name="numTel" class="form-control" placeholder="" id="numTel">
                </div>
                <div class="clearfix"></div>
                            <%-- 
Enters: null
Outs: Var in the servlet change the state to activate an action 
Restrictions: Must be actived in "onclick"
--%> 
                <input type="submit" class="btn btn-info btn-lg btn-responsive" id="search"
                       value="Create account" name="signPac"> 
            </form>
        </div>
    </body>    

</html>
