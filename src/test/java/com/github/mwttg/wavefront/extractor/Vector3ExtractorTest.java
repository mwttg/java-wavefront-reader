package com.github.mwttg.wavefront.extractor;

import static org.assertj.core.api.Assertions.assertThat;

import org.joml.Vector3f;
import org.testng.annotations.Test;

public class Vector3ExtractorTest {

  @Test
  public void testFrom() {
    final var parts = new String[] {"token", "1.0", "2.0", "3.0"};
    final var actual = Vector3Extractor.from(parts);
    assertThat(actual).isEqualTo(new Vector3f(1.0f, 2.0f, 3.0f));
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testFrom_invalidSize() {
    final var parts = new String[] {"token", "1.0", "2.0"};
    Vector3Extractor.from(parts);
  }

  @Test(expectedExceptions = NumberFormatException.class)
  public void testFrom_parseError() {
    final var parts = new String[] {"token", "uppps...", "2.0", "3.0"};
    Vector3Extractor.from(parts);
  }
}