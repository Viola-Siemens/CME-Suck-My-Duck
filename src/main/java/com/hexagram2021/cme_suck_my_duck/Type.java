package com.hexagram2021.cme_suck_my_duck;

import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import com.hexagram2021.cme_suck_my_duck.containers.FastContainers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("Convert2MethodRef")
public enum Type {
	LIST("List", "Ljava/util/List;", Containers::newWrappedList),
	SET("Set", "Ljava/util/Set;", Containers::newWrappedSet),
	MAP("Map", "Ljava/util/Map;", Containers::newWrappedMap),
	INT_2_OBJECT_MAP("Int2ObjectMap", "Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", (object) -> FastContainers.newInt2ObjectWrappedMap(object));

	private static final Map<String, Type> BY_NAME;
	private final String typeName;
	private final String typeFullClassName;
	private final Function<Object, Object> constructor;

	Type(String typeName, String typeFullClassName, Function<Object, Object> constructor) {
		this.typeName = typeName;
		this.typeFullClassName = typeFullClassName;
		this.constructor = constructor;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public String getTypeFullClassName() {
		return this.typeFullClassName;
	}

	@SuppressWarnings("unused")
	public Object construct(Object wrapped) {
		return this.constructor.apply(wrapped);
	}

	public static Type fromName(String typeName) {
		Type ret = BY_NAME.get(typeName);
		if (ret == null) {
			throw new IllegalArgumentException(String.format("No type named %s!", typeName));
		}
		return ret;
	}

	static {
		BY_NAME = new HashMap<>();
		for (Type type : values()) {
			BY_NAME.put(type.getTypeName(), type);
		}
	}
}
