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
package org.tinygroup.dbrouter.config;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tinygroup.dbrouter.ShardRule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 分片
 */
@XStreamAlias("shard")
public class Shard {
	private transient Map<Connection, Connection> shardConnection =
            new HashMap<Connection, Connection>();
	private transient Map<String, String> tableMappingMap = null;
    /**
     * 分片标识
     */
    @XStreamAsAttribute
    private String id;
    /**
     * 分片命中规则
     */
    @XStreamAlias("shard-rules")
    private List<ShardRule> shardRules;
    /**
     * 数据源标识
     */
    @XStreamAlias("data-source-id")
    @XStreamAsAttribute
    private String dataSourceId;
    /**
     * 读权重
     */
    @XStreamAsAttribute
    @XStreamAlias("read-weight")
    private int readWeight = 10;
    /**
     * 写权重
     */
    @XStreamAsAttribute
    @XStreamAlias("write-able")
    private boolean writeAble;
    /**
     * 表名
     */
    @XStreamAlias("table-mappings")
    private List<TableMapping> tableMappings;


    public Shard() {

    }

    public Shard(String id, String dataSourceId) {
        this.id = id;
        this.dataSourceId = dataSourceId;
    }

    public Shard(String id, String dataSourceId, int readWeight) {
        this.id = id;
        this.dataSourceId = dataSourceId;
        this.readWeight = readWeight;
    }

    public Shard(String id, String dataSourceId, boolean writeAble, int readWeight) {
        this.id = id;
        this.dataSourceId = dataSourceId;
        this.readWeight = readWeight;
        this.writeAble = writeAble;
    }

    public List<TableMapping> getTableMappings() {
        return tableMappings;
    }

    public void setTableMappings(List<TableMapping> tableMappings) {
        this.tableMappings = tableMappings;
    }

    public void setConnection(Connection connection, Connection realConnection) {
    	if(shardConnection==null){
    		shardConnection=new HashMap<Connection, Connection>();
    	}
        shardConnection.put(connection, realConnection);
    }

    public Connection getConnection(Connection connection) {
        return shardConnection.get(connection);
    }

    public int getReadWeight() {
        return readWeight;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public void setReadWeight(int readWeight) {
        this.readWeight = readWeight;
    }

    
    public boolean isWriteAble() {
		return writeAble;
	}

	public void setWriteAble(boolean writeAble) {
		this.writeAble = writeAble;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ShardRule> getShardRules() {
        return shardRules;
    }

    public void setShardRules(List<ShardRule> shardRules) {
        this.shardRules = shardRules;
    }

    public String getShardTableName(String tableName) {
        String shardTableName = tableName;
        if (tableMappings != null) {
            for (TableMapping mapping : tableMappings) {
                if (mapping.getTableName().equalsIgnoreCase(tableName)) {
                    shardTableName = mapping.getShardTableName();
                    break;
                }
            }
        }
        return shardTableName;
    }

    public Map<String, String> getTableMappingMap() {
        if (tableMappings != null && tableMappingMap == null) {
            tableMappingMap = new HashMap<String, String>();
            for (TableMapping mapping : tableMappings) {
                tableMappingMap.put(mapping.getTableName(), mapping.getShardTableName());
            }
        }
        return tableMappingMap;
    }

}
