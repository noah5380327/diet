import axios, { InternalAxiosRequestConfig } from 'axios';
import { storeToRefs } from 'pinia';
import { Notify } from 'quasar';
import router from 'src/router';
import { useRootStore } from 'stores/root';

export interface AxiosRequestConfig extends InternalAxiosRequestConfig {
  notDealDefaultError: boolean;
  notDealDefaultMessage: boolean;
}

const SERVER_BASE_API = '/api';
const SERVER_LOGIN_TIME_OUT_ERROR_CODE = 'token_exception';

const store = useRootStore();
const { tokenValue } = storeToRefs(store);
const { setToken } = store;

const instance = axios.create();

instance.interceptors.request.use(
  // eslint-disable-next-line @typescript-eslint/ban-ts-comment
  // @ts-ignore
  (config: AxiosRequestConfig) => {
    const url = config.url || '/';
    config.url = `${SERVER_BASE_API}${url}`;
    const method = config.method || 'get';
    config.method = method;
    config.headers.Authorization = `Bearer ${tokenValue.value}`;
    config.notDealDefaultError = config.notDealDefaultError || false;
    config.notDealDefaultMessage = config.notDealDefaultMessage || false;
    config.responseType = config.responseType || 'json';

    if (method === 'get') {
      config.params = config.data || {};
    } else {
      config.data = config.data || {};
    }

    return config;
  },
  (error) => {
    Notify.create({
      type: 'negative',
      message: 'Request Failed',
    });
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (response) => {
    if (response.config.responseType !== 'json') {
      return response.data;
    }
    return response.data.data;
  },
  (error) => {
    const { response } = error;

    if (!response) {
      Notify.create({
        type: 'negative',
        message: 'Network Connection Unavailable',
      });
      return Promise.reject(error);
    }

    const responseData = response.data;
    const { notDealDefaultError, notDealDefaultMessage } =
      response.config as AxiosRequestConfig;

    if (!notDealDefaultError) {
      if (responseData.code === SERVER_LOGIN_TIME_OUT_ERROR_CODE) {
        setToken('');
        router.replace({
          name: 'login',
        });
        return Promise.reject(error);
      }

      if (!notDealDefaultMessage) {
        const errorMessage = responseData.message;
        Notify.create({
          type: 'negative',
          message: errorMessage,
        });
      }

      return Promise.reject(error);
    }

    return Promise.reject(error);
  }
);

export default instance;
