package com.hexagram2021.cme_suck_my_duck.utils;

import java.security.SecureRandom;

public final class TraceIdGenerator {
	private static final SecureRandom random = new SecureRandom();

	private static String globalTraceId = generateTraceId();

	public static String generateTraceId() {
		return Integer.toHexString(random.nextInt());
	}

	public static void updateTraceId() {
		globalTraceId = generateTraceId();
	}

	public static String getGlobalTraceId() {
		return globalTraceId;
	}

	private TraceIdGenerator() {
	}
}
