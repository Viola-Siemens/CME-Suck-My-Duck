# CME-Suck-My-Duck
Output all stack traces when modifying a certain collection for debugging `ConcurrentModificationException` and `IndexOutOfBoundsException`.

## Usage for Minecraft

First, add this jar to `mods` folder.

Second, edit your Java Virtual Machine Argument in your launcher. Add `-javaagent:mods/CMESuckMyDuck-<version>.jar=<class full name>;<field name>;<type>;<phase>`.

Finally, run the game, play and wait until the crash happens.

## Usage for Other Java Projects

Similar to Minecraft. The only different step is that you should add `gson` and `asm` jar to classpath (-cp) before javaagent, and add our `CMESuckMyDuck-<version>.jar` to classpath after javaagent.

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

## Experiments

Code (simulates concurrent modification):

```java
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static final List<Integer> LIST = new ArrayList<>();
	public static final Random RANDOM = new Random(42);

	private static void modify1() {
		int cnt = 8;

		Random random = new Random(RANDOM.nextInt());
		int a = -1;
		try {
			while (cnt > 0) {
				if (LIST.contains(a)) {
					LIST.remove(a);
					cnt -= 1;
				}
				a = random.nextInt(256);

				try {
					Thread.sleep(RANDOM.nextInt(100));
				} catch (InterruptedException e) {
					System.err.println("[" + DATE_FORMAT.format(new Date()) + "] " + Thread.currentThread().getName());
					e.printStackTrace();
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("[" + DATE_FORMAT.format(new Date()) + "] " + Thread.currentThread().getName());
			e.printStackTrace();
		}
	}

	private static void modify2() {
		int cnt = 32;

		Random random = new Random(RANDOM.nextInt());
		int a = random.nextInt(256);
		try {
			while(cnt > 0) {
				if(!LIST.contains(a)) {
					LIST.add(a);
					cnt -= 1;
				}
				a = random.nextInt(256);

				try {
					Thread.sleep(RANDOM.nextInt(100));
				} catch(InterruptedException e) {
					System.err.println("[" + DATE_FORMAT.format(new Date()) + "] " + Thread.currentThread().getName());
					e.printStackTrace();
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("[" + DATE_FORMAT.format(new Date()) + "] " + Thread.currentThread().getName());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		List<Integer> temp = new ArrayList<>();
		for(int i = 0; i < 200; ++i) {
			temp.add(i);
		}

		LIST.addAll(temp);
		Thread t2 = new Thread(Main::modify2, "modify-2");
		t2.start();
		Thread t1 = new Thread(Main::modify1, "modify-1");
		t1.start();
		Thread t3 = new Thread(Main::modify1, "modify-3");
		t3.start();
		try {
			for(int i: LIST) {
				System.out.println(i);
				t1.join();
				t2.join();
				t3.join();
			}
		} catch(ConcurrentModificationException e) {
			System.err.println("[" + DATE_FORMAT.format(new Date()) + "] " + Thread.currentThread().getName());
			e.printStackTrace();
		}

		System.out.println("size: " + LIST.size());
	}
}
```

w.o. our agent:

![](readme-assets/wo.png)

with our agent:

![](readme-assets/with.png)

With the modification history of `LIST`, we can know which threads cause the crash.