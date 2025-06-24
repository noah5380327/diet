export interface User {
  id: string;
  username: string;
  locked: boolean;
  role: string;
  createdTime: string;
  updatedTime: string;
}

export interface UserLoginPayload {
  username: string;
  password: string;
}

export interface UserLoginResponse {
  object: UserLoginResponseObject;
  token: string;
}

export interface UserLoginResponseObject {
  user: User;
  role: string;
}

export interface UserRegisterPayload {
  username: string;
  password: string;
  isCoach: boolean;
}
