package io.github.mwttg.wavefront.transformer;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class WavefrontTest {
  private static final float[] ARRAY = {1.0f, 2.0f, 3.0f};
  private static final float[] SIMILAR_ARRAY = {1.0f, 2.0f, 3.0f};
  private static final float[] OTHER_SIMILAR_ARRAY = {1.0f, 2.0f, 3.0f};
  private static final float[] OTHER_ARRAY = {9.0f, 8.0f, 7.0f};

  @Test
  public void testHashCode_equalsConsistency() {
    final var subject1 = new Wavefront(ARRAY, null, ARRAY);
    final var subject2 = new Wavefront(SIMILAR_ARRAY, null, SIMILAR_ARRAY);

    final var hashCode1 = subject1.hashCode();
    final var hashCode2 = subject2.hashCode();

    assertThat(hashCode1).isEqualTo(hashCode2);
  }

  @Test
  public void testHashCode_internalConsistency() {
    var input = new float[] {1.0f};
    final var subject = new Wavefront(input, null, null);
    final var actual1 = subject.hashCode();

    subject.vertices()[0] = 2.0f; // change the value
    final var actual2 = subject.hashCode();

    assertThat(actual1).isNotEqualTo(actual2);
  }

  @SuppressWarnings({"EqualsWithItself", "ConstantConditions"})
  @Test
  public void testEquals_reflexive() {
    final var subject1 = new Wavefront(ARRAY, null, ARRAY);
    final var actual = subject1.equals(subject1);
    assertThat(actual).isTrue();
  }

  @Test
  public void testEquals_symmetric() {
    final var subject1 = new Wavefront(ARRAY, null, ARRAY);
    final var subject2 = new Wavefront(SIMILAR_ARRAY, null, SIMILAR_ARRAY);

    final var actual1 = subject1.equals(subject2);
    final var actual2 = subject2.equals(subject1);

    assertThat(actual1).isTrue();
    assertThat(actual2).isTrue();
  }

  @Test
  public void testEquals_transitive() {
    final var subject1 = new Wavefront(ARRAY, null, ARRAY);
    final var subject2 = new Wavefront(SIMILAR_ARRAY, null, SIMILAR_ARRAY);
    final var subject3 = new Wavefront(OTHER_SIMILAR_ARRAY, null, OTHER_SIMILAR_ARRAY);

    final var actual1 = subject1.equals(subject2);
    final var actual2 = subject2.equals(subject3);
    final var actual3 = subject1.equals(subject3);

    assertThat(actual1).isTrue();
    assertThat(actual2).isTrue();
    assertThat(actual3).isTrue();
  }

  @Test
  public void testEquals_notEqual() {
    final var subject1 = new Wavefront(ARRAY, null, null);
    final var subject2 = new Wavefront(OTHER_ARRAY, null, null);

    final var actual = subject1.equals(subject2);
    assertThat(actual).isFalse();
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testEquals_withNull() {
    final var subject1 = new Wavefront(ARRAY, null, ARRAY);
    final var actual = subject1.equals(null);
    assertThat(actual).isFalse();
  }

  @Test
  public void testToString() {
    final var subject1 = new Wavefront(ARRAY, null, OTHER_ARRAY);
    final var actual = subject1.toString();

    assertThat(actual).isEqualTo(
        "Wavefront[vertices=[1.0, 2.0, 3.0], textureCoordinates=null, normals=[9.0, 8.0, 7.0]]");
  }
}
