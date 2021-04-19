package br.eti.rslemos.it;

import javax.enterprise.inject.Produces;

public class ClockMaker {
  @Produces SwissClock make() {
    return new SwissClock();
  }
}
