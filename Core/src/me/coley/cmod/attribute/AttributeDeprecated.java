package me.coley.cmod.attribute;

import me.coley.cmod.attribute.Attribute;
import me.coley.cmod.attribute.AttributeType;

public class AttributeDeprecated extends Attribute {

	public AttributeDeprecated(int name) {
		super(name, AttributeType.DEPRECATED);
	}
}
