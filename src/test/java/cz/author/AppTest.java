package cz.author;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {
  String upperThree = "[Country{name='Hungary', rate=27.0}, Country{name='Sweden', rate=25.0}, Country{name='Denmark', rate=25.0}, Country{name='Finland', rate=24.0}, Country{name='Greece', rate=24.0}, Country{name='Croatia', rate=25.0}]";
  String lowerThree = "[Country{name='Malta', rate=18.0}, Country{name='Cyprus', rate=19.0}, Country{name='Luxembourg', rate=17.0}, Country{name='Romania', rate=19.0}, Country{name='Germany', rate=19.0}]";

  @Test
  public void testUpperThreeCountries() throws IOException {
    String data = App.getData(App.DATA_URL);
    List<Country> countries = App.filterCountries(data,FilterMethod.Upper);
    assertEquals(countries.toString(),upperThree);

  }
  @Test
  public void testLowerThreeCountries() throws IOException {
    String data = App.getData(App.DATA_URL);
    List<Country> countries = App.filterCountries(data,FilterMethod.Lower);
    assertEquals(countries.toString(),lowerThree);
  }
}