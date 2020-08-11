/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Consulta Hospital
/** <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
 * url = "jdbc:postgresql://localhost:5432/HospitalTEC"
 * user = "postgres"  password = "1234"/>
 *
 * <sql:query var="result" dataSource="${snapshot}">
 * SELECT * FROM PatientTel
 * </sql:query>
 */
// Consulta TSE
/**
 * <sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
 * url = "jdbc:postgresql://localhost:5432/TSE"
 * user = "postgres"  password = "1234"/>
 *
 * <sql:query var="result" dataSource="${snapshot}">
 * SELECT * FROM PatientTel
 * </sql:query>
 */
@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    public static ArrayList resultados = new ArrayList();
    public static ArrayList person = new ArrayList();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * This methos, GET, manage all the function in the code, all the var come
     * here and do their respective actions
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CallableStatement cs;
        processRequest(request, response);

        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            cs = con.prepareCall("");

            String ident = request.getParameter("id"); 
            //Dispatcher
            RequestDispatcher rd;

            //Buttons
            String btL = request.getParameter("LogiN"); 
            String btRF = request.getParameter("regfun"); 
            String btRP = request.getParameter("regpat"); 
            String btCreatePatient = request.getParameter("signPac"); 
            String btCreateFunc = request.getParameter("createFunc"); 
            String btSoliCita = request.getParameter("btSoliCita"); 
            String btRegiCita = request.getParameter("btRegiCita"); 
            String btCancelCita = request.getParameter("cancelCita"); 
            String btValidarCancel = request.getParameter("validarCancel"); 
            String btCancelCitaFun = request.getParameter("cancelCitaFun"); 
            String brValidarCancelCitaFun = request.getParameter("btCancelCitaFun"); 
            String btAsignaCita = request.getParameter("asignarCita"); 
            String btcodeAsing = request.getParameter("codeAsing"); 
            String btAtenderCita = request.getParameter("atenderCita"); 
            String btAtendiendo = request.getParameter("botonAtender"); 
            String btInternar = request.getParameter("btnInternar"); 
            String btHospi = request.getParameter("hospi"); 
            String btUpdateRegi = request.getParameter("btUpdateRegi");
            String crudMC = request.getParameter("crudMC"); 
            String btCreateMC = request.getParameter("btCreateMC"); 
            String btDeleteMC = request.getParameter("btDeleteMC"); 
            String crudDiag = request.getParameter("crudDiag"); 
            String createDiag = request.getParameter("createDiag"); 
            String deleteDiag = request.getParameter("deleteDiag");
            String crudArea = request.getParameter("crudArea"); 
            String btCrearArea = request.getParameter("btCrearArea"); 
            String btBorrarArea = request.getParameter("btBorrarArea");
            String crudTrata = request.getParameter("crudTrata"); 
            String btCreaTreat = request.getParameter("btCreaTreat"); 
            String btDenleTe = request.getParameter("btDenleTe"); 
