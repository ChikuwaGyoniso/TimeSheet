package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.WorkTime;

public class ViewTimeSheetDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/sampleappdb?cahracterEncording=UTF-8";
	private final String DB_USER = "Sampleuser";
	private final String DB_PASS = "chikuwanoowari458";
	private final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	public List<WorkTime> findAll(WorkTime worktime) {
		List<WorkTime> list = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			Class.forName(DB_DRIVER);
			String sql = "SELECT * FROM timesheet WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, worktime.getUserId());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String userid = rs.getString("UserId");
				String date = rs.getString("date");
				Time start_time = rs.getTime("Start_Time");
				Time end_time = rs.getTime("End_Time");
				String work_contents = rs.getString("Work_Contents");
				Time nomal_time = rs.getTime("Nomal_Time");
				Time midnight_time = rs.getTime("Midnight_Time");
				Time holiday_time = rs.getTime("Hpliday_Time");
				Time holiday_midnight_time = rs.getTime("Holiday_Midnight_Time");
				Time worktime_sum = rs.getTime("WorkTime_Sum");
				worktime = new WorkTime(userid, date, start_time, end_time, work_contents,
						nomal_time, midnight_time, holiday_time, holiday_midnight_time, worktime_sum);
				list.add(worktime);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}