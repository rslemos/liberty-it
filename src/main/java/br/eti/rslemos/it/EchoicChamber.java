package br.eti.rslemos.it;

import java.io.Reader;

import javax.enterprise.inject.Alternative;

@Alternative
public class EchoicChamber {
  public String introduce(String noise) {
    return noise;
  }

  public Reader introduce(Reader noise) {
    return noise;
  }
}
