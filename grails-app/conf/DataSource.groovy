dataSource {
	pooled = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			// url = "jdbc:h2:devDb;MVCC=TRUE"
			driverClassName = "org.postgresql.Driver"
			dialect = org.hibernate.dialect.PostgreSQLDialect

			url = "jdbc:postgresql://localhost:5432/rostertrackerdev"

			username = "postgres"
			password =  "pg"
		}
	}
	test {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:h2:mem:testDb;MVCC=TRUE"
		}
	}
	production {
		dataSource {
			dbCreate = "create-drop"
			driverClassName = "org.postgresql.Driver"
			dialect = org.hibernate.dialect.PostgreSQLDialect

			uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")

			url = "jdbc:postgresql://"+uri.host+uri.path
			username = uri.userInfo.split(":")[0]
			password = uri.userInfo.split(":")[1]
		}

		//        dataSource {
		//            dbCreate = "update"
		//            url = "jdbc:h2:prodDb;MVCC=TRUE"
		//            pooled = true
		//            properties {
		//               maxActive = -1
		//               minEvictableIdleTimeMillis=1800000
		//               timeBetweenEvictionRunsMillis=1800000
		//               numTestsPerEvictionRun=3
		//               testOnBorrow=true
		//               testWhileIdle=true
		//               testOnReturn=true
		//               validationQuery="SELECT 1"
		//            }
		//        }
	}
}
