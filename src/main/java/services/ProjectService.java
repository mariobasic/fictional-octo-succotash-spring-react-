package services;

import domain.Project;
import exceptions.ProjectIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProjectRepository;

import java.util.Optional;

@Service
public class ProjectService {

  private ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public Project saveOrUpdate(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      return projectRepository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exists");
    }

  }

  public Project findProjectByIdentifier(String projectId) {
    Optional<Project> project = projectRepository.findByProjectIdentirier(projectId.toUpperCase());

    return project.orElseThrow(() -> new ProjectIdException("Project ID " + projectId + " does not exist"));
  }

}
