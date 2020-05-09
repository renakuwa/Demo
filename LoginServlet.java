

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//index.jspの入力データを取得しusername変数に格納
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		
		//まず入力されているかどうか判断
		if(username.isEmpty() && password.isEmpty()) {//名前もパスワードも未入力の時
			//jspファイルにエラーメッセージを表示させる
			request.setAttribute("errorMsg", "名前が入力されていません。<br>パスワードが入力されていません。");
			//ログイン画面に戻る
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if(username.isEmpty()) {//名前未入力時
			//エラーメッセージを表示
			//エラーメッセージの内容をerrorに格納
			String error = "名前が入力されていません。";
			//jspファイルに表示させる　　errorMsgはデータ名
			request.setAttribute("errorMsg", error);
			//ログイン画面に戻る
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);	
		}
		else if(password.isEmpty()) {//パスワード未入力時
			//エラーメッセージを表示
			String error2 = "パスワードが入力されていません。";
			//jspファイルに表示させる
			request.setAttribute("errorMsg", error2);
			//ログイン画面に戻る
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);	
		}
		else {//全部入力されている時
		System.out.println(username + "\t" + password);
		
		//LoginBeanオブジェクトをuserに格納
		LoginBean user = new LoginBean();
		//username変数に、userに格納した情報をセットする
		user.setUsername(username);
		user.setPassword(password);
		
		//LoginDAOオブジェクト  データベース接続のオブジェクト
		LoginDAO loginDao = new LoginDAO();
		
		try {
			System.out.println(loginDao.check(user));
			if(loginDao.check(user)) {//userに格納した、LoginBeanで設定したデータをデータベース照合して成功したら次ページを表示
				request.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			}
			else {//入力した情報がDBと不一致の場合
				String error3 = "名前またはパスワードが間違っています。";
				request.setAttribute("errorMsg", error3);
				request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	  }
	}
}
