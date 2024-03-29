Dependency injection (DI) is a technique widely used in programming and well suited to Android development, where dependencies are provided to a class instead of creating them itself. By following DI principles, you lay the groundwork for good app architecture, greater code reusability, and ease of testing. Have you ever tried manual dependency injection in your app? Even with many of the existing dependency injection libraries today, it requires a lot of boilerplate code as your project becomes larger, since you have to construct every class and its dependencies by hand, and create containers to reuse and manage dependencies.

By following DI principles, you lay the groundwork for good app architecture, greater code reusability, and ease of testing.

The new Hilt library defines a standard way to do DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically for you.

Hilt is built on top of the popular DI library Dagger so benefits from the compile time correctness, runtime performance, scalability, and Android Studio support that Dagger provides. Due to this, Dagger’s seen great adoption on 30% of top 10k apps of the Google Play Store. However, because of the compile time code generation, expect a build time increase.

Since many Android framework classes are instantiated by the OS itself, there’s an associated boilerplate when using Dagger in Android apps. Unlike Dagger, Hilt is integrated with Jetpack libraries and Android framework classes and removes most of that boilerplate to let you focus on just the important parts of defining and injecting bindings without worrying about managing all of the Dagger setup and wiring. It automatically generates and provides:

Components for integrating Android framework classes with Dagger that you would otherwise need to create by hand.
Scope annotations for the components that Hilt generates automatically.
Predefined bindings and qualifiers.
Best of all, as Dagger and Hilt can coexist together, apps can be migrated on an as-needed basis.
