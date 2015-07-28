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
package com.eclipsesource.connect.persistence;

import java.util.Dictionary;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import com.eclipsesource.connect.persistence.util.MongoDBClientFactory;
import com.mongodb.DB;


public class MongoDBFactory implements ManagedService {

  static final String PROPERTY_HOST = "db.host";
  static final String PROPERTY_PORT = "db.port";
  static final String PROPERTY_DB_NAME = "db.name";

  private final MongoDBClientFactory clientFactory;
  private DB db;

  public MongoDBFactory() {
    this( new MongoDBClientFactory() );
  }

  MongoDBFactory( MongoDBClientFactory clientFactory ) {
    this.clientFactory = clientFactory;
  }

  @Override
  public void updated( Dictionary<String, ?> properties ) throws ConfigurationException {
    validateProperties( properties );
    String host = ( String )properties.get( PROPERTY_HOST );
    int port = Integer.parseInt( ( String )properties.get( PROPERTY_PORT ) );
    String dbName = ( String )properties.get( PROPERTY_DB_NAME );
    db = clientFactory.createDB( host, dbName, port );
  }

  private void validateProperties( Dictionary<String, ?> properties ) throws ConfigurationException {
    if( properties.get( PROPERTY_HOST ) == null ) {
      throw new ConfigurationException( PROPERTY_HOST, "MongoDB host must be set" );
    }
    if( properties.get( PROPERTY_DB_NAME ) == null ) {
      throw new ConfigurationException( PROPERTY_DB_NAME, "MongoDB name must be set" );
    }
    if( properties.get( PROPERTY_PORT ) == null ) {
      throw new ConfigurationException( PROPERTY_PORT, "MongoDB port must be set" );
    }
  }

  public DB getDB() {
    return db;
  }
}