package ma.emsi.salesmanagementservice.graph;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GraphQLQueryService {
    Object getSClientId(String query, Long id);
    Object getSProduitId(String query, Long id);
    Object getQentiter(String query, Long id);

    Object executeMutation(String mutation, Map<String, Object> variables);
}
