package tk.yubari.backend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE-HYSTRIX",fallback = FallbackHandler.class)
public interface PaymentFeignService {
    @GetMapping("/api/payment/hystrix/info/{id}")
    String hystrixInfo(@PathVariable("id") Integer id);

    @GetMapping("/api/payment/hystrix/fail/{id}")
    String hystrixFail(@PathVariable("id") Integer id);
}
