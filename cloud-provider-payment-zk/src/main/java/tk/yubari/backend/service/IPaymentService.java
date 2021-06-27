package tk.yubari.backend.service;

import tk.yubari.backend.entities.Payment;

public interface IPaymentService {
    int create(Payment payment);

    Payment getPaymentById(long id);
}
