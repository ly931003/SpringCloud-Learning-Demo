package tk.yubari.backend.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LLoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
