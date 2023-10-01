package micro.dao.domain.converter.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter
public abstract class StandardConverter<T> implements AttributeConverter<T, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(T details) {
        try {
            return objectMapper.writeValueAsString(details);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public T convertToEntityAttribute(String detailsJSON) {
        try {
            return objectMapper.readValue(detailsJSON, getTypeReference());
        } catch (final IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected abstract TypeReference<T> getTypeReference();

}