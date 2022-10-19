package com.learning.ppmtool.service;

import com.learning.ppmtool.domain.Project;

public interface ProjectService {
	
	public Project saveOrUpdate(Project projectObj);

	public Project getProjectByIdentfier(String projectId);

	public Iterable<Project> getAllProjects();

	public void deleteProjectByIdentifier(String projectId);

}
