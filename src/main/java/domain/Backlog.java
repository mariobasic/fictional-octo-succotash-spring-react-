package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
public class Backlog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer PTSequence = 0;
  private String projectIdentifier;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "project_id", nullable = false)
  @JsonIgnore
  private Project project;

  //OneToMany projecttasks
}
