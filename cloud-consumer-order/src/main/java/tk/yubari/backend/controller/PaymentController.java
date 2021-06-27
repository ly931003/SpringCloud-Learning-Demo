package tk.yubari.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tk.yubari.backend.entities.CommonResult;
import tk.yubari.backend.entities.Payment;
import tk.yubari.backend.loadbalance.LLoadBalancer;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private LLoadBalancer lLoadBalancer;
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
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }
        ServiceInstance instances1 = lLoadBalancer.instances(instances);
        URI uri = instances1.getUri();
        return restTemplate.getForEntity(uri + "/api/payment/" + id, CommonResult.class).getBody();
    }
}
