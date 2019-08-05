package web;

import domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ProjectService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  private ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping()
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

    if (result.hasErrors()) {

      Map<String, String> errorMap = result.getFieldErrors()
                                           .stream()
                                           .collect(Collectors.toMap(FieldError::getField,
                                               DefaultMessageSourceResolvable::getDefaultMessage, (s, s2) -> s2));

      return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    Project project1 = projectService.saveOrUpdate(project);
    return new ResponseEntity<>(project1, HttpStatus.CREATED);
  }

}
