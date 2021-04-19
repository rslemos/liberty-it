package br.eti.rslemos.it;

import javax.enterprise.inject.Produces;

public class MassiveStar {
  @Produces BlackHole collapse() {
    return new BlackHole();
  }
}
