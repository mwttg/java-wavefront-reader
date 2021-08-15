package com.github.mwttg.wavefront.extractor;

import com.github.mwttg.wavefront.utilities.FileUtilities;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtractorServiceIntegrationTest {

    private ExtractorService subject;

    @BeforeMethod
    public void setup() {
        subject = new ExtractorService();
    }

    @Test
    public void testExtractFrom_type4() throws IOException {
        final var lines = FileUtilities.readFromResources("simple-plane-type4.obj");
        final var actual = subject.extractFrom(lines);

        assertThat(actual).isEqualTo(new FileData(
                List.of(
                        new Vector3f(-1.0f, 0.0f, 1.0f),
                        new Vector3f(1.0f, 0.0f, 1.0f),
                        new Vector3f(-1.0f, 0.0f, -1.0f),
                        new Vector3f(1.0f, 0.0f, -1.0f)),
                List.of(
                        new Vector2f(1.0f, 0.0f),
                        new Vector2f(0.0f, 1.0f),
                        new Vector2f(0.0f, 0.0f),
                        new Vector2f(1.0f, 1.0f)),
                List.of(
                        new Vector3f(0.0f, 1.0f, 0.0f)),
                List.of(
                        new Triangle(
                                new Index(1, 0, 0),
                                new Index(2, 1, 0),
                                new Index(0, 2, 0)),
                        new Triangle(
                                new Index(1, 0, 0),
                                new Index(3, 3, 0),
                                new Index(2, 1, 0)))));
    }

    @Test
    public void testExtractFrom_type3() throws IOException {
        final var lines = FileUtilities.readFromResources("simple-plane-type3.obj");
        final var actual = subject.extractFrom(lines);

        assertThat(actual).isEqualTo(new FileData(
                List.of(
                        new Vector3f(-1.0f, 0.0f, 1.0f),
                        new Vector3f(1.0f, 0.0f, 1.0f),
                        new Vector3f(-1.0f, 0.0f, -1.0f),
                        new Vector3f(1.0f, 0.0f, -1.0f)),
                List.of(),
                List.of(
                        new Vector3f(0.0f, 1.0f, 0.0f)),
                List.of(
                        new Triangle(
                                new Index(1, null, 0),
                                new Index(2, null, 0),
                                new Index(0, null, 0)),
                        new Triangle(
                                new Index(1, null, 0),
                                new Index(3, null, 0),
                                new Index(2, null, 0)))));
    }

    @Test
    public void testExtractFrom_type2() throws IOException {
        final var lines = FileUtilities.readFromResources("simple-plane-type2.obj");
        final var actual = subject.extractFrom(lines);

        assertThat(actual).isEqualTo(new FileData(
                List.of(
                        new Vector3f(-1.0f, 0.0f, 1.0f),
                        new Vector3f(1.0f, 0.0f, 1.0f),
                        new Vector3f(-1.0f, 0.0f, -1.0f),
                        new Vector3f(1.0f, 0.0f, -1.0f)),
                List.of(
                        new Vector2f(1.0f, 0.0f),
                        new Vector2f(0.0f, 1.0f),
                        new Vector2f(0.0f, 0.0f),
                        new Vector2f(1.0f, 1.0f)),
                List.of(),
                List.of(
                        new Triangle(
                                new Index(1, 0, null),
                                new Index(2, 1, null),
                                new Index(0, 2, null)),
                        new Triangle(
                                new Index(1, 0, null),
                                new Index(3, 3, null),
                                new Index(2, 1, null)))));
    }

    @Test
    public void testExtractFrom_type1() throws IOException {
        final var lines = FileUtilities.readFromResources("simple-plane-type1.obj");
        final var actual = subject.extractFrom(lines);

        assertThat(actual).isEqualTo(new FileData(
                List.of(
                        new Vector3f(-1.0f, 0.0f, 1.0f),
                        new Vector3f(1.0f, 0.0f, 1.0f),
                        new Vector3f(-1.0f, 0.0f, -1.0f),
                        new Vector3f(1.0f, 0.0f, -1.0f)),
                List.of(),
                List.of(),
                List.of(
                        new Triangle(
                                new Index(1, null, null),
                                new Index(2, null, null),
                                new Index(0, null, null)),
                        new Triangle(
                                new Index(1, null, null),
                                new Index(3, null, null),
                                new Index(2, null, null)))));
    }

    @Test
    public void testExtractFrom_wrongFile() throws IOException {
        final var lines = FileUtilities.readFromResources("valid-textfile.txt");
        final var actual = subject.extractFrom(lines);

        assertThat(actual).isEqualTo(new FileData(
                List.of(),
                List.of(),
                List.of(),
                List.of()));
    }

    @Test(expectedExceptions = IOException.class)
    public void testExtractFrom_fileDoesNotExist() throws IOException {
        final var lines = FileUtilities.readFromResources("doesnt-exist.txt");
        subject.extractFrom(lines);
    }
}