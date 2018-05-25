/**
 * generated by Xtext 2.13.0
 */
package org.trimatek.digideal.contract.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.trimatek.digideal.contract.ContractPackage;
import org.trimatek.digideal.contract.Subject;
import org.trimatek.digideal.contract.SupervisedBy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Supervised By</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.trimatek.digideal.contract.impl.SupervisedByImpl#getAgent <em>Agent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SupervisedByImpl extends SentenceImpl implements SupervisedBy
{
  /**
   * The cached value of the '{@link #getAgent() <em>Agent</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAgent()
   * @generated
   * @ordered
   */
  protected Subject agent;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SupervisedByImpl()
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
    return ContractPackage.Literals.SUPERVISED_BY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Subject getAgent()
  {
    return agent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAgent(Subject newAgent, NotificationChain msgs)
  {
    Subject oldAgent = agent;
    agent = newAgent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContractPackage.SUPERVISED_BY__AGENT, oldAgent, newAgent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAgent(Subject newAgent)
  {
    if (newAgent != agent)
    {
      NotificationChain msgs = null;
      if (agent != null)
        msgs = ((InternalEObject)agent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContractPackage.SUPERVISED_BY__AGENT, null, msgs);
      if (newAgent != null)
        msgs = ((InternalEObject)newAgent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContractPackage.SUPERVISED_BY__AGENT, null, msgs);
      msgs = basicSetAgent(newAgent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ContractPackage.SUPERVISED_BY__AGENT, newAgent, newAgent));
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
      case ContractPackage.SUPERVISED_BY__AGENT:
        return basicSetAgent(null, msgs);
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
      case ContractPackage.SUPERVISED_BY__AGENT:
        return getAgent();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ContractPackage.SUPERVISED_BY__AGENT:
        setAgent((Subject)newValue);
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
      case ContractPackage.SUPERVISED_BY__AGENT:
        setAgent((Subject)null);
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
      case ContractPackage.SUPERVISED_BY__AGENT:
        return agent != null;
    }
    return super.eIsSet(featureID);
  }

} //SupervisedByImpl
