/**********************************************
 * [Copyright ©]
 * @File: Log.java 
 * @author Frank-Wang
 * @Date: 2019.05.16
 * @Version: 1.0
 * @Since: JDK 1.8.0_92
 **********************************************/
package com.anselm.tools.record;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * &emsp;&emsp;&emsp;&emsp;<em><b>Class Log</em></b><BR/>
 * <BR/>
 * 
 * This class provides all the action about logs.<BR/>
 * <BR/>
 * For constructor detailed visits, please refer to {@link #Log()}.<BR/>
 * For output print detailed visits, please refer to {@link #log()}.<BR/>
 * Doing action thou log file, please set the log file first.For detailed
 * visits, please refer to {@link #setLogFile(String)}.
 * 
 * @author Frank-Wang
 */
public class Log {
	/** Output related variables of log file */
	private FileOutputStream fos = null;
	/** Output related variables of log file */
	private OutputStreamWriter osw = null;

	/** Used to confirm whether to create a file */
	private boolean logtofile = false;

	/** Path of the log file */
	private String logFilePath = "";

	/** Main topic name */
	private String Topic = "";
	/** Subtopic name */
	private String SubTopic = "()";
	/** Max lengh of name */
	private Integer MaxLenghOfName = 10;

	/** Ways to output format used to rewrite dates */
	private static final SimpleDateFormat SYS_FILE_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd", Locale.TAIWAN);
	/** Ways to output format used to rewrite dates */
	private static final SimpleDateFormat SYS_DATA_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss:SSS", Locale.TAIWAN);

	/** Start timer */
	private Date logStartTime = new Date();
	/** End timger */
	private Date logCloseTime = null;

	/**
	 * Creator mode switch <BR/>
	 * <BR/>
	 * {@code True} -> Builder pattern.<BR/>
	 * {@code False} -> User pattern.
	 */
	private Boolean BuilderPattern = false;

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>Log</em></b><BR/>
	 * <BR/>
	 * 
	 * Constructor of class.<BR/>
	 * This constructor will do nothing.<BR/>
	 * 
	 * @see #Log(String)
	 * @see #Log(String, Boolean)
	 * @see #Log(String, String)
	 * @see #Log(String, String, Boolean)
	 * 
	 * @author Frank-Wang
	 */
	public Log() {
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>Log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;Log({@linkplain String} topic)</font><BR/>
	 * <BR/>
	 * 
	 * Constructor of class.<BR/>
	 * This constructor will call {@link #init(String, String, Boolean)} to set
	 * topic.Empty of input to {@link #init(String, String, Boolean)} will be
	 * default input.<BR/>
	 * 
	 * @param topic Input topic in {@link #Topic}.
	 * 
	 * @see #Log()
	 * @see #Log(String, Boolean)
	 * @see #Log(String, String)
	 * @see #Log(String, String, Boolean)
	 * 
	 * @author Frank-Wang
	 */
	public Log(String topic) {
		init(topic, "", false);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>Log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;Log({@linkplain String} topic, ,
	 * {@linkplain Boolean} _builderPattern)</font><BR/>
	 * <BR/>
	 * 
	 * 
	 * Constructor of class.<BR/>
	 * This constructor will call {@link #init(String, String, Boolean)} to set
	 * topic and builder pattern.Empty of input to
	 * {@link #init(String, String, Boolean)} will be default input.<BR/>
	 * 
	 * @param topic           Input topic in {@link #Topic}.
	 * @param _builderPattern Select output mode in {@link #BuilderPattern}.
	 * 
	 * @see #Log()
	 * @see #Log(String)
	 * @see #Log(String, String)
	 * @see #Log(String, String, Boolean)
	 * 
	 * @author Frank-Wang
	 */
	public Log(String topic, Boolean _builderPattern) {
		init(topic, "", _builderPattern);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>Log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;Log({@linkplain String} topic, ,
	 * {@linkplain String} subtopic)</font><BR/>
	 * <BR/>
	 * 
	 * Constructor of class.<BR/>
	 * This constructor will call {@link #init(String, String, Boolean)} to set
	 * topic and subTopic.Empty of input to {@link #init(String, String, Boolean)}
	 * will be default input.<BR/>
	 * 
	 * @param topic    Input topic in {@link #Topic}.
	 * @param subtopic Input subtopic in {@link #SubTopic}.
	 * 
	 * @see #Log()
	 * @see #Log(String)
	 * @see #Log(String, Boolean)
	 * @see #Log(String, String, Boolean)
	 * 
	 * @author Frank-Wang
	 */
	public Log(String topic, String subtopic) {
		init(topic, subtopic, false);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>Log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;Log({@linkplain String} topic, ,
	 * {@linkplain String} subtopic, {@linkplain Boolean}
	 * _builderPattern)</font><BR/>
	 * <BR/>
	 * 
	 * Constructor of class.<BR/>
	 * This constructor will call {@link #init(String, String, Boolean)} to set
	 * topic, subTopic and builder pattern.Empty of input to
	 * {@link #init(String, String, Boolean)} will be default input.<BR/>
	 * 
	 * @param topic           Input topic in {@link #Topic}.
	 * @param subtopic        Input subtopic in {@link #SubTopic}.
	 * @param _builderPattern Select output mode in {@link #BuilderPattern}.
	 * 
	 * @see #Log()
	 * @see #Log(String)
	 * @see #Log(String, Boolean)
	 * @see #Log(String, String)
	 * 
	 * @author Frank-Wang
	 */
	public Log(String topic, String subtopic, Boolean _builderPattern) {
		init(topic, subtopic, _builderPattern);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>init</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;init({@linkplain String} topic, ,
	 * {@linkplain String} subtopic, {@linkplain Boolean}
	 * _builderPattern)</font><BR/>
	 * <BR/>
	 * 
	 * This function will set topic and subtopic. Builder Pattern will control
	 * output mode,if only want to set builder pattern, please call
	 * {@link #setBuilderPattern(Boolean)}.
	 * 
	 * @param _topic          Input topic in {@link #Topic}.
	 * @param _subtopic       Input subtopic in {@link #SubTopic}.
	 * @param _builderPattern Select output mode in {@link #BuilderPattern}.
	 * 
	 * @author Frank-Wang
	 */
	private void init(String _topic, String _subtopic, Boolean _builderPattern) {

		this.Topic = _topic;
		this.SubTopic = "(" + _subtopic + ")";
		this.BuilderPattern = _builderPattern;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>setTopic</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;setTopic({@linkplain String} _topic)</font><BR/>
	 * <BR/>
	 * 
	 * This function set topic to this class, if going to print out on log, please
	 * call {@link #logHeading()}.
	 * 
	 * @param _topic Input topic in {@link #Topic}.
	 * 
	 * @author Frank-Wang
	 */
	public void setTopic(String _topic) {
		this.Topic = _topic;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>setSubTopic</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;setSubTopic({@linkplain String}
	 * _subTopic)</font><BR/>
	 * <BR/>
	 * 
	 * This function set subtopic to this class, if going to print out on log,
	 * please call {@link #logHeading()}.
	 * 
	 * @param _subTopic Input subtopic in {@link #SubTopic}.
	 * 
	 * @author Frank-Wang
	 */
	public void setSubTopic(String _subTopic) {
		this.SubTopic = _subTopic;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>setBuilderPattern</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;setBuilderPattern({@linkplain Boolean}
	 * _builderPattern)</font><BR/>
	 * <BR/>
	 * 
	 * This function set builder pattern.Builder pattern will controls ouput mode.
	 * 
	 * @param _builderPattern Select output mode in {@link #BuilderPattern}.
	 * 
	 * @author Frank-Wang
	 */
	public void setBuilderPattern(Boolean _builderPattern) {
		this.BuilderPattern = _builderPattern;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>setMaxLengthOfName</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;setMaxLengthOfName({@linkplain Integer}
	 * _maxLenghOfName)</font><BR/>
	 * <BR/>
	 * 
	 * This function set max lengh of name in this class.
	 * 
	 * @param _maxLenghOfName Input to {@link #MaxLenghOfName}.
	 * 
	 * @author Frank-Wang
	 */
	public void setMaxLengthOfName(Integer _maxLenghOfName) {
		this.MaxLenghOfName = _maxLenghOfName;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getTopic</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getTopic()</font><BR/>
	 * <BR/>
	 * 
	 * This function return topic in this class.
	 * 
	 * @return String of {@link #Topic}.
	 * 
	 * @author Frank-Wang
	 */
	public String getTopic() {
		return this.Topic;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getSubTopic</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getSubTopic()</font><BR/>
	 * <BR/>
	 * 
	 * This function return subtopic in this class.
	 * 
	 * @return String of {@link #SubTopic}.
	 * 
	 * @author Frank-Wang
	 */
	public String getSubTopic() {
		return this.SubTopic;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getBuilderPattern</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getBuilderPattern()</font><BR/>
	 * <BR/>
	 * 
	 * This function return builder pattern in this class.
	 * 
	 * @return Boolean of {@link #BuilderPattern}.
	 * 
	 * @author Frank-Wang
	 */
	public Boolean getBuilderPattern() {
		return this.BuilderPattern;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getMaxLengthOfName</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getMaxLengthOfName()</font><BR/>
	 * <BR/>
	 * 
	 * This function return max lengh of name in this class.
	 * 
	 * @return Integer of {@link #MaxLenghOfName}.
	 * 
	 * @author Frank-Wang
	 */
	public Integer getMaxLengthOfName() {
		return this.MaxLenghOfName;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getMethodName</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getMethodName()</font><BR/>
	 * <BR/>
	 * 
	 * This function will get who calls this function.
	 * 
	 * @return Method name.
	 */
	public String getMethodName() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

		if (stackTraceElements.length > 2) {
			for (int i = 2; i < stackTraceElements.length; i++) {
				if (stackTraceElements[i].getMethodName().startsWith("log"))
					continue;
				return stackTraceElements[i].getMethodName();
			}
		}
		return "";
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>setLogFile</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;setLogFile({@linkplain String},
	 * {@link Ini})</font><BR/>
	 * <BR/>
	 * 
	 * This function will create log file in the file path that input.Input
	 * something like<BR/>
	 * <B><em>path.log </em></B> or <B><em>path</em></B>.<BR/>
	 * File name will end with path[YYYY.MM.DD].log.<BR/>
	 * Input ini to set builder pattern at the same time.
	 * 
	 * @param filePath Some string to input in {@link #logFilePath}.
	 * @param ini      From {@link Ini}.
	 * 
	 * @throws IOException
	 * 
	 * @author Frank-Wang
	 */
	public void setLogFile(String filePath, Ini ini) throws IOException {
		String[] tmpFilePath = filePath.split(".log");
		filePath = tmpFilePath[0] + " [" + getCurrentTimeStringWithYYYY_MM_DD() + "].log";

		File File = new File(filePath);
		if (!File.exists())
			File.createNewFile();
		fos = new FileOutputStream(filePath, true);
		osw = new OutputStreamWriter(fos, "UTF-8");
		this.logtofile = true;
		this.logFilePath = filePath;
		if (ini != null) {
			getPatternInConfig(ini);
		} else {
			logger("Ini is null, please initial Ini.java or check input Ini");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getPatternInConfig</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getPatternInConfig({@link Ini})</font><BR/>
	 * <BR/>
	 * 
	 * This function will get value by config and set builder pattern.
	 * 
	 * @param ini From {@link Ini}.
	 * 
	 * @author Frank-Wang
	 */
	private void getPatternInConfig(Ini ini) {
		String strBuilderPattern = ini.getValue("Program Use", "BuilderPattern");

		if (strBuilderPattern == null) {
			log("No search for [Program Use, BuilderPattern], please check config.");
		}

		if (!strBuilderPattern.isEmpty()) {
			if (strBuilderPattern.toLowerCase().equals("true") || strBuilderPattern.toLowerCase().equals("false")) {
				Boolean flagBuilderPattern = Boolean.parseBoolean(strBuilderPattern);
				setBuilderPattern(flagBuilderPattern);
				logger("Builder Pattern :" + getBuilderPattern());
			} else {
				logger("Config [Program Use, BuilderPattern] data was not [Boolean]");
			}
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getLogFile</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getLogFile()</font><BR/>
	 * <BR/>
	 * 
	 * This function will return log file location.
	 * 
	 * @return File of {@link #logFilePath}.
	 * 
	 * @author Frank-Wang
	 */
	public File getLogFile() {
		return new File(this.logFilePath);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getStartTime</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getStartTime()</font><BR/>
	 * <BR/>
	 * 
	 * This function will return {@linkplain #logStartTime}.<BR/>
	 * Default start time will be the time you initialization this class.
	 * 
	 * @return Start time in date type
	 * 
	 * @author Frank-Wang
	 */
	public Date getStartTime() {
		return this.logStartTime;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getCloseTime</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getCloseTime()</font><BR/>
	 * <BR/>
	 * 
	 * This function will return {@linkplain #logCloseTime}.<BR/>
	 * If return null, please use {@link #setCloseTime()} to set close time.
	 * 
	 * @return Close time in date type.
	 * 
	 * @author Frank-Wang
	 */
	public Date getCloseTime() {
		return this.logCloseTime;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log()</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, Object)} to print nothing in log.<BR/>
	 * With builder pattern please use {@link #logger()}.
	 * 
	 * @see #log(Object)
	 * @see #log(int, Object)
	 * 
	 * @author Frank-Wang
	 */
	public void log() {
		log("");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger()</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, Object)} to print nothing in log.<BR/>
	 * With user pattern please use {@link #log()}.
	 * 
	 * @see #logger(Object)
	 * @see #logger(int, Object)
	 * 
	 * @author Frank-Wang
	 */
	public void logger() {
		if (BuilderPattern) {
			log("");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, Object)} to print input object in log.<BR/>
	 * With builder pattern please use {@link #logger(Object)}.
	 * 
	 * @param log Data to output.
	 * 
	 * @see #log()
	 * @see #log(int, Object)
	 * 
	 * @author Frank-Wang
	 */
	public void log(Object log) {
		log(0, log);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, Object)} to print input object in log.<BR/>
	 * With user pattern please use {@link #log(Object)}.
	 * 
	 * @param log Data to output.
	 * 
	 * @see #logger()
	 * @see #logger(int, Object)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(Object log) {
		if (BuilderPattern) {
			log(0, log);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Int},
	 * {@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * Function print input object in log and do input time of tab.<BR/>
	 * With builder pattern please use {@link #logger(int,Object)}.
	 * 
	 * @param xd  Tab key times.
	 * @param log Data to output.
	 * 
	 * @see #log()
	 * @see #log(Object)
	 * 
	 * @author Frank-Wang
	 */
	public void log(int xd, Object log) {
		String prefix = "";// tab 次數

		for (int d = 0; d < xd + 0; d++)
			prefix = "\t|" + prefix;

		String[] tmp = (log + "").split("\r\n"); // 輸入物件是否有換行符號

		for (int i = 0; i < tmp.length; i++) {
			if (i == 0 && ((log == null ? "" : log) + "").startsWith("\r\n"))
				continue;
			log = ("[#" + (getCurrentTimeStringWithHH_MM_SS_SSS()) + "] ["
					+ cramLeft(getMethodName(), " ", MaxLenghOfName) + "] ") + prefix + tmp[i];

			System.out.println(log);
			if (logtofile) {
				try {
					osw.append("\r\n" + log + "");
					osw.flush();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Int},
	 * {@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * Function print input object in log and do input time of tab.<BR/>
	 * With user pattern please use {@link #log(int,Object)}.
	 * 
	 * @param xd  Tab key times.
	 * @param log Data to output.
	 * 
	 * @see #logger()
	 * @see #logger(Object)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(int xd, Object log) {
		if (BuilderPattern) {
			String prefix = "";// tab 次數

			for (int d = 0; d < xd + 0; d++)
				prefix = "\t|" + prefix;

			String[] tmp = (log + "").split("\r\n"); // 輸入物件是否有換行符號

			for (int i = 0; i < tmp.length; i++) {
				if (i == 0 && ((log == null ? "" : log) + "").startsWith("\r\n"))
					continue;
				log = ("[#" + (getCurrentTimeStringWithHH_MM_SS_SSS()) + "] ["
						+ cramLeft(getMethodName(), " ", MaxLenghOfName) + "] ") + prefix + tmp[i];

				System.out.println(log);
				if (logtofile) {
					try {
						osw.append("\r\n" + log + "");
						osw.flush();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Map)} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(Map)}.
	 * 
	 * @param map Data to output.
	 * 
	 * @see #log(int, Map)
	 * @see #log(String, Map)
	 * @see #log(int, String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void log(Map<?, ?> map) {
		log(0, "", map);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Map)} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(Map)}.
	 * 
	 * @param map Data to output.
	 * 
	 * @see #logger(int, Map)
	 * @see #logger(String, Map)
	 * @see #logger(int, String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(Map<?, ?> map) {
		if (BuilderPattern) {
			log(0, "", map);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Int},
	 * {@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Map)} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(int, Map)}.
	 * 
	 * @param xd  Tab key times.
	 * @param map Data to output.
	 * 
	 * @see #log(Map)
	 * @see #log(String, Map)
	 * @see #log(int, String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void log(int xd, Map<?, ?> map) {
		log(xd, "", map);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Int},
	 * {@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Map)} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(int, Map)}.
	 * 
	 * @param xd  Tab key times.
	 * @param map Data to output.
	 * 
	 * @see #logger(Map)
	 * @see #logger(String, Map)
	 * @see #logger(int, String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(int xd, Map<?, ?> map) {
		if (BuilderPattern) {
			log(xd, "", map);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain String},
	 * {@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Map)} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(String, Map)}.
	 * 
	 * @param name Map name.
	 * @param map  Data to output.
	 * 
	 * @see #log(Map)
	 * @see #log(int, Map)
	 * @see #log(int, String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void log(String name, Map<?, ?> map) {
		log(0, name, map);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain String},
	 * {@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Map)} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(String, Map)}.
	 * 
	 * @param name Map name.
	 * @param map  Data to output.
	 * 
	 * @see #logger(Map)
	 * @see #logger(int, Map)
	 * @see #logger(int, String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(String name, Map<?, ?> map) {
		if (BuilderPattern) {
			log(0, name, map);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Int}, {@linkplain String},
	 * {@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function will print input object in log.<BR/>
	 * With builder pattern please use {@link #logger(int, String, Map)}.
	 * 
	 * @param xd   Tab key times.
	 * @param name Map name.
	 * @param map  Data to output.
	 * 
	 * @see #log(Map)
	 * @see #log(int, Map)
	 * @see #log(String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void log(int xd, String name, Map<?, ?> map) {
		if (map == null)
			map = new HashMap<String, Object>();
		Iterator<?> itr = map.entrySet().iterator();
		log(xd, "[" + name + "]");

		if (itr.hasNext() == false)
			log(xd, "  map is empty");

		int len = 0;
		while (itr.hasNext()) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itr.next();
			if ((entry.getKey() + "").length() > len)
				len = (entry.getKey() + "").length();
		}

		itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itr.next();
			log(xd, "  [" + cramLeft(entry.getKey() + "", " ", len) + "] == " + getArrayToString(entry.getValue()));
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Int}, {@linkplain String},
	 * {@linkplain Map})</font><BR/>
	 * <BR/>
	 * 
	 * Function will print input object in log.<BR/>
	 * With user pattern please use {@link #log(int, String, Map)}.
	 * 
	 * @param xd   Tab key times.
	 * @param name Map name.
	 * @param map  Data to output.
	 * 
	 * @see #logger(Map)
	 * @see #logger(int, Map)
	 * @see #logger(String, Map)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(int xd, String name, Map<?, ?> map) {
		if (BuilderPattern) {
			if (map == null)
				map = new HashMap<String, Object>();
			Iterator<?> itr = map.entrySet().iterator();
			log(xd, "[" + name + "]");

			if (itr.hasNext() == false)
				log(xd, "  map is empty");

			int len = 0;
			while (itr.hasNext()) {
				Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itr.next();
				if ((entry.getKey() + "").length() > len)
					len = (entry.getKey() + "").length();
			}

			itr = map.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itr.next();
				log(xd, "  [" + cramLeft(entry.getKey() + "", " ", len) + "] == " + getArrayToString(entry.getValue()));
			}
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(List)}.
	 * 
	 * @param list Data to output.
	 * 
	 * @see #log(int, List)
	 * @see #log(String, List)
	 * @see #log(int, String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void log(List<?> list) {
		log(0, "", list);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(List)}.
	 * 
	 * @param list Data to output.
	 * 
	 * @see #logger(int, List)
	 * @see #logger(String, List)
	 * @see #logger(int, String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(List<?> list) {
		if (BuilderPattern) {
			log(0, "", list);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Int},
	 * {@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(int, List)}.
	 * 
	 * @param xd   Tab key times.
	 * @param list Data to output.
	 * 
	 * @see #log(List)
	 * @see #log(String, List)
	 * @see #log(int, String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void log(int xd, List<?> list) {
		log(xd, "", list);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Int},
	 * {@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(int, List)}.
	 * 
	 * @param xd   Tab key times.
	 * @param list Data to output.
	 * 
	 * @see #logger(List)
	 * @see #logger(String, List)
	 * @see #logger(int, String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(int xd, List<?> list) {
		if (BuilderPattern) {
			log(xd, "", list);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain String},
	 * {@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(String, List)}.
	 * 
	 * @param name List name.
	 * @param list Data to output.
	 * 
	 * @see #log(List)
	 * @see #log(int, List)
	 * @see #log(int, String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void log(String name, List<?> list) {
		log(0, name, list);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain String},
	 * {@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(String, List)}.
	 * 
	 * @param name List name.
	 * @param list Data to output.
	 * 
	 * @see #logger(List)
	 * @see #logger(int, List)
	 * @see #logger(int, String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(String name, List<?> list) {
		if (BuilderPattern) {
			log(0, name, list);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Int}, {@linkplain String},
	 * {@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(int, String, List)}.
	 * 
	 * @param xd   Tab key times.
	 * @param name List name.
	 * @param list Data to output.
	 * 
	 * @see #log(List)
	 * @see #log(int, List)
	 * @see #log(String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void log(int xd, String name, List<?> list) {
		log(xd, name, list.toArray());
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Int}, {@linkplain String},
	 * {@linkplain List})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(int, String, List)}.
	 * 
	 * @param xd   Tab key times.
	 * @param name List name.
	 * @param list Data to output.
	 * 
	 * @see #logger(List)
	 * @see #logger(int, List)
	 * @see #logger(String, List)
	 * 
	 * @author Frank-Wang
	 */
	public void logger(int xd, String name, List<?> list) {
		if (BuilderPattern) {
			log(xd, name, list.toArray());
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(Object[])}.
	 * 
	 * @param array Data to output.
	 * 
	 * @see #log(int, Object[])
	 * @see #log(String, Object[])
	 * @see #log(int, String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void log(Object[] array) {
		log(0, "", array);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(Object[])}.
	 * 
	 * @param array Data to output.
	 * 
	 * @see #logger(int, Object[])
	 * @see #logger(String, Object[])
	 * @see #logger(int, String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void logger(Object[] array) {
		if (BuilderPattern) {
			log(0, "", array);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Int},
	 * {@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(int, Object[])}.
	 * 
	 * @param xd    Tab key times.
	 * @param array Data to output.
	 * 
	 * @see #log(Object[])
	 * @see #log(String, Object[])
	 * @see #log(int, String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void log(int xd, Object[] array) {
		log(xd, "", array);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Int},
	 * {@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(int, Object[])}.
	 * 
	 * @param xd    Tab key times.
	 * @param array Data to output.
	 * 
	 * @see #logger(Object[])
	 * @see #logger(String, Object[])
	 * @see #logger(int, String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void logger(int xd, Object[] array) {
		if (BuilderPattern) {
			log(xd, "", array);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain String},
	 * {@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #log(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With builder pattern please use {@link #logger(String, Object[])}.
	 * 
	 * @param name  Array name.
	 * @param array Data to output.
	 * 
	 * @see #log(Object[])
	 * @see #log(int, Object[])
	 * @see #log(int, String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void log(String name, Object[] array) {
		log(0, name, array);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain String},
	 * {@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function call {@link #logger(int, String, Object[])} to print input object in
	 * log.<BR/>
	 * With user pattern please use {@link #log(String, Object[])}.
	 * 
	 * @param name  Array name.
	 * @param array Data to output.
	 * 
	 * @see #logger(Object[])
	 * @see #logger(int, Object[])
	 * @see #logger(int, String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void logger(String name, Object[] array) {
		if (BuilderPattern) {
			log(0, name, array);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>log</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;log({@linkplain Int}, {@linkplain String},
	 * {@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function will print input object in log.<BR/>
	 * With builder pattern please use {@link #logger(int, String, Object[])}.
	 * 
	 * @param xd    Tab key times.
	 * @param name  Array name.
	 * @param array Data to output.
	 * 
	 * @see #log(Object[])
	 * @see #log(int, Object[])
	 * @see #log(String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void log(int xd, String name, Object[] array) {
		if (array == null)
			array = new Object[] {};

		log(xd, "[" + name + "]");

		if (array.length > 0)
			for (int i = 0; i < array.length; i++)
				log(xd, "  Array[" + i + "] = " + array[i]);
		else
			log(xd, "  Array is empty");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logger</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logger({@linkplain Int}, {@linkplain String},
	 * {@linkplain Array[]})</font><BR/>
	 * <BR/>
	 * 
	 * Function will print input object in log.<BR/>
	 * With user pattern please use {@link #log(int, String, Object[])}.
	 * 
	 * @param xd    Tab key times.
	 * @param name  Array name.
	 * @param array Data to output.
	 * 
	 * @see #logger(Object[])
	 * @see #logger(int, Object[])
	 * @see #logger(String, Object[])
	 * 
	 * @author Frank-Wang
	 */
	public void logger(int xd, String name, Object[] array) {
		if (BuilderPattern) {
			if (array == null)
				array = new Object[] {};

			log(xd, "[" + name + "]");

			if (array.length > 0)
				for (int i = 0; i < array.length; i++)
					log(xd, "  Array[" + i + "] = " + array[i]);
			else
				log(xd, "  Array is empty");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logHeading</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logHeading()</font><BR/>
	 * <BR/>
	 * 
	 * This function will print topic and subtopic in log file with strengthen
	 * text.It will be like [ topic (subtopic) ].
	 * 
	 * @author Frank-Wang
	 */
	public void logHeading() {
		String xxx = "";

		if (SubTopic.equals("()")) {
			for (int i = 0; i < (Topic.length() % 2 == 0 ? Topic.length() / 2 : Topic.length() / 2 + 1); i++)
				xxx += "═";

			log("╔" + xxx + "╗");

			if (Topic.length() % 2 == 0)
				log("║" + Topic + "║");
			else
				log("║" + Topic + " ║");

			log("╚" + xxx + "╝");
		} else {
			for (int i = 0; i < (Topic.length() + SubTopic.length()) / 2 + 2; i++)
				xxx += "═";
			log("╔" + xxx + "╗");
			log("║ " + Topic + " " + SubTopic + " ║");
			log("╚" + xxx + "╝");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logTextBox</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logTextBox({@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #logTextBox(int, Object)} to print input object in
	 * log with strengthen text.<BR/>
	 * With builder pattern please use {@link #loggerTextBox(Object)}.
	 * 
	 * @param log Data to output.
	 * 
	 * @see #logTextBox(int, Object)
	 * 
	 * @author Frank-Wang
	 */
	public void logTextBox(Object log) {
		logTextBox(0, log);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerTextBox</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerTextBox({@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #loggerTextBox(int, Object)} to print input object
	 * in log with strengthen text.<BR/>
	 * With user pattern please use {@link #logTextBox(Object)}.
	 * 
	 * @param log Data to output.
	 * 
	 * @see #loggerTextBox(int, Object)
	 * 
	 * @author Frank-Wang
	 */
	public void loggerTextBox(Object log) {
		if (BuilderPattern) {
			logTextBox(0, log);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logTextBox</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logTextBox({@linkplain Int},
	 * {@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * This function print input object in log with strengthen text.<BR/>
	 * With builder pattern please use {@link #loggerTextBox(int, Object)}.
	 * 
	 * @param xd  Tab key times.
	 * @param log Data to output.
	 * 
	 * @see #logTextBox(Object)
	 * 
	 * @author Frank-Wang
	 */
	public void logTextBox(int xd, Object log) {
		char[] cr = (log + "").toCharArray();
		String xxx = "";

		for (int i = 0; i < (cr.length) / 2 + 1; i++)
			xxx += "═";
		log(xd, "╔" + xxx + "╗");
		log(xd, "║ " + log + " ║");
		log(xd, "╚" + xxx + "╝");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerTextBox</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerTextBox({@linkplain Int},
	 * {@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * This function print input object in log with strengthen text.<BR/>
	 * With user pattern please use {@link #logTextBox(int, Object)}.
	 * 
	 * @param xd  Tab key times.
	 * @param log Data to output.
	 * 
	 * @see #loggerTextBox(Object)
	 * 
	 * @author Frank-Wang
	 */
	public void loggerTextBox(int xd, Object log) {
		if (BuilderPattern) {
			char[] cr = (log + "").toCharArray();
			String xxx = "";

			for (int i = 0; i < cr.length + 2; i++)
				xxx += "═";
			log(xd, "╔" + xxx + "╗");
			log(xd, "║ " + log + " ║");
			log(xd, "╚" + xxx + "╝");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logException({@linkplain Exception})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #logException(int, Exception, String)} to print
	 * input exception error message in log.<BR/>
	 * With builder pattern please use {@link #loggerException(Exception)}.
	 * 
	 * @param e Exception to output.
	 * 
	 * @see #logException(Exception, String)
	 * @see #logException(int, Exception)
	 * @see #logException(int, Exception, String)
	 * 
	 * @author Frank-Wang
	 */
	public void logException(Exception e) {
		logException(0, e, "");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size=
	 * "1">&emsp;loggerException({@linkplain Exception})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #loggerException(int, Exception, String)} to print
	 * input exception error message in log.<BR/>
	 * With user pattern please use {@link #logException(Exception)}.
	 * 
	 * @param e Exception to output.
	 * 
	 * @see#loggerException(Exception, String)
	 * @see #loggerException(int, Exception)
	 * @see #loggerException(int, Exception, String)
	 * 
	 * @author Frank-Wang
	 */
	public void loggerException(Exception e) {
		if (BuilderPattern) {
			logException(0, e, "");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logException({@linkplain Exception},
	 * {@linkplain String})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #logException(int, Exception, String)} to print
	 * input exception error message in log.<BR/>
	 * With builder pattern please use {@link #loggerException(Exception, String)}.
	 * 
	 * @param e      Exception to output.
	 * @param filter Input String message in the front of exception error message.
	 * 
	 * @see #logException(Exception)
	 * @see #logException(int, Exception)
	 * @see #logException(int, Exception, String)
	 * 
	 * @author Frank-Wang
	 */
	public void logException(Exception e, String filter) {
		logException(0, e, filter);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerException({@linkplain Exception},
	 * {@linkplain String})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #loggerException(int, Exception, String)} to print
	 * input exception error message in log.<BR/>
	 * With user pattern please use {@link #logException(Exception, String)}.
	 * 
	 * @param e      Exception to output.
	 * @param filter Input String message in the front of exception error message.
	 * 
	 * @see #loggerException(Exception)
	 * @see#loggerException(int, Exception)
	 * @see #loggerException(int, Exception, String)
	 * 
	 * @author Frank-Wang
	 */
	public void loggerException(Exception e, String filter) {
		if (BuilderPattern) {
			logException(0, e, filter);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logException({@linkplain Int},
	 * {@linkplain Exception})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #logException(int, Exception, String)} to print
	 * input exception error message in log.<BR/>
	 * With builder pattern please use {@link #loggerException(int, Exception)}.
	 * 
	 * @param xd Tab key times.
	 * @param e  Exception to output.
	 * 
	 * @see#logException(Exception)
	 * @see #logException(Exception, String)
	 * @see #logException(int, Exception, String)
	 * 
	 * @author Frank-Wang
	 */
	public void logException(int xd, Exception e) {
		logException(xd, e, "");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerException({@linkplain Int},
	 * {@linkplain Exception})</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #loggerException(int, Exception, String)} to print
	 * input exception error message in log.<BR/>
	 * With user pattern please use {@link #logException(int, Exception)}.
	 * 
	 * @param xd Tab key times.
	 * @param e  Exception to output.
	 * 
	 * @see #loggerException(Exception)
	 * @see #loggerException(Exception, String)
	 * @see #loggerException(int, Exception, String)
	 * 
	 * @author Frank-Wang
	 */
	public void loggerException(int xd, Exception e) {
		if (BuilderPattern) {
			logException(xd, e, "");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logException({@linkplain Int},
	 * {@linkplain Exception}, {@linkplain String})</font><BR/>
	 * <BR/>
	 * 
	 * This function print input exception error message in log.<BR/>
	 * With builder pattern please use
	 * {@link #loggerException(int, Exception, String)}.
	 * 
	 * @param xd     Tab key times.
	 * @param e      Exception to output.
	 * @param filter Input String message in the front of exception error message.
	 * 
	 * @see#logException(Exception)
	 * @see #logException(Exception, String)
	 * @see #logException(int, Exception)
	 * 
	 * @author Frank-Wang
	 */
	public void logException(int xd, Exception e, String filter) {
		log(xd, "■[Exception] " + (filter.length() > 0 ? ("(" + filter + ") ") : "") + e.getMessage());

		StackTraceElement[] stackElements = e.getStackTrace();
		for (StackTraceElement em : stackElements) {
			if (!em.getClassName().startsWith(filter))
				continue;
			log(xd + 1, em.getClassName() + ":" + em.getLineNumber() + " (/" + em.getMethodName() + ")");
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerException</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerException({@linkplain Int},
	 * {@linkplain Exception}, {@linkplain String})</font><BR/>
	 * <BR/>
	 * 
	 * This function print input exception error message in log.<BR/>
	 * With user pattern please use {@link #logException(int, Exception, String)}.
	 * 
	 * @param xd     Tab key times.
	 * @param e      Exception to output.
	 * @param filter Input String message in the front of exception error message.
	 * 
	 * @see #loggerException(Exception)
	 * @see #loggerException(Exception, String)
	 * @see #loggerException(int, Exception)
	 * 
	 * @author Frank-Wang
	 */
	public void loggerException(int xd, Exception e, String filter) {
		if (BuilderPattern) {
			log(xd, "■[Exception] " + (filter.length() > 0 ? ("(" + filter + ") ") : "") + e.getMessage());
			StackTraceElement[] stackElements = e.getStackTrace();
			for (StackTraceElement em : stackElements) {
				if (!em.getClassName().startsWith(filter))
					continue;
				log(xd + 1, em.getClassName() + ":" + em.getLineNumber() + " (/" + em.getMethodName() + ")");
			}
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>setStartTime</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;setStartTime()</font><BR/>
	 * <BR/>
	 * 
	 * This function will record the current time as the start time.<BR/>
	 * Calling {@link #setCloseTime()} to record the current times as the end
	 * time.<BR/>
	 * Calling {@link #logTimeSummary()} to calculate the time difference between
	 * the start time and the end time.
	 * 
	 * @author Frank-Wang
	 */
	public void setStartTime() {
		this.logStartTime = new Date();
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>setCloseTime</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;setCloseTime()</font><BR/>
	 * <BR/>
	 * 
	 * This function will record the current time as the end time.<BR/>
	 * Calling {@link #setStartTime()} to record the current times as the start
	 * time.<BR/>
	 * Calling {@link #logTimeSummary()} to calculate the time difference between
	 * the start time and the end time.
	 * 
	 * @author Frank-Wang
	 */
	public void setCloseTime() {
		this.logCloseTime = new Date();
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logTimeSummary</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logTimeSummary()</font><BR/>
	 * <BR/>
	 * 
	 * This function will calculate the time difference between the start time and
	 * the end time using {@link #getTimeDiff(long, long)}, also print result in the
	 * log file with strengthen text.<BR/>
	 * Calling {@link #setStartTime()} to record the current times as the start
	 * time.If the start time is not set, the class initialization time will be used
	 * as the start time.<BR/>
	 * Calling {@link #setEndTime()} to record the current times as the end time.If
	 * the end time is not set, the current time will be used as the end time.<BR/>
	 * Returning function please call {@link #getTimeSummary()}.<BR/>
	 * With builder pattern please use {@link #loggerTimeSummary()}.
	 * 
	 * @author Frank-Wang
	 */
	public void logTimeSummary() {
		if (this.logCloseTime == null)
			this.logCloseTime = new Date();
		String xxx = "[Time Summary]";
		String xxx1 = "══" + xxx + "════════════════";
		String xxx2 = "══" + xxx + "════════════════";

		log("╔" + xxx1 + "╗");
		log("║" + "◆Time Usage:\t" + getTimeDiff(this.logStartTime.getTime(), this.logCloseTime.getTime()));
		log("╚" + xxx2 + "╝");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerTimeSummary</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerTimeSummary()</font><BR/>
	 * <BR/>
	 * 
	 * This function will calculate the time difference between the start time and
	 * the end time using {@link #getTimeDiff(long, long)}, also print result in the
	 * log file with strengthen text.<BR/>
	 * Calling {@link #setStartTime()} to record the current times as the start
	 * time.If the start time is not set, the class initialization time will be used
	 * as the start time.<BR/>
	 * Calling {@link #setEndTime()} to record the current times as the end time.If
	 * the end time is not set, the current time will be used as the end time.<BR/>
	 * Returning function please call {@link #getTimeSummary()}.<BR/>
	 * With user pattern please use {@link #logTimeSummary()}.
	 * 
	 * @author Frank-Wang
	 */
	public void loggerTimeSummary() {
		if (this.logCloseTime == null)
			this.logCloseTime = new Date();
		String xxx = "[Time Summary]";
		String xxx1 = "══" + xxx + "════════════════";
		String xxx2 = "══" + xxx + "════════════════";

		log("╔" + xxx1 + "╗");
		log("║" + "◆Time Usage:\t" + getTimeDiff(this.logStartTime.getTime(), this.logCloseTime.getTime()));
		log("╚" + xxx2 + "╝");
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getTimeSummary</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getTimeSummary()</font><BR/>
	 * <BR/>
	 * 
	 * This function will calculate the time difference between the start time and
	 * the end time using {@link #getTimeDiff(long, long)}.<BR/>
	 * Calling {@link #setStartTime()} to record the current times as the start
	 * time.If the start time is not set, the class initialization time will be used
	 * as the start time.<BR/>
	 * Calling {@link #setEndTime()} to record the current times as the end time.If
	 * the end time is not set, the current time will be used as the end time.<BR/>
	 * Printing function please call {@link #getTimeSummary()}.
	 * 
	 * @return String of use time.
	 * 
	 * @author Frank-Wang
	 */
	public String getTimeSummary() {
		if (this.logCloseTime == null)
			this.logCloseTime = new Date();
		return getTimeDiff(this.logStartTime.getTime(), this.logCloseTime.getTime());
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getTimeDiff</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getTimeDiff({@linkplain Long},
	 * {@linkplain Long})</font><BR/>
	 * <BR/>
	 * 
	 * This function willl calculate the time difference between the start time and
	 * the end time.
	 * 
	 * @param t1 Input start time.
	 * @param t2 Input End time.
	 * 
	 * @return String of use time.
	 * 
	 * @author Frank-Wang
	 */
	public static String getTimeDiff(long t1, long t2) {
		long diff = t1 > t2 ? t1 - t2 : t2 - t1;
		int hh = (int) (diff / 1000 / 60 / 60 % 24);
		int mm = (int) (diff / 1000 / 60 % 60);
		int ss = (int) (diff / 1000 % 60);
		return (hh > 0 ? hh + "(h) " : "") + (hh > 0 || mm > 0 ? mm + "(m) " : "") + ss + "(s) \t= " + diff;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getTimeDiff</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getTimeDiff({@linkplain Long},
	 * {@linkplain Long})</font><BR/>
	 * <BR/>
	 * 
	 * This function will get current time in
	 * {@code Year + Month + Day + Hour + Minute + Second} and return in String.It
	 * will be something like [yyyyMMddhhmmss].
	 * 
	 * @return String of time stamp.
	 * 
	 * @author Frank-Wang
	 */
	public String getTimeStamp() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) < 9 ? "0" : "")
				+ (calendar.get(Calendar.MONTH) + 1) + "" + (calendar.get(Calendar.DAY_OF_MONTH) < 9 ? "0" : "")
				+ calendar.get(Calendar.DAY_OF_MONTH) + "" + (calendar.get(Calendar.HOUR_OF_DAY) < 9 ? "0" : "")
				+ calendar.get(Calendar.HOUR_OF_DAY) + "" + (calendar.get(Calendar.MINUTE) < 9 ? "0" : "")
				+ calendar.get(Calendar.MINUTE) + "" + (calendar.get(Calendar.SECOND) < 9 ? "0" : "")
				+ calendar.get(Calendar.SECOND);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getCurrentTimeStringWithYYYY_MM_DD</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getCurrentTimeStringWithYYYY_MM_DD()</font><BR/>
	 * <BR/>
	 * 
	 * This function will get current time with {@code Year + Month + Day}.It will
	 * be something like [ yyyyMMdd ].
	 * 
	 * @see #getCurrentTimeStringWithHH_MM_SS_SSS()
	 * @see #getCurrentTimeStringWithByChoose(String)
	 * 
	 * @return String of current time.
	 * 
	 * @author Frank-Wang
	 */
	public String getCurrentTimeStringWithYYYY_MM_DD() {
		SYS_FILE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		return SYS_FILE_DATE_FORMAT.format(new Date());
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getCurrentTimeStringWithHH_MM_SS_SSS</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size=
	 * "1">&emsp;getCurrentTimeStringWithHH_MM_SS_SSS()</font><BR/>
	 * <BR/>
	 * 
	 * This function will get current time with
	 * {@code Hour + Minute + Second + Millisecond}.It will be something like [
	 * hhmmssSSS ].
	 * 
	 * @see #getCurrentTimeStringWithYYYY_MM_DD()
	 * @see #getCurrentTimeStringWithByChoose(String)
	 * 
	 * @return String of current time.
	 * 
	 * @author Frank-Wang
	 */
	public String getCurrentTimeStringWithHH_MM_SS_SSS() {
		SYS_DATA_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		return SYS_DATA_DATE_FORMAT.format(new Date());
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getCurrentTimeStringWithByChoose</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size=
	 * "1">&emsp;getCurrentTimeStringWithByChoose({@linkplain String})</font><BR/>
	 * <BR/>
	 * 
	 * This function will get current time with user input format.For detailed
	 * visits, please refer to<BR/>
	 * <b>"https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html"</b>.
	 * 
	 * @param dateAndTimePatterns Input String like <b>"h:mm a"</b>, result will be
	 *                            like <b>"12:08 PM"</b>.For more information,
	 *                            please go to the link page.
	 * 
	 * @see #getCurrentTimeStringWithYYYY_MM_DD()
	 * @see #getCurrentTimeStringWithHH_MM_SS_SSS()
	 * 
	 * @return String of current time.
	 * 
	 * @author Frank-Wang
	 */
	public String getCurrentTimeStringWithByChoose(String dateAndTimePatterns) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateAndTimePatterns, Locale.TAIWAN);
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		return dateFormat.format(new Date());
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>cramLeft</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;cramLeft({@linkplain String},
	 * {@linkplain String}, {@linkplain Int})</font><BR/>
	 * <BR/>
	 * 
	 * This function will add characters to the left of the input string.<BR/>
	 * If input something like <b>("123","-",5)</b>, result string will be like
	 * <b>"--123"</b>.
	 * 
	 * @param str    Input String to do adding characters.
	 * @param fill   Input character to add.
	 * @param length Total length of the string.
	 * 
	 * @return String of end adding characters to the left.
	 * 
	 * @author Frank-Wang
	 */
	private String cramLeft(String str, String fill, int length) {
		int gap = length - str.length();
		for (int i = 0; i < gap; i++)
			str = fill + str;
		return str;
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>getArrayToString</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;getArrayToString({@linkplain Object})</font><BR/>
	 * <BR/>
	 * 
	 * This function will change array data to string data.<BR/>
	 * You can input <b>{@link Map}, {@link Array[]}, {@link ArrayList}</b>, result
	 * string will be separate by <b>","</b>.
	 * 
	 * @param object Hope to convert data.
	 * 
	 * @return String of data.
	 * 
	 * @author Frank-Wang
	 */
	public String getArrayToString(Object object) {
		String tmp = "";
		try {
			Object[] objArray = (Object[]) object;
			for (int i = 0; i < objArray.length; i++)
				tmp += ((i > 0) ? "," : "") + objArray[i];
		} catch (Exception e) {
			tmp += object + "";
		}
		return tmp;
	}

	/**
	 * Some strange symbol<BR/>
	 * <b>"▅", "═", "█"</b>
	 */
	private String[] SeparatorBarChars = { "▅", "═", "█" };

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logSeparatorBar</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logSeparatorBar()</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #logSeparatorBar(int)} to print separator bar in
	 * log.<BR/>
	 * With builder pattern please use {@link #loggerSeparatorBar()}.
	 * 
	 * @see #logSeparatorBar(int)
	 * 
	 * @author Frank-Wang
	 */
	public void logSeparatorBar() {
		logSeparatorBar(0);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerSeparatorBar</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerSeparatorBar()</font><BR/>
	 * <BR/>
	 * 
	 * This function call {@link #loggerSeparatorBar(int)} to print separator bar in
	 * log.<BR/>
	 * With user pattern please use {@link #logSeparatorBar()}.
	 * 
	 * @see #loggerSeparatorBar(int)
	 * 
	 * @author Frank-Wang
	 */
	public void loggerSeparatorBar() {
		if (BuilderPattern) {
			logSeparatorBar(0);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logSeparatorBar</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logSeparatorBar({@linkplain Int})</font><BR/>
	 * <BR/>
	 * 
	 * This function print separator bar in log.<BR/>
	 * User can change separate characters in {@link #SeparatorBarChars}.<BR/>
	 * With builder pattern please use {@link #loggerSeparatorBar(int)}.
	 * 
	 * @param x Selected character.
	 * 
	 * @see #logSeparatorBar()
	 * 
	 * @author Frank-Wang
	 */
	public void logSeparatorBar(int x) {
		String SeparatorBar = "";
		for (int i = 0; i < 100; i++)
			SeparatorBar += SeparatorBarChars[(x >= SeparatorBarChars.length - 1) ? x : 0];
		logSeparatorBar(SeparatorBar);
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>loggerSeparatorBar</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;loggerSeparatorBar({@linkplain Int})</font><BR/>
	 * <BR/>
	 * 
	 * This function print separator bar in log.<BR/>
	 * User can change separate characters in {@link #SeparatorBarChars}.<BR/>
	 * With user pattern please use {@link #logSeparatorBar(int)}.
	 * 
	 * @param x Selected character.
	 * 
	 * @see #loggerSeparatorBar()
	 * 
	 * @author Frank-Wang
	 */
	public void loggerSeparatorBar(int x) {
		if (BuilderPattern) {
			String SeparatorBar = "";
			for (int i = 0; i < 100; i++)
				SeparatorBar += SeparatorBarChars[(x >= SeparatorBarChars.length - 1) ? x : 0];
			logSeparatorBar(SeparatorBar);
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>logSeparatorBar</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;logSeparatorBar({@linkplain Int})</font><BR/>
	 * <BR/>
	 * 
	 * This function print String in log with out title time.<BR/>
	 * 
	 * @param SeparatorBar String to print in log.
	 * 
	 * @author Frank-Wang
	 */
	private void logSeparatorBar(String SeparatorBar) {
		SeparatorBar = "" + SeparatorBar + "";
		System.out.println(SeparatorBar);
		if (logtofile) {
			try {
				osw.append("\r\n" + SeparatorBar);
				osw.flush();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>deleteLogFile</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;deleteLogFile()</font><BR/>
	 * <BR/>
	 * 
	 * This function delete log file.<BR/>
	 * 
	 * @author Frank-Wang
	 */
	public void deleteLogFile() {
		if (this.logtofile) {
			try {
				(new File(this.logFilePath)).delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.logtofile = false;
		}
	}

	/**
	 * &emsp;&emsp;&emsp;&emsp;<em><b>close</em></b><BR/>
	 * <BR/>
	 * &emsp;<font size= "1">&emsp;close()</font><BR/>
	 * <BR/>
	 * 
	 * This function close {@link #fos} and {@link osw}.<BR/>
	 * 
	 * @throws IOException
	 * 
	 * @author Frank-Wang
	 */
	public void close() throws IOException {
		fos.close();
		osw.close();
	}
}
