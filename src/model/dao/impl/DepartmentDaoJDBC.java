package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			String query = "insert into department (Name) values (?)";
			st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			int linhasInsert = st.executeUpdate();
			if( linhasInsert > 0 ) {
				System.out.println("Insert realizado com sucesso!!!");
				ResultSet rs = st.getGeneratedKeys();
				while( rs.next() ) {
					int id = rs.getInt(1);
					System.out.println("Seguinte ID foi inserido: " + id);
				}
			}else {
				throw new DbException("Houve um problema na inserção deste registro");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(null);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			String query = "update department set Name = ? where Id = ?";
			st = conn.prepareStatement(query);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			int linhasUpdate = st.executeUpdate();
			if( linhasUpdate > 0 ) {
				System.out.println("Update realizado com sucesso!!!");
			}else {
				throw new DbException("Houve um problema na atualização deste registro");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;		
		try {
			String query = "delete from department where Id = ?";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			int linhasDelete = st.executeUpdate();
			if( linhasDelete > 0 ) {
				System.out.println("Delete realizado com sucesso!!!");
			}else {
				throw new DbException("Houve um problema na deleção deste ID");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String query = "select Id, Name from department where Id = ?";			
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if( rs.next() ) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}else {
				throw new DbException("Houve um problema no retorno desses dados");
			}			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		Statement st = null;
		ResultSet rs = null;
		List<Department> listDep = new ArrayList<>();
		try {
			st = conn.createStatement();
			String query = "select Id, Name from department";
			rs = st.executeQuery(query);
			while( rs.next() ) {
				listDep.add(new Department(rs.getInt("Id"), rs.getString("Name")));
			}
			return listDep;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

}
