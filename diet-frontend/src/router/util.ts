import { RouteLocationNormalized } from 'vue-router';
import { IGNORE_TOKEN_ROUTES } from './constant';

export const isIgnoreRoute = (route: RouteLocationNormalized): boolean => {
  const ignoreTokenIndex = IGNORE_TOKEN_ROUTES.findIndex((path) =>
    new RegExp(path).test(route.path)
  );
  return ignoreTokenIndex > -1;
};
