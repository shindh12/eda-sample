package com.study.eda;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.*;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTag;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@ArchTag("architectureRule")
@AnalyzeClasses(packagesOf = ArchitectureTest.class)
public class ArchitectureTest {

	// Hexagonal Architecture Rules
	@ArchTest
	public static final ArchRule architecture = onionArchitecture()
		.domainModels("..domain..")
		.domainServices().withOptionalLayers(true)
		.applicationServices("..application..")
		.adapter("adapter", "..adapter..");

	@ArchTest
	public static final ArchRule Domain은_Application을_참조해서는_안된다 = noClasses().that()
		.resideInAPackage("..domain..")
		.should()
		.dependOnClassesThat()
		.resideInAnyPackage("..application..");

	@ArchTest
	public static final ArchRule Application은_Adapter를_참조해서는_안된다 = noClasses().that()
		.resideInAPackage("..application..")
		.should()
		.dependOnClassesThat()
		.resideInAnyPackage("..adapter..");

	@ArchTest
	public static final ArchRule Input_Adapter_는_Application_Service를_참조해서는_안된다 = noClasses().that()
		.resideInAPackage("..adapter.in.web..")
		.should()
		.dependOnClassesThat()
		.resideInAnyPackage("..application.service..");

}