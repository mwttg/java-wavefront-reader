package io.github.mwttg.wavefront.transformer;

import io.github.mwttg.wavefront.extractor.FileData;

/**
 * A function for transforming the internal data model {@link FileData} to multiple float Arrays
 * (used by OpenGL).
 */
public final class TransformerService {

  private TransformerService() {
  }

  /**
   * Takes the internal data model {@link FileData} (of an .obj file) and transforms it to a
   * data model {@link Wavefront}.
   *
   * @param data internal representation of an .obj file
   * @return a {@link Wavefront} holding float Array for vertices and if available texture
   *     coordinates and/or normals
   */
  public static Wavefront transform(final FileData data) {
    final var originalVertices = data.vertices();
    final var originalTextureCoordinates = data.textureCoordinates();
    final var originalNormals = data.normals();
    final var originalFaces = data.faces();

    final var resultVertices = new float[originalFaces.size() * 3 * 3];
    float[] resultTextureCoordinates = null;
    float[] resultNormals = null;

    for (int index = 0; index < originalFaces.size(); index++) {
      final var triangle = originalFaces.get(index);
      final var point1 = triangle.point1();
      final var point2 = triangle.point2();
      final var point3 = triangle.point3();

      final var vertexIndexPoint1 = point1.vertex();
      final var vertex1 = originalVertices.get(vertexIndexPoint1);
      resultVertices[index * 9] = vertex1.x();
      resultVertices[index * 9 + 1] = vertex1.y();
      resultVertices[index * 9 + 2] = vertex1.z();

      final var vertexIndexPoint2 = point2.vertex();
      final var vertex2 = originalVertices.get(vertexIndexPoint2);
      resultVertices[index * 9 + 3] = vertex2.x();
      resultVertices[index * 9 + 4] = vertex2.y();
      resultVertices[index * 9 + 5] = vertex2.z();

      final var vertexIndexPoint3 = point3.vertex();
      final var vertex3 = originalVertices.get(vertexIndexPoint3);
      resultVertices[index * 9 + 6] = vertex3.x();
      resultVertices[index * 9 + 7] = vertex3.y();
      resultVertices[index * 9 + 8] = vertex3.z();

      if (point1.textureCoordinate() != null) {  // simple check which wavefront file type we have
        if (resultTextureCoordinates == null) {
          resultTextureCoordinates = new float[originalFaces.size() * 3 * 2];
        }

        final var uvIndexPoint1 = point1.textureCoordinate();
        final var uv1 = originalTextureCoordinates.get(uvIndexPoint1);
        resultTextureCoordinates[index * 6] = uv1.x();
        resultTextureCoordinates[index * 6 + 1] = uv1.y();

        final var uvIndexPoint2 = point2.textureCoordinate();
        final var uv2 = originalTextureCoordinates.get(uvIndexPoint2);
        resultTextureCoordinates[index * 6 + 2] = uv2.x();
        resultTextureCoordinates[index * 6 + 3] = uv2.y();

        final var uvIndexPoint3 = point3.textureCoordinate();
        final var uv3 = originalTextureCoordinates.get(uvIndexPoint3);
        resultTextureCoordinates[index * 6 + 4] = uv3.x();
        resultTextureCoordinates[index * 6 + 5] = uv3.y();
      }

      if (point1.normal() != null) {  // simple check which wavefront file type we have
        if (resultNormals == null) {
          resultNormals = new float[originalFaces.size() * 3 * 3];
        }

        final var normalIndexPoint1 = point1.normal();
        final var normal1 = originalNormals.get(normalIndexPoint1);
        resultNormals[index * 9] = normal1.x();
        resultNormals[index * 9 + 1] = normal1.y();
        resultNormals[index * 9 + 2] = normal1.z();

        final var normalIndexPoint2 = point2.normal();
        final var normal2 = originalNormals.get(normalIndexPoint2);
        resultNormals[index * 9 + 3] = normal2.x();
        resultNormals[index * 9 + 4] = normal2.y();
        resultNormals[index * 9 + 5] = normal2.z();

        final var normalIndexPoint3 = point3.normal();
        final var normal3 = originalNormals.get(normalIndexPoint3);
        resultNormals[index * 9 + 6] = normal3.x();
        resultNormals[index * 9 + 7] = normal3.y();
        resultNormals[index * 9 + 8] = normal3.z();
      }
    }

    return new Wavefront(resultVertices, resultTextureCoordinates, resultNormals);
  }
}
