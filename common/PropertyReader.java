
public class PropertyReader {

	public static final String KEY_EMAIL_ADDRESS = "email.sender.address";
	public static final String KEY_EMAIL_SEND_PWD = "email.send.pwd";
	public static final String KEY_EMAIL_SEND_HOST = "email.send.host";
	public static final String KEY_EMAIL_PORT = "email.port";
	public static final String KEY_EMAIL_SEND_WAIT_TIME = "email.send.wait.time";
	public static final String KEY_DB_HOST = "db.host";
	public static final String KEY_DB_PORT = "db.port";
	public static final String KEY_DB_SCHEMA_NAME = "db.schema.name";
	public static final String KEY_DB_USER = "db.user";
	public static final String KEY_DB_PWD = "db.pwd";
	public static final String KEY_MSG_TRADER_SUBJECT = "message.trader.subject";
	public static final String KEY_MSG_TRADER_BODY = "message.trader.body";
	public static final String KEY_MSG_50_2_SUBJECT = "message.infoOffice.subject";
	public static final String KEY_MSG_50_2_BODY = "message.infoOffice.body";


	private static final String INIT_FILE_PATH = "resources/sample.properties";
	private static final Properties properties;

	private PropertyReader() throws Exception {
	}

	static {
		try (InputStream inputStream = PropertyReader.class
				.getClassLoader().getResourceAsStream(INIT_FILE_PATH)) {
      
			properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

		} catch (IOException e) {
			// ファイル読み込みに失敗
			e.printStackTrace();

		}
	}

	//	static {
	//		properties = new Properties();
	//		try {
	//			properties.load(Files.newBufferedReader(Paths.get(INIT_FILE_PATH), StandardCharsets.UTF_8));
	//		} catch (IOException e) {
	//			// ファイル読み込みに失敗
	//			System.out.println(e);
	//			System.out.println(String.format("ファイルの読み込みに失敗しました。ファイル名:%s", INIT_FILE_PATH));
	//		}
	//	}

	/**
	 * プロパティ値を取得する
	 *
	 * @param key キー
	 * @return 値
	 */
	public static String getProperty(final String key) {
		//return key;
		return getProperty(key, "");
	}

	/**
	 * プロパティ値を取得する
	 *
	 * @param key キー
	 * @param defaultValue デフォルト値
	 * @return キーが存在しない場合、デフォルト値
	 *          存在する場合、値
	 */
	public static String getProperty(final String key, final String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

}
