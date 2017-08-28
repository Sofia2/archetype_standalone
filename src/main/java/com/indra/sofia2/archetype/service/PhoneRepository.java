package com.indra.sofia2.archetype.service;

import java.util.List;

import com.indra.sofia2.archetype.service.bean.phone.ArchetypephoneOntology;
import com.indra.sofia2.aspect.Param;
import com.indra.sofia2.aspect.Sofia2Delete;
import com.indra.sofia2.aspect.Sofia2Insert;
import com.indra.sofia2.aspect.Sofia2Query;
import com.indra.sofia2.aspect.Sofia2Repository;
import com.indra.sofia2.aspect.Sofia2Update;
import com.indra.sofia2.beans.SofiaId;

@Sofia2Repository("archetype_phone")
public interface PhoneRepository {
	
	@Sofia2Query("select * from archetype_phone")
	List<ArchetypephoneOntology> consulta();
	
	@Sofia2Query("select * from archetype_phone where archetype_phone.id=$id")
	List<ArchetypephoneOntology> consultaCausa(@Param("$id")String id);
	
	@Sofia2Update()
	List<SofiaId> update(ArchetypephoneOntology phone);
	
	@Sofia2Insert()
	SofiaId insert(ArchetypephoneOntology phone);
	
	@Sofia2Delete()
	List<SofiaId> delete(String id);

}
