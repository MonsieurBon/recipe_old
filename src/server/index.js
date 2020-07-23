import hapi from '@hapi/hapi';
import inert from '@hapi/inert';
import path from 'path';
import apiPlugin from './api.plugin';

const PORT = process.env.PORT || 8080;
const HOST = process.env.HOST || 'localhost';

const init = async () => {
  const server = hapi.server({
    port: PORT,
    host: HOST,
  });

  await server.register(inert);

  await server.register(apiPlugin, {
    routes: {
      prefix: '/api',
    },
  });

  server.route({
    method: 'GET',
    path: '/{param*}',
    handler: {
      directory: {
        path: path.resolve(process.cwd(), 'public'),
        index: 'index.html'
      }
    }
  });

  await server.start();
  console.log('Server running on %s', server.info.uri);
};

process.on('unhandledRejection', err => {
  console.error(err);
  process.exit(1);
});

init();
