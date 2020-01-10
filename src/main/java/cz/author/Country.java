package cz.author;

import com.jayway.jsonpath.JsonPath;
import java.util.Map;
import java.util.Objects;

public class Country {
  // implements Comparable

  public String name;
  public double stdRate;

  public Double getStdRate() {
    return this.stdRate;
  }

  public Country(Map<String,String> object){
    this.name = object.get("name");
    this.stdRate = JsonPath.read(object,"$.periods[0].rates.standard");
  }
  public Country(String name, double rate){
    this.name = name;
    this.stdRate =rate;
  }

  @Override
  public String toString() {
    return "Country{" +
            "name='" + name + '\'' +
            ", rate=" + this.stdRate +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Country country = (Country) o;
    return Double.compare(country.stdRate, stdRate) == 0 &&
            Objects.equals(name, country.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, stdRate);
  }

  //  @Override
//  public int compareTo(Object o) {
//    return (int) (this.getStdRate() - ((Country) o).getStdRate());
//  }
}
