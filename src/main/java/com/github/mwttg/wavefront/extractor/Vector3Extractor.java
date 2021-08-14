package com.github.mwttg.wavefront.extractor;

import org.joml.Vector3f;

final class Vector3Extractor {

    private Vector3Extractor() {
    }

    static Vector3f from(final String[] parts) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid line format size of line parts is '%s' expected is 4, e.g. 'token x y z'.".formatted(parts.length));
        }

        final var x = Float.parseFloat(parts[1]);
        final var y = Float.parseFloat(parts[2]);
        final var z = Float.parseFloat(parts[3]);

        return new Vector3f(x, y, z);
    }
}
