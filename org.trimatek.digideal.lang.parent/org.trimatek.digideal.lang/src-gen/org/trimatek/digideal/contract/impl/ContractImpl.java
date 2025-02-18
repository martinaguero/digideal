/**
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.contract.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.trimatek.digideal.contract.Contract;
import org.trimatek.digideal.contract.ContractPackage;
import org.trimatek.digideal.contract.Sentence;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contract</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.trimatek.digideal.contract.impl.ContractImpl#getCid <em>Cid</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.impl.ContractImpl#getParagraph <em>Paragraph</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContractImpl extends MinimalEObjectImpl.Container implements Contract
{
  /**
   * The default value of the '{@link #getCid() <em>Cid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCid()
   * @generated
   * @ordered
   */
  protected static final String CID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCid() <em>Cid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCid()
   * @generated
   * @ordered
   */
  protected String cid = CID_EDEFAULT;

  /**
   * The cached value of the '{@link #getParagraph() <em>Paragraph</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParagraph()
   * @generated
   * @ordered
   */
  protected EList<Sentence> paragraph;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ContractImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ContractPackage.Literals.CONTRACT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCid()
  {
    return cid;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCid(String newCid)
  {
    String oldCid = cid;
    cid = newCid;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ContractPackage.CONTRACT__CID, oldCid, cid));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Sentence> getParagraph()
  {
    if (paragraph == null)
    {
      paragraph = new EObjectContainmentEList<Sentence>(Sentence.class, this, ContractPackage.CONTRACT__PARAGRAPH);
    }
    return paragraph;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ContractPackage.CONTRACT__PARAGRAPH:
        return ((InternalEList<?>)getParagraph()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ContractPackage.CONTRACT__CID:
        return getCid();
      case ContractPackage.CONTRACT__PARAGRAPH:
        return getParagraph();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ContractPackage.CONTRACT__CID:
        setCid((String)newValue);
        return;
      case ContractPackage.CONTRACT__PARAGRAPH:
        getParagraph().clear();
        getParagraph().addAll((Collection<? extends Sentence>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ContractPackage.CONTRACT__CID:
        setCid(CID_EDEFAULT);
        return;
      case ContractPackage.CONTRACT__PARAGRAPH:
        getParagraph().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ContractPackage.CONTRACT__CID:
        return CID_EDEFAULT == null ? cid != null : !CID_EDEFAULT.equals(cid);
      case ContractPackage.CONTRACT__PARAGRAPH:
        return paragraph != null && !paragraph.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (cid: ");
    result.append(cid);
    result.append(')');
    return result.toString();
  }

} //ContractImpl
