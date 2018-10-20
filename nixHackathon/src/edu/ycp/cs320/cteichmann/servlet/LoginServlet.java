package edu.ycp.cs320.cteichmann.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.cteichmann.controller.Controller;
import edu.ycp.cs320.cteichmann.model.Model;
import edu.ycp.cs320.cteichmann.model.LoginModel;
import edu.ycp.cs320.cteichmann.persist.DerbyDatabase;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("account Servlet: doGet");
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Login Servlet: doPost");
		String errorMessage = null;
		Boolean validAccount = false;
		Model model = new Model();
		LoginModel model = new LoginModel();
		DerbyDatabase db = new DerbyDatabase(); 
		// Decode form parameters and dispatch to controller
		if(req.getParameter("SignIn") !=null) {
			System.out.print("sign in pressed");
			model.setUserName(req.getParameter("signinUsername"));
			model.setPassword(req.getParameter("signinPassword"));
			if (model.getUserName() == null || model.getPassword() == null || model.getUserName().equals("") || model.getPassword().equals("")) {
				errorMessage = "Please specify both user name and password";
			} else {
				try {
					validAccount = db.ValidateAccountByUserNameAndPassword(model.getUserName(), model.getPassword());
					System.out.println(validAccount);
				} catch (SQLException e) {
					e.printStackTrace();
					errorMessage = "Issue Finding Account";
				}
			}
		}
		else if(req.getParameter("SignUp") !=null) {
			db = new DerbyDatabase();
			model.setUserName(req.getParameter("signupUsername"));
			model.setPassword(req.getParameter("signupPassword"));
			model.setFavoriteID(model.getFavoriteName(req.getParameter("signupFavoriteName")));
			if (model.getUserName() == null || model.getPassword() == null || model.getUserName().equals("") || model.getPassword().equals("")) {
				errorMessage = "Please specify both user name and password";
			} else{
				System.out.println("   Username: <" + model.getUserName() + "> Password: <" + model.getPassword() + ">");	
				if(model.getPassword().equals(req.getParameter("ConfirmPassword"))){
					try {
						validAccount = db.AddAccountIntoAccountTable(model.getUserName(), model.getPassword(), model.getFavoriteID());
						validAccount = true;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (validAccount == true) {
			System.out.println("Success- Redirecting to /overwatch");
			// store user object in session
			req.getSession().setAttribute("user", model.getUserName());
			resp.sendRedirect(req.getContextPath() +"/overwatch");
			req.getRequestDispatcher("/_view/overwatch.jsp").forward(req, resp);
			return;
		}
		else{
			errorMessage = "Username and/or password invalid";
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			System.out.println("Invalid account - redirecting to /login");
			return;
		}
	}
}
/*</style>
<div id="Background-Body">
  <script>
//San Fransisco Shock	 		document.body.style.backgroundColor = "#fc4c02";
//Boston Uprising 				document.body.style.backgroundColor = "174b97";
//Florida Mayhem 					document.body.style.backgroundColor = "#af272f";
//Houston Outlaws  				document.body.style.backgroundColor = "#96ca4f";
//Philadelphia Fusion  		document.body.style.backgroundColor = "#f99d2a";
//London Spitfire 				document.body.style.backgroundColor = "#59cbe8";
//Seoul Dynasty  					document.body.style.backgroundColor = "#ac8a00";
//Dallas Fuel  						document.body.style.backgroundColor = "#0072ce";
//Los Angeles Valiant  		document.body.style.backgroundColor = "#e5d456";
//New York Excillisor			document.body.style.backgroundColor = "#0f56ea";
//Shanghai Dragons				document.body.style.backgroundColor = "#d22630";
//Los Angeles Gladiators  document.body.style.backgroundColor = "#381460";

//document.body.style.backgroundColor = "#381460";
document.body.style.backgroundColor = "#0072ce";
</script>*/
