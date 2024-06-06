Task :ClassAnalyzer.main()
Methods of class SampleTest:
public void SampleTest.nonStaticTestMethod()
public static void SampleTest.testMethod1()
public static void SampleTest.normalMethod()
public static void SampleTest.testMethod2()

Invoking @Test annotated static methods:
Skipping method (not static or has parameters): nonStaticTestMethod
Invoking method: testMethod1
testMethod1 executed
Invoking method: testMethod2
testMethod2 executed

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.4/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 474ms
2 actionable tasks: 2 executed
1:14:34 PM: Execution finished ':ClassAnalyzer.main()'.
