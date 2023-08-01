package jp.co.marimogt.takken.batch.dba;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jp.co.marimogt.takken.batch.common.PropertyReader;
import jp.co.marimogt.takken.batch.entity.NoticeMail;
import jp.co.marimogt.takken.batch.entity.TraderExpirationNoticeMail;
import jp.co.marimogt.takken.batch.wirte.WirteError;

public class EmployeeDBA implements DatabaseAccessor {
	//private DBConfig db = new DBConfig();

	//データベースからお客様のメールを抽出します。
	public List<NoticeMail> selectSendMessageData() {

		List<NoticeMail> messageDataList = new ArrayList<>();

		String url = "jdbc:mysql://" + PropertyReader.getProperty(PropertyReader.KEY_DB_HOST) + ":"
				+ PropertyReader.getProperty(PropertyReader.KEY_DB_PORT) + "/"
				+ PropertyReader.getProperty(PropertyReader.KEY_DB_SCHEMA_NAME);
		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(url,
					PropertyReader.getProperty(PropertyReader.KEY_DB_USER),
					PropertyReader.getProperty(PropertyReader.KEY_DB_PWD))) {

				String query = "SELECT no, name, employee_no, shunin_limit_date, expiration_email1, expiration_email2 FROM employee "
						+
						" WHERE shunin_limit_date < DATE_ADD(CURDATE(), INTERVAL 90 DAY) and shunin_limit_date >= CURRENT_DATE ";
				try (Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query)) {

					// 結果をArrayListに格納
					while (rs.next()) {

						TraderExpirationNoticeMail message = new TraderExpirationNoticeMail();
						String email1 = rs.getString("expiration_email1");
						String email2 = rs.getString("expiration_email2");
						List<String> receiveAddressList = new ArrayList<>();
						if (email1 != null && !email1.equals("")) {
							;
							receiveAddressList.add(email1);
						}
						if (email2 != null && !email2.equals("")) {
							receiveAddressList.add(email2);
						}

						message.setExpirationDate(rs.getObject("shunin_limit_date", LocalDate.class));
						message.setTraderName(rs.getString("name"));
						message.setEmployeeNo(rs.getString("employee_no"));
						message.setReceiveAddressArray(receiveAddressList);
						messageDataList.add(message);

					}

				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					System.out.println(ex.getSQLState());
          System.out.println(ex.getErrorCode());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return messageDataList;
	}

}
