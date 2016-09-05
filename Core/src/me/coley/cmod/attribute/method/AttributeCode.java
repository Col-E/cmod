package me.coley.cmod.attribute.method;

import java.util.List;

import me.coley.cmod.attribute.Attribute;
import me.coley.cmod.attribute.AttributeType;

/**
 * The Code of a method. Contains sub-attributes:
 * <ul>
 * <li>{@link #lines LineNumberTable} - <i>May be null</i>
 * <li>{@link #variables LocalVariableTable} - <i>May be null</i>
 * <li>{@link #variableTypes LocalVariableTypeTable}
 * <li>{@link #stackMap StackMapTable}
 * </ul>
 * May contain others, but those four are standard.
 * 
 * @author Matt
 */
public class AttributeCode extends Attribute {
	/**
	 * Max number of values allowed on the stack.
	 */
	public int stack;
	/**
	 * Max number of variables allowed in the method.
	 */
	public int locals;
	/**
	 * A {@link me.coley.cmod.attribute.method.AttributeLineNumberTable table}
	 * that correlates opcode indices and line numbers <i>(Debugging). <br>
	 * May be null.</i>
	 */
	public AttributeLineNumberTable lines;
	/**
	 * A {@link me.coley.cmod.attribute.method.AttributeLocalVariableTable
	 * table} containing local variables.
	 */
	public AttributeLocalVariableTable variables;
	/**
	 * A {@link me.coley.cmod.attribute.method.AttributeLocalVariableTypeTable
	 * table} containing types of local variables. <br>
	 * <i>May be null</i>
	 */
	public AttributeLocalVariableTypeTable variableTypes;
	/**
	 * A {@link me.coley.cmod.attribute.method.AttributeStackMapTable table}
	 * that supplies information about the stack and stored locals at given
	 * bytecode offsets.
	 */
	public AttributeStackMapTable stackMap;
	/**
	 * List of attributes that aren't {@link #lines}, {@link #variables},
	 * {@link #variableTypes}, or {@link #stackMap}. Most likely empty in 99% of
	 * all cases.<br>
	 */
	public List<Attribute> attributes;

	public AttributeCode(int name, int stack, int locals, OpcodeListData_TEMP data, List<Attribute> attributes) {
		super(name, AttributeType.CONSTANT_VALUE);
		this.stack = stack;
		this.locals = locals;
		for (Attribute attribute : attributes) {
			switch (attribute.type) {
			case LINE_NUMBER_TABLE:
				lines = (AttributeLineNumberTable) attribute;
				break;
			case LOCAL_VARIABLE_TABLE:
				variables = (AttributeLocalVariableTable) attribute;
				break;
			case LOCAL_VARIABLE_TYPE_TABLE:
				variableTypes = (AttributeLocalVariableTypeTable) attribute;
				break;
			case STACK_MAP_TABLE:
				stackMap = (AttributeStackMapTable) attribute;
				break;
			default:
				attributes.add(attribute);
				break;
			}
		}
	}

}
