package com.github.mwttg.wavefront.extractor;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FaceExtractorTest {

    @Test
    public void testFrom_type1() {
        final var parts = new String[]{"f", "1", "2", "3"};
        final var actual = FaceExtractor.from(parts);

        assertThat(actual).isEqualTo(new Triangle(
                new Index(1, null, null),
                new Index(2, null, null),
                new Index(3, null, null)));
    }

    @Test
    public void testFrom_type2() {
        final var parts = new String[]{"f", "1/4", "2/5", "3/6"};
        final var actual = FaceExtractor.from(parts);

        assertThat(actual).isEqualTo(new Triangle(
                new Index(1, 4, null),
                new Index(2, 5, null),
                new Index(3, 6, null)));
    }

    @Test
    public void testFrom_type3() {
        final var parts = new String[]{"f", "1//4", "2//5", "3//6"};
        final var actual = FaceExtractor.from(parts);

        assertThat(actual).isEqualTo(new Triangle(
                new Index(1, null, 4),
                new Index(2, null, 5),
                new Index(3, null, 6)));
    }

    @Test
    public void testFrom_type4() {
        final var parts = new String[]{"f", "1/4/7", "2/5/8", "3/6/9"};
        final var actual = FaceExtractor.from(parts);

        assertThat(actual).isEqualTo(new Triangle(
                new Index(1, 4, 7),
                new Index(2, 5, 8),
                new Index(3, 6, 9)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFrom_sizeOfPartsIsInvalid() {
        final var parts = new String[]{"f", "1/4/7", "2/5/8"};
        FaceExtractor.from(parts);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFrom_invalidIndexPattern() {
        final var parts = new String[]{"f", "1/4/7", "2/5/8", "3&3"};
        FaceExtractor.from(parts);
    }
}