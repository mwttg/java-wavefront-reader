package io.github.mwttg.wavefront.extractor;

import static org.assertj.core.api.Assertions.assertThat;

import org.joml.Vector2f;
import org.testng.annotations.Test;

public class Vector2ExtractorTest {

  @Test
  public void testFrom() {
    final var parts = new String[] {"token", "1.0", "2.0"};
    final var actual = Vector2Extractor.from(parts);
    assertThat(actual).isEqualTo(new Vector2f(1.0f, 2.0f));
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testFrom_invalidSize() {
    final var parts = new String[] {"token", "1.0", "2.0", "3.0"};
    Vector2Extractor.from(parts);
  }

  @Test(expectedExceptions = NumberFormatException.class)
  public void testFrom_parseError() {
    final var parts = new String[] {"token", "uppps...", "2.0"};
    Vector2Extractor.from(parts);
  }
}