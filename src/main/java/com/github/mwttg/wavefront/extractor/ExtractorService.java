package com.github.mwttg.wavefront.extractor;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public final class ExtractorService {

    private static final String SPACE = " ";

    private ExtractorService() {
    }

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
            }
        }

        return new FileData(vertices, textureCoords, normals, faces);
    }
}
