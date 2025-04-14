package fun.acowbo.simpleaccounting;

import fun.acowbo.simpleaccounting.service.impl.SysUserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="https://acowbo.fun">acowbo</a> * @date 2023/12/26
 */
@SpringBootApplication
@MapperScan("fun.acowbo.simpleaccounting.mapper")
public class SimpleAccountingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAccountingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDefaultUser(SysUserServiceImpl userService) {
        return args -> userService.initializeDefaultUser();
    }
}
