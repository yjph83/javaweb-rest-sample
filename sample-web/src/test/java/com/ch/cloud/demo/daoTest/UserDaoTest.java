package com.ch.cloud.demo.daoTest;

import com.ch.cloud.demo.cache.RedisCacheConfig;
import com.ch.cloud.demo.dao.UserDAO;
import com.ch.cloud.demo.entity.UserDO;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 * 不构建整个Spring Context，只构建指定的Controller进行测试。需要对相关的依赖进行mock.@WebMvcTest(xxxController.class)
 * 时间:2019-08-02 14:21
 *
 * @author: xinghong.xiong
 */
@RunWith(SpringRunner.class) // 声明在Spring的环境中进行单元测试
//@MybatisTest  // 针对mybatis的持久层测试自于mybatis-spring-boot-starter-test项目，它是mybatis团队根据spring的习惯来实现的。
// spring还提供@DataJpaTest和@JdbcTest，分别对应JPA持久化方案和JDBC持久化方案。
// 此处的MybatisTest 不支持redis加载配置，故需要解决这个问题；
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserDaoTest {



    @Autowired
    RedisCacheConfig redisCacheConfig;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void test1_save() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setAge(36);
        userDO.setName("yjph83");
        userDO.setStatus(1);
        int result = userDAO.save(userDO);
        Assertions.assertThat(result).isEqualTo(1);
    }

}
