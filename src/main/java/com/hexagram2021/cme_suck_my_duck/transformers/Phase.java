package com.hexagram2021.cme_suck_my_duck.transformers;

import java.util.HashMap;
import java.util.Map;

public enum Phase {
	STATIC("static"), NONSTATIC("nonstatic");

	private static final Map<String, Phase> BY_NAME;
	private final String phaseName;

	Phase(String phaseName) {
		this.phaseName = phaseName;
	}

	public String getPhaseName() {
		return this.phaseName;
	}

	public static Phase fromName(String phaseName) {
		Phase ret = BY_NAME.get(phaseName);
		if (ret == null) {
			throw new IllegalArgumentException(String.format("No phase named %s!", phaseName));
		}
		return ret;
	}

	public boolean isStatic() {
		return this == STATIC;
	}

	static {
		BY_NAME = new HashMap<>();
		for (Phase phase : values()) {
			BY_NAME.put(phase.getPhaseName(), phase);
		}
	}
}
