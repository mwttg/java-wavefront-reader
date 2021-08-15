package com.github.mwttg.wavefront;

import com.github.mwttg.wavefront.extractor.ExtractorService;
import com.github.mwttg.wavefront.transformer.TransformerService;
import com.github.mwttg.wavefront.transformer.Wavefront;
import com.github.mwttg.wavefront.utilities.FileUtilities;

import java.io.IOException;

/**
 * This is the entry point for the user of that library. With that WavefrontReader function the user
 * can read an .obj file and transform it to Arrays of float (collected inside the {@link Wavefront}
 * data record).
 * <p>
 * Additional information:
 * (1) Only triangulated .obj files are supported.
 * (2) OpenGL index drawing is NOT supported at the moment. The data (vertices, textureCoordinates, normals)
 * is duplicated. The duplication is defined by the faces (triangles) of the .obj file.
 * (3) The following .obj 'types' are supported:
 * - only vertices (face definition: 'f v1 v2 v3')
 * - vertices with texture coordinates (face definition: 'f v1/vt1 v2/vt2 v3/vt3)
 * - vertices with normals (face definition: f v1//vn1 v2//vn2 v3//vn3)
 * - vertices with texture coordinates and with normals (face definition: f v1/vt1/vn1 v2/vt2/vn2 v3/vt3/vn3)
 * Depending on the type, the belonging float Array inside the {@link Wavefront} data structure is set. The
 * missing data results in setting the belonging float Array to 'null'.
 */
public final class WavefrontReader {

    private WavefrontReader() {
    }

    /**
     * reads an .obj file from the resource folder
     *
     * @param filename path to the file inside the resource folder
     * @return a {@link Wavefront} data structure (float array)
     * @throws IOException if reading the file fails for any reason
     */
    public static Wavefront fromResource(final String filename) throws IOException {
        final var fileLines = FileUtilities.readFromResources(filename);
        final var fileData = ExtractorService.extractFrom(fileLines);
        return TransformerService.transform(fileData);
    }
}
