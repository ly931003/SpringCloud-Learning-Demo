package tk.yubari.backend.controller;

import tk.yubari.backend.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import tk.yubari.backend.entities.CommonResult;
import tk.yubari.backend.entities.Payment;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("result: " + result);
        if (result > 0) {
            return new CommonResult<>(200, serverPort + " ok", payment);
        } else {
            return new CommonResult<>(500, serverPort + " fail");
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("result: " + payment);
        if (payment != null) {
            return new CommonResult<>(200, serverPort + " ok", payment);
        } else {
            return new CommonResult<>(500, serverPort + " fail");
        }
    }

    @GetMapping("/payment/discovery")
    public String discovery() {
        discoveryClient.getServices().forEach(service -> {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            instances.forEach(serviceInstance -> {
                log.info(serviceInstance.toString());
            });
        });
        return "ok";
    }
}
