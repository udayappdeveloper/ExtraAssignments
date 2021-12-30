package com.zensar;

import java.io.*;
import java.sql.*;

public class ImageRetrieveDb {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/extra_assignments_practise",
					"root", "Saketh1@");
			File file = new File("C:\\Users\\UK64512\\Desktop\\IMAGETEST\\test_ret.png");
			FileOutputStream fos = new FileOutputStream(file);
			byte b[];
			Blob blob;
			PreparedStatement ps = con.prepareStatement("select * from image_table");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				blob = rs.getBlob("image");
				b = blob.getBytes(1, (int) blob.length());
				fos.write(b);
			}
			ps.close();
			fos.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
