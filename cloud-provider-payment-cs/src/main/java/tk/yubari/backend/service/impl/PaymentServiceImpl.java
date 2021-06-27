package tk.yubari.backend.service.impl;

import tk.yubari.backend.dao.PaymentDao;
import tk.yubari.backend.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.yubari.backend.entities.Payment;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
