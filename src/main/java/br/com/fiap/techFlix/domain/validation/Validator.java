package br.com.fiap.techFlix.domain.validation;

import java.util.Collection;
import java.util.List;

public class Validator {

    public static final String MESSAGE_NOT_EMPTY_OR_NULL = " can't be empty or null.";
    public static final String MESSAGE_NOT_NULL = " can't be null.";
    public static final String MESSAGE_INTERVAL_VALIDATION = "Size must be greater than zero";

    public static void notEmptyOrNull(String text, String field) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(field + MESSAGE_NOT_EMPTY_OR_NULL);
        }
    }

    public static void notEmptyOrNull(Collection<?> itens, String field) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException(field + MESSAGE_NOT_EMPTY_OR_NULL);
        }
    }

    public static void objectNotNull(Object object, String field) {
        if (object == null) {
            throw new IllegalArgumentException(field + MESSAGE_NOT_NULL);
        }
    }

    public static void greaterThan(long value, long baseValue, String field) {
        if (baseValue >= value) {
            throw new IllegalArgumentException(field + MESSAGE_INTERVAL_VALIDATION);
        }
    }

}
