package com.github.mwttg.wavefront.extractor;

import org.joml.Vector2f;

final class Vector2Extractor {

    private Vector2Extractor() {
    }

    static Vector2f from(final String[] parts) {
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid line format size of line parts is '%s' expected is 3, e.g. 'token x y'.".formatted(parts.length));
        }

        final var u = Float.parseFloat(parts[1]);
        final var v = Float.parseFloat(parts[2]);

        return new Vector2f(u, v);
    }
}
