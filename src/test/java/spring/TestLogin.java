package spring;

import com.alibaba.fastjson.JSON;
import com.ebrun.holiday.model.Employee;
import com.ebrun.holiday.service.LoginService;
import com.ebrun.holiday.util.Constant;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by DaoDao on 2015/5/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestLogin {
    private static final Logger LOGGER = Logger.getLogger(TestMyBatis.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    public LoginService getLoginService() {
        return loginService;
    }
    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    @Test
    public void test1(){
        Employee employee = loginService.login("admin@ebrun.com","1234");
        if(employee==null){
            LOGGER.info("找不到这个employee");
        }
        LOGGER.info(JSON.toJSONStringWithDateFormat(employee, Constant.DATE_FORMAT_YMDHMS));
    }
}
