import axios from 'src/utils/axios';
import {
  CoachStudent,
  CoachStudentCreatePayload,
  CoachStudentUpdatePayload,
} from 'src/server/interfaces/coachStudent';

export function getCurrentCoachStudent(): Promise<CoachStudent> {
  return axios({
    url: '/coachStudents/current',
  });
}

export function createCoachStudent(
  payload: CoachStudentCreatePayload
): Promise<void> {
  return axios({
    method: 'post',
    url: '/coachStudents',
    data: payload,
  });
}

export function updateCoachStudentById(
  id: string,
  payload: CoachStudentUpdatePayload
): Promise<void> {
  return axios({
    method: 'put',
    url: `/coachStudents/${id}`,
    data: payload,
  });
}

export function updateCoachStudentStatusById(
  id: string,
  status: string
): Promise<void> {
  return axios({
    method: 'put',
    url: `/coachStudents/${id}/status/${status}`,
  });
}
