package libai.aliyun.fileroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("libai.aliyun.fileroom.mapper")
@SpringBootApplication
public class FileroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileroomApplication.class, args);
	}

}
