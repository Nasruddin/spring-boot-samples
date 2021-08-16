package starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class RedisSpringBootApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

    public static void main(String[] args) {
        SpringApplication.run(RedisSpringBootApplication.class, args);
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register(RedisSpringBootApplication.class);
        ctx.refresh();

        StringRedisTemplate stringRedisTemplate  = ctx.getBean(StringRedisTemplate.class);
        // Using set to set value
        stringRedisTemplate.opsForValue().set("N", "Nasir");
        stringRedisTemplate.opsForValue().set("A", "Asmit");
        //Fetch values from set
        System.out.println(stringRedisTemplate.opsForValue().get("N"));
        System.out.println(stringRedisTemplate.opsForValue().get("A"));
        //Using Hash Operation
        String zahir = "Zahir";
        stringRedisTemplate.opsForHash().put("Z", String.valueOf(zahir.hashCode()), zahir);
        System.out.println(stringRedisTemplate.opsForHash().get("Z", String.valueOf(zahir.hashCode())));


    }

}
