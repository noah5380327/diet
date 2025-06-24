import axios from 'src/utils/axios';
import {
  User,
  UserLoginPayload,
  UserLoginResponse,
  UserRegisterPayload,
} from 'src/server/interfaces/user';

export function userLogin(
  payload: UserLoginPayload
): Promise<UserLoginResponse> {
  return axios({
    method: 'post',
    url: `/users/login?username=${payload.username}&password=${payload.password}`,
  });
}

export function userRegister(payload: UserRegisterPayload): Promise<void> {
  return axios({
    method: 'post',
    url: '/users/register',
    data: payload,
  });
}

export function getCurrentUser(): Promise<User> {
  return axios({
    url: '/users/current',
  });
}

export function getAllCoaches(): Promise<User[]> {
  return axios({
    url: '/users/coaches',
  });
}
