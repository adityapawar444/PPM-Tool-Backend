package com.learning.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.ppmtool.domain.Project;
import com.learning.ppmtool.service.ProjectService;
import com.learning.ppmtool.service.ValidationErrorService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ValidationErrorService validationService;
	
	@PostMapping("")
	public ResponseEntity<?> saveOrUpdateProject(@Valid @RequestBody Project projectObj, BindingResult result){
		
		ResponseEntity<?> validationErrors = validationService.getAllValidationErrors(result);
		if(validationErrors != null)
			return validationErrors;
		
		Project savedOrUpdatedObj = projectService.saveOrUpdate(projectObj);
		return new ResponseEntity<Project>(savedOrUpdatedObj,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectByIdentifier(@PathVariable("projectId") String projectId){
		Project project = projectService.getProjectByIdentfier(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAlProjects(){
		return projectService.getAllProjects();
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable("projectId") String projectId){
		projectService.deleteProjectByIdentifier(projectId);
		return new ResponseEntity<String>("Project with ID: '" + projectId + "' deleted successfully",HttpStatus.OK);
	}

}
