package io.github.mwttg.wavefront.utilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import org.testng.annotations.Test;

public class FileUtilitiesIntegrationTest {

  @Test
  public void testFrom() throws Exception {
    final var filename = "valid-textfile.txt";
    final var actual = FileUtilities.readFromResources(filename);
    final var expected = List.of("1. line", "2. line", "3. line");

    assertThat(actual).isEqualTo(expected);
  }

  @Test(expectedExceptions = IOException.class)
  public void testFrom_FileDoesNotExist() throws Exception {
    final var filename = "does-not-exist.txt";
    FileUtilities.readFromResources(filename);
  }
}