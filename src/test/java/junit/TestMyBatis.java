package junit;

import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DaoDao on 2015/5/11.
 */
public class TestMyBatis {
    private ApplicationContext ac;
    private EmployeeService employeeService;
    @Before
    public void before(){
        ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
        employeeService = (EmployeeService) ac.getBean("employeeService");
    }
    @Test
    public void test1(){
        Employee employee = employeeService.getEmployeeById(1);
        String name = employee.getName();
        System.out.println(name);
    }
}
