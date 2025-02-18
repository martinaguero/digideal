/**
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.contract;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pay To</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.trimatek.digideal.contract.PayTo#getPayer <em>Payer</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.PayTo#getCollector <em>Collector</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.PayTo#getSts <em>Sts</em>}</li>
 * </ul>
 *
 * @see org.trimatek.digideal.contract.ContractPackage#getPayTo()
 * @model
 * @generated
 */
public interface PayTo extends Sentence
{
  /**
   * Returns the value of the '<em><b>Payer</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Payer</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Payer</em>' reference.
   * @see #setPayer(Subject)
   * @see org.trimatek.digideal.contract.ContractPackage#getPayTo_Payer()
   * @model
   * @generated
   */
  Subject getPayer();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.PayTo#getPayer <em>Payer</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Payer</em>' reference.
   * @see #getPayer()
   * @generated
   */
  void setPayer(Subject value);

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
   * @see org.trimatek.digideal.contract.ContractPackage#getPayTo_Collector()
   * @model
   * @generated
   */
  Subject getCollector();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.PayTo#getCollector <em>Collector</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Collector</em>' reference.
   * @see #getCollector()
   * @generated
   */
  void setCollector(Subject value);

  /**
   * Returns the value of the '<em><b>Sts</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sts</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sts</em>' attribute.
   * @see #setSts(int)
   * @see org.trimatek.digideal.contract.ContractPackage#getPayTo_Sts()
   * @model
   * @generated
   */
  int getSts();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.PayTo#getSts <em>Sts</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sts</em>' attribute.
   * @see #getSts()
   * @generated
   */
  void setSts(int value);

} // PayTo
