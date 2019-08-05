package web;

import domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.MapValidationErrorService;
import services.ProjectService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  private ProjectService projectService;

  private MapValidationErrorService mapValidationErrorService;

  @Autowired
  public ProjectController(ProjectService projectService, MapValidationErrorService mapValidationErrorService) {
    this.projectService = projectService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping()
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    if (errorMap!=null) return errorMap;

    Project project1 = projectService.saveOrUpdate(project);
    return new ResponseEntity<>(project1, HttpStatus.CREATED);
  }

}
