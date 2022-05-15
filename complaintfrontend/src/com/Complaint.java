package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class Complaint {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem( String i_type, String message, String status) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into complaint (`c_id`,`i_type`,`message`,`status`)"
                    + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, i_type);
			 preparedStmt.setString(3, message);
			 preparedStmt.setString(4, status);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newcomplaint = readcomplaint();
			output = "{\"status\":\"success\", \"data\": \"" + newcomplaint + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the complaint.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readcomplaint() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output =  "<table border='1'><tr><th>Complaint ID</th><th>Inquiry type</th><th>Message</th><th>Status <th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from complaint";

			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				 String CID = Integer.toString(rs.getInt("c_id"));
				 String IType = rs.getString("i_type");
				 String Message = rs.getString("message");
				 String Status = rs.getString("status");
				 
				// Add into the html table
				output += "<tr><td><input id=\'hidProjectIDUpdate\' name=\'hidProjectIDUpdate\' type=\'hidden\' value=\'"
						+ CID  + "</td>";
				output += "<td>" +IType + "</td>";
				output += "<td>" + Message + "</td>";
				output += "<td>" +Status + "</td>";

				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success' data-userid='" + CID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + CID + "'></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updateProject(int cid, String itype, String message, String status) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE complaint SET i_type=?,message=?,status=? WHERE c_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			 preparedStmt.setString(1, itype);
			 preparedStmt.setString(2, message);
			 preparedStmt.setString(3, status);
			 preparedStmt.setInt(4, cid);
			 

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newcomplaint = readcomplaint();
			output = "{\"status\":\"success\", \"data\": \"" + newcomplaint + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating .\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteProject(String c_id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from complaint where c_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1,Integer.parseInt( c_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newcomplaint = readcomplaint();
			output = "{\"status\":\"success\", \"data\": \"" + newcomplaint + "\"}";
		} catch (Exception e) {
			output = "Error while deleting.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
