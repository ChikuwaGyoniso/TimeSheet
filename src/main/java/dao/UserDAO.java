package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.User;

public class UserDAO extends DataSourceManager {

	public User findByLogin(Login login) {
		User user = null;
		try {

			String sql = "SELECT USER_ID, PASS, MAIL, NAME FROM APPUSER WHERE USER_ID = ? AND PASS = SHA2(?,256)";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, login.getUserId());
			pstmt.setString(2, login.getPass());

			//SELECT文を実行し、結果を取得
			ResultSet rs = pstmt.executeQuery();

			//一致したユーザーがいた場合
			//そのユーザーを表すUserインスタンスを生成
			if (rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				user = new User(userId, pass, mail, name);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}

	public boolean RegisterUser(User user) {
		try {

			String sql = "INSERT INTO APPUSER (USER_ID, PASS, MAIL, NAME) VALUES(?,SHA2(?,256),?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			//Insert文中の「？」も使用する値を設定しSQLを完成
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPass());
			pstmt.setString(3, user.getMail());
			pstmt.setString(4, user.getName());

			//resultには追加された行数が代入される
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;

	}
}
