aici nu trece un test din path ul directorului dat > Task :ClassAnalyzer.main()
Class does not contain any public test methods: ClassAnalyzer
Class does not contain any public test methods: org.example.Main
Invoking method: testEquality
Invoking method: testDivision
Invoking method: testArrayEquality
Invoking method: testAddition
Invoking method: testNotNull
Invoking method: testNull
Invoking method: testSubtraction
Invoking method: testMultiplication
Invoking method: testGreaterThan
Invoking method: testLessThan
Invoking method: testFailingCase
java.lang.reflect.InvocationTargetException
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.base/java.lang.reflect.Method.invoke(Method.java:568)
at ClassAnalyzer.processClass(ClassAnalyzer.java:113)
at ClassAnalyzer.processClasses(ClassAnalyzer.java:89)
at ClassAnalyzer.main(ClassAnalyzer.java:35)
Caused by: java.lang.AssertionError: expected:<100> but was:<50>
at org.junit.Assert.fail(Assert.java:89)
Caused by: java.lang.AssertionError: expected:<100> but was:<50>

	at org.junit.Assert.failNotEquals(Assert.java:835)
	at org.junit.Assert.assertEquals(Assert.java:647)
	at org.junit.Assert.assertEquals(Assert.java:633)
	at SampleTest.testFailingCase(SampleTest.java:38)
	... 7 more
Class: SampleTest
Test Statistics:
Total tests: 11
Passed tests: 10
Failed tests: 1


acum trec toate testele 
Class does not contain any public test methods: ClassAnalyzer
Class does not contain any public test methods: org.example.Main
Invoking method: testDivision
Invoking method: testAddition
Invoking method: testSubtraction
Invoking method: testMultiplication
Invoking method: testNotNull
Invoking method: testArrayEquality
Invoking method: testFailingCase
Invoking method: testNull
Invoking method: testGreaterThan
Invoking method: testLessThan
Invoking method: testEquality
Class: SampleTest
Test Statistics:
Total tests: 11
Passed tests: 11
Failed tests: 0


acum am pus path ul cu pentru .jar 
"D:\OneDrive\Documente\GitHub\Programare-Avansata\Laborator 12\Homework\out\artifacts\Homework_jar\Homework.jar"
Class does not contain any public test methods: ClassAnalyzer
Class does not contain any public test methods: org.example.Main
Invoking method: testNull
Invoking method: testSubtraction
Invoking method: testAddition
Invoking method: testGreaterThan
Invoking method: testDivision
Invoking method: testLessThan
Invoking method: testEquality
Invoking method: testNotNull
Invoking method: testArrayEquality
Invoking method: testFailingCase
Invoking method: testMultiplication
Class: SampleTest
Test Statistics:
Total tests: 11
Passed tests: 11
Failed tests: 0


aici am pus un alt path cu alt laborator cu teste ele nu nu trec neaparat ca am modificat baza de date prin oracle si cam toate au picat :))))))
Class does not contain any public test methods: AuthorTest
Class does not contain any public test methods: BookTest

Class: GenreTest
Test Statistics:
Total tests: 1
Passed tests: 0
Failed tests: 1
Class does not contain any public test methods: jdbctests.JdbcAuthorTest
Class does not contain any public test methods: jdbctests.JdbcBookTest
Class does not contain any public test methods: jdbctests.JdbcGenreTest
Class does not contain any public test methods: jdbctests.JdbcPublishingHouseTest
Class does not contain any public test methods: jpatests.JpaAuthorTest
Class does not contain any public test methods: jpatests.JpaBookTest
Class does not contain any public test methods: jpatests.JpaGenreTest
Class does not contain any public test methods: jpatests.JpaPublishingHouseTest
Invoking method: testPublishingHouseBookRelationship
java.lang.reflect.InvocationTargetException
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.base/java.lang.reflect.Method.invoke(Method.java:568)
at ClassAnalyzer.processClass(ClassAnalyzer.java:112)
at ClassAnalyzer.processClasses(ClassAnalyzer.java:88)
at ClassAnalyzer.main(ClassAnalyzer.java:34)
Caused by: java.lang.NoClassDefFoundError: org/example/entities/PublishingHouse
at PublishingHouseTest.testPublishingHouseBookRelationship(PublishingHouseTest.java:13)
... 7 more
Caused by: java.lang.NoClassDefFoundError: org/example/entities/PublishingHouse

Caused by: java.lang.ClassNotFoundException: org.example.entities.PublishingHouse
Caused by: java.lang.ClassNotFoundException: org.example.entities.PublishingHouse

	at java.base/java.net.URLClassLoader.findClass(URLClassLoader.java:445)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:592)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:525)
	... 8 more
Class: PublishingHouseTest
Test Statistics:
Total tests: 1
Passed tests: 0
Failed tests: 1


