/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.mongodb.engine.comparemode.impl;

import org.bson.BSONObject;
import org.tinygroup.context.Context;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;

/**
 * 
 * 功能说明:小于比较模式 

 * 开发人员: renhui <br>
 * 开发时间: 2013-11-27 <br>
 * <br>
 */
public class LessThanCompareMode extends AbstractNeedValueCompareMode {

	public BSONObject generateBSONObject(String propertyName, Object value, Context context) {
		return new BasicDBObject(propertyName, new BasicDBObject(QueryOperators.LT, value));
	}

	public String getCompareKey() {
		return "lessThan";
	}

}
