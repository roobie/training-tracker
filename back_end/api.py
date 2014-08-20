import web

urls = (
    '/(.*)', 'Service'
)

app = web.application(urls, globals())

class Service:
    def GET(self, name):
        web.header('Access-Control-Allow-Origin',      '*')
        web.header('Access-Control-Allow-Credentials', 'true')
        return {'message': GET OK!'}

    def POST(self, name):
        web.header('Access-Control-Allow-Origin',      '*')
        web.header('Access-Control-Allow-Credentials', 'true')
        data = web.data()
        return {'message': "POST OK! %s" % data}

if __name__ == "__main__":
    app.run()
