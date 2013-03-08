databaseChangeLog {
    changeSet(id: 'hosts-data-1', author: 'karl-johankarlberg') {
        loadData(tableName: 'hosts', file: 'db/data/hosts.csv') {
            column(name: 'id', type: 'NUMERIC')
            column(name: 'name', type: 'STRING')
            column(name: 'url', type: 'STRING')
            column(name: 'context', type: 'STRING')
        }
    }
    changeSet(id: 'bundle-data-1', author: 'karl-johankarlberg') {
        loadData(tableName: 'bundles', file: 'db/data/bundles.csv') {
            column(name: 'id', type: 'NUMERIC')
            column(name: 'release_name', type: 'STRING')
            column(name: 'who', type: 'STRING')
            column(name: 'when', type: 'DATE')
        }
    }
    changeSet(id: 'bundle-content-1', author: 'karl-johankarlberg') {
        loadData(tableName: 'bundle_content', file: 'db/data/bundle-content.csv') {
            column(name: 'id', type: 'NUMERIC')
            column(name: 'release_name', type: 'STRING')
            column(name: 'who', type: 'STRING')
            column(name: 'when', type: 'DATE')
        }
    }
}
