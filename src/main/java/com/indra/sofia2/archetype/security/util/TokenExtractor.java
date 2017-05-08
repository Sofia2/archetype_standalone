package com.indra.sofia2.archetype.security.util;

public interface TokenExtractor {
	
	/**
	 * Extracts the token from the header
	 * @param header
	 * @return
	 */
	
	String extract(String header);
}
