package br.eti.rslemos.it;

import javax.enterprise.inject.Produces;

public class EchoicChamberManufacturer {
  @Produces EchoicChamber build() {
    return new EchoicChamber();
  }
}
