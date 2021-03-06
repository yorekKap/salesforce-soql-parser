/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tools.soql.query;

import org.mule.tools.soql.SOQLDataVisitor;

/**
 * Created by damianpelaez on 3/7/16.
 */
public interface SOQLData {

    String toSOQLText();

    <T> T accept(SOQLDataVisitor<? extends T> soqlDataVisitor);

}
