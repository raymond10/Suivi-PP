/**
 * 
 */
package fr.utt.sig.suivi.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author oracle
 * 
 */
@SuppressWarnings("all")
public class CSVExport {
	protected final Log logger = LogFactory.getLog(getClass());
	private StringBuffer sb = new StringBuffer();

	public CSVExport(List paramList) {
		if (paramList != null) {
			LinkedHashMap localLinkedHashMap = (LinkedHashMap) paramList.get(0);
			Set localSet = localLinkedHashMap.keySet();
			Iterator localIterator1 = localSet.iterator();
			Object localObject;
			while (localIterator1.hasNext()) {
				localObject = (String) localIterator1.next();
				this.sb.append("\"" + ((String) localObject).trim() + "\";");
			}
			this.sb.append("\r\n");
			for (int i = 1; i < paramList.size(); i++) {
				localObject = (LinkedHashMap) paramList.get(i);
				if (localObject != null) {
					Iterator localIterator2 = localSet.iterator();
					while (localIterator2.hasNext()) {
						String str1 = (String) localIterator2.next();
						String str2 = (String) ((LinkedHashMap) localObject)
								.get(str1);
						if (str2 == null)
							str2 = "";
						this.sb.append("\"" + str2.trim() + "\";");
					}
				}
				this.sb.append("\r\n");
			}
		}
	}

	public StringBuffer getSb() {
		return this.sb;
	}
}
