package tk.yubari.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tk.yubari.backend.entities.CommonResult;
import tk.yubari.backend.entities.Payment;

@RestController
@Slf4j
public class PaymentController {
    public String PAYMENT_URL = "http://cloud-payment-service-nacos";
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForEntity(PAYMENT_URL + "/api/payment", payment, CommonResult.class).getBody();
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        return restTemplate.getForEntity(PAYMENT_URL + "/api/payment/" + id, CommonResult.class).getBody();
    }
}
