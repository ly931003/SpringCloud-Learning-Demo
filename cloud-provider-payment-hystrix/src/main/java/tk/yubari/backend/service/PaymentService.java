package tk.yubari.backend.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo(Integer id) {
        return Thread.currentThread().getName() + " paymentInfo " + id;
    }

    public String paymentInfoTimeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + " paymentInfoTimeout " + id;
    }
}
