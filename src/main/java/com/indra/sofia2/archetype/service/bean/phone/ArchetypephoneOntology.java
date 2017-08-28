package com.indra.sofia2.archetype.service.bean.phone;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.indra.sofia2.beans.Ontology;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * archetype_phone Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "archetype_phone"
})
public class ArchetypephoneOntology
    extends Ontology<ArchetypePhone>
    implements Serializable
{

    /**
     * Info archetype_phone
     * (Required)
     * 
     */
    @JsonProperty("archetype_phone")
    @JsonPropertyDescription("Info archetype_phone")
    private ArchetypePhone archetypePhone;

    /**
     * Info archetype_phone
     * (Required)
     * 
     */
    @JsonProperty("archetype_phone")
    public ArchetypePhone getArchetypePhone() {
        return archetypePhone;
    }

    /**
     * Info archetype_phone
     * (Required)
     * 
     */
    @JsonProperty("archetype_phone")
    public void setArchetypePhone(ArchetypePhone archetypePhone) {
        this.archetypePhone = archetypePhone;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(archetypePhone).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ArchetypephoneOntology) == false) {
            return false;
        }
        ArchetypephoneOntology rhs = ((ArchetypephoneOntology) other);
        return new EqualsBuilder().append(archetypePhone, rhs.archetypePhone).isEquals();
    }

    public ArchetypePhone getData() {
        return archetypePhone;
    }

    public void setData(ArchetypePhone archetypePhone) {
        this.archetypePhone = archetypePhone;
    }

}

