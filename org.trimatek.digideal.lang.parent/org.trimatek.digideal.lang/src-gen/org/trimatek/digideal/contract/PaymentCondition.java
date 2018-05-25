/**
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.contract;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Payment Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.trimatek.digideal.contract.PaymentCondition#getCollector <em>Collector</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.PaymentCondition#getDescription <em>Description</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.PaymentCondition#getLevel <em>Level</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.PaymentCondition#getFacts <em>Facts</em>}</li>
 * </ul>
 *
 * @see org.trimatek.digideal.contract.ContractPackage#getPaymentCondition()
 * @model
 * @generated
 */
public interface PaymentCondition extends Sentence
{
  /**
   * Returns the value of the '<em><b>Collector</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Collector</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Collector</em>' reference.
   * @see #setCollector(Subject)
   * @see org.trimatek.digideal.contract.ContractPackage#getPaymentCondition_Collector()
   * @model
   * @generated
   */
  Subject getCollector();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.PaymentCondition#getCollector <em>Collector</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Collector</em>' reference.
   * @see #getCollector()
   * @generated
   */
  void setCollector(Subject value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see org.trimatek.digideal.contract.ContractPackage#getPaymentCondition_Description()
   * @model
   * @generated
   */
  String getDescription();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.PaymentCondition#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);

  /**
   * Returns the value of the '<em><b>Level</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Level</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Level</em>' containment reference.
   * @see #setLevel(FactsLevel)
   * @see org.trimatek.digideal.contract.ContractPackage#getPaymentCondition_Level()
   * @model containment="true"
   * @generated
   */
  FactsLevel getLevel();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.PaymentCondition#getLevel <em>Level</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Level</em>' containment reference.
   * @see #getLevel()
   * @generated
   */
  void setLevel(FactsLevel value);

  /**
   * Returns the value of the '<em><b>Facts</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Facts</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Facts</em>' containment reference.
   * @see #setFacts(Facts)
   * @see org.trimatek.digideal.contract.ContractPackage#getPaymentCondition_Facts()
   * @model containment="true"
   * @generated
   */
  Facts getFacts();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.PaymentCondition#getFacts <em>Facts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Facts</em>' containment reference.
   * @see #getFacts()
   * @generated
   */
  void setFacts(Facts value);

} // PaymentCondition
