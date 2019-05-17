/**********************************************
 * [Copyright ©]
 * @File: Ini.java 
 * @author Frank-Wang
 * @Date: 2019.05.16
 * @Version: 1.0
 * @Since: JDK 1.8.0_92
 **********************************************/
package com.anselm.tools.record;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * &emsp;&emsp;&emsp;&emsp;<em><b>Class Ini</em></b><BR/>
 * <BR/>
 * 
 * This class provides all the action about Ini.<BR/>
 * For constructor detailed visits, please refer to {@linkplain #Ini()}. For get
 * file value detailed visits, please refer to
 * {@linkplain #getValue(String, String)}.
 * 
 * @author Frank-Wang
 */
public class Ini {
	/** Set annotation format */
	final String SKIP_PREFIX = "##";
	/** Set annotation format */
	final String KeyDelimiter = "##";

	/** Store all the information in the config */
	Map<String, String> Parameter = new HashMap<String, String>();

	/** Config file path */
	public String IniFilePath = "";

	/**
	 * Encoding format :<BR/>
	 * <BR/>
	 * &emsp;Big5：這是繁體中文 de facto 標準。<BR/>
	 * &emsp;CNS11643：台灣的官方標準繁體中文編碼。 <BR/>
	 * &emsp;Cp937：繁體中文加上 6204 個使用者自定的字元。 <BR/>
	 * &emsp;Cp948：繁體中文版 IBM OS/2 用的編碼方式。 <BR/>
	 * &emsp;Cp964：繁體中文版 IBM AIX 用的編碼方式。 <BR/>
	 * &emsp;EUC_TW：台灣的加強版 Unicode。 <BR/>
	 * &emsp;ISO2022CN：編碼中文的一套標準。<BR/>
	 * &emsp;ISO2022CN_CNS：編碼中文的一套標準，繁體版，襲自 CNS11643。<BR/>
	 * &emsp;MS950 or Cp950：ASCII + Big5，用於台灣和香港的繁體中文 MS Windows作業系統。<BR/>
	 * &emsp;Unicode：有次序記號的 Unicode。<BR/>
	 * &emsp;Unicode：有次序記號的 Unicode。<BR/>
	 * &emsp;UnicodeLittle：使用 little-endian（由小到大）的次序為 Unicode 編碼。<BR/>
	 * &emsp;UTF8：使用 UTF-8 為 Unicode 編碼。<BR/>
	 */
	private final String encodingFormat = "MS950";

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>Ini</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;Ini()</font><BR/>
	 * <BR/>
	 * 
	 * Constructor of class.<BR/>
	 * This constructor will call {@link #init(File)} to read config. Default file
	 * path will be <b>[ D:\\Anselm_Program_Data\\Config.ini ]</b> .<BR/>
	 * 
	 * @see #Ini(String)
	 * 
	 * @author Frank-Wang
	 */
	public Ini() {
		init("D:\\Anselm_Program_Data\\Config.ini");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>Ini</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;Ini({@linkplain String})</font><BR/>
	 * <BR/>
	 * 
	 * Constructor of class.<BR/>
	 * This constructor will call {@link #init(File)} to read config in input
	 * path.<BR/>
	 * 
	 * @see #Ini()
	 * 
	 * @author Frank-Wang
	 */
	public Ini(String SRC_INI) {
		init(SRC_INI);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>init</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;init({@linkplain String})</font><BR/>
	 * <BR/>
	 * 
	 * This function will change input {@linkplain String} to {@linkplain File} and
	 * call {@link #init(File)} to read config file.
	 * 
	 * @param SRC_INI Input file path.
	 * 
	 * @author Frank-Wang
	 */
	private void init(String SRC_INI) {
		File file = new File(SRC_INI);
		try {
			System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
		}
		init(file);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>init</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;init({@linkplain File})</font><BR/>
	 * <BR/>
	 * 
	 * This function will read config file data and input
	 * {@linkplain #Parameter}.<BR/>
	 * Please refer to {@linkplain #encodingFormat} for the encoding format.
	 * 
	 * @param src_ini File path.
	 * 
	 * @author Frank-Wang
	 */
	private void init(File src_ini) {
		if (src_ini == null || !src_ini.exists() || !src_ini.isFile()) {
			System.out.println("## The specified ini path [" + src_ini + "] ==> Does not exist!");
			return;
		}

		this.IniFilePath = src_ini.getPath();

		BufferedReader in = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(src_ini), encodingFormat);

			in = new BufferedReader(isr);

			String category = "$";
			while (in.ready()) {
				String str = in.readLine();

				if (str.trim().startsWith(SKIP_PREFIX))
					continue;
				str = str.split(SKIP_PREFIX)[0].trim();

				if (str.length() == 0) {
					continue;
				} else if (str.startsWith("[") && str.endsWith("]")) {
					category = str.substring(1, str.length() - 1);
				} else {
					if (str.indexOf("=") > 0) {
						String[] content = new String[2];
						content[0] = str.substring(0, str.indexOf("="));
						content[1] = str.substring(str.indexOf("=") + 1);
						Parameter.put(category + KeyDelimiter + content[0], content[1]);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Catch IOException:" + e.getMessage());
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getValue</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getValue({@linkplain String},
	 * {@linkplain #String})</font><BR/>
	 * <BR/>
	 * 
	 * This function will get value in config file.<BR/>
	 * <BR/>
	 * In config will like :<BR/>
	 * ╔════════════════╗<BR/>
	 * &emsp;[category]<BR/>
	 * &emsp;parameter=return data<BR/>
	 * ╚════════════════╝<BR/>
	 * 
	 * @param category  Input config data in middle brackets.
	 * @param parameter Input config data under brackets.
	 * @return
	 * 
	 * @author Frank-Wang
	 */
	public String getValue(String category, String parameter) {
		return (String) Parameter.get(category + KeyDelimiter + parameter);
	}

}
