package tk.yubari.backend.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LLoadBalancerImpl implements LLoadBalancer {
    private final AtomicInteger counter = new AtomicInteger();

    public final int getNext() {
        int current, next;
        do {
            current = counter.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;

        } while (!counter.compareAndSet(current, next));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int size = serviceInstances.size();
        return serviceInstances.get(getNext() % size);
    }
}
