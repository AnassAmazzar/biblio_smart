package ma.emsi.salesmanagementservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "product-service", url = "http://localhost:8091/product/graphql")
public interface ProductServiceFeign {
    @PostMapping
    Map<String, Object> getQentiter(@RequestBody Map<String, Object> request_qt);

    @PostMapping
    Map<String, Object> getProductById(@RequestBody Map<String, Object> request_prod);

}
