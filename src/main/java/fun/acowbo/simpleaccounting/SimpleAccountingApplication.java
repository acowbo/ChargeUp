package fun.acowbo.simpleaccounting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="https://acowbo.fun">acowbo</a> * @date 2023/12/26
 */
@SpringBootApplication
@MapperScan("fun.acowbo.simpleaccounting.mapper")
public class SimpleAccountingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleAccountingApplication.class, args);
    }
}
