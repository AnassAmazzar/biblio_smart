package ma.emsi.salesmanagementservice.graph;

import org.springframework.stereotype.Service;

@Service
public interface GraphQLQueryService {
    Object getSClientId(String query, Long id);
    Object getSProduitId(String query, Long id);
    Object getQentiter(String query, Long id);

    Object updateQuantite(String query, Long id);
}
