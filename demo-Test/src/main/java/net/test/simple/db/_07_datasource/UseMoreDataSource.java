/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.test.simple.db._07_datasource;
import static net.hasor.test.utils.HasorUnit.newID;
import java.io.IOException;
import java.sql.SQLException;
import net.hasor.core.AppContext;
import net.hasor.core.Hasor;
import net.hasor.db.jdbc.core.JdbcTemplate;
import net.test.simple.db._07_datasource.warp.MoreDataSourceWarp;
import org.junit.Test;
/**
 * 使用多数据源例子
 * @version : 2014年7月23日
 * @author 赵永春(zyc@hasor.net)
 */
public class UseMoreDataSource {
    @Test
    public void useMoreDataSource() throws SQLException, IOException {
        //1.构建AppContext
        AppContext app = Hasor.createAppContext("net/test/simple/db/jdbc-config.xml", new MoreDataSourceWarp());
        //2.取得JDBC操作接口
        JdbcTemplate mJDBC = app.getInstance("mysql");
        JdbcTemplate hJDBC = app.getInstance("hsql");
        //3.初始化表
        this.initData(mJDBC, hJDBC);
        //
        System.out.println("MySQL User Count :" + mJDBC.queryForInt("select count(*) from TB_User"));
        System.out.println("HSQL User Count :" + hJDBC.queryForInt("select count(*) from TB_User"));
        //
    }
    private void initData(JdbcTemplate mJDBC, JdbcTemplate hJDBC) throws SQLException, IOException {
        String insertUser_1 = "insert into TB_User values(?,'默罕默德','muhammad','123','muhammad@hasor.net','2011-06-08 20:08:08');";
        String insertUser_2 = "insert into TB_User values(?,'安妮.贝隆','belon','123','belon@hasor.net','2011-06-08 20:08:08');";
        String insertUser_3 = "insert into TB_User values(?,'赵飞燕','muhammad','123','muhammad@hasor.net','2011-06-08 20:08:08');";
        //
        //1.初始化MySQL
        if (mJDBC.tableExist("TB_User") == false) {
            mJDBC.loadSQL("net/test/simple/db/TB_User.sql");
        } else {
            mJDBC.execute("delete from TB_User");
        }
        mJDBC.update(insertUser_1, newID());//执行插入语句
        mJDBC.update(insertUser_2, newID());//执行插入语句
        //2.初始化HSQL
        if (hJDBC.tableExist("TB_User") == false) {
            hJDBC.loadSQL("net/test/simple/db/TB_User.sql");
        } else {
            hJDBC.execute("delete from TB_User");
        }
        hJDBC.update(insertUser_3, newID());//执行插入语句
    }
}