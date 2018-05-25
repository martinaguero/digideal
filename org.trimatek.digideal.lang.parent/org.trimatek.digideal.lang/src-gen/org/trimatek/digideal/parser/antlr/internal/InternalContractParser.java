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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalContractParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'CONTRATO #'", "'@'", "'{'", "','", "'}'", "'pagar\\u00E1 a'", "'la suma de STS'", "'si:'", "'demostrable con CUALQUIERA de los siguientes hechos:'", "'demostrable con TODOS los siguientes hechos:'", "'y supervisado por '"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalContractParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalContractParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalContractParser.tokenNames; }
    public String getGrammarFileName() { return "InternalContract.g"; }



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




    // $ANTLR start "entryRuleContract"
    // InternalContract.g:64:1: entryRuleContract returns [EObject current=null] : iv_ruleContract= ruleContract EOF ;
    public final EObject entryRuleContract() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContract = null;


        try {
            // InternalContract.g:64:49: (iv_ruleContract= ruleContract EOF )
            // InternalContract.g:65:2: iv_ruleContract= ruleContract EOF
            {
             newCompositeNode(grammarAccess.getContractRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleContract=ruleContract();

            state._fsp--;

             current =iv_ruleContract; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleContract"


    // $ANTLR start "ruleContract"
    // InternalContract.g:71:1: ruleContract returns [EObject current=null] : (otherlv_0= 'CONTRATO #' ( (lv_cid_1_0= RULE_ID ) ) ( (lv_paragraph_2_0= ruleSentence ) )* ) ;
    public final EObject ruleContract() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_cid_1_0=null;
        EObject lv_paragraph_2_0 = null;



        	enterRule();

        try {
            // InternalContract.g:77:2: ( (otherlv_0= 'CONTRATO #' ( (lv_cid_1_0= RULE_ID ) ) ( (lv_paragraph_2_0= ruleSentence ) )* ) )
            // InternalContract.g:78:2: (otherlv_0= 'CONTRATO #' ( (lv_cid_1_0= RULE_ID ) ) ( (lv_paragraph_2_0= ruleSentence ) )* )
            {
            // InternalContract.g:78:2: (otherlv_0= 'CONTRATO #' ( (lv_cid_1_0= RULE_ID ) ) ( (lv_paragraph_2_0= ruleSentence ) )* )
            // InternalContract.g:79:3: otherlv_0= 'CONTRATO #' ( (lv_cid_1_0= RULE_ID ) ) ( (lv_paragraph_2_0= ruleSentence ) )*
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getContractAccess().getCONTRATOKeyword_0());
            		
            // InternalContract.g:83:3: ( (lv_cid_1_0= RULE_ID ) )
            // InternalContract.g:84:4: (lv_cid_1_0= RULE_ID )
            {
            // InternalContract.g:84:4: (lv_cid_1_0= RULE_ID )
            // InternalContract.g:85:5: lv_cid_1_0= RULE_ID
            {
            lv_cid_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_cid_1_0, grammarAccess.getContractAccess().getCidIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getContractRule());
            					}
            					setWithLastConsumed(
            						current,
            						"cid",
            						lv_cid_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalContract.g:101:3: ( (lv_paragraph_2_0= ruleSentence ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_STRING||LA1_0==12||LA1_0==18||LA1_0==21) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalContract.g:102:4: (lv_paragraph_2_0= ruleSentence )
            	    {
            	    // InternalContract.g:102:4: (lv_paragraph_2_0= ruleSentence )
            	    // InternalContract.g:103:5: lv_paragraph_2_0= ruleSentence
            	    {

            	    					newCompositeNode(grammarAccess.getContractAccess().getParagraphSentenceParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_paragraph_2_0=ruleSentence();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getContractRule());
            	    					}
            	    					add(
            	    						current,
            	    						"paragraph",
            	    						lv_paragraph_2_0,
            	    						"org.trimatek.digideal.Contract.Sentence");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleContract"


    // $ANTLR start "entryRuleSentence"
    // InternalContract.g:124:1: entryRuleSentence returns [EObject current=null] : iv_ruleSentence= ruleSentence EOF ;
    public final EObject entryRuleSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSentence = null;


        try {
            // InternalContract.g:124:49: (iv_ruleSentence= ruleSentence EOF )
            // InternalContract.g:125:2: iv_ruleSentence= ruleSentence EOF
            {
             newCompositeNode(grammarAccess.getSentenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSentence=ruleSentence();

            state._fsp--;

             current =iv_ruleSentence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSentence"


    // $ANTLR start "ruleSentence"
    // InternalContract.g:131:1: ruleSentence returns [EObject current=null] : ( ( (lv_sentenceType_0_0= RULE_STRING ) ) | this_Subject_1= ruleSubject | this_Reference_2= ruleReference | this_PayTo_3= rulePayTo | this_PaymentCondition_4= rulePaymentCondition | this_SupervisedBy_5= ruleSupervisedBy ) ;
    public final EObject ruleSentence() throws RecognitionException {
        EObject current = null;

        Token lv_sentenceType_0_0=null;
        EObject this_Subject_1 = null;

        EObject this_Reference_2 = null;

        EObject this_PayTo_3 = null;

        EObject this_PaymentCondition_4 = null;

        EObject this_SupervisedBy_5 = null;



        	enterRule();

        try {
            // InternalContract.g:137:2: ( ( ( (lv_sentenceType_0_0= RULE_STRING ) ) | this_Subject_1= ruleSubject | this_Reference_2= ruleReference | this_PayTo_3= rulePayTo | this_PaymentCondition_4= rulePaymentCondition | this_SupervisedBy_5= ruleSupervisedBy ) )
            // InternalContract.g:138:2: ( ( (lv_sentenceType_0_0= RULE_STRING ) ) | this_Subject_1= ruleSubject | this_Reference_2= ruleReference | this_PayTo_3= rulePayTo | this_PaymentCondition_4= rulePaymentCondition | this_SupervisedBy_5= ruleSupervisedBy )
            {
            // InternalContract.g:138:2: ( ( (lv_sentenceType_0_0= RULE_STRING ) ) | this_Subject_1= ruleSubject | this_Reference_2= ruleReference | this_PayTo_3= rulePayTo | this_PaymentCondition_4= rulePaymentCondition | this_SupervisedBy_5= ruleSupervisedBy )
            int alt2=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt2=1;
                }
                break;
            case 12:
                {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==RULE_ID) ) {
                    switch ( input.LA(3) ) {
                    case 13:
                        {
                        alt2=2;
                        }
                        break;
                    case 16:
                        {
                        alt2=4;
                        }
                        break;
                    case EOF:
                    case RULE_STRING:
                    case 12:
                    case 18:
                    case 21:
                        {
                        alt2=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 5, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;
                }
                }
                break;
            case 18:
                {
                alt2=5;
                }
                break;
            case 21:
                {
                alt2=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalContract.g:139:3: ( (lv_sentenceType_0_0= RULE_STRING ) )
                    {
                    // InternalContract.g:139:3: ( (lv_sentenceType_0_0= RULE_STRING ) )
                    // InternalContract.g:140:4: (lv_sentenceType_0_0= RULE_STRING )
                    {
                    // InternalContract.g:140:4: (lv_sentenceType_0_0= RULE_STRING )
                    // InternalContract.g:141:5: lv_sentenceType_0_0= RULE_STRING
                    {
                    lv_sentenceType_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    					newLeafNode(lv_sentenceType_0_0, grammarAccess.getSentenceAccess().getSentenceTypeSTRINGTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getSentenceRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"sentenceType",
                    						lv_sentenceType_0_0,
                    						"org.eclipse.xtext.common.Terminals.STRING");
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalContract.g:158:3: this_Subject_1= ruleSubject
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getSubjectParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Subject_1=ruleSubject();

                    state._fsp--;


                    			current = this_Subject_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalContract.g:167:3: this_Reference_2= ruleReference
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getReferenceParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Reference_2=ruleReference();

                    state._fsp--;


                    			current = this_Reference_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalContract.g:176:3: this_PayTo_3= rulePayTo
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getPayToParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_PayTo_3=rulePayTo();

                    state._fsp--;


                    			current = this_PayTo_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalContract.g:185:3: this_PaymentCondition_4= rulePaymentCondition
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getPaymentConditionParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_PaymentCondition_4=rulePaymentCondition();

                    state._fsp--;


                    			current = this_PaymentCondition_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalContract.g:194:3: this_SupervisedBy_5= ruleSupervisedBy
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getSupervisedByParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_SupervisedBy_5=ruleSupervisedBy();

                    state._fsp--;


                    			current = this_SupervisedBy_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSentence"


    // $ANTLR start "entryRuleSubject"
    // InternalContract.g:206:1: entryRuleSubject returns [EObject current=null] : iv_ruleSubject= ruleSubject EOF ;
    public final EObject entryRuleSubject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubject = null;


        try {
            // InternalContract.g:206:48: (iv_ruleSubject= ruleSubject EOF )
            // InternalContract.g:207:2: iv_ruleSubject= ruleSubject EOF
            {
             newCompositeNode(grammarAccess.getSubjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSubject=ruleSubject();

            state._fsp--;

             current =iv_ruleSubject; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSubject"


    // $ANTLR start "ruleSubject"
    // InternalContract.g:213:1: ruleSubject returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_address_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_email_5_0= ruleEmail ) ) otherlv_6= '}' ) ;
    public final EObject ruleSubject() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_address_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_email_5_0 = null;



        	enterRule();

        try {
            // InternalContract.g:219:2: ( (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_address_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_email_5_0= ruleEmail ) ) otherlv_6= '}' ) )
            // InternalContract.g:220:2: (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_address_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_email_5_0= ruleEmail ) ) otherlv_6= '}' )
            {
            // InternalContract.g:220:2: (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_address_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_email_5_0= ruleEmail ) ) otherlv_6= '}' )
            // InternalContract.g:221:3: otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_address_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_email_5_0= ruleEmail ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getSubjectAccess().getCommercialAtKeyword_0());
            		
            // InternalContract.g:225:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalContract.g:226:4: (lv_name_1_0= RULE_ID )
            {
            // InternalContract.g:226:4: (lv_name_1_0= RULE_ID )
            // InternalContract.g:227:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_1_0, grammarAccess.getSubjectAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSubjectRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getSubjectAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalContract.g:247:3: ( (lv_address_3_0= RULE_STRING ) )
            // InternalContract.g:248:4: (lv_address_3_0= RULE_STRING )
            {
            // InternalContract.g:248:4: (lv_address_3_0= RULE_STRING )
            // InternalContract.g:249:5: lv_address_3_0= RULE_STRING
            {
            lv_address_3_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            					newLeafNode(lv_address_3_0, grammarAccess.getSubjectAccess().getAddressSTRINGTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSubjectRule());
            					}
            					setWithLastConsumed(
            						current,
            						"address",
            						lv_address_3_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getSubjectAccess().getCommaKeyword_4());
            		
            // InternalContract.g:269:3: ( (lv_email_5_0= ruleEmail ) )
            // InternalContract.g:270:4: (lv_email_5_0= ruleEmail )
            {
            // InternalContract.g:270:4: (lv_email_5_0= ruleEmail )
            // InternalContract.g:271:5: lv_email_5_0= ruleEmail
            {

            					newCompositeNode(grammarAccess.getSubjectAccess().getEmailEmailParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_8);
            lv_email_5_0=ruleEmail();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSubjectRule());
            					}
            					set(
            						current,
            						"email",
            						lv_email_5_0,
            						"org.trimatek.digideal.Contract.Email");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getSubjectAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSubject"


    // $ANTLR start "entryRuleEmail"
    // InternalContract.g:296:1: entryRuleEmail returns [String current=null] : iv_ruleEmail= ruleEmail EOF ;
    public final String entryRuleEmail() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEmail = null;


        try {
            // InternalContract.g:296:45: (iv_ruleEmail= ruleEmail EOF )
            // InternalContract.g:297:2: iv_ruleEmail= ruleEmail EOF
            {
             newCompositeNode(grammarAccess.getEmailRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEmail=ruleEmail();

            state._fsp--;

             current =iv_ruleEmail.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEmail"


    // $ANTLR start "ruleEmail"
    // InternalContract.g:303:1: ruleEmail returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleEmail() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalContract.g:309:2: (this_STRING_0= RULE_STRING )
            // InternalContract.g:310:2: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		current.merge(this_STRING_0);
            	

            		newLeafNode(this_STRING_0, grammarAccess.getEmailAccess().getSTRINGTerminalRuleCall());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEmail"


    // $ANTLR start "entryRuleReference"
    // InternalContract.g:320:1: entryRuleReference returns [EObject current=null] : iv_ruleReference= ruleReference EOF ;
    public final EObject entryRuleReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReference = null;


        try {
            // InternalContract.g:320:50: (iv_ruleReference= ruleReference EOF )
            // InternalContract.g:321:2: iv_ruleReference= ruleReference EOF
            {
             newCompositeNode(grammarAccess.getReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReference=ruleReference();

            state._fsp--;

             current =iv_ruleReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReference"


    // $ANTLR start "ruleReference"
    // InternalContract.g:327:1: ruleReference returns [EObject current=null] : (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalContract.g:333:2: ( (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) ) )
            // InternalContract.g:334:2: (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalContract.g:334:2: (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) )
            // InternalContract.g:335:3: otherlv_0= '@' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getReferenceAccess().getCommercialAtKeyword_0());
            		
            // InternalContract.g:339:3: ( (otherlv_1= RULE_ID ) )
            // InternalContract.g:340:4: (otherlv_1= RULE_ID )
            {
            // InternalContract.g:340:4: (otherlv_1= RULE_ID )
            // InternalContract.g:341:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_1, grammarAccess.getReferenceAccess().getTypeSubjectCrossReference_1_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReference"


    // $ANTLR start "entryRulePayTo"
    // InternalContract.g:356:1: entryRulePayTo returns [EObject current=null] : iv_rulePayTo= rulePayTo EOF ;
    public final EObject entryRulePayTo() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePayTo = null;


        try {
            // InternalContract.g:356:46: (iv_rulePayTo= rulePayTo EOF )
            // InternalContract.g:357:2: iv_rulePayTo= rulePayTo EOF
            {
             newCompositeNode(grammarAccess.getPayToRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePayTo=rulePayTo();

            state._fsp--;

             current =iv_rulePayTo; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePayTo"


    // $ANTLR start "rulePayTo"
    // InternalContract.g:363:1: rulePayTo returns [EObject current=null] : (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'pagar\\u00E1 a' (otherlv_3= '@' ( (otherlv_4= RULE_ID ) ) ) otherlv_5= 'la suma de STS' ( (lv_sts_6_0= RULE_INT ) ) ) ;
    public final EObject rulePayTo() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_sts_6_0=null;


        	enterRule();

        try {
            // InternalContract.g:369:2: ( (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'pagar\\u00E1 a' (otherlv_3= '@' ( (otherlv_4= RULE_ID ) ) ) otherlv_5= 'la suma de STS' ( (lv_sts_6_0= RULE_INT ) ) ) )
            // InternalContract.g:370:2: (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'pagar\\u00E1 a' (otherlv_3= '@' ( (otherlv_4= RULE_ID ) ) ) otherlv_5= 'la suma de STS' ( (lv_sts_6_0= RULE_INT ) ) )
            {
            // InternalContract.g:370:2: (otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'pagar\\u00E1 a' (otherlv_3= '@' ( (otherlv_4= RULE_ID ) ) ) otherlv_5= 'la suma de STS' ( (lv_sts_6_0= RULE_INT ) ) )
            // InternalContract.g:371:3: otherlv_0= '@' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'pagar\\u00E1 a' (otherlv_3= '@' ( (otherlv_4= RULE_ID ) ) ) otherlv_5= 'la suma de STS' ( (lv_sts_6_0= RULE_INT ) )
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getPayToAccess().getCommercialAtKeyword_0());
            		
            // InternalContract.g:375:3: ( (otherlv_1= RULE_ID ) )
            // InternalContract.g:376:4: (otherlv_1= RULE_ID )
            {
            // InternalContract.g:376:4: (otherlv_1= RULE_ID )
            // InternalContract.g:377:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPayToRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(otherlv_1, grammarAccess.getPayToAccess().getPayerSubjectCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getPayToAccess().getPagarAKeyword_2());
            		
            // InternalContract.g:392:3: (otherlv_3= '@' ( (otherlv_4= RULE_ID ) ) )
            // InternalContract.g:393:4: otherlv_3= '@' ( (otherlv_4= RULE_ID ) )
            {
            otherlv_3=(Token)match(input,12,FOLLOW_3); 

            				newLeafNode(otherlv_3, grammarAccess.getPayToAccess().getCommercialAtKeyword_3_0());
            			
            // InternalContract.g:397:4: ( (otherlv_4= RULE_ID ) )
            // InternalContract.g:398:5: (otherlv_4= RULE_ID )
            {
            // InternalContract.g:398:5: (otherlv_4= RULE_ID )
            // InternalContract.g:399:6: otherlv_4= RULE_ID
            {

            						if (current==null) {
            							current = createModelElement(grammarAccess.getPayToRule());
            						}
            					
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_11); 

            						newLeafNode(otherlv_4, grammarAccess.getPayToAccess().getCollectorSubjectCrossReference_3_1_0());
            					

            }


            }


            }

            otherlv_5=(Token)match(input,17,FOLLOW_12); 

            			newLeafNode(otherlv_5, grammarAccess.getPayToAccess().getLaSumaDeSTSKeyword_4());
            		
            // InternalContract.g:415:3: ( (lv_sts_6_0= RULE_INT ) )
            // InternalContract.g:416:4: (lv_sts_6_0= RULE_INT )
            {
            // InternalContract.g:416:4: (lv_sts_6_0= RULE_INT )
            // InternalContract.g:417:5: lv_sts_6_0= RULE_INT
            {
            lv_sts_6_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            					newLeafNode(lv_sts_6_0, grammarAccess.getPayToAccess().getStsINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPayToRule());
            					}
            					setWithLastConsumed(
            						current,
            						"sts",
            						lv_sts_6_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePayTo"


    // $ANTLR start "entryRulePaymentCondition"
    // InternalContract.g:437:1: entryRulePaymentCondition returns [EObject current=null] : iv_rulePaymentCondition= rulePaymentCondition EOF ;
    public final EObject entryRulePaymentCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePaymentCondition = null;


        try {
            // InternalContract.g:437:57: (iv_rulePaymentCondition= rulePaymentCondition EOF )
            // InternalContract.g:438:2: iv_rulePaymentCondition= rulePaymentCondition EOF
            {
             newCompositeNode(grammarAccess.getPaymentConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePaymentCondition=rulePaymentCondition();

            state._fsp--;

             current =iv_rulePaymentCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePaymentCondition"


    // $ANTLR start "rulePaymentCondition"
    // InternalContract.g:444:1: rulePaymentCondition returns [EObject current=null] : (otherlv_0= 'si:' (otherlv_1= '@' ( (otherlv_2= RULE_ID ) ) ) ( (lv_description_3_0= RULE_STRING ) ) ( (lv_level_4_0= ruleFactsLevel ) ) ( (lv_facts_5_0= ruleFacts ) ) ) ;
    public final EObject rulePaymentCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_description_3_0=null;
        EObject lv_level_4_0 = null;

        EObject lv_facts_5_0 = null;



        	enterRule();

        try {
            // InternalContract.g:450:2: ( (otherlv_0= 'si:' (otherlv_1= '@' ( (otherlv_2= RULE_ID ) ) ) ( (lv_description_3_0= RULE_STRING ) ) ( (lv_level_4_0= ruleFactsLevel ) ) ( (lv_facts_5_0= ruleFacts ) ) ) )
            // InternalContract.g:451:2: (otherlv_0= 'si:' (otherlv_1= '@' ( (otherlv_2= RULE_ID ) ) ) ( (lv_description_3_0= RULE_STRING ) ) ( (lv_level_4_0= ruleFactsLevel ) ) ( (lv_facts_5_0= ruleFacts ) ) )
            {
            // InternalContract.g:451:2: (otherlv_0= 'si:' (otherlv_1= '@' ( (otherlv_2= RULE_ID ) ) ) ( (lv_description_3_0= RULE_STRING ) ) ( (lv_level_4_0= ruleFactsLevel ) ) ( (lv_facts_5_0= ruleFacts ) ) )
            // InternalContract.g:452:3: otherlv_0= 'si:' (otherlv_1= '@' ( (otherlv_2= RULE_ID ) ) ) ( (lv_description_3_0= RULE_STRING ) ) ( (lv_level_4_0= ruleFactsLevel ) ) ( (lv_facts_5_0= ruleFacts ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getPaymentConditionAccess().getSiKeyword_0());
            		
            // InternalContract.g:456:3: (otherlv_1= '@' ( (otherlv_2= RULE_ID ) ) )
            // InternalContract.g:457:4: otherlv_1= '@' ( (otherlv_2= RULE_ID ) )
            {
            otherlv_1=(Token)match(input,12,FOLLOW_3); 

            				newLeafNode(otherlv_1, grammarAccess.getPaymentConditionAccess().getCommercialAtKeyword_1_0());
            			
            // InternalContract.g:461:4: ( (otherlv_2= RULE_ID ) )
            // InternalContract.g:462:5: (otherlv_2= RULE_ID )
            {
            // InternalContract.g:462:5: (otherlv_2= RULE_ID )
            // InternalContract.g:463:6: otherlv_2= RULE_ID
            {

            						if (current==null) {
            							current = createModelElement(grammarAccess.getPaymentConditionRule());
            						}
            					
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_6); 

            						newLeafNode(otherlv_2, grammarAccess.getPaymentConditionAccess().getCollectorSubjectCrossReference_1_1_0());
            					

            }


            }


            }

            // InternalContract.g:475:3: ( (lv_description_3_0= RULE_STRING ) )
            // InternalContract.g:476:4: (lv_description_3_0= RULE_STRING )
            {
            // InternalContract.g:476:4: (lv_description_3_0= RULE_STRING )
            // InternalContract.g:477:5: lv_description_3_0= RULE_STRING
            {
            lv_description_3_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_description_3_0, grammarAccess.getPaymentConditionAccess().getDescriptionSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPaymentConditionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"description",
            						lv_description_3_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalContract.g:493:3: ( (lv_level_4_0= ruleFactsLevel ) )
            // InternalContract.g:494:4: (lv_level_4_0= ruleFactsLevel )
            {
            // InternalContract.g:494:4: (lv_level_4_0= ruleFactsLevel )
            // InternalContract.g:495:5: lv_level_4_0= ruleFactsLevel
            {

            					newCompositeNode(grammarAccess.getPaymentConditionAccess().getLevelFactsLevelParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_5);
            lv_level_4_0=ruleFactsLevel();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPaymentConditionRule());
            					}
            					set(
            						current,
            						"level",
            						lv_level_4_0,
            						"org.trimatek.digideal.Contract.FactsLevel");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalContract.g:512:3: ( (lv_facts_5_0= ruleFacts ) )
            // InternalContract.g:513:4: (lv_facts_5_0= ruleFacts )
            {
            // InternalContract.g:513:4: (lv_facts_5_0= ruleFacts )
            // InternalContract.g:514:5: lv_facts_5_0= ruleFacts
            {

            					newCompositeNode(grammarAccess.getPaymentConditionAccess().getFactsFactsParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_facts_5_0=ruleFacts();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPaymentConditionRule());
            					}
            					set(
            						current,
            						"facts",
            						lv_facts_5_0,
            						"org.trimatek.digideal.Contract.Facts");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePaymentCondition"


    // $ANTLR start "entryRuleFactsLevel"
    // InternalContract.g:535:1: entryRuleFactsLevel returns [EObject current=null] : iv_ruleFactsLevel= ruleFactsLevel EOF ;
    public final EObject entryRuleFactsLevel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFactsLevel = null;


        try {
            // InternalContract.g:535:51: (iv_ruleFactsLevel= ruleFactsLevel EOF )
            // InternalContract.g:536:2: iv_ruleFactsLevel= ruleFactsLevel EOF
            {
             newCompositeNode(grammarAccess.getFactsLevelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFactsLevel=ruleFactsLevel();

            state._fsp--;

             current =iv_ruleFactsLevel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFactsLevel"


    // $ANTLR start "ruleFactsLevel"
    // InternalContract.g:542:1: ruleFactsLevel returns [EObject current=null] : ( ( (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts ) ) ) ;
    public final EObject ruleFactsLevel() throws RecognitionException {
        EObject current = null;

        EObject lv_options_0_1 = null;

        EObject lv_options_0_2 = null;



        	enterRule();

        try {
            // InternalContract.g:548:2: ( ( ( (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts ) ) ) )
            // InternalContract.g:549:2: ( ( (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts ) ) )
            {
            // InternalContract.g:549:2: ( ( (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts ) ) )
            // InternalContract.g:550:3: ( (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts ) )
            {
            // InternalContract.g:550:3: ( (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts ) )
            // InternalContract.g:551:4: (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts )
            {
            // InternalContract.g:551:4: (lv_options_0_1= ruleAnyFact | lv_options_0_2= ruleAllFacts )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==19) ) {
                alt3=1;
            }
            else if ( (LA3_0==20) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalContract.g:552:5: lv_options_0_1= ruleAnyFact
                    {

                    					newCompositeNode(grammarAccess.getFactsLevelAccess().getOptionsAnyFactParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_options_0_1=ruleAnyFact();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFactsLevelRule());
                    					}
                    					set(
                    						current,
                    						"options",
                    						lv_options_0_1,
                    						"org.trimatek.digideal.Contract.AnyFact");
                    					afterParserOrEnumRuleCall();
                    				

                    }
                    break;
                case 2 :
                    // InternalContract.g:568:5: lv_options_0_2= ruleAllFacts
                    {

                    					newCompositeNode(grammarAccess.getFactsLevelAccess().getOptionsAllFactsParserRuleCall_0_1());
                    				
                    pushFollow(FOLLOW_2);
                    lv_options_0_2=ruleAllFacts();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFactsLevelRule());
                    					}
                    					set(
                    						current,
                    						"options",
                    						lv_options_0_2,
                    						"org.trimatek.digideal.Contract.AllFacts");
                    					afterParserOrEnumRuleCall();
                    				

                    }
                    break;

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFactsLevel"


    // $ANTLR start "entryRuleAnyFact"
    // InternalContract.g:589:1: entryRuleAnyFact returns [EObject current=null] : iv_ruleAnyFact= ruleAnyFact EOF ;
    public final EObject entryRuleAnyFact() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyFact = null;


        try {
            // InternalContract.g:589:48: (iv_ruleAnyFact= ruleAnyFact EOF )
            // InternalContract.g:590:2: iv_ruleAnyFact= ruleAnyFact EOF
            {
             newCompositeNode(grammarAccess.getAnyFactRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnyFact=ruleAnyFact();

            state._fsp--;

             current =iv_ruleAnyFact; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnyFact"


    // $ANTLR start "ruleAnyFact"
    // InternalContract.g:596:1: ruleAnyFact returns [EObject current=null] : ( (lv_value_0_0= 'demostrable con CUALQUIERA de los siguientes hechos:' ) ) ;
    public final EObject ruleAnyFact() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalContract.g:602:2: ( ( (lv_value_0_0= 'demostrable con CUALQUIERA de los siguientes hechos:' ) ) )
            // InternalContract.g:603:2: ( (lv_value_0_0= 'demostrable con CUALQUIERA de los siguientes hechos:' ) )
            {
            // InternalContract.g:603:2: ( (lv_value_0_0= 'demostrable con CUALQUIERA de los siguientes hechos:' ) )
            // InternalContract.g:604:3: (lv_value_0_0= 'demostrable con CUALQUIERA de los siguientes hechos:' )
            {
            // InternalContract.g:604:3: (lv_value_0_0= 'demostrable con CUALQUIERA de los siguientes hechos:' )
            // InternalContract.g:605:4: lv_value_0_0= 'demostrable con CUALQUIERA de los siguientes hechos:'
            {
            lv_value_0_0=(Token)match(input,19,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getAnyFactAccess().getValueDemostrableConCUALQUIERADeLosSiguientesHechosKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getAnyFactRule());
            				}
            				setWithLastConsumed(current, "value", lv_value_0_0, "demostrable con CUALQUIERA de los siguientes hechos:");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnyFact"


    // $ANTLR start "entryRuleAllFacts"
    // InternalContract.g:620:1: entryRuleAllFacts returns [EObject current=null] : iv_ruleAllFacts= ruleAllFacts EOF ;
    public final EObject entryRuleAllFacts() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAllFacts = null;


        try {
            // InternalContract.g:620:49: (iv_ruleAllFacts= ruleAllFacts EOF )
            // InternalContract.g:621:2: iv_ruleAllFacts= ruleAllFacts EOF
            {
             newCompositeNode(grammarAccess.getAllFactsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAllFacts=ruleAllFacts();

            state._fsp--;

             current =iv_ruleAllFacts; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAllFacts"


    // $ANTLR start "ruleAllFacts"
    // InternalContract.g:627:1: ruleAllFacts returns [EObject current=null] : ( (lv_value_0_0= 'demostrable con TODOS los siguientes hechos:' ) ) ;
    public final EObject ruleAllFacts() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalContract.g:633:2: ( ( (lv_value_0_0= 'demostrable con TODOS los siguientes hechos:' ) ) )
            // InternalContract.g:634:2: ( (lv_value_0_0= 'demostrable con TODOS los siguientes hechos:' ) )
            {
            // InternalContract.g:634:2: ( (lv_value_0_0= 'demostrable con TODOS los siguientes hechos:' ) )
            // InternalContract.g:635:3: (lv_value_0_0= 'demostrable con TODOS los siguientes hechos:' )
            {
            // InternalContract.g:635:3: (lv_value_0_0= 'demostrable con TODOS los siguientes hechos:' )
            // InternalContract.g:636:4: lv_value_0_0= 'demostrable con TODOS los siguientes hechos:'
            {
            lv_value_0_0=(Token)match(input,20,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getAllFactsAccess().getValueDemostrableConTODOSLosSiguientesHechosKeyword_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getAllFactsRule());
            				}
            				setWithLastConsumed(current, "value", lv_value_0_0, "demostrable con TODOS los siguientes hechos:");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAllFacts"


    // $ANTLR start "entryRuleFacts"
    // InternalContract.g:651:1: entryRuleFacts returns [EObject current=null] : iv_ruleFacts= ruleFacts EOF ;
    public final EObject entryRuleFacts() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFacts = null;


        try {
            // InternalContract.g:651:46: (iv_ruleFacts= ruleFacts EOF )
            // InternalContract.g:652:2: iv_ruleFacts= ruleFacts EOF
            {
             newCompositeNode(grammarAccess.getFactsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFacts=ruleFacts();

            state._fsp--;

             current =iv_ruleFacts; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFacts"


    // $ANTLR start "ruleFacts"
    // InternalContract.g:658:1: ruleFacts returns [EObject current=null] : (otherlv_0= '{' ( ( (lv_fact_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )* ) otherlv_4= '}' ) ;
    public final EObject ruleFacts() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_fact_1_0=null;
        Token otherlv_2=null;
        Token lv_fact_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalContract.g:664:2: ( (otherlv_0= '{' ( ( (lv_fact_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )* ) otherlv_4= '}' ) )
            // InternalContract.g:665:2: (otherlv_0= '{' ( ( (lv_fact_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )* ) otherlv_4= '}' )
            {
            // InternalContract.g:665:2: (otherlv_0= '{' ( ( (lv_fact_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )* ) otherlv_4= '}' )
            // InternalContract.g:666:3: otherlv_0= '{' ( ( (lv_fact_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )* ) otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getFactsAccess().getLeftCurlyBracketKeyword_0());
            		
            // InternalContract.g:670:3: ( ( (lv_fact_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )* )
            // InternalContract.g:671:4: ( (lv_fact_1_0= RULE_STRING ) ) (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )*
            {
            // InternalContract.g:671:4: ( (lv_fact_1_0= RULE_STRING ) )
            // InternalContract.g:672:5: (lv_fact_1_0= RULE_STRING )
            {
            // InternalContract.g:672:5: (lv_fact_1_0= RULE_STRING )
            // InternalContract.g:673:6: lv_fact_1_0= RULE_STRING
            {
            lv_fact_1_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            						newLeafNode(lv_fact_1_0, grammarAccess.getFactsAccess().getFactSTRINGTerminalRuleCall_1_0_0());
            					

            						if (current==null) {
            							current = createModelElement(grammarAccess.getFactsRule());
            						}
            						addWithLastConsumed(
            							current,
            							"fact",
            							lv_fact_1_0,
            							"org.eclipse.xtext.common.Terminals.STRING");
            					

            }


            }

            // InternalContract.g:689:4: (otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==14) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalContract.g:690:5: otherlv_2= ',' ( (lv_fact_3_0= RULE_STRING ) )
            	    {
            	    otherlv_2=(Token)match(input,14,FOLLOW_6); 

            	    					newLeafNode(otherlv_2, grammarAccess.getFactsAccess().getCommaKeyword_1_1_0());
            	    				
            	    // InternalContract.g:694:5: ( (lv_fact_3_0= RULE_STRING ) )
            	    // InternalContract.g:695:6: (lv_fact_3_0= RULE_STRING )
            	    {
            	    // InternalContract.g:695:6: (lv_fact_3_0= RULE_STRING )
            	    // InternalContract.g:696:7: lv_fact_3_0= RULE_STRING
            	    {
            	    lv_fact_3_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            	    							newLeafNode(lv_fact_3_0, grammarAccess.getFactsAccess().getFactSTRINGTerminalRuleCall_1_1_1_0());
            	    						

            	    							if (current==null) {
            	    								current = createModelElement(grammarAccess.getFactsRule());
            	    							}
            	    							addWithLastConsumed(
            	    								current,
            	    								"fact",
            	    								lv_fact_3_0,
            	    								"org.eclipse.xtext.common.Terminals.STRING");
            	    						

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getFactsAccess().getRightCurlyBracketKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFacts"


    // $ANTLR start "entryRuleSupervisedBy"
    // InternalContract.g:722:1: entryRuleSupervisedBy returns [EObject current=null] : iv_ruleSupervisedBy= ruleSupervisedBy EOF ;
    public final EObject entryRuleSupervisedBy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSupervisedBy = null;


        try {
            // InternalContract.g:722:53: (iv_ruleSupervisedBy= ruleSupervisedBy EOF )
            // InternalContract.g:723:2: iv_ruleSupervisedBy= ruleSupervisedBy EOF
            {
             newCompositeNode(grammarAccess.getSupervisedByRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSupervisedBy=ruleSupervisedBy();

            state._fsp--;

             current =iv_ruleSupervisedBy; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSupervisedBy"


    // $ANTLR start "ruleSupervisedBy"
    // InternalContract.g:729:1: ruleSupervisedBy returns [EObject current=null] : (otherlv_0= 'y supervisado por ' ( (lv_agent_1_0= ruleSubject ) ) ) ;
    public final EObject ruleSupervisedBy() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_agent_1_0 = null;



        	enterRule();

        try {
            // InternalContract.g:735:2: ( (otherlv_0= 'y supervisado por ' ( (lv_agent_1_0= ruleSubject ) ) ) )
            // InternalContract.g:736:2: (otherlv_0= 'y supervisado por ' ( (lv_agent_1_0= ruleSubject ) ) )
            {
            // InternalContract.g:736:2: (otherlv_0= 'y supervisado por ' ( (lv_agent_1_0= ruleSubject ) ) )
            // InternalContract.g:737:3: otherlv_0= 'y supervisado por ' ( (lv_agent_1_0= ruleSubject ) )
            {
            otherlv_0=(Token)match(input,21,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getSupervisedByAccess().getYSupervisadoPorKeyword_0());
            		
            // InternalContract.g:741:3: ( (lv_agent_1_0= ruleSubject ) )
            // InternalContract.g:742:4: (lv_agent_1_0= ruleSubject )
            {
            // InternalContract.g:742:4: (lv_agent_1_0= ruleSubject )
            // InternalContract.g:743:5: lv_agent_1_0= ruleSubject
            {

            					newCompositeNode(grammarAccess.getSupervisedByAccess().getAgentSubjectParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_agent_1_0=ruleSubject();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSupervisedByRule());
            					}
            					set(
            						current,
            						"agent",
            						lv_agent_1_0,
            						"org.trimatek.digideal.Contract.Subject");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSupervisedBy"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000241022L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x000000000000C000L});

}