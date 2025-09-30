package com.hexagram2021.cme_suck_my_duck;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hexagram2021.cme_suck_my_duck.transformers.*;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import com.hexagram2021.cme_suck_my_duck.utils.SharedConstants;
import org.objectweb.asm.Opcodes;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import static com.hexagram2021.cme_suck_my_duck.utils.SharedConstants.LOG_PATH;

public class CMESuckMyDuck {
	public static final Gson GSON = new Gson();
	public static final Log logger = new Log(LOG_PATH, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
	public static final int ASM_API_VERSION;
	public static final boolean INJECT_METHOD;
	@Nullable
	public static final Integer LOCAL_VAR_INDEX;

	@Nullable
	public static final String TRACE_ID_UPDATER;

	public static void main(String[] args) {
		System.out.println("This project can only be used as javaagent.");
		System.out.println("Usage:");
		System.out.println("\t-javaagent:CMESuckMyDuck-<version>.jar=<class full name>;<field name>;<type>;<phase>");
		System.out.println("For example:");
		System.out.println("\t-javaagent:mods/CMESuckMyDuck-" + SharedConstants.VERSION + ".jar=net/minecraft/server/packs/resources/ReloadableResourceManager;f_203816_;List;nonstatic");
		System.out.println("Which means, each modification of list `f_203816_`, which is a nonstatic member in `ReloadableResourceManager`, will be traced - when add and remove is called, a stacktrace will be printed to the log.");
		System.out.println("All valid types:");
		for(Type type: Type.values()) {
			System.out.printf(" -\t%s\n", type.getTypeName());
		}
		System.out.println("All valid phases:");
		for(Phase phase: Phase.values()) {
			System.out.printf(" -\t%s\n", phase.getPhaseName());
		}
		System.out.println();
		System.out.println("Other JVM args:");
		System.out.println(" -Dcme_suck_my_duck.log_level=<level>");
		System.out.println("\tDefault <level>=1, which means no debug message will be logged. If <level> is 0, query functions like Map#get, Set#containsAll will also be logged. This is NOT recommended because it may make the log files too long to be read.");
		System.out.println(" -Dcme_suck_my_duck.asm_api_version=<version>");
		System.out.println("\tDefault <version>=9, which means we use ASM API of version 9.x. For older versions of Minecraft (such as 1.12.2), API level operations such as ASM_9 cannot be applied, so you can set it to a lower value (suck as 5).");
		System.out.println(" -Dcme_suck_my_duck.file_max_entries=<size>");
		System.out.println("\tDefault <size>=1000, which means after every 1000 stack traces, old log file will be deleted, and new log file with 1000 stack traces will be renamed, and a newer log file with the latest stack trace will be create - when system crashes, the latest 1001 ~ 2000 stack traces will be accessible in two log files.");
		System.out.println(" -Dcme_suck_my_duck.log_wait_time=<milliseconds>");
		System.out.println("\tDefault <milliseconds>=500, which means log file I/O will be triggered every half second. All cached stack traces will be logged after log file I/O.");
		System.out.println(" -Dcme_suck_my_duck.whitelist_constructor_stacktrace=<str>");
		System.out.println("\tDefault <str> is an empty string, which means all containers will be monitored if class name matches and field name matches. If not empty, the container will only be monitored if any line in the stack trace where the container is constructed includes the content of <str>.");
		System.out.println(" -Dcme_suck_my_duck.transform_to_thread_safe=<bool>");
		System.out.println("\tDefault <bool>=false. If true, no stack traces will be logged and the container will be converted into a thread-safe container. This is NOT recommended unless you like slowness and don't want to fix the problem.");
		System.out.println(" -Dcme_suck_my_duck.inject_method=<bool>");
		System.out.println("\tDefault <bool>=false. If true, you should use `-javaagent:CMESuckMyDuck-<version>.jar=<class full name>;<method name>` and whenever this method is called, you will receive a stack trace in log files.");
		System.out.println(" -Dcme_suck_my_duck.stop_logging_if_exception_created=<bool>");
		System.out.println("\tDefault <bool>=true. If false, it still keeps logging after critical exception being thrown.");
		System.out.println(" -Dcme_suck_my_duck.trace_id_updater=<class full name>;<method name>");
		System.out.println("\tDefault empty string. If not empty, the given method will also be called to update trace id, which might be useful in `inject_method` mode.");
	}

	public static void premain(String agentArg, Instrumentation inst) {
		//Pre-Load
		try (InputStream is = CMESuckMyDuck.class.getResourceAsStream("/meta.json")) {
			String version = GSON.fromJson(new InputStreamReader(Objects.requireNonNull(is)), JsonObject.class).get("version").getAsString();
			logger.info(String.format("CMESuckMyDuck v%s", version));
		} catch (Exception e) {
			logger.error("Error getting version of CMESuckMyDuck.");
			logger.error(e);
		}

		String[] args = agentArg.split(";");
		if(INJECT_METHOD) {
			if (LOCAL_VAR_INDEX != null) {
				logger.error("Cannot inject method when local variable index is specified.");
				return;
			}
			if (args.length < 2) {
				logger.error("Failed to parse agent arguments. Expect 2 arguments, found %d: [%s].", args.length, Log.buildArrayString(args));
				return;
			}
			// net.minecraft.client.renderer.item.ItemProperties => net/minecraft/client/renderer/item/ItemProperties
			args[0] = args[0].replace(".", "/");
			// Inject method Main
			inst.addTransformer(new InjectLogTransformer(args[0], args[1]), true);
			logger.info("Successfully added transformer for method %s of class %s.", args[1], args[0]);
		} else if(LOCAL_VAR_INDEX != null) {
			if(LOCAL_VAR_INDEX < 0) {
				logger.error("Local variable index cannot be negative.");
				return;
			}
			if (args.length < 3) {
				logger.error("Failed to parse agent arguments. Expect 3 arguments, found %d: [%s].", args.length, Log.buildArrayString(args));
				return;
			}
			// net.minecraft.client.renderer.item.ItemProperties => net/minecraft/client/renderer/item/ItemProperties
			args[0] = args[0].replace(".", "/");
			// launch([Ljava.lang.String?)V => launch([Ljava.lang.String;)V
			args[1] = args[1].replace("?", ";");
			// Local Main
			inst.addTransformer(new WrapLocalContainerTransformer(args[0], args[1], Type.fromName(args[2]), LOCAL_VAR_INDEX), true);
			logger.info("Successfully added transformer for local field (index %d) of method %s of class %s, type %s.", LOCAL_VAR_INDEX, args[1], args[0], args[2]);
		} else {
			//Parse
			if (args.length < 4) {
				logger.error("Failed to parse agent arguments. Expect 4 arguments, found %d: [%s].", args.length, Log.buildArrayString(args));
				return;
			}
			// net.minecraft.client.renderer.item.ItemProperties => net/minecraft/client/renderer/item/ItemProperties
			args[0] = args[0].replace(".", "/");
			// Main
			inst.addTransformer(new WrapContainerTransformer(args[0], args[1], Type.fromName(args[2]), Phase.fromName(args[3])), true);
			logger.info("Successfully added transformer for field %s of class %s, type %s, phase %s.", args[1], args[0], args[2], args[3]);
		}
		if(TRACE_ID_UPDATER != null && !TRACE_ID_UPDATER.isEmpty()) {
			String[] updaterArgs = TRACE_ID_UPDATER.split(";");
			if(updaterArgs.length < 2) {
				logger.error("Failed to parse trace id updater arguments. Expect 2 arguments, found %d: [%s].", updaterArgs.length, Log.buildArrayString(updaterArgs));
				return;
			}
			updaterArgs[0] = updaterArgs[0].replace(".", "/");
			inst.addTransformer(new InjectTraceIdUpdaterTransformer(updaterArgs[0], updaterArgs[1]), true);
			logger.info("Successfully added trace id updater for method %s of class %s.", updaterArgs[1], updaterArgs[0]);
		}
	}

	static {
		int asmApiVersion = Opcodes.ASM9;
		try {
			int level = Integer.parseInt(System.getProperty("cme_suck_my_duck.asm_api_version"));
			asmApiVersion = level << 16;
		} catch (Exception ignored) {
		}
		ASM_API_VERSION = asmApiVersion;
		boolean injectMethod = false;
		try {
			injectMethod = Boolean.parseBoolean(System.getProperty("cme_suck_my_duck.inject_method"));
		} catch (Exception ignored) {
		}
		INJECT_METHOD = injectMethod;

		Integer localVarIndex = null;
		try {
			localVarIndex = Integer.parseInt(System.getProperty("cme_suck_my_duck.local_var_index"));
		} catch (Exception ignored) {
		}
		LOCAL_VAR_INDEX = localVarIndex;

		TRACE_ID_UPDATER = System.getProperty("cme_suck_my_duck.trace_id_updater");
	}
}