package workplace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);

		//AddEmployee addEmployee = new AddEmployee("firstname", "lastname", "email@gmail.com", "123445", 122334.344, 1L);
		//GeneralUtil.checkRequiredProperties(addEmployee, Arrays.asList("firstName", "lastName", "email", "salary", "phone", "departmentId"));

		// Employee employee = new Employee();
		// employee = GeneralUtil.getCopyOf(addEmployee,employee);
		// System.out.println(employee);

		
	}

}
