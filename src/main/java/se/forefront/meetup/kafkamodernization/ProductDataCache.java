package se.forefront.meetup.kafkamodernization;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A bean consuming data from the "products" Kafka topic and updating a cache on new messages
 * The cache can be queried on a REST GET endpoint.
 */
@ApplicationScoped
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductDataCache {
    private static Logger logger = LoggerFactory.getLogger(ProductDataCache.class);

    private ConcurrentHashMap<Integer, Product> cache = new ConcurrentHashMap<>();

    @Incoming("products")
    public void cacheProductUpdate(Product product) {
        logger.info("Updating cache for product id: {}", product.productId);
        cache.put(product.productId, product);
    }

    @APIResponses(value = {@APIResponse(responseCode = "404", description = "No product found")})
    @GET @Path("/{productId}")
    public Product getProduct(@PathParam("productId") int productId) {
        Product match = cache.get(productId);
        logger.info("Querying cache for product id: {}, match: {}",
                productId, match != null);
        if (match == null) {
            throw new NotFoundException("No such product found");
        }
        return match;
    }

    public static class Product {
        public int productId;
        public String name;
        public String category;
    }
}