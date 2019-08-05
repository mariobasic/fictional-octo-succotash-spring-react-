package web;

import domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  private ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping()
  public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
    Project project1 = projectService.saveOrUpdate(project);
    return new ResponseEntity<>(project1, HttpStatus.CREATED);
  }
}
