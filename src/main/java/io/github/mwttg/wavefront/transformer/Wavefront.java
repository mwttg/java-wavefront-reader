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

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Wavefront{");
    sb.append("vertices=");
    if (vertices == null) {
      sb.append("null");
    } else {
      sb.append('[');
      for (int i = 0; i < vertices.length; ++i) {
        sb.append(i == 0 ? "" : ", ").append(vertices[i]);
      }
      sb.append(']');
    }
    sb.append(", textureCoordinates=");
    if (textureCoordinates == null) {
      sb.append("null");
    } else {
      sb.append('[');
      for (int i = 0; i < textureCoordinates.length; ++i) {
        sb.append(i == 0 ? "" : ", ").append(textureCoordinates[i]);
      }
      sb.append(']');
    }
    sb.append(", normals=");
    if (normals == null) {
      sb.append("null");
    } else {
      sb.append('[');
      for (int i = 0; i < normals.length; ++i) {
        sb.append(i == 0 ? "" : ", ").append(normals[i]);
      }
      sb.append(']');
    }
    sb.append('}');
    return sb.toString();
  }
}
