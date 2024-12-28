package ma.emsi.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@Configuration
@Component
@CrossOrigin("*")
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


    /*
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeBuilder){
        return routeBuilder.routes()
                //.filters(f->f.rewritePath("/(?<segment>.*)", "/${segment}"))
                .route("product-service", p->p.path("/**").uri("http://localhost:8091"))
                .route("client-service", p->p.path("/**").uri("http://localhost:8090"))
                .build();
    }*/

}
