package domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Project name is required")
  private String projectName;

  @NotBlank(message = "Project Identifier is required")
  @Size(min=4, max=5, message = "Please use 4 or 5 characters")
  @Column(updatable = false, unique = true)
  private String projectIdentifier;

  @NotBlank(message = "Project description is required")
  private String description;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private LocalDate startDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private LocalDate endDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private LocalDate createdAt;

  @JsonFormat(pattern = "yyyy-mm-dd")
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
