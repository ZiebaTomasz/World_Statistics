package com.tom.world.domain;


import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Word {

  @Id
  private UUID uuid;
  private String word;
  private Integer occurrences;

}
