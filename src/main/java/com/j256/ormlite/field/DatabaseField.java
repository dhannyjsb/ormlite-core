package com.j256.ormlite.field;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.j256.ormlite.db.DatabaseType;

/**
 * Annotation that identifies a field in a class that corresponds to a column in the database and will be persisted.
 * Fields that are not to be persisted such as transient or other temporary fields probably should be ignored. For
 * example:
 * 
 * <p>
 * <blockquote>
 * 
 * <pre>
 * &#064;DatabaseField(id = true)
 * private String name;
 * 
 * &#064;DatabaseField(columnName = &quot;passwd&quot;, canBeNull = false)
 * private String password;
 * </pre>
 * 
 * </blockquote>
 * </p>
 * 
 * <p>
 * <b> WARNING:</b> If you add any extra fields here, you will need to add them to {@link DatabaseFieldConfig} as well.
 * </p>
 * 
 * @author graywatson
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface DatabaseField {

	/**
	 * The name of the column in the database. If not set then the name is taken from the field name.
	 */
	String columnName() default "";

	/**
	 * The {@link JdbcType} associated with the field. If not set then the Java class of the field is used to match with
	 * the appropriate {@link JdbcType}.
	 */
	JdbcType jdbcType() default JdbcType.UNKNOWN;

	/**
	 * The default value of the field for creating the table. Default is none.
	 */
	String defaultValue() default "";

	/**
	 * Width of array fields (often for strings). Default is database-specific.
	 */
	int width() default 0;

	/**
	 * Whether the field can be assigned to null or have no value. Default is true.
	 */
	boolean canBeNull() default true;

	/**
	 * Whether the field is the id field or not. Default is false. Only one field can have this set in a class. If you
	 * don't have it set then you won't be able to use the query, update, and delete by ID methods. Only one of this,
	 * {@link #generatedId}, and {@link #generatedIdSequence} can be specified.
	 */
	boolean id() default false;

	/**
	 * Whether the field is an auto-generated id field. Default is false. With databases for which
	 * {@link DatabaseType#isIdSequenceNeeded} is true then this will cause the name of the sequence to be
	 * auto-generated. To specify the name of the sequence use {@link #generatedIdSequence}. Only one of this,
	 * {@link #id}, and {@link #generatedIdSequence} can be specified.
	 */
	boolean generatedId() default false;

	/**
	 * The name of the sequence number to be used to generate this value. Default is none. This is only necessary for
	 * database for which {@link DatabaseType#isIdSequenceNeeded} is true and you already have a defined sequence that
	 * you want to use. If you use {@link #generatedId} instead then the code will auto-generate a sequence name. Only
	 * one of this, {@link #id}, and {@link #generatedId} can be specified.
	 */
	String generatedIdSequence() default "";

	/**
	 * Field is a non-primitive object that corresponds to another class that is also stored in the database. It must
	 * have an id field (either {@link #id}, {@link #generatedId}, or {@link #generatedIdSequence} which will be stored
	 * in this table. When an object is returned from a query call, any foreign objects will just have the id field set
	 * in it. To get all of the other fields you will have to do a refresh on the object using its own Dao.
	 */
	boolean foreign() default false;

	/**
	 * Package should use get...() and set...() to access the field value instead of the default direct field access via
	 * reflection. This may be necessary if the object you are storing has protections around it.
	 * 
	 * <p>
	 * <b>NOTE:</b> The name of the get method <i>must</i> match getXxx() where Xxx is the name of the field with the
	 * first letter capitalized. The get <i>must</i> return a class which matches the field's. The set method
	 * <i>must</i> match setXxx(), have a single argument whose class matches the field's, and return void. For example:
	 * </p>
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * &#064;DatabaseField
	 * private Integer orderCount;
	 * 
	 * public Integer getOrderCount() {
	 * 	return orderCount;
	 * }
	 * 
	 * public void setOrderCount(Integer orderCount) {
	 * 	this.orderCount = orderCount;
	 * }
	 * </pre>
	 * 
	 * </blockquote>
	 */
	boolean useGetSet() default false;

	/**
	 * If the field is an Enum and the database has a value that is not one of the names in the enum then this name will
	 * be used instead. It must match one of the enum names. This is mainly useful when you are worried about backwards
	 * compatibility with older database rows or future compatibility if you have to roll back to older data definition.
	 */
	String unknownEnumName() default "";

	/**
	 * If this is set to true (default false) then it will throw a SQLException if a null value is attempted to be
	 * de-persisted into a primitive. This must only be used on a primitive field. If this is false then if the database
	 * field is null, the value of the primitive will be set to 0.
	 */
	boolean throwIfNull() default false;
}