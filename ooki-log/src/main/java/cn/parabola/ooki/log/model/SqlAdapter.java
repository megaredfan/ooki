package cn.parabola.ooki.log.model;

public class SqlAdapter {
	public SqlAdapter(String sql){
		this.sql = sql;
	}
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {
		return "SqlAdapter [sql=" + sql + "]";
	}
	
}
