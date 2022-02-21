import com.bjpowernode.controllers.ActionController;
import com.bjpowernode.exceptions.LoginException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyTest {

    @Test
    public void Test() throws SQLException, LoginException {
        ApplicationContext ac=null;
        ac=new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
        DataSource dataSource=ac.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());

    }

}
