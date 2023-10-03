package micro.dao.domain.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.Converter;
import micro.dao.domain.converter.standard.StandardConverter;

import java.util.Map;

@Converter
public class HashMapConverter extends StandardConverter<Map<String, Object>> {
    @Override
    protected TypeReference<Map<String, Object>> getTypeReference() {
        return new TypeReference<>(){};
    }
}