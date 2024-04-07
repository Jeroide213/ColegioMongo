package com.example.ColegioMongo.Config.Converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class LongToObjectIdConverter implements Converter<Long, ObjectId> {
    @Override
    public ObjectId convert(Long source) {
        return source != null ? new ObjectId(Long.toHexString(source)) : null;
    }
}