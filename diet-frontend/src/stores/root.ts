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
    isStudent: (state) => {
      return state.role ? state.role === 'STUDENT' : true;
    },
    isCoach: (state) => {
      return state.role ? state.role === 'COACH' : false;
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
