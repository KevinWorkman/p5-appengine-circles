package com.google.codeu.data;

import java.util.UUID;

public class Circle {

  private UUID id;
  private float x;
  private float y;
  private float size;
  long timestamp;
  
  public Circle(float x, float y, float size) {
    this(UUID.randomUUID(), x, y, size, System.currentTimeMillis());
  }

  public Circle(UUID id, float x, float y, float size, long timestamp) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.size = size;
    this.timestamp = timestamp;
  }

  public float getX() {
	  return x;
  }
  
  public float getY() {
	  return y;
  }
  
  public float getSize() {
	  return size;
  }
  
  public UUID getId() {
    return id;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
