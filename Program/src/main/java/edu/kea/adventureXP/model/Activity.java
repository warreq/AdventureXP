package edu.kea.adventureXP.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class representing an Activity with a name, description and price.
 */
@Entity
@Table(name = "ACTIVITY")
public class Activity implements Comparable<Activity> {
  
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private long    id;
  
  @Column
  private String  name;
  
  @Column
  private String  description;
  
  @Column
  private double  price;
  
  @Column
  private boolean isActive;
  
  /**
   * Controller for creating an Activity parsing its name, description and
   * price.
   * 
   * @param name The name of the Activity
   * @param description A description describing the Activity
   * @param price The price of the activity (per person per hour)
   */
  public Activity(String name, String description, double price, boolean isActive) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.isActive = isActive;
  }
  
  /**
   * Constructor for creating an Activity only parsing in the name of said
   * Activity.
   * 
   * @param name The name of the Activity.
   */
  public Activity(String name) {
    this(name, "", 0, true);
  }
  
  public Activity() {
    
  }
  
  public boolean getIsActive() {
    return this.isActive;
  }
  
  public void setIsActive(boolean active) {
    this.isActive = active;
  }
  
  public long getId() {
    return this.id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public double getPrice() {
    return this.price;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  @Override
  public int compareTo(Activity o) {
    return -1;
  }
  
  public int hashCode() {
      return (int) (this.name.hashCode() + this.id + this.description.hashCode());
  }
  
  public String toString() {
      StringBuilder b = new StringBuilder();
      b.append(this.id);
      b.append(" : ");
      b.append(this.name);
      b.append(" : ");
      if(this.isActive) {
          b.append("active");
      } else {
          b.append("inactive");
      }
      return b.toString();
  }
  
}
