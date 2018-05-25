/*
 * generated by Xtext 2.13.0
 */
grammar InternalContract;

options {
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package org.trimatek.digideal.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.trimatek.digideal.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.trimatek.digideal.services.ContractGrammarAccess;

}

@parser::members {

 	private ContractGrammarAccess grammarAccess;

    public InternalContractParser(TokenStream input, ContractGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "Contract";
   	}

   	@Override
   	protected ContractGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleContract
entryRuleContract returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getContractRule()); }
	iv_ruleContract=ruleContract
	{ $current=$iv_ruleContract.current; }
	EOF;

// Rule Contract
ruleContract returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='CONTRATO #'
		{
			newLeafNode(otherlv_0, grammarAccess.getContractAccess().getCONTRATOKeyword_0());
		}
		(
			(
				lv_cid_1_0=RULE_ID
				{
					newLeafNode(lv_cid_1_0, grammarAccess.getContractAccess().getCidIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getContractRule());
					}
					setWithLastConsumed(
						$current,
						"cid",
						lv_cid_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getContractAccess().getParagraphSentenceParserRuleCall_2_0());
				}
				lv_paragraph_2_0=ruleSentence
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getContractRule());
					}
					add(
						$current,
						"paragraph",
						lv_paragraph_2_0,
						"org.trimatek.digideal.Contract.Sentence");
					afterParserOrEnumRuleCall();
				}
			)
		)*
	)
;

// Entry rule entryRuleSentence
entryRuleSentence returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSentenceRule()); }
	iv_ruleSentence=ruleSentence
	{ $current=$iv_ruleSentence.current; }
	EOF;

// Rule Sentence
ruleSentence returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				lv_sentenceType_0_0=RULE_STRING
				{
					newLeafNode(lv_sentenceType_0_0, grammarAccess.getSentenceAccess().getSentenceTypeSTRINGTerminalRuleCall_0_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSentenceRule());
					}
					setWithLastConsumed(
						$current,
						"sentenceType",
						lv_sentenceType_0_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		    |
		{
			newCompositeNode(grammarAccess.getSentenceAccess().getSubjectParserRuleCall_1());
		}
		this_Subject_1=ruleSubject
		{
			$current = $this_Subject_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getSentenceAccess().getReferenceParserRuleCall_2());
		}
		this_Reference_2=ruleReference
		{
			$current = $this_Reference_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getSentenceAccess().getPayToParserRuleCall_3());
		}
		this_PayTo_3=rulePayTo
		{
			$current = $this_PayTo_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getSentenceAccess().getPaymentConditionParserRuleCall_4());
		}
		this_PaymentCondition_4=rulePaymentCondition
		{
			$current = $this_PaymentCondition_4.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getSentenceAccess().getSupervisedByParserRuleCall_5());
		}
		this_SupervisedBy_5=ruleSupervisedBy
		{
			$current = $this_SupervisedBy_5.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleSubject
entryRuleSubject returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSubjectRule()); }
	iv_ruleSubject=ruleSubject
	{ $current=$iv_ruleSubject.current; }
	EOF;

// Rule Subject
ruleSubject returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='@'
		{
			newLeafNode(otherlv_0, grammarAccess.getSubjectAccess().getCommercialAtKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getSubjectAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSubjectRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		otherlv_2='{'
		{
			newLeafNode(otherlv_2, grammarAccess.getSubjectAccess().getLeftCurlyBracketKeyword_2());
		}
		(
			(
				lv_address_3_0=RULE_STRING
				{
					newLeafNode(lv_address_3_0, grammarAccess.getSubjectAccess().getAddressSTRINGTerminalRuleCall_3_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSubjectRule());
					}
					setWithLastConsumed(
						$current,
						"address",
						lv_address_3_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		otherlv_4=','
		{
			newLeafNode(otherlv_4, grammarAccess.getSubjectAccess().getCommaKeyword_4());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSubjectAccess().getEmailEmailParserRuleCall_5_0());
				}
				lv_email_5_0=ruleEmail
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSubjectRule());
					}
					set(
						$current,
						"email",
						lv_email_5_0,
						"org.trimatek.digideal.Contract.Email");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_6='}'
		{
			newLeafNode(otherlv_6, grammarAccess.getSubjectAccess().getRightCurlyBracketKeyword_6());
		}
	)
;

// Entry rule entryRuleEmail
entryRuleEmail returns [String current=null]:
	{ newCompositeNode(grammarAccess.getEmailRule()); }
	iv_ruleEmail=ruleEmail
	{ $current=$iv_ruleEmail.current.getText(); }
	EOF;

// Rule Email
ruleEmail returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	this_STRING_0=RULE_STRING
	{
		$current.merge(this_STRING_0);
	}
	{
		newLeafNode(this_STRING_0, grammarAccess.getEmailAccess().getSTRINGTerminalRuleCall());
	}
;

// Entry rule entryRuleReference
entryRuleReference returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReferenceRule()); }
	iv_ruleReference=ruleReference
	{ $current=$iv_ruleReference.current; }
	EOF;

