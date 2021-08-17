package io.github.mwttg.wavefront.extractor;

/**
 * Used for extracting the face definition of the .obj file. Only triangles are supported!
 */
final class FaceExtractor {

  private FaceExtractor() {
  }

  /**
   * Takes the parts of a face definition .obj file line (e.g. 'f 1/3/7 2/5/8 3/6/9' split
   * at spaces) and generates a {@link Triangle} definition from it that hold the index information.
   *
   * @param parts of a .obj face definition file line, containing the token and three points
   * @return the resulting triangle with index information
   */
  static Triangle from(final String[] parts) {
    if (parts.length != 4) {
      throw new IllegalArgumentException(
          ("Invalid line format. Size of line parts for face definition is '%s' "
              + "expected is 4, e.g. 'f i1/t1 i2/t2 i3/t3'.").formatted(parts.length));
    }

    final var point1 = IndexExtractor.from(parts[1]);
    final var point2 = IndexExtractor.from(parts[2]);
    final var point3 = IndexExtractor.from(parts[3]);

    return new Triangle(point1, point2, point3);
  }
}
