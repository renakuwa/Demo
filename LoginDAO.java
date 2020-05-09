//SQL用のインポート

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class LoginDAO {//データベースアクセスオブジェクト
	
	//入力データがデータベースと一致するかチェックするメソッド
	public boolean check(LoginBean user) throws ClassNotFoundException{
		boolean status = false;
		
		//mySQLに接続させるための文
		Class.forName("com.mysql.jdbc.Driver");
		
		try(//データベースと接続する　　　Demoはデータベース名
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo?serverTimezone=UTC", "root", "GReeeeN2!");
		//データベースのuserinfoテーブルからusernameとpasswordを取り出す
		PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where username = ? and password = ?")) {
			
			//取り出した情報DBに入力情報を文字列としてセットする
			//１はテーブル内のusername,2はpassword
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			System.out.println(preparedStatement);
			
			//DBと照合した結果を格納　　　　　　　SQL文を実行するメソッド
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();//次のテーブルを
		}
		catch(SQLException e) {
			System.out.println("No:" + e.getMessage());
		}
		return status;
	}
	
}
