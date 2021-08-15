package com.github.mwttg.wavefront.extractor;

/**
 * Holds the index information of one point of the .obj face definition.
 * Example: 'f 1/2/3 4/5/6 7/8/9' (a face definition of the .obj file)
 * During the process this line gets split at the spaces. One result of that
 * is '1/2/3' this holds the index information of the first point of the triangle.
 * 1 - the index of the vertex
 * 2 - the index of the texture coordinate
 * 3 - the index of the normal
 */
public record Index(int vertex, Integer textureCoordinate, Integer normal) {
}
