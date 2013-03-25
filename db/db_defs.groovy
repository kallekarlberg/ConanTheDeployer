databaseChangeLog {
    changeSet(id: 'hosts-table-def', author: 'karl-johankarlberg') {
        createTable(tableName: 'HOSTS') {
            column(name: 'ID', type: 'BIGINT') {
                constraints(nullable: false, primaryKey: true, primaryKeyName: 'HOST_ID')
            }
            column(name: 'NAME', type: 'VARCHAR(200)')
            column(name: 'URL', type: 'VARCHAR(100)')
            column(name: 'CONTEXT', type: 'VARCHAR(200)')
        }
    }
    changeSet(id: 'bundles-table-def', author: 'karl-johankarlberg') {
        createTable(tableName: 'BUNDLES') {
            column(name: 'ID', type: 'BIGINT') {
                constraints(nullable: false, primaryKey: true, primaryKeyName: 'PK_BUNDLE')
            }
            column(name: 'RELEASE_NAME', type: 'VARCHAR(200)') {
                constraints(nullable: false)
            }
            column(name: 'CREATED_BY', type: 'VARCHAR(200)') {
                constraints(nullable: false)
            }
            column(name: 'CREATED_WHEN', type: 'TIMESTAMP')
            column(name: 'APPROVED', type: 'BOOLEAN')
            column(name: 'APPROVED_BY', type: 'VARCHAR(200)')
            column(name: 'APPROVED_WHEN', type: 'TIMESTAMP')
            column(name: 'DEV_NOTES', type: 'VARCHAR(200)')
            column(name: 'CSO_NOTES', type: 'VARCHAR(200)')
            column(name: 'PRODUCT_NOTES', type: 'VARCHAR(200)')
        }
    }
    changeSet(id: 'bundles-content-table-def', author: 'karl-johankarlberg') {
        createTable(tableName: 'BUNDLE_CONTENT') {
            column(name: 'ID', type: 'BIGINT') {
                constraints(nullable: false, primaryKey: true, primaryKeyName: 'PK_BUNDLE_CONTENT')
            }
            column(name: 'BUNDLE_ID', type: 'BIGINT') {
                constraints(nullable: false)
            }
            column(name: 'DEPLOYED_APP_ID', type: 'BIGINT')
        }
    }
}