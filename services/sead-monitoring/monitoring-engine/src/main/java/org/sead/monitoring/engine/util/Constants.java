/*
 *
 * Copyright 2015 The Trustees of Indiana University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * @author charmadu@umail.iu.edu
 */

package org.sead.monitoring.engine.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {

	public static String mongoHost;
	public static int mongoPort;

	public static String pdtDbName;
	public static String monDbName;
    public static String dataoneDbName;
    public final static String INFINITE = "inf";

	static {
		try {
			loadConfigurations();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadConfigurations() throws IOException {
		InputStream inputStream = Constants.class
				.getResourceAsStream("default.properties");
		Properties props = new Properties();
		props.load(inputStream);
		mongoHost = props.getProperty("mongo.host", "localhost");
		mongoPort = Integer.parseInt(props.getProperty("mongo.port", "27017"));
		monDbName = props.getProperty("mon.db.name", "sead-mon");
        dataoneDbName = props.getProperty("dataone.db.name", "sead-dataone");
		pdtDbName = props.getProperty("pdt.db.name", "sead-pdt");
	}
}
