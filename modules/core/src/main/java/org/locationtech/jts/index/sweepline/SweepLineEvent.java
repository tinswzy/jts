


/*
 * The JTS Topology Suite is a collection of Java classes that
 * implement the fundamental operations required to validate a given
 * geo-spatial data set to a known topological specification.
 * 
 * Copyright (C) 2016 Vivid Solutions
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the Vivid Solutions BSD
 * License v1.0 (found at the root of the repository).
 * 
 */
package org.locationtech.jts.index.sweepline;

/**
 * @version 1.7
 */
public class SweepLineEvent
  implements Comparable
{
  public static final int INSERT = 1;
  public static final int DELETE = 2;

  private double xValue;
  private int eventType;
  private SweepLineEvent insertEvent; // null if this is an INSERT event
  private int deleteEventIndex;

  SweepLineInterval sweepInt;
  public SweepLineEvent(double x, SweepLineEvent insertEvent, SweepLineInterval sweepInt)
  {
    xValue = x;
    this.insertEvent = insertEvent;
    this.eventType = INSERT;
    if (insertEvent != null)
      eventType = DELETE;
    this.sweepInt = sweepInt;
  }

  public boolean isInsert() { return insertEvent == null; }
  public boolean isDelete() { return insertEvent != null; }
  public SweepLineEvent getInsertEvent() { return insertEvent; }
  public int getDeleteEventIndex() { return deleteEventIndex; }
  public void setDeleteEventIndex(int deleteEventIndex) { this.deleteEventIndex = deleteEventIndex; }

  SweepLineInterval getInterval() { return sweepInt; }

  /**
   * ProjectionEvents are ordered first by their x-value, and then by their eventType.
   * It is important that Insert events are sorted before Delete events, so that
   * items whose Insert and Delete events occur at the same x-value will be
   * correctly handled.
   */
  public int compareTo(Object o) {
    SweepLineEvent pe = (SweepLineEvent) o;
    if (xValue < pe.xValue) return  -1;
    if (xValue > pe.xValue) return   1;
    if (eventType < pe.eventType) return  -1;
    if (eventType > pe.eventType) return   1;
    return 0;
  }


}