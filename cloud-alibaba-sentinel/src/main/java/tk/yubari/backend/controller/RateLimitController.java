package tk.yubari.backend.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.yubari.backend.entities.CommonResult;

@RestController
@Slf4j
public class RateLimitController {
    @GetMapping("/byRes")
    @SentinelResource(value = "byRes", blockHandler = "blockHandler")
    public CommonResult<String> byRes() {
        return new CommonResult<>(200, "ok", "byRes");
    }

    public CommonResult<String> blockHandler(BlockException blockException) {
        return new CommonResult<>(444, blockException.getMessage());
    }
}
