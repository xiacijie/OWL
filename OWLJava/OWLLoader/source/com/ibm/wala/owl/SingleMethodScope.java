/*
 * Copyright (c) 2002 - 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 */
/*
 * Created on Sep 27, 2005
 */
package com.ibm.wala.owl;

import java.util.Collection;
import java.util.Collections;

import com.ibm.wala.classLoader.Language;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.types.ClassLoaderReference;

public class SingleMethodScope extends AnalysisScope {

  public SingleMethodScope() {
    this(Collections.singleton(Language.JAVA));
  }

  protected void initCoreForSingleMethod() {
    initCoreForJava();
    setLoaderImpl(ClassLoaderReference.Application, "com.ibm.wala.owl.SingleMethodLoader");
  }

  protected SingleMethodScope(Collection<? extends Language> languages) {
    super(languages);
    initCoreForSingleMethod();
    initSynthetic(ClassLoaderReference.Application);
  }
}
