package services;

import domain.Backlog;
import domain.Project;
import exceptions.ProjectIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.BacklogRepository;
import repositories.ProjectRepository;

import java.util.Optional;

@Service
public class ProjectService {

  private ProjectRepository projectRepository;
  private BacklogRepository backlogRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
    this.projectRepository = projectRepository;
    this.backlogRepository = backlogRepository;
  }

  public Project saveOrUpdate(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

      if (project.getId() == null) {
        Backlog backlog = new Backlog();
        project.setBacklog(backlog);
        backlog.setProject(project);
        backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      }

      if (project.getId() != null) {
        project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
      }

      return projectRepository.save(project);

    } catch (Exception e) {
      throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exists");
    }

  }

  public Project findProjectByIdentifier(String projectId) {
    Optional<Project> project = projectRepository.findByProjectIdentirier(projectId.toUpperCase());

    return project.orElseThrow(() -> new ProjectIdException("Project ID " + projectId + " does not exist"));
  }

  public Iterable<Project> findAllProjects() {
    return projectRepository.findAll();
  }

  public void deleteProjectByIdentifier(String projectId) {
    Project project = projectRepository.findByProjectIdentirier(projectId.toUpperCase())
                                       .orElseThrow(() -> new ProjectIdException(
                                                     " Your cannot delete project with id " + projectId));
    projectRepository.delete(project);
  }

}
