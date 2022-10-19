package com.learning.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.ppmtool.domain.Project;
import com.learning.ppmtool.exceptions.ProjectIdException;
import com.learning.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRespository;

	@Override
	public Project saveOrUpdate(Project projectObj) {
		try {
			projectObj.setProjectIdentifier(projectObj.getProjectIdentifier().toUpperCase());
			return projectRespository.save(projectObj);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Proejct ID '" + projectObj.getProjectIdentifier().toUpperCase() + "' already Exists");
		}
	}

	@Override
	public Project getProjectByIdentfier(String projectId) {

		Project project = projectRespository.findProjectByProjectIdentifier(projectId);

		if (project == null)
			throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exist");

		return project;
	}

	@Override
	public Iterable<Project> getAllProjects() {
		return projectRespository.findAll();
	}

	@Override
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRespository.findProjectByProjectIdentifier(projectId);

		if (project == null)
			throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exist");
		
		projectRespository.delete(project);

	}

}
