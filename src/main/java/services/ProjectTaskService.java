package services;

import domain.Backlog;
import domain.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.BacklogRepository;
import repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

  private BacklogRepository backlogRepository;
  private ProjectTaskRepository projectTaskRepository;
  
  @Autowired
  public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository) {
    this.backlogRepository = backlogRepository;
    this.projectTaskRepository = projectTaskRepository;
  }
  
  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
    projectTask.setBacklog(backlog);
    Integer backlogSequence = backlog.getPTSequence();
    backlogSequence++;

    projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);

    if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
      projectTask.setPriority(3);
    }

    if (projectTask.getStatus().equals("") || projectTask.getStatus() == null) {
      projectTask.setStatus("TODO");
    }

    return projectTaskRepository.save(projectTask);
  }
}
