package com.github.mwttg.wavefront.extractor;

import org.joml.Vector3f;

/**
 * Used for extracting the vertex definition and normal definition of the .obj file.
 */
final class Vector3Extractor {

  private Vector3Extractor() {
  }

  /**
   * Takes the parts of a vertex/normal definition .obj file line (e.g. 'v 1.0 2.0 3.0' split
   * at spaces) and generates a {@link Vector3f} from it.
   *
   * @param parts of a .obj vertex/normal definition file line, containing the token and three
   *              numbers (u, y, and z)
   * @return the vertex/normal represented as a {@link Vector3f}
   */
  static Vector3f from(final String[] parts) {
    if (parts.length != 4) {
      throw new IllegalArgumentException(
          "Invalid line format size of line parts is '%s' expected is 4, e.g. 'token x y z'."
              .formatted(parts.length));
    }

    final var x = Float.parseFloat(parts[1]);
    final var y = Float.parseFloat(parts[2]);
    final var z = Float.parseFloat(parts[3]);

    return new Vector3f(x, y, z);
  }
}
