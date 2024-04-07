package com.example.ColegioMongo.Config.Converters;

import org.springframework.core.convert.converter.Converter;
import org.bson.types.ObjectId;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class ObjectIdToLongConverter implements Converter<ObjectId, Long> {
    @Override
    public Long convert(ObjectId source) {
        return source != null ? Long.valueOf(source.getTimestamp()) : null;
    }
}