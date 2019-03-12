package com.google.codeu.servlets;

import com.google.codeu.data.Circle;
import com.google.codeu.data.Datastore;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/circles")
public class CircleServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("application/json");
    
    List<Circle> circles = datastore.getCircles();
    Gson gson = new Gson();
    String json = gson.toJson(circles);

    response.getWriter().println(json);
  }

  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	  float x = Float.parseFloat(request.getParameter("x"));
	  float y = Float.parseFloat(request.getParameter("y"));
	  float size = Float.parseFloat(request.getParameter("size"));
	  Circle circle = new Circle(x, y, size);
	  datastore.storeCircle(circle);

  }
}
