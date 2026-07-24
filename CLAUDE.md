# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述喵~

CME-Suck-My-Duck 是一个 Java 字节码增强工具，用于调试和追踪 Java 集合（List、Set、Map 等）的并发修改异常（ConcurrentModificationException）和索引越界异常（IndexOutOfBoundsException）喵~。该工具通过 Java Agent 技术，在运行时动态修改字节码，包装目标集合并记录所有修改操作的栈跟踪信息喵~。

## 构建与测试命令喵~

### 构建项目
```bash
# Windows
gradlew.bat build

# Unix/Linux/Mac
./gradlew build
```

### 运行测试
```bash
# Windows
gradlew.bat test

# Unix/Linux/Mac
./gradlew test
```

### 清理构建产物
```bash
# Windows
gradlew.bat clean

# Unix/Linux/Mac
./gradlew clean
```

## 核心架构喵~

### 1. Java Agent 入口点
- **CMESuckMyDuck.java**: Java Agent 的主入口类，包含 `premain` 方法喵~
  - 解析命令行参数（类名、字段名/方法名、类型、阶段）喵~
  - 根据配置创建合适的 Transformer（WrapContainerTransformer、InjectLogTransformer 或 WrapLocalContainerTransformer）喵~
  - 支持通过系统属性配置多个选项（日志级别、ASM 版本、文件大小限制等）喵~

### 2. 字节码转换器（transformers 包）
该项目使用 ASM 库进行字节码操作，有三种主要的 Transformer 模式喵~：

- **WrapContainerTransformer**: 包装类的字段（静态或非静态），将原始集合替换为包装版本喵~
- **InjectLogTransformer**: 在方法调用处注入日志记录代码（inject mode）喵~
- **WrapLocalContainerTransformer**: 包装方法内的局部变量集合喵~
- **InjectTraceIdUpdaterTransformer**: 注入 Trace ID 更新逻辑喵~
- **Phase**: 枚举类型，表示 `static` 或 `nonstatic` 字段喵~

### 3. 容器包装系统（containers 包）
核心设计模式是 Wrapper Pattern，所有包装类都继承自 `AbstractWrappedContainer`喵~：

- **AbstractWrappedContainer**: 基类，持有原始容器引用和 traceId，提供日志记录方法（logQuery、logIteration、logModify）喵~
- **Containers**: 标准 Java 集合包装工厂（List、Set、Map、Iterator、ListIterator、Deque）喵~
- **FastContainers**: Fastutil 库集合包装工厂（IntList、LongList、ObjectList、IntSet、Int2ObjectMap 等）喵~
- **GuavaContainers**: Guava 库集合包装工厂（BiMap、Multiset、Multimap）喵~

每个包装类会拦截集合的所有方法调用，并通过 `TraceLogger` 记录修改操作的栈跟踪信息喵~。

### 4. 日志系统（log 和 utils 包）
- **TraceLogger**: 高性能日志记录器，在创建 SuckTrace 之前先检查线程是否应被忽略喵~
- **Log**: 日志工具类，支持异步写入、日志级别控制、文件轮转（基于 file_max_entries）喵~
- **SuckTrace**: 封装 Throwable，用于捕获调用栈信息喵~
- **TraceIdGenerator**: 为每个被监控的容器生成唯一的 Trace ID喵~
- **LogStrategy**: 日志策略接口，用于控制日志行为喵~
- **LogStrategies**: 提供四种日志策略实现：ThreadNameStrategy、StopLoggingIfExceptionCreatedStrategy、WhitelistConstructorStacktraceStrategy、LogLevelStrategy喵~

### 5. 类型系统（Type 枚举）
`Type` 枚举定义了所有支持的集合类型及其对应的包装器构造函数喵~：
- Java 标准集合：List、Set、Map、Iterator、ListIterator
- Fastutil 集合：IntList、LongList、IntSet、Int2ObjectMap、Object2IntMap 等
- Guava 集合：BiMap、Multiset、Multimap

## 重要技术细节喵~

### 命名映射规范
- **Forge 1.20.1**: 使用 SRG 名称（如 `f_120229_`）喵~
- **Fabric**: 使用 intermediary 名称喵~
- **NeoForge**: 使用 official 名称喵~

### ASM 版本兼容性
- 默认使用 ASM API 9（`Opcodes.ASM9`），适用于现代 Minecraft 版本喵~
- 对于旧版本（如 Minecraft 1.12.2），需要设置 `-Dcme_suck_my_duck.asm_api_version=5` 降低 API 级别喵~

### 性能优化要点
- 避免在非必要时创建 Throwable 对象（SuckTrace）喵~
- 使用线程白名单/黑名单机制（ignore_threads）减少日志开销喵~
- 支持日志级别控制（log_level=0 会记录 Query 操作，开销极大，不推荐）喵~
- 异步日志写入，每 500ms 批量刷盘（可通过 log_wait_time 配置）喵~

### 使用示例
```bash
# 监控 SoundEngine 的非静态 Map 字段
-javaagent:mods/CMESuckMyDuck-1.1.2.jar=net/minecraft/client/sounds/SoundEngine;f_120229_;Map;nonstatic

# 注入方法模式
-Dcme_suck_my_duck.inject_method=true -javaagent:CMESuckMyDuck-1.1.2.jar=org/example/MyClass;myMethod

# 监控局部变量
-Dcme_suck_my_duck.local_var_index=2 -javaagent:CMESuckMyDuck-1.1.2.jar=org/example/MyClass;myMethod;List
```

## 开发注意事项喵~

1. 本项目使用 **Java 8** 编译（sourceCompatibility 和 targetCompatibility 都设为 8），确保向后兼容旧版 Minecraft喵~
2. 所有包装类必须正确处理嵌套包装（避免 `WrappedList<WrappedList<T>>`）喵~
3. 添加新的集合类型时，需要同步更新 `Type` 枚举和对应的 Containers 工厂方法喵~
4. Transformer 实现时需要注意 ASM API 版本的动态适配（通过 `CMESuckMyDuck.ASM_API_VERSION`）喵~
5. 日志文件路径固定为 `CMESuckMyDuck.log`，支持自动轮转避免文件过大喵~
