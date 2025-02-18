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
import org.trimatek.digideal.contract.Facts;
import org.trimatek.digideal.contract.FactsLevel;
import org.trimatek.digideal.contract.PaymentCondition;
import org.trimatek.digideal.contract.Subject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Payment Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.trimatek.digideal.contract.impl.PaymentConditionImpl#getCollector <em>Collector</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.impl.PaymentConditionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.impl.PaymentConditionImpl#getLevel <em>Level</em>}</li>
 *   <li>{@link org.trimatek.digideal.contract.impl.PaymentConditionImpl#getFacts <em>Facts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PaymentConditionImpl extends SentenceImpl implements PaymentCondition
{
  /**
   * The cached value of the '{@link #getCollector() <em>Collector</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCollector()
   * @generated
   * @ordered
   */
  protected Subject collector;

  /**
   * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected static final String DESCRIPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected String description = DESCRIPTION_EDEFAULT;

  /**
   * The cached value of the '{@link #getLevel() <em>Level</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLevel()
   * @generated
   * @ordered
   */
  protected FactsLevel level;

  /**
   * The cached value of the '{@link #getFacts() <em>Facts</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFacts()
   * @generated
   * @ordered
   */
  protected Facts facts;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PaymentConditionImpl()
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
    return ContractPackage.Literals.PAYMENT_CONDITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Subject getCollector()
  {
    if (collector != null && collector.eIsProxy())
    {
      InternalEObject oldCollector = (InternalEObject)collector;
      collector = (Subject)eResolveProxy(oldCollector);
      if (collector != oldCollector)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContractPackage.PAYMENT_CONDITION__COLLECTOR, oldCollector, collector));
      }
    }
    return collector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Subject basicGetCollector()
  {
    return collector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCollector(Subject newCollector)
  {
    Subject oldCollector = collector;
    collector = newCollector;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ContractPackage.PAYMENT_CONDITION__COLLECTOR, oldCollector, collector));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription(String newDescription)
  {
    String oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ContractPackage.PAYMENT_CONDITION__DESCRIPTION, oldDescription, description));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FactsLevel getLevel()
  {
    return level;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLevel(FactsLevel newLevel, NotificationChain msgs)
  {
    FactsLevel oldLevel = level;
    level = newLevel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContractPackage.PAYMENT_CONDITION__LEVEL, oldLevel, newLevel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLevel(FactsLevel newLevel)
  {
    if (newLevel != level)
    {
      NotificationChain msgs = null;
      if (level != null)
        msgs = ((InternalEObject)level).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContractPackage.PAYMENT_CONDITION__LEVEL, null, msgs);
      if (newLevel != null)
        msgs = ((InternalEObject)newLevel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContractPackage.PAYMENT_CONDITION__LEVEL, null, msgs);
      msgs = basicSetLevel(newLevel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ContractPackage.PAYMENT_CONDITION__LEVEL, newLevel, newLevel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Facts getFacts()
  {
    return facts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFacts(Facts newFacts, NotificationChain msgs)
  {
    Facts oldFacts = facts;
    facts = newFacts;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContractPackage.PAYMENT_CONDITION__FACTS, oldFacts, newFacts);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFacts(Facts newFacts)
  {
    if (newFacts != facts)
    {
      NotificationChain msgs = null;
      if (facts != null)
        msgs = ((InternalEObject)facts).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContractPackage.PAYMENT_CONDITION__FACTS, null, msgs);
      if (newFacts != null)
        msgs = ((InternalEObject)newFacts).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContractPackage.PAYMENT_CONDITION__FACTS, null, msgs);
      msgs = basicSetFacts(newFacts, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ContractPackage.PAYMENT_CONDITION__FACTS, newFacts, newFacts));
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
      case ContractPackage.PAYMENT_CONDITION__LEVEL:
        return basicSetLevel(null, msgs);
      case ContractPackage.PAYMENT_CONDITION__FACTS:
        return basicSetFacts(null, msgs);
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
      case ContractPackage.PAYMENT_CONDITION__COLLECTOR:
        if (resolve) return getCollector();
        return basicGetCollector();
      case ContractPackage.PAYMENT_CONDITION__DESCRIPTION:
        return getDescription();
      case ContractPackage.PAYMENT_CONDITION__LEVEL:
        return getLevel();
      case ContractPackage.PAYMENT_CONDITION__FACTS:
        return getFacts();
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
      case ContractPackage.PAYMENT_CONDITION__COLLECTOR:
        setCollector((Subject)newValue);
        return;
      case ContractPackage.PAYMENT_CONDITION__DESCRIPTION:
        setDescription((String)newValue);
        return;
      case ContractPackage.PAYMENT_CONDITION__LEVEL:
        setLevel((FactsLevel)newValue);
        return;
      case ContractPackage.PAYMENT_CONDITION__FACTS:
        setFacts((Facts)newValue);
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
      case ContractPackage.PAYMENT_CONDITION__COLLECTOR:
        setCollector((Subject)null);
        return;
      case ContractPackage.PAYMENT_CONDITION__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
        return;
      case ContractPackage.PAYMENT_CONDITION__LEVEL:
        setLevel((FactsLevel)null);
        return;
      case ContractPackage.PAYMENT_CONDITION__FACTS:
        setFacts((Facts)null);
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
      case ContractPackage.PAYMENT_CONDITION__COLLECTOR:
        return collector != null;
      case ContractPackage.PAYMENT_CONDITION__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
      case ContractPackage.PAYMENT_CONDITION__LEVEL:
        return level != null;
      case ContractPackage.PAYMENT_CONDITION__FACTS:
        return facts != null;
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
    result.append(" (description: ");
    result.append(description);
    result.append(')');
    return result.toString();
  }

} //PaymentConditionImpl
