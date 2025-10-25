package com.hexagram2021.cme_suck_my_duck;

import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import com.hexagram2021.cme_suck_my_duck.containers.FastContainers;
import com.hexagram2021.cme_suck_my_duck.containers.GuavaContainers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

@SuppressWarnings({"Convert2MethodRef", "java:S1612"})
public enum Type {
	LIST("List", "Ljava/util/List;", Containers::newWrappedList),
	SET("Set", "Ljava/util/Set;", Containers::newWrappedSet),
	MAP("Map", "Ljava/util/Map;", Containers::newWrappedMap),
	ITERATOR("Iterator", "Ljava/util/Iterator;", Containers::newWrappedIterator),
	LIST_ITERATOR("ListIterator", "Ljava/util/ListIterator;", Containers::newWrappedListIterator),
	@Deprecated @SuppressWarnings("java:S1874")
	ARRAY_LIST("ArrayList", "Ljava/util/List;", Containers::newWrappedArrayList),
	INT_LIST("IntList", "Lit/unimi/dsi/fastutil/ints/IntList;", object -> FastContainers.newIntWrappedList(object)),
	LONG_LIST("LongList", "Lit/unimi/dsi/fastutil/longs/LongList;", object -> FastContainers.newLongWrappedList(object)),
	OBJECT_LIST("ObjectList", "Lit/unimi/dsi/fastutil/objects/ObjectList;", object -> FastContainers.newObjectWrappedList(object)),
	INT_SET("IntSet", "Lit/unimi/dsi/fastutil/ints/IntSet;", object -> FastContainers.newIntWrappedSet(object)),
	LONG_SET("LongSet", "Lit/unimi/dsi/fastutil/longs/LongSet;", object -> FastContainers.newLongWrappedSet(object)),
	OBJECT_SET("ObjectSet", "Lit/unimi/dsi/fastutil/objects/ObjectSet;", object -> FastContainers.newObjectWrappedSet(object)),
	INT_2_OBJECT_MAP("Int2ObjectMap", "Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", object -> FastContainers.newInt2ObjectWrappedMap(object)),
	OBJECT_2_INT_MAP("Object2IntMap", "Lit/unimi/dsi/fastutil/objects/Object2IntMap;", object -> FastContainers.newObject2IntWrappedMap(object)),
	LONG_2_OBJECT_MAP("Long2ObjectMap", "Lit/unimi/dsi/fastutil/longs/Long2ObjectMap;", object -> FastContainers.newLong2ObjectWrappedMap(object)),
	OBJECT_2_LONG_MAP("Object2LongMap", "Lit/unimi/dsi/fastutil/objects/Object2LongMap;", object -> FastContainers.newObject2LongWrappedMap(object)),
	INT_ITERATOR("IntIterator", "Lit/unimi/dsi/fastutil/ints/IntIterator;", object -> FastContainers.newIntIterator(object)),
	LONG_ITERATOR("LongIterator", "Lit/unimi/dsi/fastutil/longs/LongIterator;", object -> FastContainers.newLongIterator(object)),
	OBJECT_ITERATOR("ObjectIterator", "Lit/unimi/dsi/fastutil/objects/ObjectIterator;", object -> FastContainers.newObjectIterator(object)),
	INT_LIST_ITERATOR("IntListIterator", "Lit/unimi/dsi/fastutil/ints/IntListIterator;", object -> FastContainers.newIntListIterator(object)),
	LONG_LIST_ITERATOR("LongListIterator", "Lit/unimi/dsi/fastutil/longs/LongListIterator;", object -> FastContainers.newLongListIterator(object)),
	OBJECT_LIST_ITERATOR("ObjectListIterator", "Lit/unimi/dsi/fastutil/objects/ObjectListIterator;", object -> FastContainers.newObjectListIterator(object)),
	BI_MAP("BiMap", "Lcom/google/common/collect/BiMap;", object -> GuavaContainers.newWrappedBiMap(object)),
	MULTISET("Multiset", "Lcom/google/common/collect/Multiset;", object -> GuavaContainers.newWrappedMultiset(object)),
	MULTIMAP("Multimap", "Lcom/google/common/collect/Multimap;", object -> GuavaContainers.newWrappedMultimap(object));

	private static final Map<String, Type> BY_NAME;
	private final String typeName;
	private final String typeFullClassName;
	private final UnaryOperator<Object> constructor;

	Type(String typeName, String typeFullClassName, UnaryOperator<Object> constructor) {
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
