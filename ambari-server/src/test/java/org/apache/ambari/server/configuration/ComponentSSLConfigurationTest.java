/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.configuration;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * ComponentSSLConfiguration tests.
 */
public class ComponentSSLConfigurationTest {

  public static ComponentSSLConfiguration getConfiguration(String path, String pass, String type, boolean gangliaSSL, boolean nagiosSSL) {
    Properties ambariProperties = new Properties();
    ambariProperties.setProperty(Configuration.SSL_TRUSTSTORE_PATH_KEY, path);
    ambariProperties.setProperty(Configuration.SSL_TRUSTSTORE_PASSWORD_KEY, pass);
    ambariProperties.setProperty(Configuration.SSL_TRUSTSTORE_TYPE_KEY, type);
    ambariProperties.setProperty(Configuration.GANGLIA_HTTPS_KEY, Boolean.toString(gangliaSSL));
    ambariProperties.setProperty(Configuration.NAGIOS_HTTPS_KEY, Boolean.toString(nagiosSSL));

    Configuration configuration =  new TestConfiguration(ambariProperties);

    ComponentSSLConfiguration sslConfiguration = new ComponentSSLConfiguration();

    sslConfiguration.init(configuration);

    return sslConfiguration;
  }

  @Test
  public void testGetTruststorePath() throws Exception {
    ComponentSSLConfiguration sslConfiguration = getConfiguration("tspath", "tspass", "tstype", true, false);
    Assert.assertEquals("tspath", sslConfiguration.getTruststorePath());
  }

  @Test
  public void testGetTruststorePassword() throws Exception {
    ComponentSSLConfiguration sslConfiguration = getConfiguration("tspath", "tspass", "tstype", true, false);
    Assert.assertEquals("tspass", sslConfiguration.getTruststorePassword());
  }

  @Test
  public void testGetTruststoreType() throws Exception {
    ComponentSSLConfiguration sslConfiguration = getConfiguration("tspath", "tspass", "tstype", true, false);
    Assert.assertEquals("tstype", sslConfiguration.getTruststoreType());
  }

  @Test
  public void testIsGangliaSSL() throws Exception {
    ComponentSSLConfiguration sslConfiguration = getConfiguration("tspath", "tspass", "tstype", true, false);
    Assert.assertTrue(sslConfiguration.isGangliaSSL());
  }

  @Test
  public void testIsNagiosSSL() throws Exception {
    ComponentSSLConfiguration sslConfiguration = getConfiguration("tspath", "tspass", "tstype", true, false);
    Assert.assertFalse(sslConfiguration.isNagiosSSL());
  }

  private static class TestConfiguration extends Configuration {

    private TestConfiguration(Properties properties) {
      super(properties);
    }

    @Override
    protected void loadSSLParams() {
    }
  }
}
