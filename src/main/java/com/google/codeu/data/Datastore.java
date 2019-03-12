package com.google.codeu.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Provides access to the data stored in Datastore. */
public class Datastore {

  private DatastoreService datastore;

  public Datastore() {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  public void storeCircle(Circle circle) {
    Entity circleEntity = new Entity("Circle", circle.getId().toString());
    circleEntity.setProperty("x", circle.getX());
    circleEntity.setProperty("y", circle.getY());
    circleEntity.setProperty("size", circle.getSize());
    circleEntity.setProperty("timestamp", circle.getTimestamp());

    datastore.put(circleEntity);
  }

  public List<Circle> getCircles() {
    List<Circle> circles = new ArrayList<>();

    Query query = new Query("Circle").addSort("timestamp", SortDirection.ASCENDING);
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        String idString = entity.getKey().getName();
        UUID id = UUID.fromString(idString);
        float x = ((Double) entity.getProperty("x")).floatValue();
        float y = ((Double) entity.getProperty("y")).floatValue();
        float size = ((Double) entity.getProperty("size")).floatValue();
        
        long timestamp = (long) entity.getProperty("timestamp");

        Circle circle = new Circle(id, x, y, size, timestamp);
        circles.add(circle);
      } catch (Exception e) {
        System.err.println("Error reading data.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }

    return circles;
  }
}