// Rule Reference
ruleReference returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='@'
		{
			newLeafNode(otherlv_0, grammarAccess.getReferenceAccess().getCommercialAtKeyword_0());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getReferenceRule());
					}
				}
				otherlv_1=RULE_ID
				{
					newLeafNode(otherlv_1, grammarAccess.getReferenceAccess().getTypeSubjectCrossReference_1_0());
				}
			)
		)
	)
;

// Entry rule entryRulePayTo
entryRulePayTo returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPayToRule()); }
	iv_rulePayTo=rulePayTo
	{ $current=$iv_rulePayTo.current; }
	EOF;

// Rule PayTo
rulePayTo returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='@'
		{
			newLeafNode(otherlv_0, grammarAccess.getPayToAccess().getCommercialAtKeyword_0());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getPayToRule());
					}
				}
				otherlv_1=RULE_ID
				{
					newLeafNode(otherlv_1, grammarAccess.getPayToAccess().getPayerSubjectCrossReference_1_0());
				}
			)
		)
		otherlv_2='pagar\u00E1 a'
		{
			newLeafNode(otherlv_2, grammarAccess.getPayToAccess().getPagarAKeyword_2());
		}
		(
			otherlv_3='@'
			{
				newLeafNode(otherlv_3, grammarAccess.getPayToAccess().getCommercialAtKeyword_3_0());
			}
			(
				(
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getPayToRule());
						}
					}
					otherlv_4=RULE_ID
					{
						newLeafNode(otherlv_4, grammarAccess.getPayToAccess().getCollectorSubjectCrossReference_3_1_0());
					}
				)
			)
		)
		otherlv_5='la suma de STS'
		{
			newLeafNode(otherlv_5, grammarAccess.getPayToAccess().getLaSumaDeSTSKeyword_4());
		}
		(
			(
				lv_sts_6_0=RULE_INT
				{
					newLeafNode(lv_sts_6_0, grammarAccess.getPayToAccess().getStsINTTerminalRuleCall_5_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getPayToRule());
					}
					setWithLastConsumed(
						$current,
						"sts",
						lv_sts_6_0,
						"org.eclipse.xtext.common.Terminals.INT");
				}
			)
		)
	)
;

// Entry rule entryRulePaymentCondition
entryRulePaymentCondition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPaymentConditionRule()); }
	iv_rulePaymentCondition=rulePaymentCondition
	{ $current=$iv_rulePaymentCondition.current; }
	EOF;

// Rule PaymentCondition
rulePaymentCondition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='si:'
		{
			newLeafNode(otherlv_0, grammarAccess.getPaymentConditionAccess().getSiKeyword_0());
		}
		(
			otherlv_1='@'
			{
				newLeafNode(otherlv_1, grammarAccess.getPaymentConditionAccess().getCommercialAtKeyword_1_0());
			}
			(
				(
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getPaymentConditionRule());
						}
					}
					otherlv_2=RULE_ID
					{
						newLeafNode(otherlv_2, grammarAccess.getPaymentConditionAccess().getCollectorSubjectCrossReference_1_1_0());
					}
				)
			)
		)
		(
			(
				lv_description_3_0=RULE_STRING
				{
					newLeafNode(lv_description_3_0, grammarAccess.getPaymentConditionAccess().getDescriptionSTRINGTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getPaymentConditionRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_3_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getPaymentConditionAccess().getLevelFactsLevelParserRuleCall_3_0());
				}
				lv_level_4_0=ruleFactsLevel
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getPaymentConditionRule());
					}
					set(
						$current,
						"level",
						lv_level_4_0,
						"org.trimatek.digideal.Contract.FactsLevel");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getPaymentConditionAccess().getFactsFactsParserRuleCall_4_0());
				}
				lv_facts_5_0=ruleFacts
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getPaymentConditionRule());
					}
					set(
						$current,
						"facts",
						lv_facts_5_0,
						"org.trimatek.digideal.Contract.Facts");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleFactsLevel
entryRuleFactsLevel returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getFactsLevelRule()); }
	iv_ruleFactsLevel=ruleFactsLevel
	{ $current=$iv_ruleFactsLevel.current; }
	EOF;

