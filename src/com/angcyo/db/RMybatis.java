package com.angcyo.db;

import com.angcyo.beans.NameTableTest1;
import com.angcyo.beans.NameTableTest2;
import com.angcyo.utils.PropertiesUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by angcyo on 2016-02-29 19:09.
 */
public class RMybatis {
    private static SqlSessionFactory factory;
    private static final String MYBATIS_CONFIG = "com/angcyo/db/config/mybatisConfig.xml";

    static {
        try {
            Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIG);
            factory = new SqlSessionFactoryBuilder().build(reader);
            PropertiesUtil.get("log4j.rootLogger", new PropertiesUtil.OnGet() {
                @Override
                public void onGet(String value) {
                    if (value.contains("DEBUG")) {
                        System.out.println("SqlSessionFactory init success");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            factory = null;
        }
    }

    public static void sql(OnSql onSql) {
        if (onSql == null) {
            return;
        }
        if (factory == null) {
            onSql.onError(new IllegalArgumentException("SqlSessionFactory 创建失败"));
            return;
        }
        SqlSession session = factory.openSession(false);
        try {
            onSql.onSql(session);
            session.commit();
        } catch (Exception e) {
            onSql.onError(e);
            session.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public interface OnSql {
        void onSql(SqlSession session);

        void onError(Exception e);
    }

    public static void p(float msg) {
        System.out.println(Thread.currentThread().getId() + "-->" + msg);
    }

    public static void p(String msg) {
        System.out.println(Thread.currentThread().getId() + "-->" + msg);
    }

    public static void main(String... args) {
        RMybatis.sql(new OnSql() {
            @Override
            public void onSql(SqlSession session) {
                List<NameTableTest1> objects = session.selectList("selectAll");
                for (NameTableTest1 name : objects) {
                    p(name.getRid());
                }
            }

            @Override
            public void onError(Exception e) {
                p(e.toString());
            }
        });

        RMybatis.sql(new OnSql() {
            @Override
            public void onSql(SqlSession session) {
                List<NameTableTest2> objects = session.selectList("selectAll2");
                for (NameTableTest2 name : objects) {
                    p(name.id);
                }
            }

            @Override
            public void onError(Exception e) {
                p(e.toString());
            }
        });
        RMybatis.sql(new OnSql() {
            @Override
            public void onSql(SqlSession session) {
                NameTableTest1 tableTest1 = new NameTableTest1();
                tableTest1.setRid(12);
                List<NameTableTest1> objects = session.selectList("select3", tableTest1);
                for (NameTableTest1 name : objects) {
                    p(name.getName_time());
                }
            }

            @Override
            public void onError(Exception e) {
                p(e.toString());
            }
        });
        RMybatis.sql(new OnSql() {
            @Override
            public void onSql(SqlSession session) {
                List<NameTableTest1> objects = session.selectList("select4", 12);
                for (NameTableTest1 name : objects) {
                    p(name.getName_time());
                }
            }

            @Override
            public void onError(Exception e) {
                p(e.toString());
            }
        });
    }
}
