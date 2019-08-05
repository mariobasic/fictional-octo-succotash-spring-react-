package domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class ProjectTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(updatable = false)
  private String projectSequence;

  @NotBlank(message = "Please include a project summary")
  private String summary;

  private String acceptanceCriteria;
  private String status;
  private Integer priority;
  private LocalDate dueDate;
  private LocalDate createdAt;
  private LocalDate updateAt;

  // ManyToOne with Backlog
  @Column(updatable = false)
  private String projectIdentifier;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDate.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updateAt = LocalDate.now();
  }
}
