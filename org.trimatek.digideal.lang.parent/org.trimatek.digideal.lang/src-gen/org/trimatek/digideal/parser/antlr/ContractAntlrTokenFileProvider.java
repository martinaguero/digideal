/*
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class ContractAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("org/trimatek/digideal/parser/antlr/internal/InternalContract.tokens");
	}
}
