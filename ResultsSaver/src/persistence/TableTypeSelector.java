package persistence;

import utils.FileType;

public class TableTypeSelector {

	public TableTypeSelector() {
	}
	
	
	public String getTableSelector(FileType type){
		switch (type) {
		case IRI:
			return "iri";
		case CRITICAL:
			return "critical";
		case SIMPLE:
			return "point_on_map";
		}
		return null;
	}
	
}
