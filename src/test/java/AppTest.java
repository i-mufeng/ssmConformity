import com.mufeng.dao.StudentDao;
import com.mufeng.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author MFGN
 * @data 2022/3/21 16:30
 * @description 测试
 */
//junit提供，可以运行指定环境
/*SpringRunner.class是SpringJUnit4ClassRunner别名
配合Junit为其提供Spring上下文环境*/
@RunWith(SpringRunner.class)

/*Spring-test指定Spring配置文件或者配置类位置的注解*/
@ContextConfiguration(locations = "classpath:applicationContext.xml")
/*确定是Web环境，用来指定webapp目录位置*/
@WebAppConfiguration
public class AppTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void testStartUp() {
        Student student = studentDao.queryById(1);
        System.out.println(student);

    }
}
