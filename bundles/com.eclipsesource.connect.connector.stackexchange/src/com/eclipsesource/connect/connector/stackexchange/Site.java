/*******************************************************************************
 * Copyright (c) 2015 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Holger Staudacher - initial API and implementation, ongoing development
 ******************************************************************************/
package com.eclipsesource.connect.connector.stackexchange;


public enum Site {

  STACKOVERFLOW( "stackoverflow" ),
  SERVERFAULT( "serverfault" ),
  STACKAPPS( "stackapps" );

  private final String name;

  private Site( String name ) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