// Rule FactsLevel
ruleFactsLevel returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getFactsLevelAccess().getOptionsAnyFactParserRuleCall_0_0());
				}
				lv_options_0_1=ruleAnyFact
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getFactsLevelRule());
					}
					set(
						$current,
						"options",
						lv_options_0_1,
						"org.trimatek.digideal.Contract.AnyFact");
					afterParserOrEnumRuleCall();
				}
				    |
				{
					newCompositeNode(grammarAccess.getFactsLevelAccess().getOptionsAllFactsParserRuleCall_0_1());
				}
				lv_options_0_2=ruleAllFacts
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getFactsLevelRule());
					}
					set(
						$current,
						"options",
						lv_options_0_2,
						"org.trimatek.digideal.Contract.AllFacts");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleAnyFact
entryRuleAnyFact returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAnyFactRule()); }
	iv_ruleAnyFact=ruleAnyFact
	{ $current=$iv_ruleAnyFact.current; }
	EOF;

// Rule AnyFact
ruleAnyFact returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_value_0_0='demostrable con CUALQUIERA de los siguientes hechos:'
			{
				newLeafNode(lv_value_0_0, grammarAccess.getAnyFactAccess().getValueDemostrableConCUALQUIERADeLosSiguientesHechosKeyword_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getAnyFactRule());
				}
				setWithLastConsumed($current, "value", lv_value_0_0, "demostrable con CUALQUIERA de los siguientes hechos:");
			}
		)
	)
;

// Entry rule entryRuleAllFacts
entryRuleAllFacts returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAllFactsRule()); }
	iv_ruleAllFacts=ruleAllFacts
	{ $current=$iv_ruleAllFacts.current; }
	EOF;

// Rule AllFacts
ruleAllFacts returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_value_0_0='demostrable con TODOS los siguientes hechos:'
			{
				newLeafNode(lv_value_0_0, grammarAccess.getAllFactsAccess().getValueDemostrableConTODOSLosSiguientesHechosKeyword_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getAllFactsRule());
				}
				setWithLastConsumed($current, "value", lv_value_0_0, "demostrable con TODOS los siguientes hechos:");
			}
		)
	)
;

// Entry rule entryRuleFacts
entryRuleFacts returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getFactsRule()); }
	iv_ruleFacts=ruleFacts
	{ $current=$iv_ruleFacts.current; }
	EOF;

// Rule Facts
ruleFacts returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='{'
		{
			newLeafNode(otherlv_0, grammarAccess.getFactsAccess().getLeftCurlyBracketKeyword_0());
		}
		(
			(
				(
					lv_fact_1_0=RULE_STRING
					{
						newLeafNode(lv_fact_1_0, grammarAccess.getFactsAccess().getFactSTRINGTerminalRuleCall_1_0_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getFactsRule());
						}
						addWithLastConsumed(
							$current,
							"fact",
							lv_fact_1_0,
							"org.eclipse.xtext.common.Terminals.STRING");
					}
				)
			)
			(
				otherlv_2=','
				{
					newLeafNode(otherlv_2, grammarAccess.getFactsAccess().getCommaKeyword_1_1_0());
				}
				(
					(
						lv_fact_3_0=RULE_STRING
						{
							newLeafNode(lv_fact_3_0, grammarAccess.getFactsAccess().getFactSTRINGTerminalRuleCall_1_1_1_0());
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getFactsRule());
							}
							addWithLastConsumed(
								$current,
								"fact",
								lv_fact_3_0,
								"org.eclipse.xtext.common.Terminals.STRING");
						}
					)
				)
			)*
		)
		otherlv_4='}'
		{
			newLeafNode(otherlv_4, grammarAccess.getFactsAccess().getRightCurlyBracketKeyword_2());
		}
	)
;

// Entry rule entryRuleSupervisedBy
entryRuleSupervisedBy returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSupervisedByRule()); }
	iv_ruleSupervisedBy=ruleSupervisedBy
	{ $current=$iv_ruleSupervisedBy.current; }
	EOF;

// Rule SupervisedBy
ruleSupervisedBy returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='y supervisado por '
		{
			newLeafNode(otherlv_0, grammarAccess.getSupervisedByAccess().getYSupervisadoPorKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSupervisedByAccess().getAgentSubjectParserRuleCall_1_0());
				}
				lv_agent_1_0=ruleSubject
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSupervisedByRule());
					}
					set(
						$current,
						"agent",
						lv_agent_1_0,
						"org.trimatek.digideal.Contract.Subject");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
