package ma.emsi.salesmanagementservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "client-service", url = "http://localhost:8090/client/graphql")
public interface ClientServiceFeign {
    //@PostMapping()
    //consumes = MediaType.APPLICATION_JSON_VALUE
    //@PostMapping
    //Map<String, Object> getClientById(@RequestBody Map<String, Object> request);
    @PostMapping
    Map<String, Object> getClientById(@RequestBody Long requestClientId);
}
