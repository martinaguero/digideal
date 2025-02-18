/*
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class ContractGrammarAccess extends AbstractGrammarElementFinder {
	
	public class ContractElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.Contract");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCONTRATOKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cCidAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cCidIDTerminalRuleCall_1_0 = (RuleCall)cCidAssignment_1.eContents().get(0);
		private final Assignment cParagraphAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cParagraphSentenceParserRuleCall_2_0 = (RuleCall)cParagraphAssignment_2.eContents().get(0);
		
		//Contract:
		//	'CONTRATO #' cid=ID paragraph+=Sentence*;
		@Override public ParserRule getRule() { return rule; }
		
		//'CONTRATO #' cid=ID paragraph+=Sentence*
		public Group getGroup() { return cGroup; }
		
		//'CONTRATO #'
		public Keyword getCONTRATOKeyword_0() { return cCONTRATOKeyword_0; }
		
		//cid=ID
		public Assignment getCidAssignment_1() { return cCidAssignment_1; }
		
		//ID
		public RuleCall getCidIDTerminalRuleCall_1_0() { return cCidIDTerminalRuleCall_1_0; }
		
		//paragraph+=Sentence*
		public Assignment getParagraphAssignment_2() { return cParagraphAssignment_2; }
		
		//Sentence
		public RuleCall getParagraphSentenceParserRuleCall_2_0() { return cParagraphSentenceParserRuleCall_2_0; }
	}
	public class SentenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.Sentence");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Assignment cSentenceTypeAssignment_0 = (Assignment)cAlternatives.eContents().get(0);
		private final RuleCall cSentenceTypeSTRINGTerminalRuleCall_0_0 = (RuleCall)cSentenceTypeAssignment_0.eContents().get(0);
		private final RuleCall cSubjectParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cReferenceParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cPayToParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cPaymentConditionParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cSupervisedByParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		
		//Sentence:
		//	sentenceType=STRING | Subject | Reference | PayTo | PaymentCondition | SupervisedBy;
		@Override public ParserRule getRule() { return rule; }
		
		//sentenceType=STRING | Subject | Reference | PayTo | PaymentCondition | SupervisedBy
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//sentenceType=STRING
		public Assignment getSentenceTypeAssignment_0() { return cSentenceTypeAssignment_0; }
		
		//STRING
		public RuleCall getSentenceTypeSTRINGTerminalRuleCall_0_0() { return cSentenceTypeSTRINGTerminalRuleCall_0_0; }
		
		//Subject
		public RuleCall getSubjectParserRuleCall_1() { return cSubjectParserRuleCall_1; }
		
		//Reference
		public RuleCall getReferenceParserRuleCall_2() { return cReferenceParserRuleCall_2; }
		
		//PayTo
		public RuleCall getPayToParserRuleCall_3() { return cPayToParserRuleCall_3; }
		
		//PaymentCondition
		public RuleCall getPaymentConditionParserRuleCall_4() { return cPaymentConditionParserRuleCall_4; }
		
		//SupervisedBy
		public RuleCall getSupervisedByParserRuleCall_5() { return cSupervisedByParserRuleCall_5; }
	}
	public class SubjectElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.Subject");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cAddressAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cAddressSTRINGTerminalRuleCall_3_0 = (RuleCall)cAddressAssignment_3.eContents().get(0);
		private final Keyword cCommaKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cEmailAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cEmailEmailParserRuleCall_5_0 = (RuleCall)cEmailAssignment_5.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		//Subject:
		//	'@' name=ID '{' address=STRING ',' email=Email '}';
		@Override public ParserRule getRule() { return rule; }
		
		//'@' name=ID '{' address=STRING ',' email=Email '}'
		public Group getGroup() { return cGroup; }
		
		//'@'
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_2() { return cLeftCurlyBracketKeyword_2; }
		
		//address=STRING
		public Assignment getAddressAssignment_3() { return cAddressAssignment_3; }
		
		//STRING
		public RuleCall getAddressSTRINGTerminalRuleCall_3_0() { return cAddressSTRINGTerminalRuleCall_3_0; }
		
		//','
		public Keyword getCommaKeyword_4() { return cCommaKeyword_4; }
		
		//email=Email
		public Assignment getEmailAssignment_5() { return cEmailAssignment_5; }
		
		//Email
		public RuleCall getEmailEmailParserRuleCall_5_0() { return cEmailEmailParserRuleCall_5_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_6() { return cRightCurlyBracketKeyword_6; }
	}
	public class PublicKeyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.PublicKey");
		private final RuleCall cIDTerminalRuleCall = (RuleCall)rule.eContents().get(1);
		
		//PublicKey:
		//	ID;
		@Override public ParserRule getRule() { return rule; }
		
		//ID
		public RuleCall getIDTerminalRuleCall() { return cIDTerminalRuleCall; }
	}
	public class EmailElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.Email");
		private final RuleCall cSTRINGTerminalRuleCall = (RuleCall)rule.eContents().get(1);
		
		//Email:
		//	STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//STRING
		public RuleCall getSTRINGTerminalRuleCall() { return cSTRINGTerminalRuleCall; }
	}
	public class ReferenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.Reference");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cTypeAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cTypeSubjectCrossReference_1_0 = (CrossReference)cTypeAssignment_1.eContents().get(0);
		private final RuleCall cTypeSubjectIDTerminalRuleCall_1_0_1 = (RuleCall)cTypeSubjectCrossReference_1_0.eContents().get(1);
		
		//Reference:
		//	'@' type=[Subject];
		@Override public ParserRule getRule() { return rule; }
		
		//'@' type=[Subject]
		public Group getGroup() { return cGroup; }
		
		//'@'
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }
		
		//type=[Subject]
		public Assignment getTypeAssignment_1() { return cTypeAssignment_1; }
		
		//[Subject]
		public CrossReference getTypeSubjectCrossReference_1_0() { return cTypeSubjectCrossReference_1_0; }
		
		//ID
		public RuleCall getTypeSubjectIDTerminalRuleCall_1_0_1() { return cTypeSubjectIDTerminalRuleCall_1_0_1; }
	}
	public class PayToElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.PayTo");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCommercialAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cPayerAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cPayerSubjectCrossReference_1_0 = (CrossReference)cPayerAssignment_1.eContents().get(0);
		private final RuleCall cPayerSubjectIDTerminalRuleCall_1_0_1 = (RuleCall)cPayerSubjectCrossReference_1_0.eContents().get(1);
		private final Keyword cPagarAKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cCommercialAtKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cCollectorAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final CrossReference cCollectorSubjectCrossReference_3_1_0 = (CrossReference)cCollectorAssignment_3_1.eContents().get(0);
		private final RuleCall cCollectorSubjectIDTerminalRuleCall_3_1_0_1 = (RuleCall)cCollectorSubjectCrossReference_3_1_0.eContents().get(1);
		private final Keyword cLaSumaDeSTSKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cStsAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cStsINTTerminalRuleCall_5_0 = (RuleCall)cStsAssignment_5.eContents().get(0);
		
		//PayTo:
		//	'@' payer=[Subject] 'pagar� a' ('@' collector=[Subject]) 'la suma de STS' sts=INT;
		@Override public ParserRule getRule() { return rule; }
		
		//'@' payer=[Subject] 'pagar� a' ('@' collector=[Subject]) 'la suma de STS' sts=INT
		public Group getGroup() { return cGroup; }
		
		//'@'
		public Keyword getCommercialAtKeyword_0() { return cCommercialAtKeyword_0; }
		
		//payer=[Subject]
		public Assignment getPayerAssignment_1() { return cPayerAssignment_1; }
		
		//[Subject]
		public CrossReference getPayerSubjectCrossReference_1_0() { return cPayerSubjectCrossReference_1_0; }
		
		//ID
		public RuleCall getPayerSubjectIDTerminalRuleCall_1_0_1() { return cPayerSubjectIDTerminalRuleCall_1_0_1; }
		
		//'pagar� a'
		public Keyword getPagarAKeyword_2() { return cPagarAKeyword_2; }
		
		//'@' collector=[Subject]
		public Group getGroup_3() { return cGroup_3; }
		
		//'@'
		public Keyword getCommercialAtKeyword_3_0() { return cCommercialAtKeyword_3_0; }
		
		//collector=[Subject]
		public Assignment getCollectorAssignment_3_1() { return cCollectorAssignment_3_1; }
		
		//[Subject]
		public CrossReference getCollectorSubjectCrossReference_3_1_0() { return cCollectorSubjectCrossReference_3_1_0; }
		
		//ID
		public RuleCall getCollectorSubjectIDTerminalRuleCall_3_1_0_1() { return cCollectorSubjectIDTerminalRuleCall_3_1_0_1; }
		
		//'la suma de STS'
		public Keyword getLaSumaDeSTSKeyword_4() { return cLaSumaDeSTSKeyword_4; }
		
		//sts=INT
		public Assignment getStsAssignment_5() { return cStsAssignment_5; }
		
		//INT
		public RuleCall getStsINTTerminalRuleCall_5_0() { return cStsINTTerminalRuleCall_5_0; }
	}
	public class PaymentConditionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.PaymentCondition");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cSiKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cCommercialAtKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cCollectorAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final CrossReference cCollectorSubjectCrossReference_1_1_0 = (CrossReference)cCollectorAssignment_1_1.eContents().get(0);
		private final RuleCall cCollectorSubjectIDTerminalRuleCall_1_1_0_1 = (RuleCall)cCollectorSubjectCrossReference_1_1_0.eContents().get(1);
		private final Assignment cDescriptionAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cDescriptionSTRINGTerminalRuleCall_2_0 = (RuleCall)cDescriptionAssignment_2.eContents().get(0);
		private final Assignment cLevelAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cLevelFactsLevelParserRuleCall_3_0 = (RuleCall)cLevelAssignment_3.eContents().get(0);
		private final Assignment cFactsAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cFactsFactsParserRuleCall_4_0 = (RuleCall)cFactsAssignment_4.eContents().get(0);
		
		//PaymentCondition:
		//	'si:' ('@' collector=[Subject]) description=STRING level=FactsLevel facts=Facts;
		@Override public ParserRule getRule() { return rule; }
		
		//'si:' ('@' collector=[Subject]) description=STRING level=FactsLevel facts=Facts
		public Group getGroup() { return cGroup; }
		
		//'si:'
		public Keyword getSiKeyword_0() { return cSiKeyword_0; }
		
		//'@' collector=[Subject]
		public Group getGroup_1() { return cGroup_1; }
		
		//'@'
		public Keyword getCommercialAtKeyword_1_0() { return cCommercialAtKeyword_1_0; }
		
		//collector=[Subject]
		public Assignment getCollectorAssignment_1_1() { return cCollectorAssignment_1_1; }
		
		//[Subject]
		public CrossReference getCollectorSubjectCrossReference_1_1_0() { return cCollectorSubjectCrossReference_1_1_0; }
		
		//ID
		public RuleCall getCollectorSubjectIDTerminalRuleCall_1_1_0_1() { return cCollectorSubjectIDTerminalRuleCall_1_1_0_1; }
		
		//description=STRING
		public Assignment getDescriptionAssignment_2() { return cDescriptionAssignment_2; }
		
		//STRING
		public RuleCall getDescriptionSTRINGTerminalRuleCall_2_0() { return cDescriptionSTRINGTerminalRuleCall_2_0; }
		
		//level=FactsLevel
		public Assignment getLevelAssignment_3() { return cLevelAssignment_3; }
		
		//FactsLevel
		public RuleCall getLevelFactsLevelParserRuleCall_3_0() { return cLevelFactsLevelParserRuleCall_3_0; }
		
		//facts=Facts
		public Assignment getFactsAssignment_4() { return cFactsAssignment_4; }
		
		//Facts
		public RuleCall getFactsFactsParserRuleCall_4_0() { return cFactsFactsParserRuleCall_4_0; }
	}
	public class FactsLevelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.FactsLevel");
		private final Assignment cOptionsAssignment = (Assignment)rule.eContents().get(1);
		private final Alternatives cOptionsAlternatives_0 = (Alternatives)cOptionsAssignment.eContents().get(0);
		private final RuleCall cOptionsAnyFactParserRuleCall_0_0 = (RuleCall)cOptionsAlternatives_0.eContents().get(0);
		private final RuleCall cOptionsAllFactsParserRuleCall_0_1 = (RuleCall)cOptionsAlternatives_0.eContents().get(1);
		
		//FactsLevel:
		//	options=(AnyFact | AllFacts);
		@Override public ParserRule getRule() { return rule; }
		
		//options=(AnyFact | AllFacts)
		public Assignment getOptionsAssignment() { return cOptionsAssignment; }
		
		//(AnyFact | AllFacts)
		public Alternatives getOptionsAlternatives_0() { return cOptionsAlternatives_0; }
		
		//AnyFact
		public RuleCall getOptionsAnyFactParserRuleCall_0_0() { return cOptionsAnyFactParserRuleCall_0_0; }
		
		//AllFacts
		public RuleCall getOptionsAllFactsParserRuleCall_0_1() { return cOptionsAllFactsParserRuleCall_0_1; }
	}
	public class AnyFactElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.AnyFact");
		private final Assignment cValueAssignment = (Assignment)rule.eContents().get(1);
		private final Keyword cValueDemostrableConCUALQUIERADeLosSiguientesHechosKeyword_0 = (Keyword)cValueAssignment.eContents().get(0);
		
		//AnyFact:
		//	value='demostrable con CUALQUIERA de los siguientes hechos:';
		@Override public ParserRule getRule() { return rule; }
		
		//value='demostrable con CUALQUIERA de los siguientes hechos:'
		public Assignment getValueAssignment() { return cValueAssignment; }
		
		//'demostrable con CUALQUIERA de los siguientes hechos:'
		public Keyword getValueDemostrableConCUALQUIERADeLosSiguientesHechosKeyword_0() { return cValueDemostrableConCUALQUIERADeLosSiguientesHechosKeyword_0; }
	}
	public class AllFactsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.AllFacts");
		private final Assignment cValueAssignment = (Assignment)rule.eContents().get(1);
		private final Keyword cValueDemostrableConTODOSLosSiguientesHechosKeyword_0 = (Keyword)cValueAssignment.eContents().get(0);
		
		//AllFacts:
		//	value='demostrable con TODOS los siguientes hechos:';
		@Override public ParserRule getRule() { return rule; }
		
		//value='demostrable con TODOS los siguientes hechos:'
		public Assignment getValueAssignment() { return cValueAssignment; }
		
		//'demostrable con TODOS los siguientes hechos:'
		public Keyword getValueDemostrableConTODOSLosSiguientesHechosKeyword_0() { return cValueDemostrableConTODOSLosSiguientesHechosKeyword_0; }
	}
	public class FactsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.Facts");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftCurlyBracketKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cFactAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cFactSTRINGTerminalRuleCall_1_0_0 = (RuleCall)cFactAssignment_1_0.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cGroup_1.eContents().get(1);
		private final Keyword cCommaKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Assignment cFactAssignment_1_1_1 = (Assignment)cGroup_1_1.eContents().get(1);
		private final RuleCall cFactSTRINGTerminalRuleCall_1_1_1_0 = (RuleCall)cFactAssignment_1_1_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//Facts:
		//	'{' (fact+=STRING ("," fact+=STRING)*) '}';
		@Override public ParserRule getRule() { return rule; }
		
		//'{' (fact+=STRING ("," fact+=STRING)*) '}'
		public Group getGroup() { return cGroup; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_0() { return cLeftCurlyBracketKeyword_0; }
		
		//fact+=STRING ("," fact+=STRING)*
		public Group getGroup_1() { return cGroup_1; }
		
		//fact+=STRING
		public Assignment getFactAssignment_1_0() { return cFactAssignment_1_0; }
		
		//STRING
		public RuleCall getFactSTRINGTerminalRuleCall_1_0_0() { return cFactSTRINGTerminalRuleCall_1_0_0; }
		
		//("," fact+=STRING)*
		public Group getGroup_1_1() { return cGroup_1_1; }
		
		//","
		public Keyword getCommaKeyword_1_1_0() { return cCommaKeyword_1_1_0; }
		
		//fact+=STRING
		public Assignment getFactAssignment_1_1_1() { return cFactAssignment_1_1_1; }
		
		//STRING
		public RuleCall getFactSTRINGTerminalRuleCall_1_1_1_0() { return cFactSTRINGTerminalRuleCall_1_1_1_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_2() { return cRightCurlyBracketKeyword_2; }
	}
	public class SupervisedByElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.trimatek.digideal.Contract.SupervisedBy");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cYSupervisadoPorKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cAgentAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cAgentSubjectParserRuleCall_1_0 = (RuleCall)cAgentAssignment_1.eContents().get(0);
		
		//SupervisedBy:
		//	'y supervisado por ' agent=Subject;
		@Override public ParserRule getRule() { return rule; }
		
		//'y supervisado por ' agent=Subject
		public Group getGroup() { return cGroup; }
		
		//'y supervisado por '
		public Keyword getYSupervisadoPorKeyword_0() { return cYSupervisadoPorKeyword_0; }
		
		//agent=Subject
		public Assignment getAgentAssignment_1() { return cAgentAssignment_1; }
		
		//Subject
		public RuleCall getAgentSubjectParserRuleCall_1_0() { return cAgentSubjectParserRuleCall_1_0; }
	}
	
	
	private final ContractElements pContract;
	private final SentenceElements pSentence;
	private final SubjectElements pSubject;
	private final PublicKeyElements pPublicKey;
	private final EmailElements pEmail;
	private final ReferenceElements pReference;
	private final PayToElements pPayTo;
	private final PaymentConditionElements pPaymentCondition;
	private final FactsLevelElements pFactsLevel;
	private final AnyFactElements pAnyFact;
	private final AllFactsElements pAllFacts;
	private final FactsElements pFacts;
	private final SupervisedByElements pSupervisedBy;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public ContractGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pContract = new ContractElements();
		this.pSentence = new SentenceElements();
		this.pSubject = new SubjectElements();
		this.pPublicKey = new PublicKeyElements();
		this.pEmail = new EmailElements();
		this.pReference = new ReferenceElements();
		this.pPayTo = new PayToElements();
		this.pPaymentCondition = new PaymentConditionElements();
		this.pFactsLevel = new FactsLevelElements();
		this.pAnyFact = new AnyFactElements();
		this.pAllFacts = new AllFactsElements();
		this.pFacts = new FactsElements();
		this.pSupervisedBy = new SupervisedByElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.trimatek.digideal.Contract".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Contract:
	//	'CONTRATO #' cid=ID paragraph+=Sentence*;
	public ContractElements getContractAccess() {
		return pContract;
	}
	
	public ParserRule getContractRule() {
		return getContractAccess().getRule();
	}
	
	//Sentence:
	//	sentenceType=STRING | Subject | Reference | PayTo | PaymentCondition | SupervisedBy;
	public SentenceElements getSentenceAccess() {
		return pSentence;
	}
	
	public ParserRule getSentenceRule() {
		return getSentenceAccess().getRule();
	}
	
	//Subject:
	//	'@' name=ID '{' address=STRING ',' email=Email '}';
	public SubjectElements getSubjectAccess() {
		return pSubject;
	}
	
	public ParserRule getSubjectRule() {
		return getSubjectAccess().getRule();
	}
	
	//PublicKey:
	//	ID;
	public PublicKeyElements getPublicKeyAccess() {
		return pPublicKey;
	}
	
	public ParserRule getPublicKeyRule() {
		return getPublicKeyAccess().getRule();
	}
	
	//Email:
	//	STRING;
	public EmailElements getEmailAccess() {
		return pEmail;
	}
	
	public ParserRule getEmailRule() {
		return getEmailAccess().getRule();
	}
	
	//Reference:
	//	'@' type=[Subject];
	public ReferenceElements getReferenceAccess() {
		return pReference;
	}
	
	public ParserRule getReferenceRule() {
		return getReferenceAccess().getRule();
	}
	
	//PayTo:
	//	'@' payer=[Subject] 'pagar� a' ('@' collector=[Subject]) 'la suma de STS' sts=INT;
	public PayToElements getPayToAccess() {
		return pPayTo;
	}
	
	public ParserRule getPayToRule() {
		return getPayToAccess().getRule();
	}
	
	//PaymentCondition:
	//	'si:' ('@' collector=[Subject]) description=STRING level=FactsLevel facts=Facts;
	public PaymentConditionElements getPaymentConditionAccess() {
		return pPaymentCondition;
	}
	
	public ParserRule getPaymentConditionRule() {
		return getPaymentConditionAccess().getRule();
	}
	
	//FactsLevel:
	//	options=(AnyFact | AllFacts);
	public FactsLevelElements getFactsLevelAccess() {
		return pFactsLevel;
	}
	
	public ParserRule getFactsLevelRule() {
		return getFactsLevelAccess().getRule();
	}
	
	//AnyFact:
	//	value='demostrable con CUALQUIERA de los siguientes hechos:';
	public AnyFactElements getAnyFactAccess() {
		return pAnyFact;
	}
	
	public ParserRule getAnyFactRule() {
		return getAnyFactAccess().getRule();
	}
	
	//AllFacts:
	//	value='demostrable con TODOS los siguientes hechos:';
	public AllFactsElements getAllFactsAccess() {
		return pAllFacts;
	}
	
	public ParserRule getAllFactsRule() {
		return getAllFactsAccess().getRule();
	}
	
	//Facts:
	//	'{' (fact+=STRING ("," fact+=STRING)*) '}';
	public FactsElements getFactsAccess() {
		return pFacts;
	}
	
	public ParserRule getFactsRule() {
		return getFactsAccess().getRule();
	}
	
	//SupervisedBy:
	//	'y supervisado por ' agent=Subject;
	public SupervisedByElements getSupervisedByAccess() {
		return pSupervisedBy;
	}
	
	public ParserRule getSupervisedByRule() {
		return getSupervisedByAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' | "'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
