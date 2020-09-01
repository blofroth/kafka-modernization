package se.forefront.meetup.kafkamodernization;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class ProductDeserializer extends JsonbDeserializer<ProductDataCache.Product> {
    public ProductDeserializer(){
        // pass the class to the parent.
        super(ProductDataCache.Product.class);
    }
}