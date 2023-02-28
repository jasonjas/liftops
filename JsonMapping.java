package liftops;

import java.util.*;
import org.kopitubruk.util.json.JSONUtil;
import org.kopitubruk.util.json.*;

public class JsonMapping {

	   public String mapToJSON(Map<?, ?> data) {
	        return JSONUtil.toJSON(data);
	    }
	    
	    public String arrayListToJSON (ArrayList<?> data) {
	        return JSONUtil.toJSON(data);
	    }
	    
	    public String listToJSON (List<?> data) {
	    	return JSONUtil.toJSON(data);
	    }
	    
}
