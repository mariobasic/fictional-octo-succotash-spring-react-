package web;

import domain.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.MapValidationErrorService;
import services.ProjectTaskService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

  private ProjectTaskService projectTaskService;

  private MapValidationErrorService mapValidationErrorService;

  @Autowired
  public BacklogController(ProjectTaskService projectTaskService, MapValidationErrorService mapValidationErrorService) {
    this.projectTaskService = projectTaskService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping("/{backlog_id}")
  public ResponseEntity<?> addPTtoBacklog(
      @Valid @RequestBody ProjectTask projectTask,
      BindingResult result,
      @PathVariable String backlog_id) {

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);

    if (errorMap != null) return errorMap;

    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);

    return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
  }
}
