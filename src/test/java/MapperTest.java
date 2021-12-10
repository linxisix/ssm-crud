import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;


/**
 * 测试dao层
 * 推荐使用spring的项目就可以使用spring单元测试，可以注入我们需要的组件
 * 导入springTest包
 *
 * @ContextConfiguration指定spring配置文件位置
 *
 * 使用autoWire自动注入
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {


    @Autowired
     DepartmentMapper departmentMapper;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){

//        //1、创建Spring IOC容器
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2、从容器中获取mapper
//
//        DepartmentMapper departmentMapper = ioc.getBean(DepartmentMapper.class);
//        EmployeeMapper employeeMapper = ioc.getBean(EmployeeMapper.class);

//        //插入部门
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"技术部"));
//        //插入员工
//        employeeMapper.insertSelective(new Employee(null,"小王","1","123@qq.com",1,new Department()));

        //批量插入
        EmployeeMapper mapper = sqlSessionTemplate.getMapper(EmployeeMapper.class);

        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            String sex;
            double v = Math.random() * 10;
            if (v>5){
                sex = "男";
            }else {
                sex = "女";
            }
            mapper.insertSelective(new Employee(null,uid,sex,uid+"@guigu.com",1));

        }
        System.out.println("success");


    }


}
