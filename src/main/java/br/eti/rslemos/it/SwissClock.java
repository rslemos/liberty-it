package br.eti.rslemos.it;

import java.util.Date;

import javax.enterprise.inject.Alternative;

import java.text.DateFormat;

@Alternative
public class SwissClock {
  private static final DateFormat DATETIME_FORMAT = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);

  public String read() {
    final Date NOW = new Date();
    return DATETIME_FORMAT.format(NOW);
  }
}
