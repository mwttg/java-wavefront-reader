package com.github.mwttg.wavefront.extractor;

import java.util.regex.Pattern;

final class IndexExtractor {

    private static final String SEPARATOR = "/";

    private IndexExtractor() {
    }

    static Index from(final String definition) {
        final var type = Type.getTypeFrom(definition);
        final var parts = definition.split(SEPARATOR);
        switch (type) {
            case TYPE1 -> {
                final var vertexIndex = Integer.parseInt(parts[0]);
                return new Index(vertexIndex, null, null);
            }
            case TYPE2 -> {
                final var vertexIndex = Integer.parseInt(parts[0]);
                final var uvIndex = Integer.parseInt(parts[1]);
                return new Index(vertexIndex, uvIndex, null);
            }
            case TYPE3 -> {
                final var vertexIndex = Integer.parseInt(parts[0]);
                final var normalIndex = Integer.parseInt(parts[2]);
                return new Index(vertexIndex, null, normalIndex);
            }
            case TYPE4 -> {
                final var vertexIndex = Integer.parseInt(parts[0]);
                final var uvIndex = Integer.parseInt(parts[1]);
                final var normalIndex = Integer.parseInt(parts[2]);
                return new Index(vertexIndex, uvIndex, normalIndex);
            }
        }
        throw new RuntimeException("No TYPE for face definition '%s' found.".formatted(definition));
    }

    enum Type {
        TYPE1,
        TYPE2,
        TYPE3,
        TYPE4;

        private static final Pattern TYPE1_PATTERN = Pattern.compile("^[0-9]+$"); // only vertex
        private static final Pattern TYPE2_PATTERN = Pattern.compile("^[0-9]+/[0-9]+$"); // vertex, uv
        private static final Pattern TYPE3_PATTERN = Pattern.compile("^[0-9]+//[0-9]+$"); // vertex, normal
        private static final Pattern TYPE4_PATTERN = Pattern.compile("^[0-9]+/[0-9]+/[0-9]+$"); // vertex, normal, uv

        static Type getTypeFrom(final String definition) {
            final var matcherType4 = TYPE4_PATTERN.matcher(definition);
            if (matcherType4.matches()) {
                return TYPE4;
            }

            final var matcherType1 = TYPE1_PATTERN.matcher(definition);
            if (matcherType1.matches()) {
                return TYPE1;
            }

            final var matcherType2 = TYPE2_PATTERN.matcher(definition);
            if (matcherType2.matches()) {
                return TYPE2;
            }

            final var matcherType3 = TYPE3_PATTERN.matcher(definition);
            if (matcherType3.matches()) {
                return TYPE3;
            }

            throw new IllegalArgumentException("No matching tpe found for definition '%s'.".formatted(definition));
        }
    }
}
