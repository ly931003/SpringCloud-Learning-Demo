package tk.yubari.backend.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tk.yubari.backend.service.PaymentFeignService;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/hystrix/info/{id}")
    public String hystrixInfo(@PathVariable("id") Integer id) {
        return paymentFeignService.hystrixInfo(id);
    }

    @GetMapping("/payment/hystrix/fail/{id}")
    @HystrixCommand
    public String hystrixFail(@PathVariable("id") Integer id) {
        int error = 1/0;
        return paymentFeignService.hystrixFail(id);
    }

    public String globalFallback() {
        return "globalFallback ";
    }
}
