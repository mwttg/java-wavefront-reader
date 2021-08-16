package com.github.mwttg.wavefront.transformer;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.mwttg.wavefront.extractor.FileData;
import com.github.mwttg.wavefront.extractor.Index;
import com.github.mwttg.wavefront.extractor.Triangle;
import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.testng.annotations.Test;

public class TransformerServiceTest {

  @Test
  public void testTransform() {
    final var faces = List.of(
        new Triangle(
            new Index(0, 0, 0),
            new Index(1, 1, 1),
            new Index(2, 2, 2)));
    final var input = createFileData(faces);
    final var actual = TransformerService.transform(input);

    assertThat(actual.vertices()).containsExactly(1.0f, 1.0f, 1.0f, 2.0f, 2.0f, 2.0f, 3.0f, 3.0f,
        3.0f);
    assertThat(actual.textureCoordinates()).contains(1.0f, 1.0f, 2.0f, 2.0f, 3.0f, 3.0f);
    assertThat(actual.normals()).containsExactly(-1.0f, -1.0f, -1.0f, -2.0f, -2.0f, -2.0f, -3.0f,
        -3.0f, -3.0f);
  }

  @Test
  public void testTransform_secondExample() {
    final var faces = List.of(
        new Triangle(
            new Index(0, 1, 2),
            new Index(0, 1, 2),
            new Index(0, 1, 2)));
    final var input = createFileData(faces);
    final var actual = TransformerService.transform(input);

    assertThat(actual.vertices()).containsExactly(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
        1.0f);
    assertThat(actual.textureCoordinates()).contains(2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f);
    assertThat(actual.normals()).containsExactly(-3.0f, -3.0f, -3.0f, -3.0f, -3.0f, -3.0f, -3.0f,
        -3.0f, -3.0f);
  }

  public FileData createFileData(final List<Triangle> faces) {
    final var vertices = List.of(
        new Vector3f(1.0f, 1.0f, 1.0f),
        new Vector3f(2.0f, 2.0f, 2.0f),
        new Vector3f(3.0f, 3.0f, 3.0f));
    final var textureCoordinates = List.of(
        new Vector2f(1.0f, 1.0f),
        new Vector2f(2.0f, 2.0f),
        new Vector2f(3.0f, 3.0f));
    final var normals = List.of(
        new Vector3f(-1.0f, -1.0f, -1.0f),
        new Vector3f(-2.0f, -2.0f, -2.0f),
        new Vector3f(-3.0f, -3.0f, -3.0f));

    return new FileData(vertices, textureCoordinates, normals, faces);
  }
}