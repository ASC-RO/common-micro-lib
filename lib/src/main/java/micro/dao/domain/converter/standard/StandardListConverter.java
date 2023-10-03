package micro.dao.domain.converter.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Converter
public abstract class StandardListConverter<T> implements AttributeConverter<List<T>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<T> workflowFields) {
        try {
            return objectMapper.writeValueAsString(workflowFields);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<T> convertToEntityAttribute(String workflowFieldsJSON) {
        if ("null".equals(workflowFieldsJSON)) {
            return Collections.emptyList();
        }
        return Optional.ofNullable(workflowFieldsJSON).map(content -> {
            try {
                return objectMapper.readValue(workflowFieldsJSON, getTypeReference());
            } catch (final IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).orElse(Collections.emptyList());
    }

    protected abstract TypeReference<List<T>> getTypeReference();

}
