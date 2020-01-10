package net.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateFormatterTest {

    DateFormatter formatter = new DateFormatter();

    @ParameterizedTest(name = "format {0} using {1}")
    @CsvSource({
            "2019-01-01, MM/dd/yyyy, 01/01/2019",
            "2019-12-31, MM/dd/yyyy, 12/31/2019",
            "2019-12-31, dd/MM/yy, 31/12/19"
    })
    /**
     * @param input standard converter is used to create an instance of {@link LocalDate}
     */
    void formatDateNotNull(LocalDate input, String pattern, String expected) {

        assertEquals(expected, formatter.format(input, pattern));
    }

    @ParameterizedTest(name = "format {0} using {1}")
    @CsvSource({
            "null, MM/dd/yyyy, null",
            "2019-01-01, MM/dd/yyyy, 01/01/2019",
            "2019-12-31, MM/dd/yyyy, 12/31/2019",
            "2019-12-31, dd/MM/yy, 31/12/19"
    })
    /**
     * Using custom {@link NullableConverter} null aware converter.
     */
    void formatDateWithNull(@ConvertWith(NullableConverter.class) LocalDate input, String pattern, @ConvertWith(NullableConverter.class) String expected) {

        assertEquals(expected, formatter.format(input, pattern));
    }

    @ParameterizedTest(name = "format {0} using {1}")
    @CsvSource({
            "null, MM/dd/yyyy, null",
            "2019-01-01, MM/dd/yyyy, 01/01/2019",
            "2019-12-31, MM/dd/yyyy, 12/31/2019",
            "2019-12-31, dd/MM/yy, 31/12/19"
    })
    /**
     * Using {@link ArgumentsAccessor} to check for null.
     */
    void formatDateWithNull(ArgumentsAccessor arguments) {

        LocalDate date = ("null".equals(arguments.getString(0)) ? null : arguments.get(0, LocalDate.class));
        String pattern = arguments.getString(1);
        String expected = ("null".equals(arguments.getString(2)) ? null : arguments.getString(2));

        assertEquals(expected, formatter.format(date, pattern));
    }

    class DateFormatter {

        private String format(LocalDate input, String pattern) {
            return input == null ? null : input.format(DateTimeFormatter.ofPattern(pattern));
        }
    }

}