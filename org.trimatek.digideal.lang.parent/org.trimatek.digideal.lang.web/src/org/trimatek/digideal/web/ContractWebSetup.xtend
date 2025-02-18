/*
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.web

import com.google.inject.Guice
import com.google.inject.Injector
import org.eclipse.xtext.util.Modules2
import org.trimatek.digideal.ContractRuntimeModule
import org.trimatek.digideal.ContractStandaloneSetup
import org.trimatek.digideal.ide.ContractIdeModule

/**
 * Initialization support for running Xtext languages in web applications.
 */
class ContractWebSetup extends ContractStandaloneSetup {
	
	override Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new ContractRuntimeModule, new ContractIdeModule, new ContractWebModule))
	}
	
}
