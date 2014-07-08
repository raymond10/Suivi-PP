/**
 * 
 */
package fr.utt.sig.suivi.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.beans.factory.InitializingBean;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

/**
 * @author oracle
 * 
 */
public class Config implements InitializingBean {
	protected final Log logger = LogFactory.getLog(getClass());
	public static final String DEFAULT_FILENAME = "config.properties";
	private final Properties properties = new Properties();
	private static String prefix = "config.";
	private static String oswinprefix = "windows.";
	private static String osmacprefix = "mac.";
	private static String oslinuxprefix = "linux.";
	private boolean isoswin;
	private boolean isosmac;
	private String propertiesfile;

	public void afterPropertiesSet() throws Exception {
		Properties os = System.getProperties();
		if (os.getProperty("os.name").indexOf("Windows") != -1)
			this.isoswin = true;
		else if (os.getProperty("os.name").indexOf("Mac OS X") != -1) {
			this.isosmac = true;
		}
		loadProperties();
	}

	private void loadProperties() throws IOException {
		String chemin = null;
		InputStream is = null;

		if (this.propertiesfile != null) {
			chemin = this.propertiesfile;
			is = new FileInputStream(chemin);
		} else {
			chemin = "config.properties";
			is = getClass().getClassLoader().getResourceAsStream(chemin);
		}
		chemin = "config.properties";
		is = getClass().getClassLoader().getResourceAsStream(chemin);

		this.properties.load(is);
	}

	public String getProperties(String key) {
		String osprefix = "";
		if (key.startsWith("dir_")) {
			if (this.isoswin)
				osprefix = oswinprefix;
			else if (this.isosmac)
				osprefix = osmacprefix;
			else
				osprefix = oslinuxprefix;

		}
		String props = prefix + osprefix + key;
		return this.properties.getProperty(props);
	}

	public void setPropertiesfile(String propertiesfile) {
		this.propertiesfile = propertiesfile;
	}
}
