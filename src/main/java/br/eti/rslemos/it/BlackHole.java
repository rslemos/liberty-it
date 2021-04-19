package br.eti.rslemos.it;

import javax.enterprise.inject.Alternative;

@Alternative
public class BlackHole {
  public void feed() {
    System.out.println("discarding");
  }
}
