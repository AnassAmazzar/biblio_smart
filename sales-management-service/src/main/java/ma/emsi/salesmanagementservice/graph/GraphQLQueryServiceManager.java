/* package ma.emsi.salesmanagementservice.graph;


import ma.emsi.salesmanagementservice.feign.ClientServiceFeign;
import ma.emsi.salesmanagementservice.feign.ProductServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GraphQLQueryServiceManager {

    @Autowired
    ClientServiceFeign clientServiceFeign;

    @Autowired
    ProductServiceFeign productServiceFeign;

   @Override
    public Object getSClientId(String query, Long id) {
        Map<String, Object> response = clientServiceFeign.getClientById(queryFunction(Map.of("id", id), query));
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        Map<String, Object> getClientById = (Map<String, Object>) data.get("getClientById");
        System.out.println("client id : " + data.get("getClientById"));
        System.out.println("data : " + data);
        return getClientById.get("id");
    }

    @Override
    public Object getSProduitId(String query, Long id) {
        System.out.println("Id Prod : " + id);
        Map<String, Object> response = productServiceFeign.getProductById(queryFunction(Map.of("id", id), query));
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        System.out.println("Prod getProductById data : " + data.get("getProduitById"));
        System.out.println("Prod data : " + data);
        Map<String, Object> getProductById = (Map<String, Object>) data.get("getProduitById");
        System.out.println("Prod getProductById 2 : " + getProductById.get("id"));
        return getProductById.get("id");
    }

    private Map<String, Object> queryFunction(Map<String, Object> variables, String q){
        Map<String, Object> request = Map.of(
                "query", q,
                "variables", variables
        );
        return request;
    }
}


Map<String, Object> variables = Map.of("id", id);

        // Construire le corps de la requête
        Map<String, Object> request = Map.of(
                "query", query,
                "variables", variables
        );
} */