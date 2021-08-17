package io.github.mwttg.wavefront.transformer;

import java.util.Arrays;

/**
 * The resulting data structure with float Arrays which can be used by OpenGL.
 */
public record Wavefront(float[] vertices, float[] textureCoordinates, float[] normals) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wavefront wavefront = (Wavefront) o;
    return Arrays.equals(vertices, wavefront.vertices) &&
        Arrays.equals(textureCoordinates, wavefront.textureCoordinates) &&
        Arrays.equals(normals, wavefront.normals);
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(vertices);
    result = 31 * result + Arrays.hashCode(textureCoordinates);
    result = 31 * result + Arrays.hashCode(normals);
    return result;
  }
}