/**
 * Enters: Boolean value
 * Outs: Check the profile in the database of the system
 * Restrictions: The value of the button need to be differeen of null
 */
            if (btL != null) {

                consultaTSE(ident);
/**
 * Enters: Boolean value
 * Outs: Search de id in the database of the TSE
 * Restrictions: The value of the button need to be different of null
 */
                if (esPaciente(ident)) {

                    rd = request.getRequestDispatcher("/MenuPaciente.jsp");
                    rd.forward(request, response);
                }
                /**
 * Enters: Boolean value
 * Outs: Check if the person is a Functionary
 * Restrictions: The value of the button need to be different of null
 */
                if (esFunct(ident)) {
                    String rol = tipoRol(ident);
                    if ("Doctor".equals(rol)) {
                        rd = request.getRequestDispatcher("/menu.jsp");
                        rd.forward(request, response);
                    }
                    if ("Nurse".equals(rol)) {
                        rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                        rd.forward(request, response);
                    }
                    if ("Secretary".equals(rol)) {
                        rd = request.getRequestDispatcher("/menSecre.jsp");
                        rd.forward(request, response);
                    }

                }

                String mensaError = "YOU ARE NOT REGISTERED ALREADY";
                request.getSession().setAttribute("mensaError", mensaError); //

                rd = request.getRequestDispatcher("/pagErrores.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Create a new functionary
 * Restrictions: The value of the button need to be different of null
 */
            if (btRF != null) {

                consultaTSE(ident);

                String name = (String) person.get(1);
                String first = (String) person.get(2);
                String second = (String) person.get(3);

                request.getSession().setAttribute("ced", ident); //Envio cedula
                request.getSession().setAttribute("name", name); //Envio nombre
                request.getSession().setAttribute("first", first); //Envio apellido
                request.getSession().setAttribute("second", second); //Envio segApe

                rd = request.getRequestDispatcher("/registroFuncionario.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Create a new patient
 * Restrictions: The value of the button need to be different of null
 */
            if (btRP != null) {

                consultaTSE(ident);
                String provi = findProvincia(ident);

                String name = (String) person.get(1);
                String first = (String) person.get(2);
                String second = (String) person.get(3);

                request.getSession().setAttribute("ced", ident); //Envio cedula
                request.getSession().setAttribute("name", name); //Envio nombre
                request.getSession().setAttribute("first", first); //Envio apellido
                request.getSession().setAttribute("second", second); //Envio segApe
                request.getSession().setAttribute("provi", provi); //Envio provincia

                rd = request.getRequestDispatcher("/registroPaciente.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Create and active the function of register patient
 * Restrictions: The value of the button need to be different of null
 */
            if (btCreatePatient != null) {

                String ced = request.getParameter("cedulaPa");
                String nameP = request.getParameter("name");
                String fLN = request.getParameter("primApe");
                String sLN = request.getParameter("segApe");
                String provin = request.getParameter("province");
                String birthD = request.getParameter("birth");
                String blood = request.getParameter("blood");
                String nacion = request.getParameter("nacion");
                String tel = request.getParameter("numTel");

                String insPat = "insert into Patient values('" + ced + "','" + nameP + "','" + fLN + "','" + sLN + "','" + provin + "','" + birthD + "','" + blood + "','" + nacion + "')";
                cs = con.prepareCall(insPat);

                String insPatTel = "insert into PatientTel values ('" + ced + "','" + tel + "')";
                cs = con.prepareCall(insPatTel);
                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);

            }

            /**
 * Enters: Boolean value
 * Outs: Create ant active the function create Functionary 
 * Restrictions: The value of the button need to be different of null
 */
            if (btCreateFunc != null) {

                String ced = request.getParameter("cedFun");
                String name = request.getParameter("nameFun");
                String first = request.getParameter("firstLNFun");
                String second = request.getParameter("secondLNFun");
                String startD = request.getParameter("startDate");
                String area = request.getParameter("idArea");
                String work = request.getParameter("idWork");

                String insFun = "insert into Functionary values('" + ced + "','" + name + "','" + first + "','" + second + "','" + startD + "')";
                cs = con.prepareCall(insFun);
                String insFunArea = "insert into FuncArea values('" + ced + "','" + area + "');";
                cs = con.prepareCall(insFunArea);
                String insTW = "insert into FuncType values('" + ced + "','" + work + "')";
                cs = con.prepareCall(insTW);
                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Solicitate the appointment 
 * Restrictions: The value of the button need to be different of null
 */
            if (btSoliCita != null) {

                String ced = (String) person.get(0);
                String telPa = telP(ced);

                request.getSession().setAttribute("tel", telPa); //

                rd = request.getRequestDispatcher("/soliCita.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Register and active the function in charge of create a new appointment
 * Restrictions: The value of the button need to be different of null
 */
            if (btRegiCita != null) {

                String ced = (String) person.get(0);
                String area = request.getParameter("codeArea");
                String date = request.getParameter("dateAppo");
                String hora = request.getParameter("horaAppo");
                String tel = request.getParameter("telP");
                String email = request.getParameter("email");

                String insAppo = "insert into Appointments values('" + idCita + "','" + ced + "','" + area + "')";
                cs = con.prepareCall(insAppo);
                String insAppoDate = "insert into AppoDates values('" + idCita + "','" + date + "','" + hora + "')";
                cs = con.prepareCall(insAppoDate);
                String insAppoState = "insert into AppoState values('" + idCita + "','Registered')";
                cs = con.prepareCall(insAppoState);
                String insAppoStateLOG = "insert into AppoStateLOG values('" + idCita + "','Registered','" + dateLocal + "')";
                cs = con.prepareCall(insAppoStateLOG);
                idCita++;

                rd = request.getRequestDispatcher("/MenuPaciente.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Activate the function of cancel Appointment and change the state of apponitment
 * Restrictions: The value of the button need to be different of null
 */
            if (btCancelCita != null) {

                String ced = (String) person.get(0);
                String instruccionCita = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN Patient ON Appointments.ident = '" + ced + "' INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("instruccionCita", instruccionCita); //
                String instruccionCitaLOG = "select distinct AppoStateLOG.idAppo, AppoStateLOG.appoState, AppoStateLOG.appoDateLOG from AppoStateLOG inner join Appointments on Appointments.ident='" + ced + "' and Appointments.idAppo = AppoStateLOG.idAppo";
                request.getSession().setAttribute("instruccionCitaLOG", instruccionCitaLOG); //

                rd = request.getRequestDispatcher("/CancelCita.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Check if the appointment can be canceled 
 * Restrictions: The value of the button need to be different of null
 */
            if (btValidarCancel != null) {

                String idCit = request.getParameter("idCitacion");
                String fecha2 = fechaCita(idCit);
                if (cancelCita(dateLocal, fecha2)) {

                    String instruCanCi = "update appoState set appoState='Canceled by Patient' where idAppo='" + idCit + "'";
                    cs = con.prepareCall(instruCanCi);
                    String insAppoStateLOG = "insert into AppoStateLOG values('" + idCit + "','Canceled by Patient','" + dateLocal + "')";
                    cs = con.prepareCall(insAppoStateLOG);
                    rd = request.getRequestDispatcher("/MenuPaciente.jsp");
                    rd.forward(request, response);
                }

                String mensaError = "THE APPOINTMENT CAN NOT BE CANCELED FALTANDO SOLO 2 DIAS";
                request.getSession().setAttribute("mensaError", mensaError); //
                rd = request.getRequestDispatcher("/pagErrores.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs:  Activate the function of canel appointment by a doctor
 * Restrictions: The value of the button need to be different of null
 */
            if (btCancelCitaFun != null) {

                String instruccionCitaFun = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo and AppoState.appoState='Registered' INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("instruccionCitaFun", instruccionCitaFun);

                rd = request.getRequestDispatcher("/cancelarCitaFun.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Check if the appointment can be canceled 
 * Restrictions: The value of the button need to be different of null
 */
            if (brValidarCancelCitaFun != null) {

                String idCite = request.getParameter("idCitFum");
                String fecha2 = fechaCita(idCite);
                if (cancelCita(dateLocal, fecha2)) {

                    String instruCanCiF = "update appoState set appoState='Canceled by MC' where idAppo='" + idCite + "'";
                    cs = con.prepareCall(instruCanCiF);
                    String insAppoStateLOGF = "insert into AppoStateLOG values('" + idCite + "','Canceled by MC','" + dateLocal + "')";
                    cs = con.prepareCall(insAppoStateLOGF);
                    String valid = (String) person.get(0);
                    String rol = tipoRol(valid);
                    if ("Doctor".equals(rol)) {
                        rd = request.getRequestDispatcher("/menu.jsp");
                        rd.forward(request, response);
                    }
                    if ("Nurse".equals(rol)) {
                        rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                        rd.forward(request, response);
                    }
                    if ("Secretary".equals(rol)) {
                        rd = request.getRequestDispatcher("/menSecre.jsp");
                        rd.forward(request, response);
                    }
                }

                String mensaError = "THE APPOINTMENT CANNOT BE CANCELED MISSING TWO DAYS";
                request.getSession().setAttribute("mensaError", mensaError); //
                rd = request.getRequestDispatcher("/pagErrores.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Asign appointment 
 * Restrictions: The value of the button need to be different of null
 */
            if (btAsignaCita != null) {

                String instruccionCitaFunAsing = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo and AppoState.appoState='Canceled by MC' INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("instruccionCitaFunAsing", instruccionCitaFunAsing);

                rd = request.getRequestDispatcher("/asignarCita.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Activate the function of the creation of the new appointment
 * Restrictions: The value of the button need to be different of null
 */
            if (btcodeAsing != null) {

                String idCite = request.getParameter("codigoCita");

                String instruCanCiF = "update appoState set appoState='Assigned' where idAppo='" + idCite + "'";
                cs = con.prepareCall(instruCanCiF);
                String insAppoStateLOGF = "insert into AppoStateLOG values('" + idCite + "','Assigned','" + dateLocal + "')";
                cs = con.prepareCall(insAppoStateLOGF);
                String valid = (String) person.get(0);
                String rol = tipoRol(valid);
                if ("Doctor".equals(rol)) {
                    rd = request.getRequestDispatcher("/menu.jsp");
                    rd.forward(request, response);
                }
                if ("Nurse".equals(rol)) {
                    rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                    rd.forward(request, response);
                }
                if ("Secretary".equals(rol)) {
                    rd = request.getRequestDispatcher("/menSecre.jsp");
                    rd.forward(request, response);
                }
                rd = request.getRequestDispatcher("/menu.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Activate the function keep appointment
 * Restrictions: The value of the button need to be different of null
 */
            if (btAtenderCita != null) {

                String citasHospi = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo and AppoState.appoState='Registered' or AppoState.appoState='Assigned' INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("citasHospi", citasHospi);

                rd = request.getRequestDispatcher("/atenderPaciente.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Change the states in the corresponding place
 * Restrictions: The value of the button need to be different of null
 */
            if (btAtendiendo != null) {

                String idCite = request.getParameter("codigoCitaHospi");
                String ced = (String) person.get(0);

                String instruCanCiF = "update appoState set appoState='Made' where idAppo='" + idCite + "'";
                cs = con.prepareCall(instruCanCiF);
                String insAppoStateLOGF = "insert into AppoStateLOG values('" + idCite + "','Made','" + dateLocal + "')";
                cs = con.prepareCall(insAppoStateLOGF);
                String diag = request.getParameter("codigoDiag");
                String trata = request.getParameter("codigoTrata");
                String observa = request.getParameter("observacion");
                String idPati = idePaci(idCite);

                String instru = "insert into AttendedAppo values('" + idPati + "','" + ced + "' ,'" + diag + "','" + trata + "','" + observa + "')";
                cs = con.prepareCall(instru);
                rd = request.getRequestDispatcher("/menu.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Fill the information of the hospitalized person
 * Restrictions: The value of the button need to be different of null
 */
            if (btInternar != null) {

                String ced = (String) person.get(0);
                String idCentro = request.getParameter("idCentro");
                String idArea = request.getParameter("idArea");
                String idDiag = request.getParameter("idDiag");
                String idTrata = request.getParameter("idTrata");
                String cedPati = request.getParameter("ceduPatient");
                String fechaIni = request.getParameter("fechaInicio");
                String fechaFin = request.getParameter("fechaFin");
                String observaH = request.getParameter("observaHospi");

                String instruR = "insert into RegisterTrack values('" + idRegistro + "','" + ced + "','" + idTrata + "','" + dateLocal + "','" + observaH + "');";
                cs = con.prepareCall(instruR);
                String instruH = "insert into Hospitalized values('" + cedPati + "','" + ced + "','" + idCentro + "','" + idArea + "','" + idDiag + "','" + idTrata + "','" + idRegistro + "')";
                cs = con.prepareCall(instruH);
                String datesH = "insert into HospitalDates values('" + cedPati + "','" + fechaIni + "','" + fechaFin + "')";
                cs = con.prepareCall(datesH);
                idRegistro++;
                rd = request.getRequestDispatcher("/menu.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Open a new jsp
 * Restrictions: The value of the button need to be different of null
 */
            if (btHospi != null) {

                rd = request.getRequestDispatcher("/Hospitalizados.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Update the register tracker
 * Restrictions: The value of the button need to be different of null
 */
            if (btUpdateRegi != null) {

                String ced = (String) person.get(0);
                String idRegi = request.getParameter("idRegiP");
                int tmp = Integer.parseInt(idRegi);
                tmp = tmp + 1;
                String idRegiP = Integer.toString(tmp);
                String dateRegi = request.getParameter("dateRegi");
                String treatRegi = request.getParameter("treatRegi");
                String observaRegi = request.getParameter("observaRegi");

                String instruc = "insert into RegisterTrack values('" + idRegiP + "','" + ced + "','" + treatRegi + "','" + dateRegi + "','" + observaRegi + "')";
                cs = con.prepareCall(instruc);
                String valid = (String) person.get(0);
                String rol = tipoRol(valid);

                if ("Doctor".equals(rol)) {
                    rd = request.getRequestDispatcher("/menu.jsp");
                    rd.forward(request, response);
                }
                if ("Nurse".equals(rol)) {
                    rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                    rd.forward(request, response);
                }
                if ("Secretary".equals(rol)) {
                    rd = request.getRequestDispatcher("/menSecre.jsp");
                    rd.forward(request, response);
                }
            }
            /**
 * Enters: Boolean value
 * Outs:  Execute  the CRUD opeations in the medical center table
 * Restrictions: The value of the button need to be different of null
 */
            if (crudMC != null) {

                rd = request.getRequestDispatcher("/crudCM.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Create a medical center
 * Restrictions: The value of the button need to be different of null
 */
            if (btCreateMC != null) {

                String codeMC = request.getParameter("codeMC");
                String nameMC = request.getParameter("nameMC");
                String capacity = request.getParameter("capacity");
                String provi = request.getParameter("provi");
                String canton = request.getParameter("canton");
                String distri = request.getParameter("distri");
                String typeCen = request.getParameter("typeCen");

                String instru1 = "insert into AttentionCenter values ('" + codeMC + "','" + nameMC + "','" + capacity + "')";
                cs = con.prepareCall(instru1);
                String instru2 = "insert into CenterTypes values ('" + codeMC + "','" + typeCen + "')";
                cs = con.prepareCall(instru2);
                String instru3 = "insert into CenterUbication values('" + codeMC + "','" + provi + "','" + canton + "','" + distri + "');";
                cs = con.prepareCall(instru3);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Delete a medical center
 * Restrictions: The value of the button need to be different of null
 */
            if (btDeleteMC != null) {

                String codeDelete = request.getParameter("codeDelete");

                String instruD = "delete from AttentionCenter where idAttCen='" + codeDelete + "'";
                cs = con.prepareCall(instruD);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  execute he CRUD operations in Diagnosis
 * Restrictions: The value of the button need to be different of null
 */
            if (crudDiag != null) {

                rd = request.getRequestDispatcher("/crudDiag.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs: Create a new diagnosis
 * Restrictions: The value of the button need to be different of null
 */
            if (createDiag != null) {

                String codeDiag = request.getParameter("codeDiag");
                String nameDiag = request.getParameter("nameDiag");
                String levelDiag = request.getParameter("levelDiag");
                String observaD = request.getParameter("observaD");

                String instru1 = "insert into Diagnosis values('" + codeDiag + "','" + nameDiag + "','" + levelDiag + "','" + observaD + "');";
                cs = con.prepareCall(instru1);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Delete a medical center
 * Restrictions: The value of the button need to be different of null
 */
            if (deleteDiag != null) {

                String codeDelete = request.getParameter("diagDelete");

                String instruD = "delete from Diagnosis where idDiag='" + codeDelete + "'";
                cs = con.prepareCall(instruD);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Execute teh CRUD operations in Health Areas
 * Restrictions: The value of the button need to be different of null
 */
            if (crudArea != null) {

                rd = request.getRequestDispatcher("/crudArea.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Create a new health area
 * Restrictions: The value of the button need to be different of null
 */
            if (btCrearArea != null) {

                String codeArea = request.getParameter("codeArea");
                String nameArea = request.getParameter("nameArea");

                String instru1 = "insert into HealthArea values('" + codeArea + "','" + nameArea + "');";
                cs = con.prepareCall(instru1);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Delete a health area
 * Restrictions: The value of the button need to be different of null
 */
            if (btBorrarArea != null) {

                String codeDelete = request.getParameter("deleteArea");

                String instruD = "delete from HealthArea where idHArea='" + codeDelete + "'";
                cs = con.prepareCall(instruD);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Execute the CRUD operations in Treatments
 * Restrictions: The value of the button need to be different of null
 */
            if (crudTrata != null) {

                rd = request.getRequestDispatcher("/crudTrata.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Create a new treatment
 * Restrictions: The value of the button need to be different of null
 */ 
            if (btCreaTreat != null) {

                String codeTreat = request.getParameter("codeTreat");
                String nombreTreat = request.getParameter("nombreTreat");
                String dosisTreat = request.getParameter("dosisTreat");
                String typeTreat = request.getParameter("typeTreat");
                String treatDiag = request.getParameter("treatDiag");

                String instru1 = "insert into InfoTreatment values('" + codeTreat + "','" + nombreTreat + "','" + dosisTreat + "','" + typeTreat + "');";
                cs = con.prepareCall(instru1);
                String instru2 = "insert into Treatments values('" + codeTreat + "','" + treatDiag + "');";
                cs = con.prepareCall(instru2);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Delete a treatment
 * Restrictions: The value of the button need to be different of null
 */
            if (btDenleTe != null) {

                String codeDelete = request.getParameter("codeDenlete");

                String instruD = "delete from InfoTreatment where idTreat='" + codeDelete + "'";
                cs = con.prepareCall(instruD);
                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }

        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println("</head>");
            out.println("<body>");
            //Setea Parametros

            //Extracción de Datos
            String ident = request.getParameter("id"); //Cedula del Loging

            //Dispatcher
            RequestDispatcher rd;

            //Botones
            String btL = request.getParameter("LogiN"); 
            String btRF = request.getParameter("regfun"); 
            String btRP = request.getParameter("regpat"); 
            String btCreatePatient = request.getParameter("signPac"); 
            String btCreateFunc = request.getParameter("createFunc"); 
            String btSoliCita = request.getParameter("btSoliCita"); 
            String btRegiCita = request.getParameter("btRegiCita"); 
            String btCancelCita = request.getParameter("cancelCita"); 
            String btValidarCancel = request.getParameter("validarCancel"); 
            String btCancelCitaFun = request.getParameter("cancelCitaFun"); 
            String brValidarCancelCitaFun = request.getParameter("btCancelCitaFun"); 
            String btAsignaCita = request.getParameter("asignarCita"); 
            String btcodeAsing = request.getParameter("codeAsing"); 
            String btAtenderCita = request.getParameter("atenderCita"); 
            String btAtendiendo = request.getParameter("botonAtender"); 
            String btInternar = request.getParameter("btnInternar"); 
            String btHospi = request.getParameter("hospi"); 
            String btUpdateRegi = request.getParameter("btUpdateRegi"); 
            String crudMC = request.getParameter("crudMC"); 
            String btCreateMC = request.getParameter("btCreateMC");
            String btDeleteMC = request.getParameter("btDeleteMC"); 
            String crudDiag = request.getParameter("crudDiag"); 
            String createDiag = request.getParameter("createDiag"); 
            String deleteDiag = request.getParameter("deleteDiag"); 
            String crudArea = request.getParameter("crudArea"); 
            String btCrearArea = request.getParameter("btCrearArea"); 
            String btBorrarArea = request.getParameter("btBorrarArea"); 
            String crudTrata = request.getParameter("crudTrata"); 
            String btCreaTreat = request.getParameter("btCreaTreat"); 
            String btDenleTe = request.getParameter("btDenleTe"); 
            String btrepoCita = request.getParameter("citasPaciente"); 
            String btrepoDiagno = request.getParameter("diagPacientes");
            String btrepoTreat = request.getParameter("trataPacientes");
            String btrepoHospi = request.getParameter("hospiPacie");
            String btrepoCitasD = request.getParameter("allCitas");
            String btRepoDiangoD = request.getParameter("allDiagnosis");
            String btRepoTreatD = request.getParameter("allTreatments");
            String btRepoDCC = request.getParameter("countCitas");
            String btRepoDCD = request.getParameter("countDiagno");
            String btRepoDCT = request.getParameter("countTreat");
            String btrepoHospiD = request.getParameter("hospiDoctor");
            String btrepoCitasN = request.getParameter("allCitasN");
            String btRepoDiangoN = request.getParameter("allDiagnosisN");
            String btRepoTreatN = request.getParameter("allTreatmentsN");
            String btRepoNCC = request.getParameter("countCitasN");
            String btRepoNCD = request.getParameter("countDiagnoN");
            String btRepoNCT = request.getParameter("countTreatN");
            String btrepoHospiN = request.getParameter("hospiDoctorN");
            String btrepoCitasS = request.getParameter("allCitasS");
            String btrepoHospiS = request.getParameter("hospiDoctorS");
/**
 * Enters: Boolean value
 * Outs: Check the profile in the database of the system
 * Restrictions: The value of the button need to be differeen of null
 */

            if (btL != null) {
/**
 * Enters: Boolean value
 * Outs: Search de id in the database of the TSE
 * Restrictions: The value of the button need to be different of null
 */
                consultaTSE(ident);

                if (esPaciente(ident)) {

                    rd = request.getRequestDispatcher("/MenuPaciente.jsp");
                    rd.forward(request, response);
                }
                if (esFunct(ident)) {
                    String rol = tipoRol(ident);
                    if ("Doctor".equals(rol)) {
                        rd = request.getRequestDispatcher("/menu.jsp");
                        rd.forward(request, response);
                    }
                    if ("Nurse".equals(rol)) {
                        rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                        rd.forward(request, response);
                    }
                    if ("Secretary".equals(rol)) {
                        rd = request.getRequestDispatcher("/menSecre.jsp");
                        rd.forward(request, response);
                    }

                }

                String mensaError = "YOU ARE NOT REGISTERED ALREADY";
                request.getSession().setAttribute("mensaError", mensaError); //

                rd = request.getRequestDispatcher("/pagErrores.jsp");
                rd.forward(request, response);
            }
                /**
 * Enters: Boolean value
 * Outs: Check if the person is a Functionary
 * Restrictions: The value of the button need to be different of null
 */
            if (btRF != null) {

                consultaTSE(ident);

                String name = (String) person.get(1);
                String first = (String) person.get(2);
                String second = (String) person.get(3);

                request.getSession().setAttribute("ced", ident); //Envio cedula
                request.getSession().setAttribute("name", name); //Envio nombre
                request.getSession().setAttribute("first", first); //Envio apellido
                request.getSession().setAttribute("second", second); //Envio segApe

                rd = request.getRequestDispatcher("/registroFuncionario.jsp");
                rd.forward(request, response);

            }

/**
 * Enters: Boolean value
 * Outs: Create a new functionary
 * Restrictions: The value of the button need to be different of null
 */
            if (btRP != null) {

                consultaTSE(ident);
                String provi = findProvincia(ident);

                String name = (String) person.get(1);
                String first = (String) person.get(2);
                String second = (String) person.get(3);

                request.getSession().setAttribute("ced", ident); //Envio cedula
                request.getSession().setAttribute("name", name); //Envio nombre
                request.getSession().setAttribute("first", first); //Envio apellido
                request.getSession().setAttribute("second", second); //Envio segApe
                request.getSession().setAttribute("provi", provi); //Envio provincia

                rd = request.getRequestDispatcher("/registroPaciente.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Create a new patient
 * Restrictions: The value of the button need to be different of null
 */

            if (btCreatePatient != null) {

                String ced = request.getParameter("cedulaPa");
                String nameP = request.getParameter("name");
                String fLN = request.getParameter("primApe");
                String sLN = request.getParameter("segApe");
                String provin = request.getParameter("province");
                String birthD = request.getParameter("birth");
                String blood = request.getParameter("blood");
                String nacion = request.getParameter("nacion");
                String tel = request.getParameter("numTel");

                String insPat = "insert into Patient values('" + ced + "','" + nameP + "','" + fLN + "','" + sLN + "','" + provin + "','" + birthD + "','" + blood + "','" + nacion + "')";
                consulta(insPat);

                String insPatTel = "insert into PatientTel values ('" + ced + "','" + tel + "')";
                consulta(insPatTel);

                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Create and active the function of register patient
 * Restrictions: The value of the button need to be different of null
 */

            if (btCreateFunc != null) {

                String ced = request.getParameter("cedFun");
                String name = request.getParameter("nameFun");
                String first = request.getParameter("firstLNFun");
                String second = request.getParameter("secondLNFun");
                String startD = request.getParameter("startDate");
                String area = request.getParameter("idArea");
                String work = request.getParameter("idWork");

                String insFun = "insert into Functionary values('" + ced + "','" + name + "','" + first + "','" + second + "','" + startD + "')";
                consulta(insFun);

                String insFunArea = "insert into FuncArea values('" + ced + "','" + area + "');";
                consulta(insFunArea);

                String insTW = "insert into FuncType values('" + ced + "','" + work + "')";
                consulta(insTW);

                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);

            }

            /**
 * Enters: Boolean value
 * Outs: Create ant active the function create Functionary 
 * Restrictions: The value of the button need to be different of null
 */
            if (btSoliCita != null) {

                String ced = (String) person.get(0);
                String telPa = telP(ced);

                request.getSession().setAttribute("tel", telPa); //

                rd = request.getRequestDispatcher("/soliCita.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Solicitate the appointment 
 * Restrictions: The value of the button need to be different of null
 */
            if (btRegiCita != null) {

                String ced = (String) person.get(0);
                String area = request.getParameter("codeArea");
                String date = request.getParameter("dateAppo");
                String hora = request.getParameter("horaAppo");
                String tel = request.getParameter("telP");
                String email = request.getParameter("email");

                String insAppo = "insert into Appointments values('" + idCita + "','" + ced + "','" + area + "')";
                consulta(insAppo);

                String insAppoDate = "insert into AppoDates values('" + idCita + "','" + date + "','" + hora + "')";
                consulta(insAppoDate);

                String insAppoState = "insert into AppoState values('" + idCita + "','Registered')";
                consulta(insAppoState);

                String insAppoStateLOG = "insert into AppoStateLOG values('" + idCita + "','Registered','" + dateLocal + "')";
                consulta(insAppoStateLOG);

                idCita++;

                rd = request.getRequestDispatcher("/MenuPaciente.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Register and active the function in charge of create a new appointment
 * Restrictions: The value of the button need to be different of null
 */
            if (btCancelCita != null) {

                String ced = (String) person.get(0);
                String instruccionCita = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN Patient ON Appointments.ident = '" + ced + "' INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("instruccionCita", instruccionCita); //
                String instruccionCitaLOG = "select distinct AppoStateLOG.idAppo, AppoStateLOG.appoState, AppoStateLOG.appoDateLOG from AppoStateLOG inner join Appointments on Appointments.ident='" + ced + "' and Appointments.idAppo = AppoStateLOG.idAppo";
                request.getSession().setAttribute("instruccionCitaLOG", instruccionCitaLOG); //

                rd = request.getRequestDispatcher("/CancelCita.jsp");
                rd.forward(request, response);

            }

/**
 * Enters: Boolean value
 * Outs: Activate the function of cancel Appointment and change the state of apponitment
 * Restrictions: The value of the button need to be different of null
 */

            if (btValidarCancel != null) {

                String idCit = request.getParameter("idCitacion");
                String fecha2 = fechaCita(idCit);
                if (cancelCita(dateLocal, fecha2)) {

                    String instruCanCi = "update appoState set appoState='Canceled by Patient' where idAppo='" + idCit + "'";
                    consulta(instruCanCi);

                    String insAppoStateLOG = "insert into AppoStateLOG values('" + idCit + "','Canceled by Patient','" + dateLocal + "')";
                    consulta(insAppoStateLOG);

                    rd = request.getRequestDispatcher("/MenuPaciente.jsp");
                    rd.forward(request, response);
                }

                String mensaError = "THE APPOINTMENT CAN NOT BE CANCELED FALTANDO SOLO 2 DIAS";
                request.getSession().setAttribute("mensaError", mensaError); //
                rd = request.getRequestDispatcher("/pagErrores.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs:  Activate the function of canel appointment by a doctor
 * Restrictions: The value of the button need to be different of null
 */

            if (btCancelCitaFun != null) {

                String instruccionCitaFun = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo and AppoState.appoState='Registered' INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("instruccionCitaFun", instruccionCitaFun);

                rd = request.getRequestDispatcher("/cancelarCitaFun.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Check if the appointment can be canceled 
 * Restrictions: The value of the button need to be different of null
 */

            if (brValidarCancelCitaFun != null) {

                String idCite = request.getParameter("idCitFum");
                String fecha2 = fechaCita(idCite);
                if (cancelCita(dateLocal, fecha2)) {

                    String instruCanCiF = "update appoState set appoState='Canceled by MC' where idAppo='" + idCite + "'";
                    consulta(instruCanCiF);

                    String insAppoStateLOGF = "insert into AppoStateLOG values('" + idCite + "','Canceled by MC','" + dateLocal + "')";
                    consulta(insAppoStateLOGF);

                    String valid = (String) person.get(0);
                    String rol = tipoRol(valid);
                    if ("Doctor".equals(rol)) {
                        rd = request.getRequestDispatcher("/menu.jsp");
                        rd.forward(request, response);
                    }
                    if ("Nurse".equals(rol)) {
                        rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                        rd.forward(request, response);
                    }
                    if ("Secretary".equals(rol)) {
                        rd = request.getRequestDispatcher("/menSecre.jsp");
                        rd.forward(request, response);
                    }
                }

                String mensaError = "THE APPOINTMENT CAN NOT BE CANCELED FALTANDO SOLO 2 DIAS";
                request.getSession().setAttribute("mensaError", mensaError); //
                rd = request.getRequestDispatcher("/pagErrores.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Asign appointment 
 * Restrictions: The value of the button need to be different of null
 */

            if (btAsignaCita != null) {

                String instruccionCitaFunAsing = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo and AppoState.appoState='Canceled by MC' INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("instruccionCitaFunAsing", instruccionCitaFunAsing);

                rd = request.getRequestDispatcher("/asignarCita.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Activate the function of the creation of the new appointment
 * Restrictions: The value of the button need to be different of null
 */
            if (btcodeAsing != null) {

                String idCite = request.getParameter("codigoCita");

                String instruCanCiF = "update appoState set appoState='Assigned' where idAppo='" + idCite + "'";
                consulta(instruCanCiF);

                String insAppoStateLOGF = "insert into AppoStateLOG values('" + idCite + "','Assigned','" + dateLocal + "')";
                consulta(insAppoStateLOGF);

                String valid = (String) person.get(0);
                String rol = tipoRol(valid);
                if ("Doctor".equals(rol)) {
                    rd = request.getRequestDispatcher("/menu.jsp");
                    rd.forward(request, response);
                }
                if ("Nurse".equals(rol)) {
                    rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                    rd.forward(request, response);
                }
                if ("Secretary".equals(rol)) {
                    rd = request.getRequestDispatcher("/menSecre.jsp");
                    rd.forward(request, response);
                }
                rd = request.getRequestDispatcher("/menu.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Activate the function keep appointment
 * Restrictions: The value of the button need to be different of null
 */

            if (btAtenderCita != null) {

                String citasHospi = "select distinct Appointments.idAppo, AppoDates.appoDate, AppoDates.appoHour, HealthArea.area, AppoState.appoState  from Appointments INNER JOIN AppoState ON Appointments.idAppo = AppoState.idAppo INNER JOIN AppoDates ON Appointments.idAppo = AppoDates.idAppo and AppoState.appoState='Registered' or AppoState.appoState='Assigned' INNER JOIN HealthArea ON Appointments.idHArea = HealthArea.idHArea;";
                request.getSession().setAttribute("citasHospi", citasHospi);

                rd = request.getRequestDispatcher("/atenderPaciente.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Change the states in the corresponding place
 * Restrictions: The value of the button need to be different of null
 */

            if (btAtendiendo != null) {

                String idCite = request.getParameter("codigoCitaHospi");
                String ced = (String) person.get(0);

                String instruCanCiF = "update appoState set appoState='Made' where idAppo='" + idCite + "'";
                consulta(instruCanCiF);

                String insAppoStateLOGF = "insert into AppoStateLOG values('" + idCite + "','Made','" + dateLocal + "')";
                consulta(insAppoStateLOGF);

                String diag = request.getParameter("codigoDiag");
                String trata = request.getParameter("codigoTrata");
                String observa = request.getParameter("observacion");
                String idPati = idePaci(idCite);

                String instru = "insert into AttendedAppo values('" + idPati + "','" + ced + "' ,'" + diag + "','" + trata + "','" + observa + "')";
                consulta(instru);

                rd = request.getRequestDispatcher("/menu.jsp");
                rd.forward(request, response);

            }

/**
 * Enters: Boolean value
 * Outs: Fill the information of the hospitalized person
 * Restrictions: The value of the button need to be different of null
 */
            if (btInternar != null) {

                String ced = (String) person.get(0);
                String idCentro = request.getParameter("idCentro");
                String idArea = request.getParameter("idArea");
                String idDiag = request.getParameter("idDiag");
                String idTrata = request.getParameter("idTrata");
                String cedPati = request.getParameter("ceduPatient");
                String fechaIni = request.getParameter("fechaInicio");
                String fechaFin = request.getParameter("fechaFin");
                String observaH = request.getParameter("observaHospi");

                String instruR = "insert into RegisterTrack values('" + idRegistro + "','" + ced + "','" + idTrata + "','" + dateLocal + "','" + observaH + "');";
                consulta(instruR);

                String instruH = "insert into Hospitalized values('" + cedPati + "','" + ced + "','" + idCentro + "','" + idArea + "','" + idDiag + "','" + idTrata + "','" + idRegistro + "')";
                consulta(instruH);

                String datesH = "insert into HospitalDates values('" + cedPati + "','" + fechaIni + "','" + fechaFin + "')";
                consulta(datesH);

                idRegistro++;
                rd = request.getRequestDispatcher("/menu.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs: Open a new jsp
 * Restrictions: The value of the button need to be different of null
 */
            if (btHospi != null) {

                rd = request.getRequestDispatcher("/Hospitalizados.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs: Update the register tracker
 * Restrictions: The value of the button need to be different of null
 */
            if (btUpdateRegi != null) {

                String ced = (String) person.get(0);
                String idRegi = request.getParameter("idRegiP");
                int tmp = Integer.parseInt(idRegi);
                tmp = tmp + 1;
                String idRegiP = Integer.toString(tmp);
                String dateRegi = request.getParameter("dateRegi");
                String treatRegi = request.getParameter("treatRegi");
                String observaRegi = request.getParameter("observaRegi");

                String instruc = "insert into RegisterTrack values('" + idRegiP + "','" + ced + "','" + treatRegi + "','" + dateRegi + "','" + observaRegi + "')";
                consulta(instruc);

                String valid = (String) person.get(0);
                String rol = tipoRol(valid);

                if ("Doctor".equals(rol)) {
                    rd = request.getRequestDispatcher("/menu.jsp");
                    rd.forward(request, response);
                }
                if ("Nurse".equals(rol)) {
                    rd = request.getRequestDispatcher("/menuEnfermero.jsp");
                    rd.forward(request, response);
                }
                if ("Secretary".equals(rol)) {
                    rd = request.getRequestDispatcher("/menSecre.jsp");
                    rd.forward(request, response);
                }
            }            /**
 * Enters: Boolean value
 * Outs:  Execute  the CRUD opeations in the medical center table
 * Restrictions: The value of the button need to be different of null
 */

            if (crudMC != null) {

                rd = request.getRequestDispatcher("/crudCM.jsp");
                rd.forward(request, response);

            }

            /**
 * Enters: Boolean value
 * Outs:  Create a medical center
 * Restrictions: The value of the button need to be different of null
 */
            if (btCreateMC != null) {

                String codeMC = request.getParameter("codeMC");
                String nameMC = request.getParameter("nameMC");
                String capacity = request.getParameter("capacity");
                String provi = request.getParameter("provi");
                String canton = request.getParameter("canton");
                String distri = request.getParameter("distri");
                String typeCen = request.getParameter("typeCen");

                String instru1 = "insert into AttentionCenter values ('" + codeMC + "','" + nameMC + "','" + capacity + "')";
                consulta(instru1);

                String instru2 = "insert into CenterTypes values ('" + codeMC + "','" + typeCen + "')";
                consulta(instru2);

                String instru3 = "insert into CenterUbication values('" + codeMC + "','" + provi + "','" + canton + "','" + distri + "');";
                consulta(instru3);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Delete a medical center
 * Restrictions: The value of the button need to be different of null
 */
            if (btDeleteMC != null) {

                String codeDelete = request.getParameter("codeDelete");

                String instruD = "delete from AttentionCenter where idAttCen='" + codeDelete + "'";
                consulta(instruD);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  execute he CRUD operations in Diagnosis
 * Restrictions: The value of the button need to be different of null
 */

            if (crudDiag != null) {

                rd = request.getRequestDispatcher("/crudDiag.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs: Create a new diagnosis
 * Restrictions: The value of the button need to be different of null
 */
            if (createDiag != null) {

                String codeDiag = request.getParameter("codeDiag");
                String nameDiag = request.getParameter("nameDiag");
                String levelDiag = request.getParameter("levelDiag");
                String observaD = request.getParameter("observaD");

                String instru1 = "insert into Diagnosis values('" + codeDiag + "','" + nameDiag + "','" + levelDiag + "','" + observaD + "');";
                consulta(instru1);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Delete a medical center
 * Restrictions: The value of the button need to be different of null
 */


            if (deleteDiag != null) {

                String codeDelete = request.getParameter("diagDelete");

                String instruD = "delete from Diagnosis where idDiag='" + codeDelete + "'";
                consulta(instruD);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Execute teh CRUD operations in Health Areas
 * Restrictions: The value of the button need to be different of null
 */

            if (crudArea != null) {

                rd = request.getRequestDispatcher("/crudArea.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Create a new health area
 * Restrictions: The value of the button need to be different of null
 */
            if (btCrearArea != null) {

                String codeArea = request.getParameter("codeArea");
                String nameArea = request.getParameter("nameArea");

                String instru1 = "insert into HealthArea values('" + codeArea + "','" + nameArea + "');";
                consulta(instru1);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);
            }

           /**
 * Enters: Boolean value
 * Outs:  Delete a health area
 * Restrictions: The value of the button need to be different of null
 */
            if (btBorrarArea != null) {

                String codeDelete = request.getParameter("deleteArea");

                String instruD = "delete from HealthArea where idHArea='" + codeDelete + "'";
                consulta(instruD);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Execute the CRUD operations in Treatments
 * Restrictions: The value of the button need to be different of null
 */
            if (crudTrata != null) {

                rd = request.getRequestDispatcher("/crudTrata.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Create a new treatment
 * Restrictions: The value of the button need to be different of null
 */ 

            if (btCreaTreat != null) {

                String codeTreat = request.getParameter("codeTreat");
                String nombreTreat = request.getParameter("nombreTreat");
                String dosisTreat = request.getParameter("dosisTreat");
                String typeTreat = request.getParameter("typeTreat");
                String treatDiag = request.getParameter("treatDiag");

                String instru1 = "insert into InfoTreatment values('" + codeTreat + "','" + nombreTreat + "','" + dosisTreat + "','" + typeTreat + "');";
                consulta(instru1);

                String instru2 = "insert into Treatments values('" + codeTreat + "','" + treatDiag + "');";
                consulta(instru2);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }
            /**
 * Enters: Boolean value
 * Outs:  Delete a treatment
 * Restrictions: The value of the button need to be different of null
 */

            if (btDenleTe != null) {

                String codeDelete = request.getParameter("codeDenlete");

                String instruD = "delete from InfoTreatment where idTreat='" + codeDelete + "'";
                consulta(instruD);

                rd = request.getRequestDispatcher("/menSecre.jsp");
                rd.forward(request, response);

            }
/**
 * Enters: Boolean value
 * Outs:  Generate a report with appointmens with a specific id
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoCita != null) {

                String ced = (String) person.get(0);

                String reporticoCitas = "select distinct AppoDates.appoDate, AppoState.appoState, HealthArea.area from Appointments inner join HealthArea on Appointments.idHarea= HealthArea.idHArea inner join AppoDates on Appointments.idAppo = AppoDates.idAppo inner join AppoState on Appointments.idAppo = AppoState.idAppo inner join Patient ON Appointments.ident = '" + ced + "'";
                request.getSession().setAttribute("reporticoCitas", reporticoCitas);

                rd = request.getRequestDispatcher("/CitasPacientesR.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs:  Generate a report with diagnosis with a specific id
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoDiagno != null) {

                String ced = (String) person.get(0);

                String reporticoDiagno = "select distinct AppoDates.appoDate, diagnosis.leveld, diagnosis.named from attendedAppo inner join patient on attendedAppo.ident= '" + ced + "' inner join diagnosis on attendedAppo.iddiag = diagnosis.iddiag inner join AppoDates on AppoDates.appoDate is not null";
                request.getSession().setAttribute("reporticoDiagno", reporticoDiagno);

                rd = request.getRequestDispatcher("/DiagnoPaciR.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs:  Generate a report with treatments with a specific id
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoTreat != null) {

                String ced = (String) person.get(0);

                String reporticoTrata = "select distinct AppoDates.appoDate, infoTreatment.namet, infoTreatment.typet from hospitalized inner join patient on '" + ced + "' = hospitalized.ident inner join infoTreatment on infoTreatment.idtreat= hospitalized.idtreat inner join AppoDates on AppoDates.appoDate is not null";
                request.getSession().setAttribute("reporticoTrata", reporticoTrata);

                rd = request.getRequestDispatcher("/TrataPaciR.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs:  Generate a report with hospitalized person with a specific id
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoHospi != null) {

                String ced = (String) person.get(0);

                String reporticoHospi = "select distinct attentionCenter.namecen, functionary.namefun, functionary.firstlastn, HealthArea.area, diagnosis.named, infoTreatment.namet, hospitaldates.entrydate, hospitaldates.exitdate, registertrack.observ from hospitalized inner join patient on '" + ced + "' = hospitalized.ident inner join attentionCenter on attentionCenter.idattcen = hospitalized.idattcen inner join functionary on functionary.idfun = hospitalized.idfun inner join HealthArea on HealthArea.idharea = hospitalized.idharea inner join diagnosis on diagnosis.iddiag = hospitalized.iddiag inner join infoTreatment on infoTreatment.idtreat = hospitalized.idtreat inner join hospitaldates on hospitaldates.ident = hospitalized.ident inner join registertrack on registertrack.idregi = hospitalized.idregi";
                request.getSession().setAttribute("reporticoHospi", reporticoHospi);

                rd = request.getRequestDispatcher("/HospiPaciR.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs:  Generate a report with Appointments and Diagnosis
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoCitasD != null) {

                rd = request.getRequestDispatcher("/CitasFuncionarioR.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs:  Generate a report with diagnosis and patients
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoDiangoD != null) {

                rd = request.getRequestDispatcher("/DiagnoFuncionarioR.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Generate a report with treatments and diagnosis
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoTreatD != null) {

                rd = request.getRequestDispatcher("/TrataFuncionarioR.jsp");
                rd.forward(request, response);
            }/**
 * Enters: Boolean value
 * Outs:  Generate a report with Appointments and Functionary 
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoDCC != null) {

                rd = request.getRequestDispatcher("/CCitasFuncionarioR.jsp");
                rd.forward(request, response);
            }/**
 * Enters: Boolean value
 * Outs:  Generate a report with Diagnosis and Treatments
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoDCD != null) {

                rd = request.getRequestDispatcher("/CDiagnoFuncionarioR.jsp");
                rd.forward(request, response);
            }/**
 * Enters: Boolean value
 * Outs:  Generate a report with treaments and functionary 
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoDCT != null) {

                rd = request.getRequestDispatcher("/CTrataFuncionarioR.jsp");
                rd.forward(request, response);
            }/**
 * Enters: Boolean value
 * Outs:  Generate a report with functionary and helathArea
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoHospiD != null) {

                rd = request.getRequestDispatcher("/HospiFuncionarioR.jsp");
                rd.forward(request, response);
            }/**
 * Enters: Boolean value
 * Outs:  Generate a report with new appointments
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoCitasN != null) {

                rd = request.getRequestDispatcher("/CitasFuncionarioR.jsp");
                rd.forward(request, response);
            }
/**
 * Enters: Boolean value
 * Outs:  Generate a report with Diagnosis 
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoDiangoN != null) {

                rd = request.getRequestDispatcher("/DiagnoFuncionarioR.jsp");
                rd.forward(request, response);
            }/**
 * Enters: Boolean value
 * Outs:  Generate a report with Treatments
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoTreatN != null) {

                rd = request.getRequestDispatcher("/TrataFuncionarioR.jsp");
                rd.forward(request, response);
            }
            /**
 * Enters: Boolean value
 * Outs:  Generate a report with functionary and appointments
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoNCC != null) {

                rd = request.getRequestDispatcher("/CCitasFuncionarioR.jsp");
                rd.forward(request, response);
            }
                        /**
 * Enters: Boolean value
 * Outs:  Generate a report with Diagnosis and functionary
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoNCD != null) {

                rd = request.getRequestDispatcher("/CDiagnoFuncionarioR.jsp");
                rd.forward(request, response);
            }
                        /**
 * Enters: Boolean value
 * Outs:  Generate a report with Treatments and Functionary
 * Restrictions: The value of the button need to be different of null
 */
            if (btRepoNCT != null) {

                rd = request.getRequestDispatcher("/CTrataFuncionarioR.jsp");
                rd.forward(request, response);
            }
                        /**
 * Enters: Boolean value
 * Outs:  Create a report with the hospitals
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoHospiN != null) {

                rd = request.getRequestDispatcher("/HospiFuncionarioR.jsp");
                rd.forward(request, response);
            }
                        /**
 * Enters: Boolean value
 * Outs:  Create a report with Appointments
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoCitasS != null) {

                rd = request.getRequestDispatcher("/CitasFuncionarioR.jsp");
                rd.forward(request, response);
            }
                        /**
 * Enters: Boolean value
 * Outs:  Generate a report of Functionary
 * Restrictions: The value of the button need to be different of null
 */
            if (btrepoHospiS != null) {

                rd = request.getRequestDispatcher("/HospiFuncionarioR.jsp");
                rd.forward(request, response);
            }

            out.println("</body>");
            out.println("</html>");

        }

    }
                        /**
 * Enters: String 
 * Outs:  Generate the conection with the database
 * Restrictions: Dont can be a null value
 */
    public void consulta(String data) {

        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = data;
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                System.out.println("---Executing Query :v---");
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

    }
                        /**
 * Enters:String with the id
 * Outs:  Array with the informatoin of the person 
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public ArrayList consultaTSE(String ident) {
        person.clear();

        String url = "jdbc:postgresql://localhost:5432/TSE";
        String user = "postgres";
        String pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = "select * from habitantes where cedula='" + ident + "'";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                String id = result.getString(1);
                String name = result.getString(2);
                String fLast = result.getString(3);
                String sLast = result.getString(4);
                person.add(id);
                person.add(name);
                person.add(fLast);
                person.add(sLast);

            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return person;
    }
                        /**
 * Enters: String ident
 * Outs:  Generate a provinceof the person
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public String findProvincia(String ident) {
        String provincia = null;

        if (ident.startsWith("1")) {
            provincia = "SAN JOSE";
        }
        if (ident.startsWith("2")) {
            provincia = "ALAJUELA";
        }
        if (ident.startsWith("3")) {
            provincia = "CARTAGO";
        }
        if (ident.startsWith("4")) {
            provincia = "HEREDIA";
        }
        if (ident.startsWith("5")) {
            provincia = "GUANACASTE";
        }
        if (ident.startsWith("6")) {
            provincia = "PUNTARENAS";
        }
        if (ident.startsWith("7")) {
            provincia = "LIMON";
        }
        if (ident.startsWith("8")) {
            provincia = "EXTRANJERO";
        }
        if (ident.startsWith("9")) {
            provincia = "EXTRANJERO";
        }

        return provincia;
    }
                        /**
 * Enters: String idend
 * Outs:  Boolean value
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public boolean esPaciente(String a) {
        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = "select * from Patient where ident='" + a + "'";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                return true;
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return false;
    }
                        /**
 * Enters: String ident
 * Outs:  Boolean value
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public boolean esFunct(String a) {
        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = "select * from Functionary where idFun='" + a + "'";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                return true;
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return false;
    }

                            /**
 * Enters: String value
 * Outs:  Generate a phone number
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public String telP(String ident) {
        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";
        String tel = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = "select tel from PatientTel where ident='" + ident + "'";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                tel = result.getString(1);
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return tel;
    }
                        /**
 * Enters: String eith the date 1 and the date 2
 * Outs:  Boolean value
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public boolean cancelCita(String a, String b) {
        String[] uno = a.split("/");
        String[] dos = b.split("/");
        int d1 = Integer.parseInt(uno[0]);
        int d2 = Integer.parseInt(dos[0]);
        int m1 = Integer.parseInt(uno[1]);
        int m2 = Integer.parseInt(dos[1]);
        if (m2 - m1 > 0) {
            return true;
        }
        if (d2 - d1 < 3) {
            return false;
        }
        return true;
    }
                        /**
 * Enters: String ident
 * Outs:  Generate a string with the date of the appointment
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public String fechaCita(String ident) {
        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";
        String fecha = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = "select appoDate from AppoDates where idAppo='" + ident + "'";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                fecha = result.getString(1);
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return fecha;
    }
                        /**
 * Enters: String idAppointment 
 * Outs:  identification of the patientn
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public String idePaci(String ident) {
        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";
        String fecha = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = "select ident from Appointments where idAppo='" + ident + "'";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                fecha = result.getString(1);
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return fecha;
    }
                        /**
 * Enters: String idetn
 * Outs:  String with the rol of the person
 * Restrictions: The value of the button need to be different of null
 */
    //------------------------------------------------------------------------//
    public String tipoRol(String ident) {
        String url = "jdbc:postgresql://localhost:5432/HospitalTEC";
        String user = "postgres";
        String pass = "1234";
        String rol = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();
            String sql = "select typeFun from InfoFuncType where idFunType=(select distinct FuncType.idFunType from FuncType inner join Functionary on '" + ident + "'=FuncType.idFun)";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                rol = result.getString(1);
            }
            result.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return rol;

    }

    public static String dateLocal = "27/07/2020";
    public static int idCita = 2;
    public static int idRegistro = 2;
}
