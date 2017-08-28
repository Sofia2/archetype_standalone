package com.indra.sofia2.archetype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.sofia2.archetype.service.PhoneRepository;
import com.indra.sofia2.archetype.service.bean.phone.ArchetypephoneOntology;
import com.indra.sofia2.beans.SofiaId;


@RestController
@RequestMapping("/phone")
public class PhoneController {
	
	@Autowired
	private PhoneRepository repository;
	
	@RequestMapping(method = RequestMethod.GET, value="/list")
    public String query() throws JsonProcessingException {
		List<ArchetypephoneOntology> retorno = repository.consulta();
		ObjectMapper objectMapper = new ObjectMapper();
		String arrayToJson = objectMapper.writeValueAsString(retorno);
		return arrayToJson;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String insert(@RequestBody ArchetypephoneOntology phone) throws JsonProcessingException {
    	SofiaId id = repository.insert(phone);
    	ObjectMapper objectMapper = new ObjectMapper();
		String arrayToJson = objectMapper.writeValueAsString(id);
		return arrayToJson;
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody ArchetypephoneOntology phone) throws JsonProcessingException {
		List<SofiaId> ids = repository.update(phone);
    	ObjectMapper objectMapper = new ObjectMapper();
		String arrayToJson = objectMapper.writeValueAsString(ids);
		return arrayToJson;
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value="id") String id) throws JsonProcessingException {
		List<SofiaId> ids =repository.delete(id);
    	ObjectMapper objectMapper = new ObjectMapper();
  		String arrayToJson = objectMapper.writeValueAsString(ids);
  		return arrayToJson;
    }

}
