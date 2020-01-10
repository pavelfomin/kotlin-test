package net.test;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.DefaultArgumentConverter;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

/**
 * Based on http://dolszewski.com/testing/parameterizedtest-with-null-values-in-cvssource/
 */
public final class NullableConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if ("null".equals(source)) {
            return null;
        }
        return DefaultArgumentConverter.INSTANCE.convert(source, targetType);
    }
}