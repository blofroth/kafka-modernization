package se.forefront.meetup.kafkamodernization;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import se.forefront.meetup.kafkamodernization.ProductDataCache;

public class ProductDeserializer extends JsonbDeserializer<ProductDataCache.Product> {
    public ProductDeserializer(){
        // pass the class to the parent.
        super(ProductDataCache.Product.class);
    }
}