package cscie259.project1.mf;

import java.util.Map;
/**
 * @author KH2169
 * This class represents an attribute pair both name and value
 */
public class AttributeEntry implements Map.Entry<String, String>{
		private final String name_, value_;
		public AttributeEntry(String name, String value){
			this.name_ = name;
			this.value_ = value;
		}
		
		@Override
		public String getKey() {
			return this.name_;
		}

		@Override
		public String getValue() {
			return this.value_;
		}

		@Override
		public String setValue(String value) {
			return this.value_;
		}
		
	}