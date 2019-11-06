'use strict'

module.exports = {
    devServer: {
        host: "0.0.0.0",
        disableHostCheck: true,
        proxy: { // proxyTable 설정
            '/api': {
                target: 'http://localhost:8080',
                changeOrigin: true
            }
        }
    }
}
