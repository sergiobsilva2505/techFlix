package br.com.fiap.techFlix.domain.validation;

import java.util.Collection;

public class Validator {

    public static final String MESSAGE_NOT_EMPTY_OR_NULL = " can't be empty or null.";
    public static final String MESSAGE_NOT_NULL = " can't be null.";
    public static final String MESSAGE_GT_VALIDATION = " must be greater than %s";
    public static final String MESSAGE_GTE_VALIDATION = " must be greater than or equal to %s";

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

    public static void notNull(Object object, String field) {
        if (object == null) {
            throw new IllegalArgumentException(field + MESSAGE_NOT_NULL);
        }
    }

    public static void greaterThan(long value, long baseValue, String field) {
        if (baseValue >= value) {
            throw new IllegalArgumentException(field + MESSAGE_GT_VALIDATION.formatted(baseValue));
        }
    }

    public static void greaterThanOrEqual(long value, long baseValue, String field) {
        if (baseValue > value) {
            throw new IllegalArgumentException(field + MESSAGE_GTE_VALIDATION.formatted(baseValue));
        }
    }

}
