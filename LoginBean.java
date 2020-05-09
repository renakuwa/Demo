
public class LoginBean {
	
	
	private String username;
	private String password;
	
	
	//getはデータを取ってくる時　setはデータを設定したい時に使う
	
	public String getUsername() {//getUsernameオブジェクトをreturnでusernameに格納
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		//thisは　このクラスで宣言した変数ですよ、という意味
		
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
