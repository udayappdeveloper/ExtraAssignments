package com.zensar;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageDbTest {

	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/extra_assignments_practise",
					"root", "Saketh1@");
			File file = new File("C:\\Users\\UK64512\\Desktop\\IMAGETEST\\test.png");

			FileInputStream fis = new FileInputStream(file);
			PreparedStatement ps = con.prepareStatement("insert into image_table (name,image) values(?,?)");
			ps.setString(1, "image 1");
			ps.setBinaryStream(2, fis, (int) file.length());
			ps.executeUpdate();

			ps.close();
			fis.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
