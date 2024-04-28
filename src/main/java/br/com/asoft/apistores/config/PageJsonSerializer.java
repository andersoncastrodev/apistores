package br.com.asoft.apistores.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> objects, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("content", objects.getContent());
        jsonGenerator.writeNumberField("pageNumber", objects.getNumber());
        jsonGenerator.writeNumberField("pageSize", objects.getSize());
        jsonGenerator.writeNumberField("totalElements", objects.getTotalElements());
        jsonGenerator.writeNumberField("totalPages", objects.getTotalPages());
        jsonGenerator.writeBooleanField("first", objects.isFirst());
        jsonGenerator.writeBooleanField("last", objects.isLast());

        jsonGenerator.writeEndObject();
    }
}
