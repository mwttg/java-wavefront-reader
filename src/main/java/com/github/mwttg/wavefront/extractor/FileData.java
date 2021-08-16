package com.github.mwttg.wavefront.extractor;

import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * The internal data structure, created from parsing the .obj file.
 */
public record FileData(List<Vector3f> vertices,
                       List<Vector2f> textureCoordinates,
                       List<Vector3f> normals,
                       List<Triangle> faces) {
}
