package com.zensar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/extra_assignments_practise",
					"root", "Saketh1@");
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
			
			//"insert into emp values(?,?,?)"

			String query = "insert into emp_copy values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			while (rs.next()) {

				pstmt.setInt(1, rs.getInt(1));
				pstmt.setString(2, rs.getString(2));
				pstmt.setString(3, rs.getString(3));
				pstmt.setDouble(4, rs.getDouble(4));

				pstmt.executeUpdate();

			}
//				System.out.println(
//						rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getDouble(4));
			pstmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
