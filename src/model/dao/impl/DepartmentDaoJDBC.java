package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			st = conn.prepareStatement(query);
			st.setString(1, obj.getName());
			int linhasAfetadas = st.executeUpdate();
			if( linhasAfetadas > 0 ) {
				System.out.println("Insert realizado com sucesso!!!");
			}else {
				throw new DbException("Houve um problema na inserção deste registro");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
