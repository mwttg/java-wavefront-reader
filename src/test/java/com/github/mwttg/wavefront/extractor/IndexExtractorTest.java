package com.github.mwttg.wavefront.extractor;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexExtractorTest {

    @Test
    public void testFrom_Type1() {
        final var definition = "1";
        final var actual = IndexExtractor.from(definition);
        assertThat(actual).isEqualTo(new Index(0, null, null));
    }

    @Test
    public void testFrom_Type2() {
        final var definition = "1/2";
        final var actual = IndexExtractor.from(definition);
        assertThat(actual).isEqualTo(new Index(0, 1, null));
    }

    @Test
    public void testFrom_Type3() {
        final var definition = "1//2";
        final var actual = IndexExtractor.from(definition);
        assertThat(actual).isEqualTo(new Index(0, null, 1));
    }

    @Test
    public void testFrom_Type4() {
        final var definition = "1/2/3";
        final var actual = IndexExtractor.from(definition);
        assertThat(actual).isEqualTo(new Index(0, 1, 2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFrom_unknownType() {
        final var definition = "1/2/3/5";
        IndexExtractor.from(definition);
    }

    @Test
    public void testGetTypeFrom_Type1() {
        final var definition = "1";
        final var actual = IndexExtractor.Type.getTypeFrom(definition);
        assertThat(actual).isEqualTo(IndexExtractor.Type.TYPE1);
    }

    @Test
    public void testGetTypeFrom_Type2() {
        final var definition = "1/2";
        final var actual = IndexExtractor.Type.getTypeFrom(definition);
        assertThat(actual).isEqualTo(IndexExtractor.Type.TYPE2);
    }

    @Test
    public void testGetTypeFrom_Type3() {
        final var definition = "1//2";
        final var actual = IndexExtractor.Type.getTypeFrom(definition);
        assertThat(actual).isEqualTo(IndexExtractor.Type.TYPE3);
    }

    @Test
    public void testGetTypeFrom_Type4() {
        final var definition = "1/2/3";
        final var actual = IndexExtractor.Type.getTypeFrom(definition);
        assertThat(actual).isEqualTo(IndexExtractor.Type.TYPE4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetTypeFrom_UnknownType() {
        final var definition = "1/2/3/5";
        IndexExtractor.Type.getTypeFrom(definition);
    }
}