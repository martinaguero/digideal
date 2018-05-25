/**
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.contract;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.trimatek.digideal.contract.Sentence#getSentenceType <em>Sentence Type</em>}</li>
 * </ul>
 *
 * @see org.trimatek.digideal.contract.ContractPackage#getSentence()
 * @model
 * @generated
 */
public interface Sentence extends EObject
{
  /**
   * Returns the value of the '<em><b>Sentence Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sentence Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sentence Type</em>' attribute.
   * @see #setSentenceType(String)
   * @see org.trimatek.digideal.contract.ContractPackage#getSentence_SentenceType()
   * @model
   * @generated
   */
  String getSentenceType();

  /**
   * Sets the value of the '{@link org.trimatek.digideal.contract.Sentence#getSentenceType <em>Sentence Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sentence Type</em>' attribute.
   * @see #getSentenceType()
   * @generated
   */
  void setSentenceType(String value);

} // Sentence
