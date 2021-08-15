package com.github.mwttg.wavefront.extractor;

import org.joml.Vector2f;

/**
 * Used for extracting the texture coordinate definition of the .obj file.
 */
final class Vector2Extractor {

    private Vector2Extractor() {
    }

    /**
     * Takes the parts of a texture coordinate definition .obj file line (e.g. 'vt 1.0 2.0' split at spaces)
     * and generates a {@link Vector2f} from it.
     *
     * @param parts of a .obj texture coordinate definition file line, containing the token and two numbers (u and v)
     * @return the texture coordinate represented as a {@link Vector2f}
     */
    static Vector2f from(final String[] parts) {
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid line format size of line parts is '%s' expected is 3, e.g. 'token x y'.".formatted(parts.length));
        }

        final var u = Float.parseFloat(parts[1]);
        final var v = Float.parseFloat(parts[2]);

        return new Vector2f(u, v);
    }
}
