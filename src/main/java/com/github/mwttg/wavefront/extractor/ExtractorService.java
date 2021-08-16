package com.github.mwttg.wavefront.extractor;

import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * A function for transforming the content of the .obj file for later processing.
 */
public final class ExtractorService {

  private static final String SPACE = " ";

  private ExtractorService() {
  }

  /**
   * Processes each line of the obj file and transforms it to an internal data structure
   * {@link FileData}. The transformation depends on the token of the line of the .obj file.
   * Only 3D data is supported.
   * v - vertex
   * vt - texture coordinate (uv)
   * vn - normal
   * f - face (only triangles are supported)
   * Unknown token will get NOT processed.
   *
   * @param lines the lines of the obj file
   * @return the data structure {@link FileData} for later processing
   */
  public static FileData extractFrom(final List<String> lines) {
    final var vertices = new ArrayList<Vector3f>();
    final var textureCoords = new ArrayList<Vector2f>();
    final var normals = new ArrayList<Vector3f>();
    final var faces = new ArrayList<Triangle>();

    for (final String line : lines) {
      if (line.isBlank()) {
        continue;
      }

      final var parts = line.split(SPACE);
      switch (parts[0]) {
        case "v" -> {
          final var vertex = Vector3Extractor.from(parts);
          vertices.add(vertex);
        }
        case "vn" -> {
          final var normal = Vector3Extractor.from(parts);
          normals.add(normal);
        }
        case "vt" -> {
          final var uv = Vector2Extractor.from(parts);
          textureCoords.add(uv);
        }
        case "f" -> {
          final var face = FaceExtractor.from(parts);
          faces.add(face);
        }
        default -> {
        }
      }
    }

    return new FileData(vertices, textureCoords, normals, faces);
  }
}
