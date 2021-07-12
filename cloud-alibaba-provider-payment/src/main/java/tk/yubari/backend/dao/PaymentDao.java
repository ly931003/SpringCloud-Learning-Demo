package tk.yubari.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import tk.yubari.backend.entities.Payment;

@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(long id);
}
