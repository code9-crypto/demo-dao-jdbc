package application;

import java.util.ArrayList;
import java.util.List;
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
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== 1º teste -> Insert ======");
		System.out.print("Qual departamento quer inserir? ");
		String dName = sc.nextLine();
		department.setName(dName);		
		departmentDao.insert(department);
		
		System.out.println();
		System.out.println("=== 2º teste -> Update ======");		
		System.out.print("Qual o ID do departmento? ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("Qual será o novo nome deste departmento? ");
		String newDepartment = sc.nextLine();
		department.setId(id);
		department.setName(newDepartment);
		departmentDao.update(department);
		
		System.out.println();
		System.out.println("=== 3º teste -> Delete ======");
		System.out.print("Digite o ID que deseja apagar: ");
		int idDel = sc.nextInt();
		departmentDao.deleteById(idDel);
		
		System.out.println();
		System.out.println("=== 4º teste -> Select por ID(findById) ======");
		System.out.print("Qual o ID do departmento que deseja encontrar: ");
		int idSel = sc.nextInt();
		department = departmentDao.findById(idSel);
		System.out.println(department);
		
		System.out.println();
		System.out.println("=== 5º teste -> Select (findAll) ======");
		List<Department> listDepartment = new ArrayList<>();
		listDepartment = departmentDao.findAll();
		for (Department dep : listDepartment) {
			System.out.println(dep);
		}
	}

}
