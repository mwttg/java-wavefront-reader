package com.github.mwttg.wavefront.transformer;

/**
 * The resulting data structure with float Arrays which can be used by OpenGL.
 */
public record Wavefront(float[] vertices, float[] textureCoordinates, float[] normals) {
}
