import { defineStore } from 'pinia';
import { LocalStorage } from 'quasar';

interface RootState {
  token: string;
  role: string;
}

export const useRootStore = defineStore('root', {
  state: (): RootState => ({
    token: '',
    role: '',
  }),
  getters: {
    tokenValue: (state) => {
      return state.token ? state.token : LocalStorage.getItem('token') || '';
    },
    isCoach: (state) => {
      return state.role
        ? state.role === 'COACH'
        : LocalStorage.getItem('role') === 'COACH';
    },
  },
  actions: {
    setToken(value: string) {
      this.token = value;
      LocalStorage.set('token', value);
    },
    setRole(value: string) {
      this.role = value;
      LocalStorage.set('role', value);
    },
  },
});
