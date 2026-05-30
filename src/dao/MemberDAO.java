//package dao;
//
//public class MemberDAO {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}

//This file handles checking user credentials when logging in and saving new users to the database.
package dao;

import db.DBConnection;
import model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO{
	public boolean deactivateMember(String memId) {

	    String query =
	        "UPDATE member SET status='INACTIVE' WHERE mem_id=?";

	    try(Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, memId);

	        return ps.executeUpdate() > 0;

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean updateMember(Member member) {

	    String query =
	        "UPDATE member SET name=?,email=?,password=?,role=?,status=?,phone=? WHERE mem_id=?";

	    try(Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, member.getName());
	        ps.setString(2, member.getEmail());
	        ps.setString(3, member.getPassword());
	        ps.setString(4, member.getRole());
	        ps.setString(5, member.getStatus());
	        ps.setString(6, member.getPhone());
	        ps.setString(7, member.getMemId());

	        return ps.executeUpdate() > 0;

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public List<Member> getAllMembers() {

	    List<Member> members = new ArrayList<>();

	    String query = "SELECT * FROM member";

	    try(Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query);
	        ResultSet rs = ps.executeQuery()) {

	        while(rs.next()) {

	            Member member = new Member();

	            member.setMemId(rs.getString("mem_id"));
	            member.setName(rs.getString("name"));
	            member.setEmail(rs.getString("email"));
	            member.setPassword(rs.getString("password"));
	            member.setRole(rs.getString("role"));
	            member.setStatus(rs.getString("status"));
	            member.setPhone(rs.getString("phone"));
	            member.setCreatedAt(rs.getTimestamp("created_at"));

	            members.add(member);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return members;
	}
	
	public Member getMemberById(String memId) {

	    String query =
	        "SELECT * FROM member WHERE mem_id=?";

	    try(Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, memId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()) {

	            Member member = new Member();

	            member.setMemId(rs.getString("mem_id"));
	            member.setName(rs.getString("name"));
	            member.setEmail(rs.getString("email"));
	            member.setPassword(rs.getString("password"));
	            member.setRole(rs.getString("role"));
	            member.setStatus(rs.getString("status"));
	            member.setPhone(rs.getString("phone"));
	            member.setCreatedAt(rs.getTimestamp("created_at"));

	            return member;
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public boolean addMember(Member member) {

	    String query =
	        "INSERT INTO member(mem_id,name,email,password,role,status,phone) VALUES(?,?,?,?,?,?,?)";

	    try(Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, member.getMemId());
	        ps.setString(2, member.getName());
	        ps.setString(3, member.getEmail());
	        ps.setString(4, member.getPassword());
	        ps.setString(5, member.getRole());
	        ps.setString(6, member.getStatus());
	        ps.setString(7, member.getPhone());

	        return ps.executeUpdate() > 0;

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
}