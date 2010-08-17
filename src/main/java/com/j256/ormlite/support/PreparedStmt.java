package com.j256.ormlite.support;

import java.sql.SQLException;
import java.sql.SQLWarning;

/**
 * A reduction of the SQL PreparedStatment so we can implement its functionality outside of JDBC.
 * 
 * @author graywatson
 */
public interface PreparedStmt {

	/**
	 * Returns the number of columns in this statement.
	 */
	public int getColumnCount() throws SQLException;

	/**
	 * Get the designated column's name.
	 * 
	 * @param column
	 *            The first column is 1, the second is 2, ...
	 */
	public String getColumnName(int column) throws SQLException;

	/**
	 * Execute the prepared statement returning true if it worked.
	 */
	public boolean execute() throws SQLException;

	/**
	 * Return any warnings generated by the statement.
	 */
	public SQLWarning getWarnings() throws SQLException;

	/**
	 * Get our results object generated by the statement.
	 */
	public Results getResults() throws SQLException;

	/**
	 * Put more results in the results object.
	 */
	public boolean getMoreResults() throws SQLException;

	/**
	 * Close the statement.
	 */
	public void close() throws SQLException;

	/**
	 * Set the parameter specified by the index and type to be null.
	 * 
	 * @param parameterIndex
	 *            Index of the parameter with 1 being the first parameter, etc..
	 * @param sqlType
	 *            SQL type of the parameter.
	 */
	public void setNull(int parameterIndex, int sqlType) throws SQLException;

	/**
	 * Set the parameter specified by the index and type to be an object.
	 * 
	 * @param parameterIndex
	 *            Index of the parameter with 1 being the first parameter, etc..
	 * @param obj
	 *            Object that we are setting.
	 * @param sqlType
	 *            SQL type of the parameter.
	 */
	public void setObject(int parameterIndex, Object obj, int sqlType) throws SQLException;

	/**
	 * Set the number of rows to return in the results.
	 */
	public void setMaxRows(int max) throws SQLException;
}
