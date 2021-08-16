package com.github.mwttg.wavefront.extractor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.testng.annotations.Test;

public class ExtractorServiceTest {

  @Test
  public void testExtractFrom_vertex() {
    final var input = List.of("v 1.0 2.0 3.0");
    final var actual = ExtractorService.extractFrom(input);
    assertThat(actual).isEqualTo(
        new FileData(
            List.of(new Vector3f(1.0f, 2.0f, 3.0f)),
            List.of(),
            List.of(),
            List.of()));
  }

  @Test
  public void testExtractFrom_uv() {
    final var input = List.of("vt 1.0 2.0");
    final var actual = ExtractorService.extractFrom(input);
    assertThat(actual).isEqualTo(
        new FileData(
            List.of(),
            List.of(new Vector2f(1.0f, 2.0f)),
            List.of(),
            List.of()));
  }

  @Test
  public void testExtractFrom_normal() {
    final var input = List.of("vn 1.0 2.0 3.0");
    final var actual = ExtractorService.extractFrom(input);
    assertThat(actual).isEqualTo(
        new FileData(
            List.of(),
            List.of(),
            List.of(new Vector3f(1.0f, 2.0f, 3.0f)),
            List.of()));
  }

  @Test
  public void testExtractFrom_face() {
    final var input = List.of("f 1/4/7 2/5/8 3/6/9");
    final var actual = ExtractorService.extractFrom(input);
    assertThat(actual).isEqualTo(
        new FileData(
            List.of(),
            List.of(),
            List.of(),
            List.of(new Triangle(
                new Index(0, 3, 6),
                new Index(1, 4, 7),
                new Index(2, 5, 8)))));
  }

  @Test
  public void testExtractFrom_unknownToken() {
    final var input = List.of("xxx 1.0 2.0");
    final var actual = ExtractorService.extractFrom(input);
    assertThat(actual).isEqualTo(
        new FileData(
            List.of(),
            List.of(),
            List.of(),
            List.of()));
  }
}
