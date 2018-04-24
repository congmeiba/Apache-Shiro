package cn.gzsxt.shirospringboot;

        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.gzsxt.shirospringboot.mapper")
public class ShiroSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringBootApplication.class, args);
    }
}
