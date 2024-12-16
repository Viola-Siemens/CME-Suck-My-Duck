# CME-Suck-My-Duck
Output all stack traces when modifying a certain collection for debugging `ConcurrentModificationException` and `ArrayIndexOutOfBoundsException`.

## Usage for Minecraft

First, add this jar to `mods` folder.

Second, edit your Java Virtual Machine Argument in your launcher. Add `-javaagent:mods/CMESuckMyDuck-<version>.jar=<class full name>;<field name>;<type>;<phase>`.

Finally, run the game, play and wait until the crash happens.

## Arguments

### \<class full name\>

A full name of the class, which has a container that you would like to monitor. Use `\` instead of `.` (a.k.a. the internal name of class).

### \<field name\>

A field name of the container in target class, which you would like to monitor.
For Forge, use SRG name.
For Fabric, use intermediary name.
For NeoForge, use official name.

### \<type\>

Currently, we only support three containers: `List`, `Set`, `Map`. This argument indicates the type of monitored container.

### \<phase\>

`static` or `nonstatic`. This argument indicates the container is a static field or non-static field.

## Examples

### ConcurrentModificationException from SoundEngine in Forge 1.20.1 Environment

`-javaagent:mods/CMESuckMyDuck-1.0.0.jar=net/minecraft/client/sounds/SoundEngine;f_120229_;Map;nonstatic`

### ConcurrentModificationException from PotionBrewing in Forge 1.20.1 Environment

`-javaagent:mods/CMESuckMyDuck-1.0.0.jar=net/minecraft/world/item/alchemy/PotionBrewing;f_43494_;List;static`

### ArrayIndexOutOfBoundsException from Zeta mod

`-javaagent:CMESuckMyDuck-1.0.0.jar=org/violetmoon/zetaimplforge/event/ForgeZetaEventBus;convertedHandlers;Map;nonstatic`

## Other options

### Log Level

Use system property `-Dcme_suck_my_duck.log_level=<level>` to set custom log level.

Default 1, which means no debug message will be logged. Users can set it to 0 to output debug message.