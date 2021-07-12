package tk.yubari.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.yubari.backend.service.PaymentService;

@RestController
@Slf4j
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/info/{id}")
    public String hystrixInfo(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo(id);
    }

    @GetMapping("/payment/hystrix/fail/{id}")
    public String hystrixFail(@PathVariable("id") Integer id) {
        return paymentService.paymentInfoTimeout(id);
    }

    @GetMapping("/payment/hystrix/circuitBreaker/{id}")
    public String hystrixCircuitBreaker(@PathVariable("id") Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }

}
