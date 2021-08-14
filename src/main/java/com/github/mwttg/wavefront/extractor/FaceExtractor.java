package com.github.mwttg.wavefront.extractor;

final class FaceExtractor {

    private FaceExtractor() {
    }

    static Triangle from(final String[] parts) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid line format. Size of line parts for face definition is '%s' expected is 4, e.g. 'f i1/t1 i2/t2 i3/t3'.".formatted(parts.length));
        }

        final var point1 = IndexExtractor.from(parts[1]);
        final var point2 = IndexExtractor.from(parts[2]);
        final var point3 = IndexExtractor.from(parts[3]);

        return new Triangle(point1, point2, point3);
    }
}
