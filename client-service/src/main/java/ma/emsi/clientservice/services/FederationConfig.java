package ma.emsi.clientservice.services;

//import com.netflix.graphql.dgs.DgsComponent;
//import com.netflix.graphql.dgs.DgsEntityFetcher;
import graphql.GraphQL;
import ma.emsi.clientservice.dao.entities.Client;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.federation.FederationSchemaFactory;

import java.util.Map;


/**
 @Configuration
 @DgsComponent
 public class FederationConfig {
 @DgsEntityFetcher(name = "Product")
 public Client fetchProduct(Map<String, Object> values) {
 String id = (String) values.get("id");
 return fetchProductById(id);
 }

 private Client fetchProductById(String id) {
 return new Client();
 }
 }
 */
