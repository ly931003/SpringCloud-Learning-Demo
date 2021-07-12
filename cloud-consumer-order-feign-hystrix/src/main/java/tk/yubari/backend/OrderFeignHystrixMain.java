package tk.yubari.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class OrderFeignHystrixMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixMain.class, args);
    }
}
