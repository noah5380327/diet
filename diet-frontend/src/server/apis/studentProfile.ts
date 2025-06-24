import axios from 'src/utils/axios';
import {
  StudentProfile,
  StudentProfileCreatePayload,
  StudentProfileUpdatePayload,
} from 'src/server/interfaces/studentProfile';

export function getCurrentStudentProfile(): Promise<StudentProfile> {
  return axios({
    url: '/studentProfiles/current',
  });
}

export function createStudentProfile(
  payload: StudentProfileCreatePayload
): Promise<void> {
  return axios({
    method: 'post',
    url: '/studentProfiles',
    data: payload,
  });
}

export function updateStudentProfileById(
  id: string,
  payload: StudentProfileUpdatePayload
): Promise<void> {
  return axios({
    method: 'put',
    url: `/studentProfiles/${id}`,
    data: payload,
  });
}
