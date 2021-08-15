package com.github.mwttg.wavefront;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class WavefrontReaderTest {

    @Test
    public void testFromResource_type1() throws IOException {
        final var filename = "simple-plane-type1.obj";
        final var actual = WavefrontReader.fromResource(filename);

        assertThat(actual.vertices()).containsExactly(1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f);
        assertThat(actual.textureCoordinates()).isNull();
        assertThat(actual.normals()).isNull();
    }

    @Test
    public void testFromResource_type2() throws IOException {
        final var filename = "simple-plane-type2.obj";
        final var actual = WavefrontReader.fromResource(filename);

        assertThat(actual.vertices()).containsExactly(1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f);
        assertThat(actual.textureCoordinates()).containsExactly(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f);
        assertThat(actual.normals()).isNull();
    }

    @Test
    public void testFromResource_type3() throws IOException {
        final var filename = "simple-plane-type3.obj";
        final var actual = WavefrontReader.fromResource(filename);

        assertThat(actual.vertices()).containsExactly(1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f);
        assertThat(actual.textureCoordinates()).isNull();
        assertThat(actual.normals()).containsExactly(0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }

    @Test
    public void testFromResource_type4() throws IOException {
        final var filename = "simple-plane-type4.obj";
        final var actual = WavefrontReader.fromResource(filename);

        assertThat(actual.vertices()).containsExactly(1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f);
        assertThat(actual.textureCoordinates()).containsExactly(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f);
        assertThat(actual.normals()).containsExactly(0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }

    @Test(expectedExceptions = IOException.class)
    public void testFromResource_fileDoesNotExist() throws IOException {
        final var filename = "i-dont-exist.obj";
        WavefrontReader.fromResource(filename);
    }
}