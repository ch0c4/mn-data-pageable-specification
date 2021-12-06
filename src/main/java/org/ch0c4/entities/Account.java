package org.ch0c4.entities;

import io.micronaut.core.annotation.Nullable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull private String username;

  @ManyToOne private Customer customer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Nullable
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(@Nullable Customer customer) {
    this.customer = customer;
  }
}
