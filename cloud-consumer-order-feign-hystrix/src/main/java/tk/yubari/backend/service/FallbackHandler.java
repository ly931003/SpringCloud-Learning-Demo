package tk.yubari.backend.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackHandler implements PaymentFeignService{
    @Override
    public String hystrixInfo(Integer id) {
        return "globalFallbackHandler";
    }

    @Override
    public String hystrixFail(Integer id) {
        return "globalFallbackHandler";
    }
}
