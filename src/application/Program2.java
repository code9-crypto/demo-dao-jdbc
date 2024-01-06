package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		//Instanciando a classe Scanner para receber dados do usuário
		Scanner sc = new Scanner(System.in);
		
		//Instanciando a classe department com o construtor vazio
		Department department = new Department();
		
		//Instanciando o método do createDepartmentDao por meio da classe DaoFactory
		//Para depois ter acesso aos métodos da classe DepartmentDaoJDBC
		DepartmentDao dDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== 1º teste -> Insert ======");
		System.out.print("Qual departamento quer inserir? ");
		String dName = sc.next();
		department.setName(dName);		
		dDao.insert(department);
	}

}
