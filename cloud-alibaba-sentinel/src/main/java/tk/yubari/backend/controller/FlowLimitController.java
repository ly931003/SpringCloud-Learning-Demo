package tk.yubari.backend.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        log.info("testA");
        return "testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "testB";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey", blockHandler = "dealFunc")
    public String testHotkey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "testHotkey";
    }

    public String dealFunc(String p1,
                           String p2,
                           BlockException blockException) {
        return "dealFunc";
    }
}
