package domain;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

@Entity
@Data
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String projectName;
  private String projectIdentifier;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;

  private LocalDate createdAt;
  private LocalDate updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDate.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDate.now();
  }
}
