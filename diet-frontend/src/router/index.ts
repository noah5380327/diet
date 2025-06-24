import {
  createMemoryHistory,
  createRouter,
  createWebHashHistory,
  createWebHistory,
} from 'vue-router';
import { storeToRefs } from 'pinia';
import { isIgnoreRoute } from './util';
import {
  DEFAULT_WEBSITE_TITLE,
  DEFAULT_WEBSITE_TITLE_SUFFIX,
} from './constant';
import routes from './routes';
import { useRootStore } from 'stores/root';

const createHistory = process.env.SERVER
  ? createMemoryHistory
  : process.env.VUE_ROUTER_MODE === 'history'
  ? createWebHistory
  : createWebHashHistory;

const Router = createRouter({
  scrollBehavior: () => ({ left: 0, top: 0 }),
  routes,

  // Leave this as is and make changes in quasar.conf.js instead!
  // quasar.conf.js -> build -> vueRouterMode
  // quasar.conf.js -> build -> publicPath
  history: createHistory(process.env.VUE_ROUTER_BASE),
});

Router.beforeEach((to, from, next) => {
  const ignoreRoute = isIgnoreRoute(to);
  if (ignoreRoute) {
    next();
    return;
  }

  const store = useRootStore();
  const { tokenValue } = storeToRefs(store);
  if (!tokenValue.value) {
    next({
      replace: true,
      name: 'login',
    });
    return;
  }

  next();
});

Router.afterEach((to) => {
  document.title = `${
    to.meta.title || DEFAULT_WEBSITE_TITLE
  } - ${DEFAULT_WEBSITE_TITLE_SUFFIX}`;
});

export default Router;
